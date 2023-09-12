package com.spinetracker.spinetracker.global.security.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.RoleEnum;
import com.spinetracker.spinetracker.global.security.command.domain.service.CustomTokenService;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import org.apache.catalina.User;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class IssueTokenServiceTest {

    static CustomTokenService customTokenService;
    static IssueTokenService issueTokenService;
    static UserPrincipal userPrincipal;
    static String defaultAccessToken;
    @BeforeAll
    static void beforeAll() {

        customTokenService = Mockito.mock(CustomTokenService.class);
        issueTokenService = Mockito.mock(IssueTokenService.class);

        userPrincipal = UserPrincipal.builder(1L, RoleEnum.MEMBER.name(), "효정").build();
        defaultAccessToken = customTokenService.createToken(userPrincipal.getId(), userPrincipal.getRole());
    }

    @DisplayName("UserPrincipal을 통해 정상적으로 토큰 발행이 되는지 테스트")
    @Test
    @WithAnonymousUser
    void testIssueTokenByUserPrincipal() {

        when(issueTokenService.issueTokenByUserPrincipal(userPrincipal)).thenReturn(anyString());

        Assertions.assertNotNull(issueTokenService.issueTokenByUserPrincipal(userPrincipal));
    }

}