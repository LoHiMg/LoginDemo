package com.kir.utils;

import lombok.Data;

import java.util.List;

@Data
public class JwtDto {

    private String username;

    private List<String> roles;

    private String channel;
}
