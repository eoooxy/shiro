package com.blogx.service;

import com.blogx.entity.RoleEntity;
import com.blogx.utils.PageUtils;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-08-14 下午5:03
 */
public interface RoleService {

    RoleEntity selectByRoleId(Integer roleId);

    List<RoleEntity> selectRoles(PageUtils pageUtils);

    int insertRoleBackId(RoleEntity roleEntity);

    int updateRole(RoleEntity roleEntity);

    int delete(Integer roleId);

}
