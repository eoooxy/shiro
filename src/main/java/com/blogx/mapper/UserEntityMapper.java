package com.blogx.mapper;

import com.blogx.entity.UserEntity;
import com.blogx.utils.MyMapper;
import com.blogx.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserEntityMapper extends MyMapper<UserEntity> {

    List<UserVo> selectUsers();
}