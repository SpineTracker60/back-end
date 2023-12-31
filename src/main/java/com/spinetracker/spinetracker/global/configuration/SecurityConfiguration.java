package com.spinetracker.spinetracker.global.configuration;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.RoleEnum;
import com.spinetracker.spinetracker.global.filter.AuthenticationExceptionFilter;
import com.spinetracker.spinetracker.global.filter.NullPointExceptionFilter;
import com.spinetracker.spinetracker.global.filter.TokenAuthenticationFilter;
import com.spinetracker.spinetracker.global.handler.CustomAccessDeniedHandler;
import com.spinetracker.spinetracker.global.handler.CustomOAuth2FailHandler;
import com.spinetracker.spinetracker.global.handler.CustomOAuth2SuccessHandler;
import com.spinetracker.spinetracker.global.security.command.application.service.CustomOAuth2UserService;
import com.spinetracker.spinetracker.global.security.command.application.service.CustomUserDetailService;
import com.spinetracker.spinetracker.global.security.command.domain.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import com.spinetracker.spinetracker.global.security.command.domain.service.CustomTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2SuccessHandler oAuth2AuthenticationSuccessHandler;
    private final CustomOAuth2FailHandler oAuth2AuthenticationFailureHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final NullPointExceptionFilter nullPointExceptionFilter;

    @Autowired
    @Qualifier("RestAuthenticationEntryPoint")
    private AuthenticationEntryPoint authEntryPoint;
    @Autowired
    private CustomTokenService customTokenService;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    AuthenticationExceptionFilter authenticationExceptionFilter(HandlerExceptionResolver resolver) {
        return new AuthenticationExceptionFilter(resolver);
    }

//    NullPointExceptionFilter nullPointExceptionFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
//        return new NullPointExceptionFilter(resolver);
//    }

    TokenAuthenticationFilter tokenAuthenticationFilter(CustomTokenService customTokenService,
                                                        CustomUserDetailService customUserDetailService) {
        return new TokenAuthenticationFilter(customTokenService, customUserDetailService);
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    @Order(0)
    public SecurityFilterChain exceptionSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .requestCache().disable()
                .securityContext().disable()
                .sessionManagement().disable()
                .requestMatchers((matchers) ->
                        matchers
                                .antMatchers(
                                        "/", "/error","/favicon.ico", "/**/*.png",
                                        "/**/*.gif", "/**/*.svg", "/**/*.jpg",
                                        "/**/*.html", "/**/*.css", "/**/*.js"
                                )
                                .antMatchers(
                                        "/api-docs",
                                        "/v2/api-docs",  "/v3/api-docs","/configuration/ui",
                                        "/swagger-resources/**", "/configuration/security",
                                        "/swagger-ui.html", "/webjars/**","/swagger/**",
                                        "/swagger-ui/**", "/swagger","/webjars/**"
                                )
                                .antMatchers(
                                        "/login/**", "/posture/ratio", "/member/info/query"
                                )
                )
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());

        return http.build();
    }
    @Bean
    @Order(1)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()

                .authorizeRequests()
                    .antMatchers("/oauth2/**", "/auth/**")
                        .hasRole(RoleEnum.MEMBER.name())
                .antMatchers("/blog/**", "/member/**", "/board/**", "/posture/**", "/fcm/**", "/product/**")
                    .permitAll()
                .antMatchers("/admin/**")
                    .hasRole(RoleEnum.ADMIN.name())
                .and()
                .oauth2Login()
                    .authorizationEndpoint()
                        .baseUri("/oauth2/authorize")
                .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                .and()
                .redirectionEndpoint()
                    .baseUri("/oauth2/callback/*")
                    .and()
                .userInfoEndpoint()
                    .userService(customOAuth2UserService)
                    .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
               .failureHandler(oAuth2AuthenticationFailureHandler);

        http
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler);

        http
                .addFilterBefore(tokenAuthenticationFilter(customTokenService, customUserDetailService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authenticationExceptionFilter(resolver), TokenAuthenticationFilter.class)
                .addFilterBefore(nullPointExceptionFilter, AuthenticationExceptionFilter.class);

        return http.build();
    }
}
