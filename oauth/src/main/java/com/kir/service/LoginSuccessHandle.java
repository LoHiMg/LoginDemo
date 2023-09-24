package com.kir.service;

import com.alibaba.fastjson.JSON;
import com.kir.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Component
public class LoginSuccessHandle implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        //github
        if (authentication instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken){
//            if(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId().equals("github")){
//                Collection<GrantedAuthority> roles = oAuth2AuthenticationToken.getAuthorities();
//                GrantedAuthority editor = new SimpleGrantedAuthority("EDITOR");
//                roles.add(editor);
//            }
        }
        String token = JwtUtil.token(authentication);
        response.setContentType("text/html;charset=UTF-8");
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("token", token);
        response.getWriter().write(JSON.toJSONString(hashMap));
    }
}
