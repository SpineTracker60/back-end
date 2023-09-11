package com.spinetracker.spinetracker.global.security.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.CreateMemberDTO;
import com.spinetracker.spinetracker.domain.member.command.application.dto.UpdateMemberDTO;
import com.spinetracker.spinetracker.domain.member.command.application.service.CreateMemberService;
import com.spinetracker.spinetracker.domain.member.command.application.service.UpdateMemberService;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.PlatformEnum;
import com.spinetracker.spinetracker.domain.member.query.application.dto.FindMemberDTO;
import com.spinetracker.spinetracker.domain.member.query.application.service.FindMemberService;
import com.spinetracker.spinetracker.global.security.command.domain.provider.OAuth2UserInfo;
import com.spinetracker.spinetracker.global.security.command.domain.provider.OAuth2UserInfoFactory;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final FindMemberService findMemberService;
    private final CreateMemberService createMemberService;
    private final UpdateMemberService updateMemberService;
    @Autowired
    public CustomOAuth2UserService(FindMemberService findMemberService, CreateMemberService createMemberService, UpdateMemberService updateMemberService) {
        this.findMemberService = findMemberService;
        this.createMemberService = createMemberService;
        this.updateMemberService = updateMemberService;
    }
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        System.out.println("oAuth2User = " + oAuth2User);
        String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId(); // 소셜 정보 가져옴
        System.out.println("registrationId = " + registrationId);

        String userNameAttributeName = oAuth2UserRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2UserInfo attributes = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, oAuth2User.getAttributes());
        System.out.println("attributes = " + attributes);
        return saveOrUpdate(attributes, registrationId);
    }

    private UserPrincipal saveOrUpdate(OAuth2UserInfo attributes, String provider) {
        FindMemberDTO member = findMemberService.findByUID(attributes.getId());
        UserPrincipal oauthMember;
        if (member == null) {
            CreateMemberDTO createMemberDTO = new CreateMemberDTO(attributes.getEmail(), attributes.getId(), attributes.getImageUrl(), PlatformEnum.valueOf(provider.toUpperCase()), attributes.getName());
            Member newMember = createMemberService.createMember(createMemberDTO);
            oauthMember = UserPrincipal.create(newMember, attributes.getAttributes());
        }
        else {
            UpdateMemberDTO updateMemberDTO = new UpdateMemberDTO(attributes.getImageUrl(), attributes.getName());
            Member updateMemberResult = updateMemberService.updateMember(member.getId(), updateMemberDTO);
            oauthMember = UserPrincipal.create(updateMemberResult, attributes.getAttributes());
        }
        return oauthMember;
    }
}
