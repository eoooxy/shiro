package com.blogx.mapper;

import com.blogx.entity.MenuEntity;
import com.blogx.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuEntityMapper extends MyMapper<MenuEntity> {
    List<MenuEntity> selectMenusByUserId(Integer userId);
}