package com.izba.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final CustomUserDetailsService customUserdetailsService;
	
	@Autowired
	public SecurityConfig(CustomUserDetailsService customUserdetailsService) {
		this.customUserdetailsService = customUserdetailsService;
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/sign_up").permitAll()
			.antMatchers("/users/**").hasAnyRole("ADMIN", "USER")
			.antMatchers("/user").hasRole("USER")
			.antMatchers("/edit-posts/**").hasRole("USER")
			.antMatchers("/edit-comments/**").hasRole("USER")
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserdetailsService));

	}

}
