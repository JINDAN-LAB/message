package com.jindan.jdy.common.shiro;

import com.jindan.jdy.service.user.JdyUserService;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    @Autowired
    JdyUserService jdUserService;


    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        System.out.println("开启了shiro注解功能1111");
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        System.out.println("开启了shiro注解功能");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("http://127.0.0.1:20201/jdy-admin/jdyUser/login");
//        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("http://www.souhu.com");
//        // 异常的跳转
//        shiroFilterFactoryBean.setUnauthorizedUrl("http://www.baidu.com");
        // 权限控制map
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        //用户，需要角色权限 “user”
//        filterChainDefinitionMap.put("/pservice/jd-facility/**", "roles[kong]");
////       管理员才能访问
//        filterChainDefinitionMap.put("/pservice/jd-purchase/*","roles[admin]");
//        有编辑权限的才能访问
//        filterChainDefinitionMap.put("/jdyRole/**","authc");
        filterChainDefinitionMap.put("/**/**", "anon");
//        filterChainDefinitionMap.put("//swagger-ui.html", "anon");
//        filterChainDefinitionMap.put("//swagger-resources", "anon");
//        filterChainDefinitionMap.put("//swagger-resources/configuration/security", "anon");
//        filterChainDefinitionMap.put("//swagger-resources/configuration/ui", "anon");
//        filterChainDefinitionMap.put("//v2/api-docs", "anon");
//        filterChainDefinitionMap.put("//webjars/springfox-swagger-ui/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    //将自己的验证方式加入容器
    @Bean
    public MyShiroRealm myShiroRealm() {
        return new MyShiroRealm(jdUserService);
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("myShiroRealm")MyShiroRealm myRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        return manager;
    }

//    @Bean(name = "securityManager")
//    public SecurityManager securityManager(@Qualifier("myRealm")MyShiroRealm myRealm) {
//        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
//        manager.setRealm(myRealm);
//        return manager;
//    }

    //权限管理，配置主要是Realm的管理认证
//    @Bean("securityManager")
//    public SecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setSessionManager(sessionManager());
//        securityManager.setRealm(myShiroRealm());
//        return securityManager;
//    }

    @Bean
    public SessionManager sessionManager(){
        CustomSessionManager  customSessionManager =new CustomSessionManager();
        customSessionManager.setGlobalSessionTimeout(3600000);
        return customSessionManager;
    }

}


//        filterChainDefinitionMap.put("/css/**", "anon"); //表示可以匿名访问
//        filterChainDefinitionMap.put("/js/**", "anon"); //表示可以匿名访问
//        filterChainDefinitionMap.put("/img/**", "anon"); //表示可以匿名访问
//        filterChainDefinitionMap.put("/font/**", "anon"); //表示可以匿名访问
//        filterChainDefinitionMap.put("/images/**", "anon"); //表示可以匿名访问
//        filterChainDefinitionMap.put("/login/**", "anon"); //表示可以匿名访问
//        filterChainDefinitionMap.put("/user_login", "anon");
//        filterChainDefinitionMap.put("/logout*","logout");
//        filterChainDefinitionMap.put("/error","anon");
//        filterChainDefinitionMap.put("/**","anon");
//        filterChainDefinitionMap.put("/pservice/jd-purchase/**","authc");