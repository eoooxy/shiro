package com.blogx.utils;

import com.blogx.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * author： xueyuan
 * date  ： 2017-08-29 下午1:39
 */
public class ShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static UserEntity getUserEntity() {
        return (UserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    public static Integer getUserId() {
        return getUserEntity().getUserId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }
}
