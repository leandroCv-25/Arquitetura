package com.lobo.session.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
    private static final long JWT_TOKEN_VALIDITY = 15 * 60;

    @Value("${jwt.secret}")
    private String secret;

    // retorna expiration date do token jwt
    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private String getKeyFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);

    }

    private Claims getAllClaimsFromToken(String token) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // gera token para user
    public String generateToken(Long key) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, Long.toString(key));
    }

    // Cria o token e define tempo de expiração pra ele
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    // valida o token
    public  Boolean validateToken(String token, Long key) {
        final String keyToken = getKeyFromToken(token);
        return (keyToken.equals(Long.toString(key)) && !isTokenExpired(token));
    }
}
