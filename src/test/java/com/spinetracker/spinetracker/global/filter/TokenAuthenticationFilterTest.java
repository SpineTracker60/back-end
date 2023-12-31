package com.spinetracker.spinetracker.global.filter;

import com.spinetracker.spinetracker.domain.member.command.application.dto.CreateMemberDTO;
import com.spinetracker.spinetracker.domain.member.command.application.service.CreateMemberService;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.PlatformEnum;
import com.spinetracker.spinetracker.global.security.command.application.service.CustomUserDetailService;
import com.spinetracker.spinetracker.global.security.command.domain.service.CustomTokenService;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import java.io.IOException;

@SpringBootTest
@Transactional
class TokenAuthenticationFilterTest {

    @Autowired
    private CreateMemberService createMemberService;

    @Autowired
    private CustomTokenService customTokenService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    TokenAuthenticationFilter tokenAuthenticationFilter(CustomTokenService customTokenService,
                                                        CustomUserDetailService customUserDetailService) {
        return new TokenAuthenticationFilter(customTokenService, customUserDetailService);
    }

    private TokenAuthenticationFilter tokenAuthenticationFilter;
    private MockHttpServletRequest mockRequest;
    private MockHttpServletResponse mockResponse;
    private MockFilterChain mockFilterChain;

    private TokenAuthenticationFilter mockTokenAuthenticationFilter;

    private UserPrincipal userPrincipal;
    private CustomUserDetailService mockUserDetailsService;
    private Member member;

    @BeforeEach
    void setUp() {
        tokenAuthenticationFilter = tokenAuthenticationFilter(customTokenService, customUserDetailService);

        mockRequest = new MockHttpServletRequest();
        mockResponse = new MockHttpServletResponse();
        mockFilterChain = new MockFilterChain();
        mockUserDetailsService = mock(CustomUserDetailService.class);
        CustomTokenService mockCustomTokenService = mock(CustomTokenService.class);

        member = createMemberService.createMember(new CreateMemberDTO(
                "test@email.com",
                "UIDTEST",
                "profileimage",
                PlatformEnum.GOOGLE,
                "남효정"
        )


        );

        userPrincipal = UserPrincipal.create(member, new HashMap<>());

        Authentication auth = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());

        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();

        String tokenValue = customTokenService.createToken(userPrincipal.getId(), userPrincipal.getRole());

        mockRequest.addHeader("Authorization", "Bearer " + tokenValue);

        mockTokenAuthenticationFilter = new TokenAuthenticationFilter(mockCustomTokenService, mockUserDetailsService);

        SecurityContextHolder.clearContext();
    }

    @DisplayName("tokenAuthenticationFilter 에서 다음 필터로 넘어가는지 테스트")
    @Test
    void testFilterContinuesToNextFilter() throws ServletException, IOException {
        when(mockUserDetailsService.loadUserByUsername(anyString()))
                .thenReturn(userPrincipal);
        MockFilterChain mockFilterChainSpy = spy(mockFilterChain);

        mockTokenAuthenticationFilter.doFilter(mockRequest, mockResponse, mockFilterChainSpy);

        verify(mockFilterChainSpy, times(1)).doFilter(mockRequest, mockResponse);
    }

    @DisplayName("tokenAuthenticationFilter를 통해 JWT 토큰에서 복호화한 사용자 정보를 SecurityContextHolder에 정상적으로 저장되는지 확인")
    @Test
    void testFilterSetsAuthenticationInSecurityContext() throws ServletException, IOException {

        tokenAuthenticationFilter.doFilter(mockRequest, mockResponse, mockFilterChain);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        Assertions.assertEquals(principal.getId(), member.getId());
        Assertions.assertEquals(principal.getName(), member.getName());
        Assertions.assertEquals(principal.getRole(), member.getRole().getKey());

    }
}