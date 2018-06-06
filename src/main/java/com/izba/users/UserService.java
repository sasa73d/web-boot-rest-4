package com.izba.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public List<User> getAllUsers(Long id, String name) {
		if (id > 0 || !name.isEmpty()) {
			return userRepository.findByIdOrNameLike(id, name);
		} else {
			return (List<User>) userRepository.findAll();
		}
	}
	
	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsernameLike(username);
	}
	
	public User checkUserExist(String username) {
		return userRepository.findByUsernameLike(username);
	}
	
	public User addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public User updateUser(User user) {
		User temp = userRepository.findByUsernameLike(user.getUsername());
		if (!(temp.getPassword()).equals(user.getPassword())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}


}
