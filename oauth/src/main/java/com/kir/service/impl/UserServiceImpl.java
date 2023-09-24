package com.kir.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kir.entity.Role;
import com.kir.entity.User;
import com.kir.mapper.RoleMapper;
import com.kir.mapper.UserMapper;
import com.kir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.security.ldap.userdetails.LdapUserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService,UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            throw new RuntimeException("user not found.");
        }

        QueryWrapper<Role> roleWrapper = new QueryWrapper<Role>();
        roleWrapper.eq("user_id",user.getId());
        List<Role> roles = roleMapper.selectList(roleWrapper);

        List<String> roleName = roles.stream().map(Role::getRole).collect(Collectors.toList());
        user.setAuthorities(AuthorityUtils.createAuthorityList(roleName));
        return user;
    }
}
