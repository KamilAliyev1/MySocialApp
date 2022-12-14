package com.kmsocialapp.security;

import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetail;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public CustomAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserSecurityDetail userSecurityDetail = new UserSecurityDetail(null,username,password,null);


        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userSecurityDetail,password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserSecurityDetail userSecurityDetail = (UserSecurityDetail)  authResult.getPrincipal();
        response.getOutputStream().print(CustomJwtutil.generateToken(userSecurityDetail,request));
    }
}
