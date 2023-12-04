package com.danram.gateway.filter;

import com.danram.gateway.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class JwtUserFilter extends AbstractGatewayFilterFactory<JwtUserFilter.Config> {
    public JwtUserFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(final Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            //header 값
            String authorizationHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            // bearer이 아니면 오류
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                // 응답 내용 수정
                String modifiedResponse = "JWT Token does not begin with Bearer String.";
                DataBuffer newResponseData = response.bufferFactory().wrap(modifiedResponse.getBytes(StandardCharsets.UTF_8));
                response.getHeaders().setContentLength(modifiedResponse.length());

                return response.writeWith(Flux.just(newResponseData));
            }

            // Token 꺼내기
            String token = authorizationHeader.split(" ")[1];

            // Token 검증
            if (!JwtUtil.validateToken(token)) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                // 응답 내용 수정
                String modifiedResponse = "JWT Token is not valid.";
                DataBuffer newResponseData = response.bufferFactory().wrap(modifiedResponse.getBytes(StandardCharsets.UTF_8));
                response.getHeaders().setContentLength(modifiedResponse.length());

                return response.writeWith(Flux.just(newResponseData));
            }

            // Token 만료 체크
            if (JwtUtil.isExpired(token)) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                // 응답 내용 수정
                String modifiedResponse = "JWT Token is expired.";
                DataBuffer newResponseData = response.bufferFactory().wrap(modifiedResponse.getBytes(StandardCharsets.UTF_8));
                response.getHeaders().setContentLength(modifiedResponse.length());

                return response.writeWith(Flux.just(newResponseData));
            }

            if(JwtUtil.getRoles(token).contains("ROLE_USER")) {
                log.info("role: {}", "ROLE_USER");
            }
            else {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                // 응답 내용 수정
                String modifiedResponse = "User does not have permission.";
                DataBuffer newResponseData = response.bufferFactory().wrap(modifiedResponse.getBytes(StandardCharsets.UTF_8));
                response.getHeaders().setContentLength(modifiedResponse.length());

                return response.writeWith(Flux.just(newResponseData));
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {

    }
}
