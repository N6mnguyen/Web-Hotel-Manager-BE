package com.codeWithProject.hotelServer.repository;

import com.codeWithProject.hotelServer.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByRoomId(Long roomId); // Tìm các bình luận theo roomId

}
