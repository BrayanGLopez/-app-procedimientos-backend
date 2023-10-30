package com.procedimientos.jwt;

import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {
	public String getToken(UserDetails user);
	public String getUsernameFromToken(String token);
	public boolean isTokenValid(String token, UserDetails userDetails);
	public <T> T getClaim(String token, Function<Claims, T> claimsResolver);
}
