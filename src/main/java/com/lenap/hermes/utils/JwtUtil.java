package com.lenap.hermes.utils;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private final Environment env;

    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    public JwtUtil(Environment environment) {
        this.env = environment;
    }

    public String getToken(String username) {
        final String secret = env.getProperty("JWT_KEY");
        Key encodedKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, encodedKey)
                .compact();
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    private JwsHeader getHeader(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getHeader();
    }

    private SecretKey getSigningKey() {
        final String secret = env.getProperty("JWT_KEY");
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }
}
