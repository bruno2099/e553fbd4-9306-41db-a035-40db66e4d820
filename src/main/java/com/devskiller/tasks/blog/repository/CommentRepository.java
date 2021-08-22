package com.devskiller.tasks.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devskiller.tasks.blog.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findCommentsByPostId(Long postId);
}
