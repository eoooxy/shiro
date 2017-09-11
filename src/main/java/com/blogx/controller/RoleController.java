package com.blogx.controller;

import com.blogx.constant.ResConstant;
import com.blogx.entity.RoleEntity;
import com.blogx.entity.RoleMenuEntity;
import com.blogx.service.RoleMenuService;
import com.blogx.service.RoleService;
import com.blogx.utils.ConvertUtils;
import com.blogx.utils.DateUtils;
import com.blogx.utils.PageUtils;
import com.blogx.utils.ResUtils;
import com.blogx.vo.RoleVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author： xueyuan
 * date  ： 2017-09-08 下午3:10
 */

@RestController
@RequestMapping(value = "/role")
@Api(tags = "role", description = "角色模块")
public class RoleController extends BaseController {

    @Autowired
    RoleService roleService;
    @Autowired
    RoleMenuService roleMenuService;

    @PostMapping(value = "/list")
    @ApiOperation("查看所有角色信息")
//    @RequiresPermissions("role:list")
    public String list(@ApiParam("页码") PageUtils pageUtils) {
        List<RoleEntity> roleEntities = roleService.selectRoles(pageUtils);
        PageInfo pageInfo = new PageInfo(roleEntities);
        return ResUtils.ok(pageInfo);
    }

    @PostMapping(value = "/add")
    @ApiOperation("新增角色")
//    @RequiresPermissions("role:add")
    public String add(@ApiParam("用户信息")   RoleVo roleVo) {
        if (StringUtils.isBlank(roleVo.getRoleName())) {
            return ResUtils.other(ResConstant.PARAMS_NOT_NULL, "角色名称为必填且不能为空!");
        }
        RoleEntity roleEntity = (RoleEntity) ConvertUtils.convertDtoAndVo(roleVo, RoleEntity.class);
        roleEntity.setRoleId(-1);
        roleEntity.setCreateTime(DateUtils.formatDate(new Date()));
        if (roleService.insertRoleBackId(roleEntity) > 0) {
            List<RoleMenuEntity> list = roleVo.getMenuIds().stream().
                    map(id -> createRoleMenu(id, roleVo.getRoleId())).collect(Collectors.toList());
            if (roleMenuService.batchInsertBeachRoleMenu(list) > 0) {
                return ResUtils.ok();
            }
            return ResUtils.err("权限资源插入有误！");
        }
        return ResUtils.err();
    }


    @PostMapping(value = "/update")
    @ApiOperation("更改角色信息")
//    @RequiresPermissions("role:update")
    public String update(@ApiParam("角色信息")   RoleVo roleVo) {
        if (roleVo.getRoleId() == null || roleVo.getRoleId() < 1) {
            return ResUtils.other(ResConstant.PARAMS_NOT_NULL, "被更改的角色Id必须不能为空且大于0");
        }
        RoleEntity roleEntity = (RoleEntity) ConvertUtils.convertDtoAndVo(roleVo, RoleEntity.class);
        //更新角色信息，直接删除原有的菜单资源，在插入新的菜单资源
        if (roleService.updateRole(roleEntity) > 0) {
            roleMenuService.deleteRoleMenuByRoleId(roleVo.getRoleId());
            List<RoleMenuEntity> list = roleVo.getMenuIds().stream().
                    map(id -> createRoleMenu(id, roleVo.getRoleId())).collect(Collectors.toList());
            roleMenuService.batchInsertBeachRoleMenu(list);
            return ResUtils.ok();
        }
        return ResUtils.err();
    }

    @PostMapping(value = "/delete")
    @ApiOperation("删除角色")
//    @RequiresPermissions("role:delete")
    public String delete(@ApiParam("需要删除的用户主键Id")   Integer roleId) {
        if (roleId < 1) {
            return ResUtils.other(ResConstant.PARAMS_NOT_NULL, "被删除的角色Id不能为空且大于0");
        }
        if (roleService.delete(roleId) > 0) {
            return ResUtils.ok();
        }
        return ResUtils.err();
    }

    //构造 RoleMenu实体类
    private RoleMenuEntity createRoleMenu(Integer menuId, Integer roleId) {
        RoleMenuEntity entity = new RoleMenuEntity();
        entity.setMenuId(menuId);
        entity.setRoleId(roleId);
        return entity;
    }
}
