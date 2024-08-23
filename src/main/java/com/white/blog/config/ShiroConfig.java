package com.white.blog.config;

import com.white.blog.filter.LoginFilter;
import com.white.blog.realm.UserRealm;
import com.white.blog.utils.RedisUtils;
import jakarta.servlet.Filter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author white_
 * @version 1.0
 * @project HuaJiao-system
 * @description shiro配置类
 * @date 2024/4/17 21:18:55
 */
@Configuration
public class ShiroConfig {


    @Autowired
    private UserRealm UserRealm;

    @Bean
    public RedisUtils redisUtils() {
        return new RedisUtils();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 添加自定义的过滤器
        Map<String, Filter> filtersMap = new HashMap<>();
        filtersMap.put("login", new LoginFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        // 编写过滤规则
        Map<String, String> filterRuleMap = new LinkedHashMap<>();
        // 访问登录登出获取验证码接口时放行
        filterRuleMap.put("/user/logout", "anon");
        filterRuleMap.put("/user/register", "anon");
        filterRuleMap.put("/user/getCode", "anon");
        filterRuleMap.put("/user/login", "anon");
        // 访问静态资源放行
        filterRuleMap.put("/resource/**", "anon");
        filterRuleMap.put("/errorAssets/**", "anon");
        // 访问 Swagger3 资源时放行
        filterRuleMap.put("/swagger-ui/**", "anon");
        filterRuleMap.put("/swagger-ui.html", "anon");
        filterRuleMap.put("/swagger-resources/**", "anon");
        filterRuleMap.put("/v3/api-docs/**", "anon");
        filterRuleMap.put("/webjars/**", "anon");
        // 访问 unauthorized 时直接放行
        filterRuleMap.put("/unauthorized/**", "anon");
        filterRuleMap.put("/unauthorized", "anon");
        // 访问无需登录接口放行
        filterRuleMap.put("/test", "anon");
        filterRuleMap.put("/getDailyRecommendation", "anon");
        filterRuleMap.put("/getAuthorRecommended", "anon");
        filterRuleMap.put("/getCarousel", "anon");
        filterRuleMap.put("/getRelatedLink", "anon");
        filterRuleMap.put("/getHomePageNotice", "anon");
        filterRuleMap.put("/getIPAddress", "anon");
        filterRuleMap.put("/user/getUserData", "anon");
        filterRuleMap.put("/blog/getUserArticle", "anon");
        filterRuleMap.put("/blog/searchArticle", "anon");
        filterRuleMap.put("/blog/getArticleTag", "anon");
        filterRuleMap.put("/blog/getArticle", "anon");
        filterRuleMap.put("/blog/getArticleDetail", "anon");
        filterRuleMap.put("/leaveMessage/getLeaveMessage", "anon");
        // 访问需要登录接口拦截验证
        // 其他接口通过 login Filter
        filterRuleMap.put("/**", "login");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);

        // 关闭 ShiroDAO 功能
        DefaultSubjectDAO defaultSubjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        // 不需要将 Shiro Session 中的东西存到任何地方（包括 Http Session 中）
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        defaultSubjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
        defaultWebSecurityManager.setSubjectDAO(defaultSubjectDAO);
        return defaultWebSecurityManager;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    //开启对shiro注解的支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(defaultWebSecurityManager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
