package com.codeWithProject.hotelServer.services.comment;

import com.codeWithProject.hotelServer.dto.CommentDto;
import com.codeWithProject.hotelServer.entity.Comment;
import java.util.List;

public interface CommentService {

    public List<Comment> getCommentsByRoomId(Long roomId);

    public Comment addComment(Long roomId, String content);

    public void  deleteComment(Long roomId);
}
