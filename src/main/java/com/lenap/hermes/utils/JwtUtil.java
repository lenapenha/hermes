package com.lenap.hermes.utils;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;


import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "cdad246dbe5d17bd6c6d8b4dea3594a331e2db289ab707f0ecfcff8f99903468bad5410b9a781a0af630a1d3799df451320fc5fca932d6a3161a60162b7147f94afed20216073f890508f65fb0395dfa9ec82cb172f43fe8b6715a445812e6931df247042d62256320b24702c3382e0e29caae4e7650732ca965d0f0c15e89cd62bd106db6acc8cb4fbf6c1faeb595d6ade8c8631eb6a5337f8c7ecd6cce635f58eda7d6945ee9512ce4557cbe3b63354857d122cf2a015dd71ee9e4477c65b5a12adfe9c05c5adea172e61971a10791988204c64af3dac4bf79ec25dd6e5d9a20b9baf0db6a40cc0c31ea0de1e78421034c5308f04d08e008ed63e8834d0588";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    public static String getToken(String username) {
        Key encodedKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, encodedKey)
                .compact();
    }

    public static Boolean validateToken(String token) {
        Claims claims = getClaims(token);
        System.out.println(getHeader(token));
        if (isTokenExpired(claims.getExpiration())) {
            throw new ExpiredJwtException(getHeader(token), claims,"Invalid session");
        }

        return true;
    }

    private static Boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    private static Claims getClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    private static JwsHeader getHeader(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getHeader();
    }

    private static SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));
    }
}
