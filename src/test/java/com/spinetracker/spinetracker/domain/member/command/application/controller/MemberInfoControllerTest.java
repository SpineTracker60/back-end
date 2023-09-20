package com.spinetracker.spinetracker.domain.member.command.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spinetracker.spinetracker.domain.member.command.application.dto.MemberInfoDTO;
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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
import java.time.LocalDate;
import java.util.stream.Stream;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberInfoControllerTest {


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

    private static Stream<Arguments> saveMember() {
        return Stream.of(
                Arguments.of(
                        new MemberInfoDTO(
                                1L,
                        "FEMALE",
                        LocalDate.parse("1995-06-04"),
                        "학생"

                        )));
    }
    @BeforeEach
    public void setup() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .addFilter(new TokenAuthenticationFilter(customTokenService, customUserDetailService))
                .build();

    }
    @ParameterizedTest(name="사용자 정보 추가 테스트")
    @WithMockCustomUser(email = "email@test.com", role = "MEMBER")
    @MethodSource("saveMember")
    void addMemberInfo(MemberInfoDTO memberInfoDTO) throws Exception {

        // 사용자 정보 토큰 생성
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String testToken = customTokenService.createToken(userPrincipal.getId(), userPrincipal.getRole());

        Long memberId = userPrincipal.getId();

        FindMemberDTO mockFindMemberDTO = new FindMemberDTO(
                0L,
                "mockName",
                GenderEnum.FEMALE.name(),
                AgeRangeEnum.FIFTY.name(),
                "학생",
                "profileImage",
                PlatformEnum.KAKAO.name(),
                RoleEnum.MEMBER.name(),
                "email@test.com"
        );
        when(findMemberService.findById(memberId)).thenReturn(mockFindMemberDTO);

        String content = objectMapper.writeValueAsString(memberInfoDTO);
        mockMvc.perform(
                        post("/member/info")
                                .header("Authorization", "Bearer " + testToken)
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpect(
                        status().isCreated()
                );
    }

    @ParameterizedTest(name="사용자 정보 수정 테스트")
    @WithMockCustomUser(email = "email@test.com", role = "MEMBER")
    @MethodSource("saveMember")
    void updateMemberInfo(MemberInfoDTO memberInfoDTO) throws Exception {

        // 사용자 정보 토큰 생성
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String testToken = customTokenService.createToken(userPrincipal.getId(), userPrincipal.getRole());

        Long memberId = userPrincipal.getId();

        FindMemberDTO mockFindMemberDTO = new FindMemberDTO(
                0L,
                "mockName",
                GenderEnum.FEMALE.name(),
                AgeRangeEnum.FIFTY.name(),
                "학생",
                "profileImage",
                PlatformEnum.KAKAO.name(),
                RoleEnum.MEMBER.name(),
                "email@test.com"
        );
        when(findMemberService.findById(memberId)).thenReturn(mockFindMemberDTO);

        String content = objectMapper.writeValueAsString(memberInfoDTO);
        mockMvc.perform(
                        put("/member/info")
                                .header("Authorization", "Bearer " + testToken)
                                .content(content)
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