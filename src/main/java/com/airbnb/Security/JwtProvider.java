package com.airbnb.Security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
    private static final String SECRET_KEY = "hjkhjjhjkjjlknbjhjnkhkjjhhjkhbkbjhvbjbhvjhjkhjjhjkjjlknbjhjnkhkjjhhjkhbkbjhvbjbhvj"; // Ensure the key length is sufficient for HMAC-SHA algorithms

    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

	
public static String generateToken(Authentication auth) {
	String jwt=Jwts.builder()
			.setIssuer("Ishor")
			.setIssuedAt(new Date())
			.setExpiration(new Date(new Date().getTime()+86400000))
			.claim("username", auth.getName())
			.signWith(key)
			.compact();
	return jwt;
}

public static String getUserNameFromJwtToken(String jwt) {
	jwt=jwt.substring(7);
	Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
	String username=String.valueOf(claims.get("username"));
	return username;
}


}
