package com.book.store.Security;

import com.book.store.Model.ApplicationUser;
import com.book.store.Service.CustomUserDetailService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwtToken);
            } catch (Exception e){
                e.printStackTrace();
            }
            ApplicationUser userDetails = this.customUserDetailService.loadUserByUsername(username);
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UsernamePasswordAuthenticationToken ut = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                ut.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(ut);

                System.out.println(userDetails.getUsername()+" "+userDetails.getAuthorities().toString());

            } else{
                System.out.println("Invalid Token: "+jwtToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
