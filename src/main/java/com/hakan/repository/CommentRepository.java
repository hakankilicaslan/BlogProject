package com.hakan.repository;

import com.hakan.entity.Comment;
import com.hakan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllCommentByPostId(Long id);
    void deleteByPostId(Long id);
}
