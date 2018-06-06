package com.izba.posts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;
	
	public List<Post> getAllPosts(Long id, Long userId) {
		if (id > 0 || userId > 0) {
			return (List<Post>) postRepository.findByIdOrUserId(id, userId);
		} else {
			return (List<Post>) postRepository.findAll();
		}
	}
	
	public Post getPostById(Long id) {
		return postRepository.findById(id).get();
	}
	
	public Post addPost(Post post) {
		return postRepository.save(post);
	}
	
	public Post updatePost(Post post) {
		return postRepository.save(post);
	}
	
	public void deletePostById(Long id) {
		postRepository.deleteById(id);
	}

}
