package com.yuds.spring_yuds.auth.controller;


import com.yuds.spring_yuds.auth.dto.LoginDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthController {


  @GetMapping("/login")
  public String login(@Valid @RequestBody LoginDto loginDto) {

    return "";
  }


  public String any(@AuthenticationPrincipal User user) {

    return "";
  }


}
