package com.yuds.spring_yuds.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

  private String accessToken;
  private String refreshToken;


}
