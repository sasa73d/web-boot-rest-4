package com.izba.users;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByIdOrNameLike(Long id, String name);
	
	User findByUsernameLike(String username);
	

}
