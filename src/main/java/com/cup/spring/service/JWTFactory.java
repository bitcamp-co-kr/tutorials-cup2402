package com.cup.spring.service;

import java.time.ZonedDateTime;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTFactory {

	private String secretKey = "ranmdon2024text12";
	private long expire = 60 * 24;	// 1day
	
	public String generateToken(String content) throws Exception {
		return Jwts.builder()
				.setIssuedAt(new Date())
				.setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant()))
				.claim("sub", content)
				.signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8"))
				.compact();
	}
	public String validateAndExtract(String token) throws Exception {
		String content = null;
		try {
			DefaultJws<?> defaultjws = (DefaultJws<?>) Jwts.parser().setSigningKey(secretKey.getBytes("UTF-8"))
					.parseClaimsJws(token);
			log.info("{}", defaultjws);
			
			DefaultClaims claims = (DefaultClaims) defaultjws.getBody();
			content = claims.getSubject();
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error("{}", e.getMessage());
			content = null;
		}
		return content;
	}
}
