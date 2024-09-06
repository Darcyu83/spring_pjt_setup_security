package com.yuds.spring_yuds.auth.service;


import com.yuds.spring_yuds.auth.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Log4j2
public class AuthService {


  public TokenResponse login(String loginId, String password) {

    return new TokenResponse();
  }
}
