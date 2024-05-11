package com.example.SpringBlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //암호화 method
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(); //비밀번호 최소길이 설정가능
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll() //모든사용자 접근가능
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated() //나머지 모든 요청은 인증된 사용자에게만 허용
                );

        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );

//        http
//                .csrf((auth) -> auth.disable());


        //다중 로그인 통제
        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)  //n개의 다중로그인 허용
                        .maxSessionsPreventsLogin(true)); //다중 로그인 개수를 초과하였을 경우 처리 방법
                        //true : 초과시 새로운 로그인 차단
                        //false : 초과시 기존 세션 하나 삭제

        //session 보호
        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId()); //로그인 시 동일한 세션에 대한 id 변경

        //로그아웃 설정
        http
                .logout((auth) -> auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/"));

        return http.build();
    }
}