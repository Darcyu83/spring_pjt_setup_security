package com.yuds.spring_yuds.security;

import com.yuds.spring_yuds.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Log4j2
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


  private final UserService userService;

  //  @Value("${jwt.accessTokenSecret}")
  private String secretKey;


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
    log.info("authorization ==== ", authorization);

    if (authorization == null || !authorization.startsWith("Bearer ")) {

      log.error("authorization이 없습니다. ");
      filterChain.doFilter(request, response);
      return;
    }

    String token = authorization.split(" ")[1];

    // token 검증
//    if (JwtUtil.isExpired(token, secretKey)) {
    if (JwtUtil.isExpired(token, "a")) {
      log.error("토큰이 만료되었습니다.");
      filterChain.doFilter(request, response);
      return;
    }

    String userName = "";

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(userName, null,
            List.of(new SimpleGrantedAuthority("USER")));

    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    filterChain.doFilter(request, response);


  }
}
