package com.spinetracker.spinetracker.global.security.command.application.service;

import com.spinetracker.spinetracker.domain.member.query.application.dto.FindMemberDTO;
import com.spinetracker.spinetracker.global.security.command.domain.aggregate.entity.Token;
import com.spinetracker.spinetracker.global.security.command.domain.aggregate.vo.MemberVO;
import com.spinetracker.spinetracker.global.security.command.domain.exception.TokenNotFoundException;
import com.spinetracker.spinetracker.global.security.command.domain.repository.TokenRepository;
import com.spinetracker.spinetracker.global.security.command.domain.service.CustomTokenService;
import com.spinetracker.spinetracker.global.security.command.domain.service.RequestMember;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class IssueTokenService {

    private final CustomTokenService customTokenService;
    private final TokenRepository tokenRepository;
    private final RequestMember requestMember;

    @Autowired
    public IssueTokenService(CustomTokenService customTokenService, TokenRepository tokenRepository, RequestMember requestMember) {
        this.customTokenService = customTokenService;
        this.tokenRepository = tokenRepository;
        this.requestMember = requestMember;
    }

    @Transactional
    public String issueTokenByUserPrincipal(UserPrincipal userPrincipal) {
        long memberId = userPrincipal.getId();
        String memberRole = userPrincipal.getRole();
        Optional<Token> findToken = tokenRepository.findTokenByMember_Id(memberId);
        String issuedToken = customTokenService.createToken(memberId, memberRole);
        if (findToken.isPresent()) {
            Token updateToken = findToken.get();
            updateToken.setAccessToken(issuedToken);
            tokenRepository.save(updateToken);
        } else {
            Token createdToken = new Token(new MemberVO(memberId), issuedToken);
            tokenRepository.save(createdToken);
        }

        return issuedToken;
    }

    public String issueTokenByAccessToken(String accessToken) {
        Token findToken = tokenRepository.findTokenByAccessToken(accessToken).orElseThrow(
                () -> new TokenNotFoundException("해당 Access Token은 폐기된 토큰입니다.")
        );
        long memberId = findToken.getMember().getId();

        FindMemberDTO findMember = requestMember.getMemberById(memberId);

        String issuedToken = customTokenService.createToken(findToken.getId(), findMember.getRole());
        findToken.setAccessToken(issuedToken);
        tokenRepository.save(findToken);

        return issuedToken;
    }
}

