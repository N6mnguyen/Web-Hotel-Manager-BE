package com.codeWithProject.hotelServer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roomId; // ID của phòng mà bình luận thuộc về

    private Long userId;

    private String content;

    private LocalDate createdAt;

    public Comment() {
        this.createdAt = LocalDate.now();
    }
}
