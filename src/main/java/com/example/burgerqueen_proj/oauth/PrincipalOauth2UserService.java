package com.example.burgerqueen_proj.oauth;

import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.member.repository.MemberRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Component
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    public PrincipalOauth2UserService(@Lazy BCryptPasswordEncoder bCryptPasswordEncoder,@Lazy MemberRepository memberRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.memberRepository = memberRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest : "+userRequest);// UserRequest를 출력
        System.out.println("userRequest : "+userRequest.getClientRegistration());// UserRequest를 출력
        System.out.println("userRequest : "+userRequest.getAccessToken().getTokenValue());// UserRequest를 출력
        System.out.println("userRequest : "+super.loadUser(userRequest).getAttributes());// return되는 super.loadUser(userRequest).getAttributes()출력

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttribute"+ oAuth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String userName = "provider_"+providerId;
        String password = bCryptPasswordEncoder.encode("password");
        String email = oAuth2User.getAttribute("email");
        String role = "ROLE_USER";

        Member memberEntity = memberRepository.findByUserName(userName);

        if (memberEntity == null){
            memberEntity = Member.builder()
                    .userName(userName)
                    .password(password)
                    .email(email)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            memberRepository.save(memberEntity);

        }


        return new PrincipalDetails(memberEntity,oAuth2User.getAttributes());


    }
}
