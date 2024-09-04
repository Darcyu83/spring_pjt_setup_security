package com.yuds.spring_yuds.security;


import io.jsonwebtoken.Jwts;


import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@PropertySource("classpath:jwt.yml")
@Component
public class JwtUtil {


  public static Key getHS256Key(String secretKey) {
    byte[] fullKeyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(Arrays.copyOfRange(fullKeyBytes, 0, 32));
  }

  public static String createJwt(String userName, String secretKey, Long expiredMs) {

/*
    Decoders.BASE64.decode(secretKey);
    secretKey.getBytes(StandardCharsets.UTF_8);
*/

    return Jwts.builder()
        .claim("userName", userName)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expiredMs))
        .signWith(getHS256Key(secretKey))
        .compact();
  }

  public static boolean isExpired(String token, String secretKey) {

    SecretKey key = (SecretKey) getHS256Key(secretKey);

    return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload()
        .getExpiration().before(new Date());
  }
}
