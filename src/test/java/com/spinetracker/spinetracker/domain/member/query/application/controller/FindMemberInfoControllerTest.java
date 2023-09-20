package com.spinetracker.spinetracker.domain.member.query.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class FindMemberInfoControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

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
    @Test
    @DisplayName("회원가입 시 추가 정보 입력 여부 확인 테스트")
    @WithMockCustomUser(email = "email@test.com", role = "MEMBER")
    void addMemberInfo() throws Exception {

        // 사용자 정보 토큰 생성
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String testToken = customTokenService.createToken(userPrincipal.getId(), userPrincipal.getRole());

        Long memberId = userPrincipal.getId();

        when(findMemberService.isAddedInformation(memberId)).thenReturn(true);
        mockMvc.perform(
                        get("/find/member")
                                .header("Authorization", "Bearer " + testToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpect(
                        status().isOk()
                );
    }
}