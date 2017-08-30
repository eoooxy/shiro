package com.blogx.entity;

import javax.persistence.*;

@Table(name = "shiro_menu")
public class MenuEntity {
    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuId;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "parent_id")
    private Integer parentId;

    private String url;

    private String type;

    @Column(name = "permissible_mark")
    private String permissibleMark;

    private String icon;

    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 预留字段
     */
    private String reserve;

    /**
     * @return menu_id
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * @param menuId
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * @return menu_name
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return permissible_mark
     */
    public String getPermissibleMark() {
        return permissibleMark;
    }

    /**
     * @param permissibleMark
     */
    public void setPermissibleMark(String permissibleMark) {
        this.permissibleMark = permissibleMark;
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return order_num
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * @param orderNum
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取预留字段
     *
     * @return reserve - 预留字段
     */
    public String getReserve() {
        return reserve;
    }

    /**
     * 设置预留字段
     *
     * @param reserve 预留字段
     */
    public void setReserve(String reserve) {
        this.reserve = reserve;
    }
}