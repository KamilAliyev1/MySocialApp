package com.kmsocialapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetail;
import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class CustomJwtutil {

    private final static String secret = "oyee";


    public static String generateToken(UserSecurityDetail userSecurityDetail, HttpServletRequest request){
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        String access_token= JWT.create()
                .withSubject(userSecurityDetail.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles",userSecurityDetail.getAuthorities().stream().map(
                        GrantedAuthority::getAuthority
                ).collect(Collectors.toList()))
                .sign(algorithm);
        return access_token;
    }

    public static Map.Entry<String, Collection> decodeToken(String authorizatinHeader){
        String token = authorizatinHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(
                role-> authorities.add(new SimpleGrantedAuthority(role))
        );

        Map.Entry<String ,Collection> details = Map.entry(username,authorities);

        return details;
    }


}
