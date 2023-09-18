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

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Embedded
    private WriterVO writer;

    @Embedded
    private ProductVO product;

    @Column(name = "board_is_deleted")
    private boolean boardIsDeleted;

    @CreatedDate
    @Column(nullable = false, name="created_date")
    private LocalDateTime createdDate;

    protected Board() {}

    public Board(Long id, String title, String content, WriterVO writer, ProductVO product) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.product = product;
        this.boardIsDeleted = false;        // 게시물 생성 시 default 가 false
        this.createdDate = LocalDateTime.now();
    }

    public Board setTitle(String title) {
        this.title = title;
        return this;
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
