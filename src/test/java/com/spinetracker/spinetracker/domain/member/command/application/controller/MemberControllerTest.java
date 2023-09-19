package com.spinetracker.spinetracker.domain.member.command.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.PlatformEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.RoleEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.repository.MemberRepository;
import com.spinetracker.spinetracker.domain.member.command.domain.service.RequestUnlinkMember;
import com.spinetracker.spinetracker.domain.member.query.application.service.FindMemberService;
import com.spinetracker.spinetracker.global.common.WithMockCustomUser;
import com.spinetracker.spinetracker.global.filter.TokenAuthenticationFilter;
import com.spinetracker.spinetracker.global.security.command.application.service.CustomUserDetailService;
import com.spinetracker.spinetracker.global.security.command.domain.service.CustomTokenService;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private CustomTokenService customTokenService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @MockBean
    private RequestUnlinkMember requestUnlinkMember;

    @BeforeEach
    public void setup() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .addFilter(new TokenAuthenticationFilter(customTokenService, customUserDetailService))
                .build();

    }
    @Test
    @DisplayName("사용자 정보 삭제 테스트")
    @WithMockCustomUser(id = "2",email = "email@test.com", role = "MEMBER")
    void deleteMember() throws Exception {

        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String testToken = customTokenService.createToken(userPrincipal.getId(), userPrincipal.getRole());

        Long memberId = userPrincipal.getId();
        Member mockFindMember = new Member(
                "email@test.com",
                "효정",
                "12423!",
                "profileImage",
                RoleEnum.MEMBER,
                PlatformEnum.KAKAO
        );

        if(requestUnlinkMember.unlinkSocial(mockFindMember)) {
            mockMvc.perform(
                            delete("/member")
                                    .header("Authorization", "Bearer " + testToken)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("utf-8")
                    )
                    .andDo(print())
                    .andExpect(
                            status().isNoContent()
                    );
        }
    }
}