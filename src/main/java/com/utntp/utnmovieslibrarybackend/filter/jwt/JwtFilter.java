package com.utntp.utnmovieslibrarybackend.filter.jwt;

import com.utntp.utnmovieslibrarybackend.service.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

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
        boolean isSignIn = path.contains("/api/auth/register") && method.equals("POST");
        boolean isGetMovies = request.getRequestURI().contains("/api/movies") && request.getMethod().equals("GET");
        boolean isGetGenres = request.getRequestURI().contains("/api/genres") && request.getMethod().equals("GET");
        boolean isGetReviews = request.getRequestURI().contains("/api/reviews") && request.getMethod().equals("GET");
        if(isLogin || isSignIn || isGetGenres || isGetMovies || isGetReviews) {
            filterChain.doFilter(request,response);
            return;
        }

        String token = null;
        if(request.getCookies() != null){
            token = Arrays.stream(request.getCookies())
                    .filter(c -> c.getName().equals("token"))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);

        }

        if(token == null || token.isEmpty()){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Missing token");
            return;
        }

        if(!jwtService.isTokenValid(token)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid token");
            return;
        }

        // if token is valid and everything is okay
        filterChain.doFilter(request,response);
    }
}
