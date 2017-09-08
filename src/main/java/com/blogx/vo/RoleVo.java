package com.blogx.vo;

import java.io.Serializable;

/**
 * author： xueyuan
 * date  ： 2017-09-08 下午3:08
 */
public class RoleVo implements Serializable {

    private Integer roleId;
    private String roleName;
    private String remark;
    private Integer createUerId;
    private String createTime;
    private String reserve;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCreateUerId() {
        return createUerId;
    }

    public void setCreateUerId(Integer createUerId) {
        this.createUerId = createUerId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }
}
