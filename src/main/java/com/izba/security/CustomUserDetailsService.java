package com.izba.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.izba.users.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AplicationUser aplicationUser = loadAplicationUserByUsername(username);
		return new User(aplicationUser.getUsername(), aplicationUser.getPassword(), AuthorityUtils.createAuthorityList(aplicationUser.getRoles()));
	}
	
	public AplicationUser loadAplicationUserByUsername(String username) {
		com.izba.users.User user = userRepository.findByUsernameLike(username);
		return new AplicationUser(user.getUsername(), user.getPassword(), user.getAuthrities());
	}
	
}
