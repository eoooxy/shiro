package com.blogx.service.impl;

import com.blogx.entity.MenuEntity;
import com.blogx.mapper.MenuEntityMapper;
import com.blogx.service.MenuService;
import com.blogx.utils.PageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * author： xueyuan
 * date  ： 2017-08-29 上午11:21
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuEntityMapper menuEntityMapper;


    /**
     * 通过用户id 得到当前用户所有的权限信息
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> selectPermissionMarkByUserId(Integer userId) {
        List<MenuEntity> list = new ArrayList<>();
//        约定 shiro_user 表中user_id=1 为管理员即拥有所有权限
        if (1 == userId) {
            list = menuEntityMapper.selectAll();
        } else {
            list = menuEntityMapper.selectMenusByUserId(userId);
        }
        Set<String> sets = new HashSet<>();
        for (MenuEntity entity : list) {
            if (StringUtils.isBlank(entity.getPermissibleMark())) {
                continue;
            }
            sets.addAll(Arrays.asList(entity.getPermissibleMark().trim().split(",")));
        }
        return sets;
    }

    @Override
    public List<MenuEntity> selectMenus(PageUtils pageUtils) {
        Example example = new Example(MenuEntity.class);
        example.setOrderByClause("parent_id asc,order_num asc");
        return menuEntityMapper.selectByExample(example);
    }

    @Override
    public int insertMenuBackId(MenuEntity menuEntity) {
        return menuEntityMapper.insertUseGeneratedKeys(menuEntity);
    }

    @Override
    public int updateMenu(MenuEntity menuEntity) {
        return menuEntityMapper.updateByPrimaryKeySelective(menuEntity);
    }

    @Override
    public int delete(Integer menuId) {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setMenuId(menuId);
        return menuEntityMapper.delete(menuEntity);
    }
}
