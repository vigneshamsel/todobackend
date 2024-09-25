package com.todoapp.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
   UserAuthenticationProvider userAuthenticationProvider;
    private final FilterChainExceptionHandler filterChainExceptionHandler;
    private final List<String> excludedPaths;


    public JwtAuthFilter(UserAuthenticationProvider userAuthenticationProvider, FilterChainExceptionHandler filterChainExceptionHandler) {
        this.userAuthenticationProvider=userAuthenticationProvider;
        this.filterChainExceptionHandler = filterChainExceptionHandler;
        this.excludedPaths = Arrays.asList("/user/login", "/user/register");

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return excludedPaths.stream().anyMatch(path::startsWith);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws AuthenticationException,ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            handleAuthentication(header, request,response);
            filterChain.doFilter(request, response);
        }catch (JWTVerificationException  exception){
            filterChainExceptionHandler.resolveException(request,response,null,exception);
        }



    }

    private void handleAuthentication(String header, HttpServletRequest request, HttpServletResponse response)  {
        if (header != null &&!header.isEmpty()) {
            String[] authElements = header.split(" ");
            if (authElements.length == 2
                    && "Bearer".equals(authElements[0])) {

                    if ("GET".equals(request.getMethod())) {
                        SecurityContextHolder.getContext().setAuthentication(
                                userAuthenticationProvider.validateToken(authElements[1]));
                    } else {
                        SecurityContextHolder.getContext().setAuthentication(
                                userAuthenticationProvider.validateTokenStrongly(authElements[1]));
                    }

            }

        }
    }
}
