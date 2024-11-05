package com.codeWithProject.hotelServer.services.comment;

import com.codeWithProject.hotelServer.entity.Comment;
import com.codeWithProject.hotelServer.entity.User;
import com.codeWithProject.hotelServer.repository.CommentRepository;

import com.codeWithProject.hotelServer.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public List<Comment> getCommentsByRoomId(Long roomId) {
        return commentRepository.findByRoomId(roomId);
    }

    public Comment addComment(Long roomId, String content) {
        Comment comment = new Comment();
        comment.setRoomId(roomId);
        comment.setContent(content);
        return commentRepository.save(comment);
    }

    public void  deleteComment(Long roomId) {
        Optional<Comment> optionalComment = commentRepository.findById(roomId);
        if (optionalComment.isPresent()) {
            commentRepository.deleteById(roomId);
        } else {
            throw new EntityNotFoundException("error");
        }
    }
}
