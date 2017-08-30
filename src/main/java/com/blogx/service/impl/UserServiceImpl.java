package com.blogx.service.impl;

import com.blogx.mapper.UserEntityMapper;
import com.blogx.entity.UserEntity;
import com.blogx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author： xueyuan
 * date  ： 2017-08-29 上午9:39
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserEntityMapper userEntityMapper;

    @Override
    public UserEntity selectByUserName(String userName) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userName);
        return userEntityMapper.selectOne(userEntity);
    }
}
