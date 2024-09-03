package com.yuds.spring_yuds.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@Log4j2
@RequiredArgsConstructor
public class SecurityConfig {


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception{

    //! 모든 접근 허용
//    http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll());

    // 단순 사용자 검증 후 페이지 이동
    http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
        .formLogin(formLogin ->
            formLogin
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/" , true));

    // url별 사용자 권한 체크
//    http.authorizeHttpRequests(requests
//            -> requests
//            .requestMatchers("/admin").hasRole("admin")
//            .requestMatchers("/user").hasRole("user")
//            .anyRequest().authenticated()
//        )
//        .formLogin(form
//            -> form
//            .usernameParameter("username")
//            .passwordParameter("password")
//            .defaultSuccessUrl("/", true)
//        );




    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user = User.builder().username("yuds").password("1234").roles("user").build();
    return new InMemoryUserDetailsManager(user);
  }


  @Bean
  public PasswordEncoder passwordEncoder(){
    return NoOpPasswordEncoder.getInstance();
  }

}
