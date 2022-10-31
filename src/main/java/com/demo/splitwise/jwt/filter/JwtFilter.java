package com.demo.splitwise.jwt.filter;

import com.demo.splitwise.jwt.utility.JWTUtility;
import com.demo.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * For every request we've to pass the JWT token
 * and our code should be able to understand the received jwt token and authenticate the user
 * To authenticate use OncePerRequestFilter from spring web.
 * Whenever a req is sent this filter will be executed first and then the req will be passed to next filter
 * */

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //get Header
        String authorization = httpServletRequest.getHeader("Authorization");
        String token = null;
        String userName = null;

        //get token and username from token
        if(null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7); //remove Bearer, hence from 7th character
            userName = jwtUtility.getUsernameFromToken(token);
        }

        //To authenticate, load userDetails, validate token,
        if(null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails
                    = userService.loadUserByUsername(userName);

            if(jwtUtility.validateToken(token,userDetails)) {
                //if the token is valid, create userid password token and add in security context
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
