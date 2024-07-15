package com.example.customermanagementsystem.config;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.customermanagementsystem.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	@Value("${security.jwt.expiration-minutes}")
    private long EXPIRATION_MINUTES;
	
	@Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

	@SuppressWarnings("deprecation")
	public String generateToken(User user, Map<String, Object> extraClaims) {
		// TODO Auto-generated method stub
		Date issuedAt =new Date(System.currentTimeMillis());
		Date expiration =new Date(issuedAt.getTime()+ (EXPIRATION_MINUTES * 60 * 1000));
		return Jwts.builder()
		             .setClaims(extraClaims)
		             .setSubject(user.getUsername())
		             .setIssuedAt(issuedAt)
		             .setExpiration(expiration)
		                .signWith(generateKey(), SignatureAlgorithm.HS256)
		                .compact();
	}

	 private Key generateKey(){
	        byte[] secreateAsBytes = Decoders.BASE64.decode(SECRET_KEY);
	        return Keys.hmacShaKeyFor(secreateAsBytes);
	    }

	    public String extractUsername(String jwt) {
	        return extractedAllClaims(jwt).getSubject();
	    }

		@SuppressWarnings("deprecation")
		private Claims extractedAllClaims(String jwt) {
			return Jwts.parser().setSigningKey(generateKey()).build()
	              .parseClaimsJws(jwt).getBody();
		}

	   

}
