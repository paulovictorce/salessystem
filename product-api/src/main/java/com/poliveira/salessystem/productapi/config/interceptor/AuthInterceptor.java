package com.poliveira.salessystem.productapi.config.interceptor;

import com.poliveira.salessystem.productapi.modules.jwt.service.JwtService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

  private static final String AUTHORIZATION = "Authorization";

  @Autowired
  JwtService jwtService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (isOption(request)) {
      return true;
    }
    var authorization = request.getHeader(AUTHORIZATION);
    jwtService.validateAuthorization(authorization);
    return true;
  }

  private boolean isOption(HttpServletRequest request) {
      return HttpMethod.OPTIONS.name().equals(request.getMethod());
    }
}
