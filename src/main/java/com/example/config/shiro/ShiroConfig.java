package com.example.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
//        return new LifecycleBeanPostProcessor();
//    }
//    @Bean
//    @DependsOn({"lifecycleBeanPostProcessor"})
//    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
//        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        advisorAutoProxyCreator.setProxyTargetClass(true);
//        return advisorAutoProxyCreator;
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro的内置过滤器
        /*
            anon:无需认证就可以访问
            authc:必须认证才可以访问
            user:必须拥有记住我功能才能用
            perms:拥有对某个资源的权限才能访问
            roles:拥有某个角色权限才能访问
         */
        //登录请求
        shiroFilterFactoryBean.setLoginUrl("/login");
        //未授权请求
        shiroFilterFactoryBean.setUnauthorizedUrl("/Unauthorized");
        //拦截map，从上到下顺序判断
        Map<String, String> filterMap = new LinkedHashMap<>();
//        shiroFilterFactoryBean.setLoginUrl("/login");
        //放权的url
//        filterMap.put("/user/login","anon");
        filterMap.put("/css/**","anon");
        filterMap.put("/js/**","anon");
        filterMap.put("/images/**","anon");
        filterMap.put("/login","anon");
        filterMap.put("/register","anon");
        filterMap.put("/user/login","anon");
        filterMap.put("/user/register","anon");
        filterMap.put("/user/logout","anon");
//        filterMap.put("/{page}","authc");

        filterMap.put("/club/set/**","roles[ROLE_ADMIN]");
        filterMap.put("/club/all","authc");

//        filterMap.put("/front/**","anon");
//        //授权的url
//        filterMap.put("/end/**","authc");
//        //认证的url
//        filterMap.put("/add","roles[ROLE_ADMIN]");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //DefaultWebSecurityManager
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    //创建 realm 对象
    @Bean
    public MyShiroRealm myShiroRealm(){
        //        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        MyShiroRealm realm = new MyShiroRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    //MD5加密
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("md5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        //是否存储为16进制(一定要开启)
//        将setStoredCredentialsHexEncoded设置为true，则需要使用toHex()进行转换成字符串，默认使用的是toBase64()
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }
}
