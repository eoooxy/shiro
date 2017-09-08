package com.blogx.vo;

import java.io.Serializable;

/**
 * author： xueyuan
 * date  ： 2017-09-08 下午3:49
 */
public class MenuVo implements Serializable {

    private Integer menuId;
    private String menuName;
    private Integer parentId;
    private String url;
    private Integer type;
    private String permissibleMark;
    private String icon;
    private Integer orderNum;


    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPermissibleMark() {
        return permissibleMark;
    }

    public void setPermissibleMark(String permissibleMark) {
        this.permissibleMark = permissibleMark;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
