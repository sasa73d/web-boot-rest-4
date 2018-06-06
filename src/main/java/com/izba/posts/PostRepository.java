package com.izba.posts;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> { 

	List<Post> findByIdOrUserId(Long id, Long userId);

}
