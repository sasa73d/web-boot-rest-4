package com.izba.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	private final CustomUserDetailsService customUserDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
			CustomUserDetailsService customUserDetailsService) {
		super(authenticationManager);
		this.customUserDetailsService = customUserDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(SecurityConstants.HEADER_STRING);
		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken usernamePasswordAuth = getAuthenticationTken(request);
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationTken(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		if (token == null) return null;
		String username = Jwts.parser().setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(token.replaceAll(SecurityConstants.TOKEN_PREFIX, ""))
				.getBody()
				.getSubject();
		
		UserDetails userDetail = customUserDetailsService.loadUserByUsername(username);
		AplicationUser aplicationUser = customUserDetailsService.loadAplicationUserByUsername(username);
		return username != null ? new UsernamePasswordAuthenticationToken(aplicationUser, null, userDetail.getAuthorities()) : null ;
	}
	
}
