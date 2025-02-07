package com.emre.galleristapp.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        //authorization boş gelmiş olabilir
        if(authHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }

        //Bearer ile başlamamış olabilir
        if(!authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token;
        String username;


        token = authHeader.substring(7);

        try {
        username = jwtService.getUsernameByToken(token);
        //fast fail
        if(username == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        //kullanıcı mevcut değil ise return et
        if(userDetails == null) {
           filterChain.doFilter(request, response);
           return;
       }

        //token valid değilse aynı hareketi yap
        if(!jwtService.validateToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

            //Kullanıcı artık işlemini yapabilir güvenli olarak işaretledik
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        authentication.setDetails(userDetails);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
