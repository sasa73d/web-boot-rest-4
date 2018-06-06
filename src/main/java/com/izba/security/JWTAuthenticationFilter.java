package com.izba.security;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.izba.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		AplicationUser aplicationUser;
		try {
			aplicationUser = new ObjectMapper().readValue(request.getInputStream(), AplicationUser.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(aplicationUser.getUsername(), aplicationUser.getPassword()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		ZonedDateTime expirationTimeUTC = ZonedDateTime.now(ZoneOffset.UTC).plus(SecurityConstants.EXPIRATION_TIME, ChronoUnit.MILLIS);
		
		 Collection<GrantedAuthority> roles = ((User)authResult.getPrincipal()).getAuthorities();
        ArrayList<GrantedAuthority> authsList = new ArrayList<>(roles.size());
        for (Object role : roles) {
            authsList.add(new SimpleGrantedAuthority(role.toString()));
        }
		Claims claims = Jwts.claims()
							.setSubject(((User)authResult.getPrincipal()).getUsername())
							.setExpiration(Date.from(expirationTimeUTC.toInstant()));
		claims.put("scopes", authsList);
		
		String token = Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
				.compact();
		
		response.getWriter().write(token);
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
	}
	
	
}
