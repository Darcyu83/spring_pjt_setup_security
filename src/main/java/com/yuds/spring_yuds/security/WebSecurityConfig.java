package com.yuds.spring_yuds.security;

import com.yuds.spring_yuds.employee.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Log4j2
public class WebSecurityConfig {


  private final UserService userService;


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(BCryptVersion.$2B, 10);
  }


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    log.info("SecurityFilterChain ==== ");
    /*
     * 사이트 간 요청 위조의 줄임말.
     * 웹 애플리케이션 취약점 중 하나로 사용자가 자신의 의지와 무관하게 공격자가 의도한 행동을 해서
     * 특정 웹 페이지를 보안에 취약하게 한다거나 수정, 삭제 등의 작업을 하게 만드는 공격 방법
     * */
    http.csrf(AbstractHttpConfigurer::disable)// csrf X
        .httpBasic(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .headers(
            headers -> headers.frameOptions((HeadersConfigurer.FrameOptionsConfig::sameOrigin)))
        .sessionManagement(
            httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(requests ->
            {
              requests.requestMatchers("/auth", "/auth/**", "/health-check").permitAll();
              requests.anyRequest().authenticated();
            }
        ).addFilterBefore(new JwtFilter(userService), UsernamePasswordAuthenticationFilter.class)
    ;
            /*
            * DSL : Domain-Specific Language : 특정 도메인이나 문제 영역에 특화된 언어
            * 해당 도메인의 용어, 구문, 추상화 등을 통해 해당 도메인의 작업을 더 쉽게 표현하고 코드를 읽기 쉽게 만드는 것을 목표
            *
            * External DSL (외부 DSL):
              - 외부 DSL은 독립적인 언어로서 도메인 특화 언어를 말한다.
              - 예시로는 SQL, HTML, CSS 등이 있다.
            * Internal DSL (내부 DSL):
              - 내부 DSL은 일반 프로그래밍 언어 내에 도메인 특화 언어를 정의하는 것을 말한다.
              - 기존 프로그래밍 언어의 문법과 구문을 사용하며, 해당 도메인의 특정 작업을 간결하게 표현
              - 예시로는 Querydsl, mockito 등이 있다.
            *
            * Querydsl
            * Querydsl은 DSL의 한 종류로서, 특히 데이터베이스 쿼리 작성을 위한 DSL을 제공
            * */

    return http.build();
  }

}
