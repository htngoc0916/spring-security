package com.example.demologinjwt.security.jwt;

import com.example.demologinjwt.security.custom.CustomUserDetailsService;
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

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //tao ham de kiem tra header tu client gui den
        String token = extractTokenFromAuthorizationHeader(request.getHeader("Authorization"));
        String userName = null;
        // lay thong tin userName của token
        if (null != token) {
            userName = jwtProvider.getUsernameFromToken(token);
        }

        if(null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
            //neu nhu token hop le
            if(jwtProvider.validateToken(token,userDetails)) {
                //tao ra ham de set
                setAuthenticationToken(request, userDetails);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String extractTokenFromAuthorizationHeader(String authorization) {
        //kiem tra header và lấy subtring để lấy chuôi token
        if (null != authorization && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }

    private void setAuthenticationToken(HttpServletRequest request, UserDetails userDetails) {
        // Tạo một đối tượng UsernamePasswordAuthenticationToken với thông tin người dùng và các roles
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // Thiết lập thông tin xác thực cho đối tượng UsernamePasswordAuthenticationToken
        // thông tin chi tiết về request như địa chỉ IP, thông tin trình duyệt, v.v.
        usernamePasswordAuthenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        // Đặt đối tượng xác thực vào SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
