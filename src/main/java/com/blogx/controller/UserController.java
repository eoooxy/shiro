package com.blogx.controller;

import com.blogx.constant.GlobalConstant;
import com.blogx.constant.ResConstant;
import com.blogx.entity.UserEntity;
import com.blogx.service.UserService;
import com.blogx.utils.*;
import com.blogx.vo.UserVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-09-08 上午9:23
 */

@RestController
@RequestMapping(value = "/user")
@Api(tags = "user", description = "用户模块")
public class UserController extends BaseController{

    @Autowired
    UserService userService;

    @PostMapping(value = "/list")
    @ApiOperation("查看所有用户信息")
//    @RequiresPermissions("user:list")
    public String list(@ApiParam("页码") PageUtils pageUtils) {
        List<UserEntity> userEntities = userService.selectUsers(pageUtils);
        PageInfo pageInfo = new PageInfo(userEntities);
        return ResUtils.ok(pageInfo);
    }

    @PostMapping(value = "/add")
    @ApiOperation("新增用户")
//    @RequiresPermissions("user:add")
    public String add(@ApiParam("用户信息") @NotBlank @RequestBody UserVo userVo) {
        if (StringUtils.isBlank(userVo.getUsername())) {
            return ResUtils.other(ResConstant.PARAMS_NOT_NULL, "用户名为必填且不能为空!");
        }
        if (userService.selectByUserName(userVo.getUsername()) != null) {
            return ResUtils.other(ResConstant.USER_IS_HAVE_CODE, ResConstant.USER_IS_HAVE_MSG);
        }
        UserEntity userEntity = (UserEntity) ConvertUtils.convertDtoAndVo(userVo, UserEntity.class);
        userEntity.setStatus(GlobalConstant.ENABLE);
        userEntity.setCreateTime(DateUtils.formatDate(new Date()));
        if (userService.insertUserBackId(userEntity) > 0) {
            return ResUtils.ok();
        }
        return ResUtils.err();
    }



    @PostMapping(value = "/update")
    @ApiOperation("更改用户信息")
//    @RequiresPermissions("user:update")
    public String update(@ApiParam("用户信息") @NotBlank @RequestBody UserVo userVo) {
        if (userVo.getUserId() == null || userVo.getUserId() < 1) {
            return ResUtils.other(ResConstant.PARAMS_NOT_NULL, "被更改的用户Id必须不能为空且大于0");
        }
        //如果存在新密码与旧密码 则一定是更改密码
        if (StringUtils.isNotBlank(userVo.getNewPassword()) && StringUtils.isNotBlank(userVo.getPassword())) {
            //判断旧密码是否正确
            if ((userService.selectByUserId(userVo.getUserId()).getPassword()
                    .equals(Md5Utils.md5Encode(userVo.getPassword(), GlobalConstant.SALT)))) {
                userVo.setPassword(userVo.getNewPassword());
            } else {
                return ResUtils.err("旧密码输入有误！");
            }
        }
        UserEntity userEntity = (UserEntity) ConvertUtils.convertDtoAndVo(userVo, UserEntity.class);
        userEntity.setUpdateTime(DateUtils.formatDate(new Date()));
        if (userService.updateUser(userEntity) > 0) {
            return ResUtils.ok();
        }
        return ResUtils.err();
    }

    @PostMapping(value = "/delete")
    @ApiOperation("删除用户")
//    @RequiresPermissions("user:delete")
    public String delete(@ApiParam("需要删除的用户主键Id") @NotNull @RequestParam Integer userId) {
        if (userId < 1) {
            return ResUtils.other(ResConstant.PARAMS_NOT_NULL, "被删除的用户Id不能为空且大于0");
        }
        if (userService.softDelete(userId) > 0) {
            return ResUtils.ok();
        }
        return ResUtils.err();
    }

}
