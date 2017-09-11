package com.blogx.controller;

import com.blogx.constant.GlobalConstant;
import com.blogx.constant.ResConstant;
import com.blogx.entity.MenuEntity;
import com.blogx.service.MenuService;
import com.blogx.utils.ConvertUtils;
import com.blogx.utils.PageUtils;
import com.blogx.utils.ResUtils;
import com.blogx.vo.MenuVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author： xueyuan
 * date  ： 2017-09-08 下午3:48
 */
@RestController
@RequestMapping(value = "/menu")
@Api(tags = "menu", description = "菜单模块")
public class MenuController extends BaseController {

    @Autowired
    MenuService menuService;

    @PostMapping(value = "/list")
    @ApiOperation("查看所有菜单信息")
//    @RequiresPermissions("menu:list")
    public String list(@ApiParam("页码")  PageUtils pageUtils) {
        List<MenuEntity> menuEntities = menuService.selectMenus(pageUtils);
        PageInfo pageInfo = new PageInfo(menuEntities);
        return ResUtils.ok(pageInfo);
    }

    @PostMapping(value = "/init")
    @ApiOperation("当前用户的菜单信息")
    public String init(@ApiParam("当前用户的菜单信息")  Integer userId) {
        List<MenuEntity> menuEntities = menuService.selectMenusByUserId(userId);
        return ResUtils.ok(menuEntities);
    }

    @PostMapping(value = "/add")
    @ApiOperation("新增菜单资源")
//    @RequiresPermissions("menu:add")
    public String add(@ApiParam("菜单信息")  MenuVo menuVo) {
        if (StringUtils.isBlank(menuVo.getMenuName())) {
            return ResUtils.other(ResConstant.PARAMS_NOT_NULL, "用户名为必填且不能为空!");
        }
        if (menuVo.getParentId() == null || menuVo.getParentId() < 0) {
            return ResUtils.other(ResConstant.PARAMS_NOT_NULL, "父菜单必选！");
        }
        if (menuVo.getType() == null) {
            return ResUtils.other(ResConstant.PARAMS_NOT_NULL, "添加资源类别为必填且不能为空!");
        }
        //权限标识，只有为目录的时候，可以没有，其他的时候必填！
        if (menuVo.getType() != GlobalConstant.menuType.List.getCode() && StringUtils.isBlank(menuVo.getPermissibleMark())) {
            return ResUtils.other(ResConstant.PARAMS_NOT_NULL, "权限标识为必填且不能为空!");
        }
        MenuEntity menuEntity = (MenuEntity) ConvertUtils.convertDtoAndVo(menuVo, MenuEntity.class);
        if (menuService.insertMenuBackId(menuEntity) > 0) {
            return ResUtils.ok();
        }
        return ResUtils.err();
    }

    @PostMapping(value = "/update")
    @ApiOperation("更改菜单信息")
//    @RequiresPermissions("menu:update")
    public String update(@ApiParam("菜单信息")  MenuVo menuVo) {
        if (menuVo.getMenuId() == null || menuVo.getMenuId() < 1) {
            return ResUtils.other(ResConstant.PARAMS_NOT_NULL, "被更改的菜单Id必须不能为空且大于0");
        }
        MenuEntity menuEntity = (MenuEntity) ConvertUtils.convertDtoAndVo(menuVo, MenuEntity.class);
        if (menuService.updateMenu(menuEntity) > 0) {
            return ResUtils.ok();
        }
        return ResUtils.err();
    }

//    删除功能，涉及的太多，暂不支持
//
//    @PostMapping(value = "/delete")
//    @ApiOperation("删除菜单")
////    @RequiresPermissions("menu:delete")
//    public String delete(@ApiParam("需要删除的菜单主键Id") @NotNull @RequestParam Integer menuId) {
//        if (menuId < 1) {
//            return ResUtils.other(ResConstant.PARAMS_NOT_NULL, "被删除的菜单Id不能为空且大于0");
//        }
//
//        //删除功能慎用，涉及到子菜单以及按钮相关
//        if (menuService.delete(menuId) > 0) {
//            return ResUtils.ok();
//        }
//        return ResUtils.err();
//    }

}
