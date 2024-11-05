package com.codeWithProject.hotelServer.controller.comment;


import com.codeWithProject.hotelServer.dto.CommentDto;
import com.codeWithProject.hotelServer.entity.Comment;
import com.codeWithProject.hotelServer.services.comment.CommentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Lấy tất cả bình luận của một phòng theo roomId
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Comment>> getCommentsByRoomId(@PathVariable Long roomId) {
        List<Comment> comments = commentService.getCommentsByRoomId(roomId);
        return ResponseEntity.ok(comments);
    }

    // Thêm bình luận mới vào một phòng
    @PostMapping("/room/{roomId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long roomId, @RequestBody CommentDto commentDto) {
        Comment newComment = commentService.addComment(roomId, commentDto.getContent());
        return ResponseEntity.ok(newComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        try {
            commentService.deleteComment(commentId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
