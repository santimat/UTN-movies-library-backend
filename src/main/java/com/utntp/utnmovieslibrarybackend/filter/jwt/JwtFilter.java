package com.utntp.utnmovieslibrarybackend.filter.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {

        // Skip JWT validation for login or register
        String path = request.getRequestURI();
        String method = request.getMethod();
        boolean isLogin = path.contains("/api/auth/login") && method.equals("POST");
        boolean isSignIn = path.contains("/api/users") && method.equals("POST");
        if(isLogin || isSignIn) {
            filterChain.doFilter(request,response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        // if header is empty or it has incorrect format
        if(authHeader == null || !authHeader.startsWith("Bearer")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Missing or invalid Authorization header");
            return;
        }

        // extract token, if header is "Bearer misupertoken" we just keep "misupertoken"
        String token = authHeader.substring(7);
        // get the payload from the JWT
        String email = jwtService.extractEmail(token);

        if(!jwtService.isTokenValid(token)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid token");
            return;
        }

        // if token is valid and everything is okay
        filterChain.doFilter(request,response);
    }
}
