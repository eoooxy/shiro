package com.blogx.service.impl;

import com.blogx.entity.UserRoleEntity;
import com.blogx.mapper.UserRoleEntityMapper;
import com.blogx.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author： xueyuan
 * date  ： 2017-09-11 上午11:26
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {


    @Autowired
    UserRoleEntityMapper userRoleEntityMapper;

    /**
     * 新增 用户权限映射表
     *
     * @param userRoleEntity
     * @return
     */
    @Override
    public int insertUserRole(UserRoleEntity userRoleEntity) {
        return userRoleEntityMapper.insertUseGeneratedKeys(userRoleEntity);
    }

    /**
     * 更新 用户权限映射表
     *
     * @param userRoleEntity
     * @return
     */
    @Override
    public int updateUserRole(UserRoleEntity userRoleEntity) {
        return userRoleEntityMapper.updateUserRoleByFkUserId(userRoleEntity);
    }
}
