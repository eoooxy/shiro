package com.blogx.service.impl;

import com.blogx.constant.GlobalConstant;
import com.blogx.entity.UserRoleEntity;
import com.blogx.mapper.UserEntityMapper;
import com.blogx.entity.UserEntity;
import com.blogx.mapper.UserRoleEntityMapper;
import com.blogx.service.UserService;
import com.blogx.utils.Md5Utils;
import com.blogx.utils.PageUtils;
import com.blogx.vo.UserVo;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.debugger.Page;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-08-29 上午9:39
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserEntityMapper userEntityMapper;

    /**
     * 根据用户名得到用户
     *
     * @param userName
     * @return
     */
    @Override
    public UserEntity selectByUserName(String userName) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userName);
        return userEntityMapper.selectOne(userEntity);
    }

    /**
     * 根据id得到用户
     *
     * @param userId
     * @return
     */
    @Override
    public UserEntity selectByUserId(Integer userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        return userEntityMapper.selectByPrimaryKey(userEntity);
    }

    /**
     * 查找所有有效的用户
     *
     * @param pageUtils
     * @return
     */
    @Override
    public List<UserVo> selectUsers(PageUtils pageUtils) {
        PageHelper.startPage(pageUtils.getPageNum(), pageUtils.getPageSize());
//        不使用这方法
//        Example example = new Example(UserEntity.class);
//        example.createCriteria().andEqualTo("status", 1);
//        return userEntityMapper.selectByExample(example);
        return userEntityMapper.selectUsers();
    }

    /**
     * 插入user 且可以返回当前插入条的主键
     *
     * @param userEntity
     * @return
     */
    @Override
    public int insertUserBackId(UserEntity userEntity) {
        if (StringUtils.isBlank(userEntity.getPassword())) {
            userEntity.setPassword(Md5Utils.md5Encode(GlobalConstant.DEFAULT_PWD, GlobalConstant.SALT));
        } else {
            userEntity.setPassword(Md5Utils.md5Encode(userEntity.getPassword(), GlobalConstant.SALT));
        }
        return userEntityMapper.insertUseGeneratedKeys(userEntity);
    }

    /**
     * 更新用户信息
     *
     * @param userEntity
     * @return
     */
    @Override
    public int updateUser(UserEntity userEntity) {
        userEntity.setPassword(Md5Utils.md5Encode(userEntity.getPassword(), GlobalConstant.SALT));
        return userEntityMapper.updateByPrimaryKeySelective(userEntity);
    }

    /**
     * 软删除
     *
     * @param userId
     * @return
     */
    @Override
    public int softDelete(Integer userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setStatus(GlobalConstant.DISABLE);
        return userEntityMapper.updateByPrimaryKeySelective(userEntity);
    }

    /**
     * 用户模糊查找
     *
     * @param pageUtils
     * @param userName
     * @return
     */
    @Override
    public List<UserEntity> seachByUserName(PageUtils pageUtils, String userName) {
        PageHelper.startPage(pageUtils.getPageNum(), pageUtils.getPageSize());
        Example example = new Example(UserEntity.class);
        example.createCriteria().andLike("username", "%" + userName + "%");
        return userEntityMapper.selectByExample(example);
    }
}
