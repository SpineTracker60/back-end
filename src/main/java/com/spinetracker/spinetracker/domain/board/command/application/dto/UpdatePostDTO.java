package com.spinetracker.spinetracker.domain.board.command.application.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdatePostDTO {
    private final String content;

    public UpdatePostDTO(String content) {
        this.content = content;
    }
}
