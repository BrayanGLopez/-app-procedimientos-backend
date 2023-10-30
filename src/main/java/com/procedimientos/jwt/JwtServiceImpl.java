package com.procedimientos.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtServiceImpl implements JwtService{
	
	private static final String SECRET_KEY = "2IUhBjNffi411N9fibxxuouv33N1awWtrxy2TQHZhTwyNZbFyw";

	@Override
	public String getToken(UserDetails user) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("Rol", user.getAuthorities());
		return getToken(claims, user);
	}
	
	private String getToken(Map<String, Object> extraClaims, UserDetails user) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	private Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		
	}
	
	private Claims getAllClaims(String token){
		return Jwts
				.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	@Override
	public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
		
		final Claims claims = getAllClaims(token);
		
		return claimsResolver.apply(claims);
	}
	
	private Date getExpiration(String token){
		return getClaim(token, Claims::getExpiration);
	}
	
	private boolean isTokenExpired(String token){
		return getExpiration(token).before(new Date());
	}

}
