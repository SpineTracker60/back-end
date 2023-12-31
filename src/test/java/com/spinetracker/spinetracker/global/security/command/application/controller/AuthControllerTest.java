package com.spinetracker.spinetracker.global.security.command.application.controller;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.AgeRangeEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.GenderEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.PlatformEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.RoleEnum;
import com.spinetracker.spinetracker.domain.member.query.application.dto.FindMemberDTO;
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

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private FindMemberService findMemberService;

    @Autowired
    private CustomTokenService customTokenService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @BeforeEach
    public void setup() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .addFilter(new TokenAuthenticationFilter(customTokenService, customUserDetailService))
                .build();

    }

    @DisplayName("임시 정보를 통해 JWT 토큰 검증 테스트")
    @WithMockCustomUser(email = "email@test.com", role = "MEMBER")
    @Test
    void testGetMethodWithJwt() throws Exception {

        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String testToken = customTokenService.createToken(userPrincipal.getId(), userPrincipal.getRole());

        FindMemberDTO mockFindMemberDTO = new FindMemberDTO(
                userPrincipal.getId(),
                "mockName",
                "profileImage",
                RoleEnum.valueOf(userPrincipal.getRole().substring(5)).name()
        );
        when(findMemberService.findById(userPrincipal.getId())).thenReturn(mockFindMemberDTO);
        System.out.println("testToken = " + testToken);

        mockMvc.perform(
                get("/auth/name")
                        .header("Authorization", "Bearer " + testToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpect(
                        status().isOk()
                );
    }

}