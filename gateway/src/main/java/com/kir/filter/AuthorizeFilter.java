package com.kir.filter;

import com.alibaba.fastjson.JSON;
import com.kir.utils.JwtDto;
import com.kir.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizeFilter implements GlobalFilter{
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        String uri = serverHttpRequest.getURI().getPath();

        if (serverHttpRequest.getHeaders().get("Authorization") == null){
            return getVoidMono(serverHttpResponse, ResponseCodeEnum.TOKEN_MISSION);
        }
        String token = serverHttpRequest.getHeaders().get("Authorization").toString().split(" ")[1];
        token = token.substring(0, token.length() - 1);

        try{
            JwtDto jwtDto = JwtUtils.tokenVerify(token);
            //TODO uri
            if (serverHttpRequest.getMethod().name().equals("PUT")
                    || serverHttpRequest.getMethod().name().equals("DELETE")
                    || serverHttpRequest.getMethod().name().equals("POST")){
                if (jwtDto.getRoles().contains("Normal")){
                    return getVoidMono(serverHttpResponse, ResponseCodeEnum.ROLE_ERROR);
                };
            }
        }catch (Exception e){
            log.error("authorize ex:",e);
            return getVoidMono(serverHttpResponse, ResponseCodeEnum.UNKNOWN_ERROR);
        }

        return chain.filter(exchange.mutate().build());
    }


    private Mono<Void> getVoidMono(ServerHttpResponse serverHttpResponse, ResponseCodeEnum responseCodeEnum) {
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        ResponseResult responseResult = ResponseResult.error(responseCodeEnum.getCode(), responseCodeEnum.getMessage());
        DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JSON.toJSONString(responseResult).getBytes());
        return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }


}
