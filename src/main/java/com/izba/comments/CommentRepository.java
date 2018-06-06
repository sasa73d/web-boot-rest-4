package com.izba.comments;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	public List<Comment> findByIdOrPostId(Long id, Long postId);
}
