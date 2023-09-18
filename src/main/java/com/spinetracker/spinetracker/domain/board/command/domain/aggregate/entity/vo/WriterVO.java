package com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class WriterVO {

    @Column(nullable = false, name = "writer")
    private Long id;

    protected WriterVO() {}

    public WriterVO(Long id) {
        this.id = id;
    }
}
