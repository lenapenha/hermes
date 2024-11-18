//package com.lenap.hermes.utils;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class JwtUtilTest {
//
//    private final JwtUtil jwtUtil  = new JwtUtil();
//
//    @Test
//    public void shouldGetTokenSuccessfully() {
//        String username = "myUsername";
//        String token = jwtUtil.getToken(username);
//        System.out.println(token);
//        assertInstanceOf(String.class, token);
//    }
//
//    @Test
//    public void shouldValidateTokenSuccessfully() {
//        String username = "myUsername";
//        String token = jwtUtil.getToken(username);
//        Boolean IsValidToken = jwtUtil.validateToken(token);
//        assertTrue(IsValidToken);
//    }
//
//    @Test
//    public void shouldReturnExpiredTokenException() {
//        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJteVVzZXJuYW1lIiwiaWF0IjoxNzMxMzY1NDU1LCJleHAiOjE3MzEzNjU0NTZ9.eNkKkzQ-aw1BBjuUZZ0wgF3gv_i8N86GZMjHDmE8ZEdHhYBOTnoVXSAXaox2uyNcSvSJd5yk3V7IZ2hJH4L7qg";
//        assertThrows(ExpiredJwtException.class, () -> jwtUtil.validateToken(token));
//    }
//}