package com.blogx.service;

import com.blogx.entity.UserEntity;
import com.blogx.utils.PageUtils;
import com.blogx.vo.UserVo;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-08-14 下午5:02
 */
public interface UserService {

    UserEntity selectByUserName(String userName);

    UserEntity selectByUserId(Integer userId);

    List<UserVo> selectUsers(PageUtils pageUtils);

    int insertUserBackId(UserEntity userEntity);

    int updateUser(UserEntity userEntity);

    int softDelete(Integer userId);

    List<UserEntity> seachByUserName(PageUtils pageUtils, String userName);
}
