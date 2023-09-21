package com.spinetracker.spinetracker.domain.member.query.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindMemberDTO {

    @Schema(type = "long", example = "1", description = "사용자 아이디 입니다.")
    private long id;

    @Schema(type = "String", example = "홍길동", description = "사용자 이름 입니다.")
    private String name;

    @Schema(type = "String", example = "https://lh3.googleusercontent.com/a/ACg8ocIfWYZ3JTCNXqBLNLGRWIrSZ84CGP4unNljYau0nXS2=s96-c", description = "사용자 프로필 이미지 입니다.")
    private String profileImage;

    @Schema(type = "String", example = "MEMBER", description = "사용자 역할 입니다.", allowableValues = {"GUEST", "MEMBER", "ADMIN"})
    private String role;

    protected FindMemberDTO() {}

    public FindMemberDTO(long id, String name, String profileImage, String role) {
        this.id = id;
        this.name = name;
        this.profileImage = profileImage;
        this.role = role;
    }
}
