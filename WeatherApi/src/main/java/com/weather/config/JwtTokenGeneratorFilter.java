package com.weather.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

	String jwt;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
             jwt = Jwts.builder()
            		.setIssuer("Ram")
            		.setSubject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+ 30000000)) 
                    .signWith(key).compact();
            response.setHeader(SecurityConstants.JWT_HEADER, jwt);
 
        }
        filterChain.doFilter(request, response);	
	}
	public String genrateToken(Authentication auth) {
		SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
		return Jwts.builder()
                .setSubject("Jwt Token")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 846000000))
                .claim("email", auth.getName())
                .claim("authorities", populateAuthorities(auth.getAuthorities()))
                .signWith(key).compact();
		
	}
	
    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        
    	Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
   
    
    }
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	
        return !request.getServletPath().equals("/login");	
	}
	
}
