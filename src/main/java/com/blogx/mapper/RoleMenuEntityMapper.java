package com.blogx.mapper;

import com.blogx.entity.RoleMenuEntity;
import com.blogx.utils.MyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenuEntityMapper extends MyMapper<RoleMenuEntity> {

    int deleteByRoleId(Integer roleId);
}