package com.blogx.service;

import com.blogx.entity.MenuEntity;

import java.util.List;
import java.util.Set;

/**
 * author： xueyuan
 * date  ： 2017-08-29 上午11:21
 */
public interface MenuService {

    Set<String> selectPermissionMarkByUserId(Integer userId);

//    List<MenuEntity> selectMenus();
}
