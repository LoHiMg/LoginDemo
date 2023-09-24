package com.kir.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kir.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
