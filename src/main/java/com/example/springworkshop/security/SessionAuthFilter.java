package com.example.springworkshop.security;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.springworkshop.service.UserService;
import com.example.springworkshop.service.UserSessionService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SessionAuthFilter extends OncePerRequestFilter {
    private final UserSessionService userSessionService; 

    public SessionAuthFilter(UserSessionService userSessionService){
        this.userSessionService = userSessionService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
    String authCookie = readCookie(request,"auth");

    if (authCookie != null){
        try{
            var user = userSessionService.findBySession(authCookie);
            var auth = new UsernamePasswordAuthenticationToken(
                user, null, List.of()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);

        }catch(Exception e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
    }
    filterChain.doFilter(request, response);
    }

    private String readCookie(HttpServletRequest request, String name) {
        if (request.getCookies() == null) return null;
        for (var c : request.getCookies()) {
            if (name.equals(c.getName())) return c.getValue();
        }
        return null;
    }






}
