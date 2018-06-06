package com.izba.comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	public List<Comment> getAllComments(Long id, Long postId) {
		if (id > 0 || postId > 0) {
			return (List<Comment>) commentRepository.findByIdOrPostId(id, postId);
		} else {
			return (List<Comment>) commentRepository.findAll();
		}
	}
	
	public Comment getCommentById(Long id) {
		return commentRepository.findById(id).get();
	}
	
	public Comment addComment(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public Comment updateComment(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public void deleteCommentById(Long id) {
		commentRepository.deleteById(id);
	}

}
