package com.blogx.service.impl;

import com.blogx.entity.MenuEntity;
import com.blogx.mapper.MenuEntityMapper;
import com.blogx.service.MenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 通过userId 得到当前用户所有的权限信息
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
}
