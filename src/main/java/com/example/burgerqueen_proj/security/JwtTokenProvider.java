package com.example.burgerqueen_proj.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {
    private final Key key;

    public JwtTokenProvider(@Value("VlwEyVBsYt9V7zq57TejMnVUyzblYcfPQye08f7MGVA9XkHa") String secretKey) {
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyByte);
    }


    public TokenInfo generateToken(Authentication authentication){
        //권한 추출 (인증된 객체에서 뽑아옴)
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        // access-token 생성
        Date accessTokenExpiredsIn = new Date(now + 86400000); //밀리세컨드로 표현
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth",authorities)
                .setExpiration(accessTokenExpiredsIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // refresh-token 생성
        Date refreshTokenExpiredIn = new Date(now + 186400000);
        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpiredIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        //실제 헤더에 토큰값이 담기는 모습
        // Http header >> key : Barer XXXX.YYYY.ZZZZ
        return TokenInfo.builder()
                .grantType("Bearer") //헤더에 담길 떄 구분key 값
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if(claims.get("auth") == null){
            throw new RuntimeException("권한정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(),"",authorities);
        return new UsernamePasswordAuthenticationToken(principal,"",authorities);


    }

    private Claims parseClaims(String accessToken) {
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken).getBody();
        }catch(ExpiredJwtException e){
            return e.getClaims();
        }
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch(SecurityException | MalformedJwtException e){
            log.info("Invalid JWT token", e);
        }catch(ExpiredJwtException e){
            log.info("Expired JWT Token", e);
        }catch(UnsupportedJwtException e){
            log.info("Unsupported JWT token",e);
        }catch(IllegalArgumentException e){
            log.info("JWT claims string is Empty",e);
        }
        return false;
    }


}
