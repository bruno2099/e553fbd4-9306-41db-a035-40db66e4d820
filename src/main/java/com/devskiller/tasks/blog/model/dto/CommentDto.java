package com.devskiller.tasks.blog.model.dto;

import java.time.LocalDateTime;

public class CommentDto {

	private String comment;

	private String author;

	private LocalDateTime creationDate;

	public CommentDto(String comment, String author, LocalDateTime creationDate) {
		this.comment = comment;
		this.author = author;
		this.creationDate = creationDate;
	}

	public String getComment() {
		return comment;
	}

	public String getAuthor() {
		return author;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}
}
