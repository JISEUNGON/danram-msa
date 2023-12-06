package com.danram.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Slf4j
@Component
public class JwtUtil {
    public static String JWT_SECRET_KEY;

    @Value("${jwt.secret}")
    public void setKey(String key) {
        JWT_SECRET_KEY = key;
    }

    public static boolean isExpired(String token) {
        boolean isExpired;

        try {
            Jwts.parserBuilder()
                    .setSigningKey(JWT_SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();

            isExpired = false;
        }
        catch (ExpiredJwtException ex) {
            log.error("jwt is expired");
            isExpired = true;
        }

        return isExpired;
    }

    /**
     * 토큰의 유효성  검증을 수행하는 validateToken 메소드
     * */
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(JWT_SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
