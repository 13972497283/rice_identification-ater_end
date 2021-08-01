package com.tenglong.config.shiro;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.HashMap;
import java.util.Map;
@Configuration
public class ShiroConfig {

    private final static String LOGIN_URL = "http://localhost:8081/";

    //实现拦截器
    //@Bean注解注释在方法上，将返回的实体类交给容器处理，id是方法名
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置拦截成功以后，访问的页面
        shiroFilterFactoryBean.setLoginUrl(LOGIN_URL);
        //拦截器Map的创建
        Map<String, String> map = new HashMap<String, String>();
        //针对不同的url，使用不同的拦截器，放到map中，即可生效，完成拦截
        //拦截所有请求
        map.put("/*", "authc");
//        //放行登陆请求
//        map.put("/", "anon");
        //放行登录认证请求
        map.put("/user/login", "anon");
        map.put("/user/register", "anon");
        //设置相关的map集合，完成拦截功能的收集
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(lcleRealm());
        return securityManager;
    }

    //实现Realm的加载
    @Bean
    public LcleRealm lcleRealm() {
        LcleRealm lcleRealm = new LcleRealm();
        lcleRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return lcleRealm;
    }


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

}