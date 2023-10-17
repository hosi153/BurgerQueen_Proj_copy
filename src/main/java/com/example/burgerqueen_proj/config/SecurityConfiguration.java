package com.example.burgerqueen_proj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure(){
        return (web)-> web.ignoring()
                .requestMatchers(toH2Console())
                .antMatchers("/static/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        security
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .formLogin()

                .and()
                .authorizeRequests() //인증과 인가를 설정하겠다는 선언(메서드)
                .antMatchers("/login","/signup","/join","/api/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
//                .loginPage("/login")
                .defaultSuccessUrl("/home")

                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true);

        return security.build();

    }

}
