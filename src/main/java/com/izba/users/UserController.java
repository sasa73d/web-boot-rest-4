package com.izba.users;

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
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/users")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public List<User> getAllUsers(@RequestParam(value="id", defaultValue = "0", required = false) Long id,
			@RequestParam(value="name", defaultValue = "", required = false) String name) {
		return userService.getAllUsers(id, name);
	}
	
	@RequestMapping("/user")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public User getUserByUsername(@RequestParam(value="username", defaultValue = "", required = false) String username) {
		return userService.getUserByUsername(username);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping("/users/{id}")
	public User getUser(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/sign_up")
	public User addUser(@RequestBody User user) {
		if (userService.checkUserExist(user.getUsername()) != null) {
			throw new RuntimeException("Username exist!");
		}
		return userService.addUser(user);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@RequestMapping(method=RequestMethod.PUT, value="/users/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(method=RequestMethod.DELETE, value="/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUserById(id);
	}

	
}
