package com.blogx.entity;

import javax.persistence.*;

@Table(name = "shiro_role")
public class RoleEntity {
    /**
     * 角色id
     */
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    private String remark;

    @Column(name = "create_uer_id")
    private Integer createUerId;

    @Column(name = "create_time")
    private String createTime;

    /**
     * 预留字段
     */
    private String reserve;

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return create_uer_id
     */
    public Integer getCreateUerId() {
        return createUerId;
    }

    /**
     * @param createUerId
     */
    public void setCreateUerId(Integer createUerId) {
        this.createUerId = createUerId;
    }

    /**
     * @return create_time
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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