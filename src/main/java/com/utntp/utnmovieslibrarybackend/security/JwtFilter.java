package com.utntp.utnmovieslibrarybackend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest req) {
        String path = req.getRequestURI();
        String method = req.getMethod();
        // must return true to avoid filter
        return (pathMatcher.match("/api/auth/login", path) && "POST".equals(method)) ||
                (pathMatcher.match("/api/auth/register", path) && "POST".equals(method)) ||
                (pathMatcher.match("/api/movies/**", path) && "GET".equals(method)) ||
                (pathMatcher.match("/api/genres/**", path) && "GET".equals(method)) ||
                (pathMatcher.match("/api/reviews/**", path) && "GET".equals(method)) ||
                (pathMatcher.match("/uploads/**", path) && "GET".equals(method));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws IOException, ServletException {

        String token = null;
        if (request.getCookies() != null) {
            token = Arrays.stream(request.getCookies())
                    .filter(c -> c.getName().equals("token"))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);

        }

        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Unauthorized: Missing token\"}");
            return;
        }

        if (!jwtService.isTokenValid(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Unauthorized: Invalid token \"}");
            return;
        }

        UserPrincipal userPrincipal = new UserPrincipal(
                jwtService.getUserId(token),
                jwtService.getEmail(token),
                jwtService.getUsername(token),
                jwtService.getRole(token)
        );

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        // if token is valid and everything is okay
        filterChain.doFilter(request, response);
    }
}
