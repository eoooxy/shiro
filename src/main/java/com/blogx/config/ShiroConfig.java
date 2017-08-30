package com.blogx.config;

import com.blogx.permission.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * author： xueyuan
 * date  ： 2017-08-29 上午9:06
 */
@Configuration
public class ShiroConfig {

    @Bean(name = "sessionManager")
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
        sessionManager.setSessionValidationSchedulerEnabled(true);
//        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager(UserRealm userRealm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager);

        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //Shiro的核心安全接口,这个属性是必须的
        shiroFilter.setSecurityManager(securityManager);
        //登录时的链接
        shiroFilter.setLoginUrl("/login");
        //登录成功之后跳转的链接
//        shiroFilter.setSuccessUrl("/index.html");
        //用户访问未授权的资源，所显示的链接地址
        shiroFilter.setUnauthorizedUrl("/unauthor");

        //shiro 过滤器
        Map<String, String> filterMap = new LinkedHashMap<>();

//        filterMap.put("/public/**", "anon");
//        filterMap.put("/webjars/**", "anon");
//        filterMap.put("/api/**", "anon");
//
//        //swagger配置
//        filterMap.put("/swagger**", "anon");
//        filterMap.put("/v2/api-docs", "anon");
//        filterMap.put("/swagger-resources/configuration/ui", "anon");

        // 登录以及验证码
        filterMap.put("/login", "anon");
        filterMap.put("/verifycode", "anon");
        filterMap.put("/verify", "anon");
        filterMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    /**
     * shiro什么周期
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启注解
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}

