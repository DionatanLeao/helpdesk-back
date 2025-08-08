package com.udemy.helpdesk.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.helpdesk.domain.dtos.CredentialsDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            CredentialsDTO credentials = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword(), List.of());
            return authenticationManager.authenticate(authenticationToken);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String username = ((UserSpringSecurity) authResult.getPrincipal()).getUsername();
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Authorization", "Bearer " + jwtUtil.generateToken(username));
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json(request));
    }

    private String json(HttpServletRequest request) {
        long timestamp = System.currentTimeMillis();
        return String.format(
                "{ \"timestamp\": %d, \"status\": 401, \"error\": \"Unauthorized\", \"message\": \"Email ou senha inválidos\", \"path\": \"%s\" }",
                timestamp,
                request.getRequestURI()
        );
    }
}
