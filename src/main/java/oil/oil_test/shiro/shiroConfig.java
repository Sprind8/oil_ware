package oil.oil_test.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class shiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加shiro的内置过滤器,可替代之前的登录过滤器
        /**
         * anon:无需认证（登录）可以访问
         * authc:必须认证才可以访问
         * user:如果使用rememberMe的功能才可以访问
         * perms:该资源必须得到资源权限才可以访问
         * role:该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String, String>();
        //认证拦截器,注意authc必须在后面，否则anon不生效
        filterMap.put("/preLogin","anon");
        filterMap.put("/userLogin","anon");
        
        //授权过滤器
        //被授权拦截后，shiro会自动跳转到位授权页面
        filterMap.put("/groupCheck","perms[102]");
        filterMap.put("/houseCheck","perms[103]");

        //修改认证拦截的跳转页面
        shiroFilterFactoryBean.setLoginUrl("/preLogin");
        filterMap.put("/*","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;


    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    /**
     * 创建Realm,这个类自定义，查询权限等等信息
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm()
    {
         return new UserRealm();
    }
}
