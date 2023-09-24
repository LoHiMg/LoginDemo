package com.kir.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.kir.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUtil {

    private static final String secret = "123456";

    public static String token(Authentication authentication){

        JwtDto jwtDto = new JwtDto();

        //Oauth2 Login
        if (authentication instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken){
            jwtDto.setChannel(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());
            jwtDto.setRoles(List.of("EDITOR"));
            jwtDto.setUsername(oAuth2AuthenticationToken.getPrincipal().getAttribute("login"));
        }else if (authentication instanceof UsernamePasswordAuthenticationToken){
            //Ldap Login
            if(authentication.getPrincipal() instanceof LdapUserDetailsImpl ldapUserDetails){
                jwtDto.setUsername(ldapUserDetails.getUsername());
                jwtDto.setChannel("ldap");
                List<String> roles = ldapUserDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
                jwtDto.setRoles(roles);
                //Db Login
            }else if (authentication.getPrincipal() instanceof User user){
                jwtDto.setUsername(user.getUsername());
                jwtDto.setChannel("db");
                List<String> roles = user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
                jwtDto.setRoles(roles);
            }
        }
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30))
                .withAudience(JSON.toJSONString(jwtDto))
                .sign(Algorithm.HMAC256(secret));
    }


    public static void tokenVerify(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        jwtVerifier.verify(token);
        JWT.decode(token).getExpiresAt();
        String json = JWT.decode(token).getAudience().get(0);
        JWTAuthentication jwtAuthentication = JSON.parseObject(json, JWTAuthentication.class);
        SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
    }
}
