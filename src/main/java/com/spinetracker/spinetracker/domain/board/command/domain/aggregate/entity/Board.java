package com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity;

import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.vo.ProductVO;
import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.vo.WriterVO;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOARD_TB")
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Embedded
    private WriterVO writer;

    @Embedded
    private ProductVO product;

    @Column(name = "board_is_deleted", nullable = false)
    private boolean boardIsDeleted;

    @CreatedDate
    @Column(nullable = false, name="created_date")
    private LocalDateTime createdDate;

    protected Board() {}

    public Board(String content, WriterVO writer, ProductVO product) {
        this.content = content;
        this.writer = writer;
        this.product = product;
        this.boardIsDeleted = false;        // 게시물 생성 시 default 가 false
        this.createdDate = LocalDateTime.now();
    }

    public Board setContent(String content) {
        this.content = content;
        return this;
    }

    public Board setBoardIsDeleted(boolean boardIsDeleted) {
        this.boardIsDeleted = boardIsDeleted;
        return this;
    }
}
