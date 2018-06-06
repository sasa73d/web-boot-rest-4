package com.izba.comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping("/comments")
	public List<Comment> getAllComments(@RequestParam(value="id", defaultValue = "0", required = false) Long id,
			@RequestParam(value="postId", defaultValue = "0", required = false) Long postId) {
		return commentService.getAllComments(id, postId);
	}
	
	@RequestMapping("/comments/{id}")
	public Comment getComment(@PathVariable Long id) {
		return commentService.getCommentById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/comments")
	public Comment addComment(@RequestBody Comment comment) {
		return commentService.addComment(comment);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/edit-comments/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public Comment updateComment(@PathVariable Long id, @RequestBody Comment comment) {
		return commentService.updateComment(comment);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/edit-comments/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public void deleteComment(@PathVariable Long id) {
		commentService.deleteCommentById(id);
	}


}
