package com.blogx.service.impl;

import com.blogx.entity.RoleMenuEntity;
import com.blogx.mapper.RoleMenuEntityMapper;
import com.blogx.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-09-11 上午11:27
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    RoleMenuEntityMapper roleMenuEntityMapper;

    /**
     * 批量新增 用户资源映射表信息
     *
     * @param list
     * @return
     */
    @Override
    public int batchInsertBeachRoleMenu(List<RoleMenuEntity> list) {
        return roleMenuEntityMapper.insertList(list);
    }

    /**
     * 更新 角色或删除角色的时候，需要删除其相关菜单资源信息
     *
     * @param roleId
     * @return
     */
    @Override
    public int deleteRoleMenuByRoleId(Integer roleId) {
        return roleMenuEntityMapper.deleteByRoleId(roleId);
    }
}
