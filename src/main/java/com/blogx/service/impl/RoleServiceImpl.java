package com.blogx.service.impl;

import com.blogx.entity.RoleEntity;
import com.blogx.entity.UserEntity;
import com.blogx.mapper.RoleEntityMapper;
import com.blogx.service.RoleService;
import com.blogx.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-08-29 上午10:10
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleEntityMapper roleEntityMapper;

    /**
     * 根据id得到角色
     *
     * @param roleId
     * @return
     */
    @Override
    public RoleEntity selectByRoleId(Integer roleId) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(roleId);
        return roleEntityMapper.selectOne(roleEntity);
    }

    /**
     * 得到所有角色
     *
     * @param pageUtils
     * @return
     */
    @Override
    public List<RoleEntity> selectRoles(PageUtils pageUtils) {
        PageHelper.startPage(pageUtils.getPageNum(), pageUtils.getPageSize());
        return roleEntityMapper.selectAll();
    }

    /**
     * 插入新角色并可以返回插入成功的主键
     *
     * @param roleEntity
     * @return
     */
    @Override
    public int insertRoleBackId(RoleEntity roleEntity) {
        return roleEntityMapper.insertUseGeneratedKeys(roleEntity);
    }

    /**
     * 更新角色
     *
     * @param roleEntity
     * @return
     */
    @Override
    public int updateRole(RoleEntity roleEntity) {
        return roleEntityMapper.updateByPrimaryKeySelective(roleEntity);
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @Override
    public int delete(Integer roleId) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(roleId);
        return roleEntityMapper.deleteByPrimaryKey(roleEntity);
    }
}
