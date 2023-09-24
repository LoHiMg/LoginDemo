package com.kir.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kir.entity.Role;
import com.kir.mapper.RoleMapper;
import com.kir.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
