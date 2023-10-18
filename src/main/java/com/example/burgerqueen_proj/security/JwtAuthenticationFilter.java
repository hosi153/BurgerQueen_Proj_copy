package com.example.burgerqueen_proj.security;

import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter  extends GenericFilterBean  {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //1. Request Header -> JWT 토큰 추출
        String token = resolveToken((HttpServletRequest) request);

        //2. 토큰 유효성 검사
        if(token !=null && jwtTokenProvider.validateToken(token)){
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        //3. 필터체인에 추가
        chain.doFilter(request, response);

    }

    private String resolveToken(HttpServletRequest request) {
        String barerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(barerToken) && barerToken.startsWith("Bearer"))
            return barerToken.substring(7);

        return null;

    }
}
