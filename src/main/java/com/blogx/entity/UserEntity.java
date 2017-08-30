package com.blogx.entity;

import javax.persistence.*;

@Table(name = "shiro_user")
public class UserEntity {
    /**
     * 主键id
     */
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 预留联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 当前账户是否有效
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private String updateTime;

    /**
     * 预留字段
     */
    private String reserve;

    /**
     * 获取主键id
     *
     * @return user_id - 主键id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置主键id
     *
     * @param userId 主键id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取账号
     *
     * @return username - 账号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置账号
     *
     * @param username 账号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取预留联系电话
     *
     * @return phone - 预留联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置预留联系电话
     *
     * @param phone 预留联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取当前账户是否有效
     *
     * @return status - 当前账户是否有效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置当前账户是否有效
     *
     * @param status 当前账户是否有效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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