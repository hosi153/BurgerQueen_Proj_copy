package com.example.burgerqueen_proj.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest : "+userRequest);// UserRequest를 출력
        System.out.println("userRequest : "+userRequest.getClientRegistration());// UserRequest를 출력
        System.out.println("userRequest : "+userRequest.getAccessToken().getTokenValue());// UserRequest를 출력
        System.out.println("userRequest : "+super.loadUser(userRequest).getAttributes());// return되는 super.loadUser(userRequest).getAttributes()출력
        return super.loadUser(userRequest);

    }
}
