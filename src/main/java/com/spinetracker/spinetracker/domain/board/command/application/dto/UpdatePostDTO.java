package com.spinetracker.spinetracker.domain.board.command.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Setter
public class UpdatePostDTO {

    @Schema(type = "String", example = "게시글 내용", description = "게시글 본문 내용 입니다.")
    private String content;

    @Schema(type = "LocalDateTime", example = "2023-09-24 15:33:13", description = "게시판 수정 날짜 및 시간 입니다.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    @JsonProperty("created_date")
    private LocalDateTime createdDate;

    public UpdatePostDTO() {}

    public UpdatePostDTO(String content, LocalDateTime createdDate) {
        this.content = content;
        this.createdDate = createdDate;
    }
}
