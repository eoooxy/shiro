package com.blogx.service;

import com.blogx.entity.RoleMenuEntity;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-09-11 上午11:24
 */
public interface RoleMenuService {

    int batchInsertBeachRoleMenu(List<RoleMenuEntity> list);

    int deleteRoleMenuByRoleId(Integer roleId);
}
