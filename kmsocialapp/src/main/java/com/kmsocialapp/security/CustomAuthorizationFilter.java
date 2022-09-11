package com.kmsocialapp.security;


import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetailService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final UserSecurityDetailService userSecurityDetailService;

    public CustomAuthorizationFilter(UserSecurityDetailService userSecurityDetailService) {
        this.userSecurityDetailService = userSecurityDetailService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getServletPath().equals("/login")){
            filterChain.doFilter(request,response);
        }
        else {
            String authorizatinHeader = request.getHeader(AUTHORIZATION);
            if (authorizatinHeader !=null && authorizatinHeader.startsWith("Bearer ")) {
                try{
                    Map.Entry<String ,Collection> decodeToken = CustomJwtutil.decodeToken(authorizatinHeader);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userSecurityDetailService.customloadUserByUsername(decodeToken.getKey()),null,decodeToken.getValue());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    filterChain.doFilter(request, response);
                }catch (Exception e){
                    response.setHeader("error",e.getMessage());
                    response.sendError(FORBIDDEN.value());
                }

            }else {
                filterChain.doFilter(request,response);
            }
        }
    }
}
