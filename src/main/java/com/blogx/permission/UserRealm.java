package com.blogx.permission;

import com.blogx.entity.UserEntity;
import com.blogx.service.MenuService;
import com.blogx.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * author： xueyuan
 * date  ： 2017-08-29 上午9:19
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;

    /**
     * 授权
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        UserEntity userEntity = (UserEntity) principal.getPrimaryPrincipal();
        Integer id = userEntity.getUserId();
        Set<String> set = menuService.selectPermissionMarkByUserId(id);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(set);

        return authorizationInfo;
    }

    /**
     * 认证用户是否正确
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        // 得到账号密码
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        UserEntity user = userService.selectByUserName(username);
        //账号密码错误
        if (null == user) {
            throw new UnknownAccountException("账号或密码错误");
        }
        if (!password.endsWith(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        //账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
