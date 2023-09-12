package com.spinetracker.spinetracker.global.security.command.domain.service;

import com.spinetracker.spinetracker.global.common.annotation.DomainService;
import com.spinetracker.spinetracker.global.security.command.domain.exception.OAuth2AuthenticationProcessingException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;


import java.security.Key;
import java.util.Date;

@DomainService
public class CustomTokenService {

    @Value("${app.auth.tokenSecret}")
    private String JWT_SECRET;

    @Value("${app.auth.tokenExpirationMsec}")   // 2시간
    private int JWT_EXPIRATION_MS;

    public String createToken(long memberId, String memberRole) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.builder()
                .setSubject(Long.toString(memberId))
                .claim("role", memberRole)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // token 암호화를 해독해서 userId를 가져오는 메소드
    public Long getUserIdFromToken(String token) {

        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }
    // token 유효성 검사
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(authToken);
            return true;
        } catch (SecurityException | MalformedJwtException ex) {
            System.out.println("잘못된 JWT 서명");
            throw new OAuth2AuthenticationProcessingException("잘못된 JWT 서명", ex.getCause());
        } catch (ExpiredJwtException ex) {
            throw new OAuth2AuthenticationProcessingException("토큰 기한 만료 (유효 시간 : " + ex.getClaims().getExpiration() + ")", ex.getCause());
        } catch (UnsupportedJwtException ex) {
            System.out.println("지원되지 않는 JWT 토큰");
            throw new OAuth2AuthenticationProcessingException("지원되지 않는 JWT 토큰", ex.getCause());
        } catch (IllegalArgumentException ex) {
            System.out.println("잘못된 JWT 토큰");
            throw new OAuth2AuthenticationProcessingException("잘못된 JWT 토큰", ex.getCause());
        }
    }

}
