package com.devskiller.tasks.blog.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.Post;
import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.repository.CommentRepository;
import com.devskiller.tasks.blog.repository.PostRepository;

@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;

	public CommentService(
			CommentRepository commentRepository,
			PostRepository postRepository) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}
	
	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 */
	public List<CommentDto> getCommentsForPost(Long postId) {
		List<Comment> comments =  commentRepository.findCommentsByPostId(postId);
		comments.sort(Comparator.comparing(Comment::getCreationDate).reversed());
		List<CommentDto> result = new ArrayList<>();		
		
		comments.forEach(comment -> {
			result.add(new CommentDto(comment.getContent(), comment.getAuthor(), comment.getCreationDate()));
		});
		
		return result;
	}

	/**
	 * Creates a new comment
	 *
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if there is no blog post for passed newCommentDto.postId
	 */
	public Long addComment(NewCommentDto newCommentDto) throws IllegalArgumentException{
		
		try {
			Post post = postRepository.findById(newCommentDto.getPostId()).get();
			
			Comment comment = new Comment();
			comment.setAuthor(newCommentDto.getAuthor());
			comment.setContent(newCommentDto.getContent());
			comment.setCreationDate(LocalDateTime.now());
			comment.setPost(post);
			
			commentRepository.save(comment);
			
			return comment.getId();
		} catch (Exception e) {
			throw new IllegalArgumentException("There's no posts for given ID.");
		}
	}
}
