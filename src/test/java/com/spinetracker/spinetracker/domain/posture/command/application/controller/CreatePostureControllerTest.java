package com.spinetracker.spinetracker.domain.posture.command.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.AgeRangeEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.GenderEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.PlatformEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.RoleEnum;
import com.spinetracker.spinetracker.domain.member.query.application.dto.FindMemberDTO;
import com.spinetracker.spinetracker.domain.member.query.application.service.FindMemberService;
import com.spinetracker.spinetracker.domain.posture.command.application.dto.CreatePostureLogDTO;
import com.spinetracker.spinetracker.global.common.WithMockCustomUser;
import com.spinetracker.spinetracker.global.filter.TokenAuthenticationFilter;
import com.spinetracker.spinetracker.global.security.command.application.service.CustomUserDetailService;
import com.spinetracker.spinetracker.global.security.command.domain.service.CustomTokenService;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class CreatePostureControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

    private static Stream<Arguments> getCreatePostureLogDTO() {
        List<CreatePostureLogDTO> createPostureLogDTOList = new ArrayList<>();
        createPostureLogDTOList.add(new CreatePostureLogDTO(
                        "START",
                        LocalDate.now(),
                        LocalTime.now(),
                        LocalTime.now().plusMinutes(10)
                ));
        createPostureLogDTOList.add(
                new CreatePostureLogDTO(
                        "TEXTNECK",
                        LocalDate.now(),
                        LocalTime.now(),
                        LocalTime.now().plusMinutes(10)
                ));
        createPostureLogDTOList.add(new CreatePostureLogDTO(
                "END",
                LocalDate.now(),
                LocalTime.now(),
                LocalTime.now().plusMinutes(10)
        ));

        return Stream.of(
                Arguments.of(
                        createPostureLogDTOList
                )
        );
    }

    @WithMockCustomUser(email = "email@test.com", role = "MEMBER")
    @ParameterizedTest(name = "자세 로그 저장 API 테스트")
    @MethodSource("getCreatePostureLogDTO")
    void testCreatePostureLog(List<CreatePostureLogDTO> createPostureLogDTO) throws Exception {

        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String testToken = customTokenService.createToken(userPrincipal.getId(), userPrincipal.getRole());

        FindMemberDTO mockFindMemberDTO = new FindMemberDTO(
                userPrincipal.getId(),
                "mockName",
                "profileImage",
                RoleEnum.valueOf(userPrincipal.getRole().substring(5)).name()
        );
        when(findMemberService.findById(userPrincipal.getId())).thenReturn(mockFindMemberDTO);

        String content = objectMapper.writeValueAsString(createPostureLogDTO);

        mockMvc.perform(
                        post("/posture/")
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
}