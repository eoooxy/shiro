package com.blogx.service;

import com.blogx.entity.UserEntity;

/**
 * author： xueyuan
 * date  ： 2017-08-14 下午5:02
 */
public interface UserService {

    UserEntity selectByUserName(String userName);
}
