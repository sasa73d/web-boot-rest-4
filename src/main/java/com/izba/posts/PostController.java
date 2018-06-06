package com.izba.posts;

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
public class PostController {
	
	@Autowired
	PostService postService;
	
	@RequestMapping("/posts")
	public List<Post> getAllPosts(@RequestParam(value="id", defaultValue = "0", required = false) Long id,
			@RequestParam(value="userId", defaultValue = "0", required = false) Long userId) {
		return postService.getAllPosts(id, userId);
	}
	
	@RequestMapping("/posts/{id}")
	public Post getPost(@PathVariable Long id) {
		return postService.getPostById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/edit-posts")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public Post addPost(@RequestBody Post post) {
		return postService.addPost(post);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/edit-posts/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
		return postService.updatePost(post);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/edit-posts/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public void deletePost(@PathVariable Long id) {
		postService.deletePostById(id);
	}


}
