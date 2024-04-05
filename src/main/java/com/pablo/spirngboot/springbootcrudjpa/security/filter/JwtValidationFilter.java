package com.pablo.spirngboot.springbootcrudjpa.security.filter;

import static com.pablo.spirngboot.springbootcrudjpa.security.TokenJWTConfig.CONTENT_TYPE;
import static com.pablo.spirngboot.springbootcrudjpa.security.TokenJWTConfig.HEADER_AUTHORIZATION;
import static com.pablo.spirngboot.springbootcrudjpa.security.TokenJWTConfig.PREFIX_STRING;
import static com.pablo.spirngboot.springbootcrudjpa.security.TokenJWTConfig.SECRET_KEY;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablo.spirngboot.springbootcrudjpa.security.SimbleGrantedAuthorityJsonCreator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(HEADER_AUTHORIZATION);
        if (header == null || !header.startsWith(PREFIX_STRING)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(PREFIX_STRING, "");

        

        try {
            
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
            String username = claims.getSubject();
            Object authoritiesClaims = claims.get("authorities");

            Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                    new ObjectMapper()
                            .addMixIn(SimpleGrantedAuthority.class, SimbleGrantedAuthorityJsonCreator.class)
                            .readValue(
                                    authoritiesClaims.toString().getBytes(),
                                    SimpleGrantedAuthority[].class));

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                    null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);


        } catch (JwtException e) {
            Map<String, String> body = new HashMap<String, String>();
            body.put("message", "Token no valido");
            body.put("error", e.getMessage());
            
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setContentType(CONTENT_TYPE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

    }

}
