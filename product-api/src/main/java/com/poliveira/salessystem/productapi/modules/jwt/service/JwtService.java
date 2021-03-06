package com.poliveira.salessystem.productapi.modules.jwt.service;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.poliveira.salessystem.productapi.config.exception.AuthenticationException;
import com.poliveira.salessystem.productapi.modules.jwt.dto.JwtResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Value("${app-config.secrets.api-secret}")
  private String apiSecret;

  private static final String EMPTY_SPACE = " ";
  private static final Integer TOKEN_INDEX = 1;

  public void validateAuthorization(String token) {
    var accessToken = extractToken(token);
    try {
      var claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(apiSecret.getBytes()))
          .build().parseClaimsJws(accessToken).getBody();
      var user = JwtResponse.getUser(claims);
      if (isEmpty(user) || isEmpty(user.getId())) {
        throw new AuthenticationException("The user is not valid.");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new AuthenticationException("Error while trying to proccess the access token.");
    }
  }

  private String extractToken(String token) {
    if (isEmpty(token)) {
      throw new AuthenticationException("The access token was not informed.");
    }

    if (token.contains(EMPTY_SPACE)) {
      return token.split(EMPTY_SPACE)[TOKEN_INDEX];
    }
    return token;
  }
}
