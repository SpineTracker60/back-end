package com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity;

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

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = true, name = "book_mark")
    private boolean bookMark;

    @Column(name = "board_is_deleted")
    private boolean boardIsDeleted;

    @CreatedDate
    @Column(nullable = false, name="created_date")
    private LocalDateTime createdDate;

    protected Board() {}

    public Board(Long id, String title, String content, String writer, boolean bookMark, boolean boardIsDeleted, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.bookMark = bookMark;
        this.boardIsDeleted = boardIsDeleted;
        this.createdDate = createdDate;
    }

    public Board setTitle(String title) {
        this.title = title;
        return this;
    }

    public Board setContent(String content) {
        this.content = content;
        return this;
    }

    public Board setBookMark(boolean bookMark) {
        this.bookMark = bookMark;
        return this;
    }
}
