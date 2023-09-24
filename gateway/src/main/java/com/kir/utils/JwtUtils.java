package com.kir.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtUtils {

    private static final String secret = "123456";

    public static JwtDto tokenVerify(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        jwtVerifier.verify(token);
        JWT.decode(token).getExpiresAt();
        String json = JWT.decode(token).getAudience().get(0);
        return JSON.parseObject(json, JwtDto.class);
    }

}
