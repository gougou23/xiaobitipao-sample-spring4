package com.xiaobitipao.sample.spring4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <pre>
 * 注解@EnableWebSecurity可以启用任意Web应用的安全性功能。
 * Spring Security必须配置在一个实现了WebSecurityConfigure的bean中，
 * 或者（简单起见）扩展WebSecurityConfigurerAdapter。
 * 这里选择扩展WebSecurityConfigurerAdapter。
 * 
 * 通过重载WebSecurityConfigurerAdapter的三个configure方法来配置Web安全，
 * 这个过程中会使用传递的参数设置行为。
 * 
 * ※该类要import到RootConfig中去，而不是WebConfig中※
 * </pre>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // @Autowired
    // private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 1.通过调用formLogin()方法设置login页面信息
        // 2.通过调用logout()方法设置logout页面信息
        // 3.通过调用authorizeRequests()方法对URL进行请求级别的安全性细节配置
        // - authenticated()要求在执行该请求时，必须已经登录了应用
        // - 如果没有认证的话，Spring Security的Filter将会捕获该请求，并将用户重定向到应用的登录页面。
        // - permitAll()允许没有任何安全限制的访问。
        http.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
            .and().logout().logoutSuccessUrl("/")
//            .and().httpBasic().realmName("Xiaobitipao")
//            .and().rememberMe().tokenRepository(new InMemoryTokenRepositoryImpl()).tokenValiditySeconds(259200).key("xiaobitipaoKey")
         // .and().requiresChannel()
            // .antMatchers("/user/register").requiresSecure()
            .and().authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().permitAll();
    }

    /**
     * 配置查询用户信息的服务。
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 基于内存的用户存储
        auth.inMemoryAuthentication().withUser("haha@haha.com").password("p").roles("USER");
        auth.inMemoryAuthentication().withUser("user").password("p").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("p").roles("USER", "ADMIN");

        // 基于数据库表的存储
        // 如果满足Spring默认的权限相关数据库表定义，那么这里什么也不需要作了，
        // 如果不满足的话，需要自己仿照Spring提供的SQL写查询SQL语句并通过usersByUsernameQuery等方法进行设置。
        // 默认SQL语句参考spring-security-core下的：
        // org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl：DEF_USERS_BY_USERNAME_QUERY
        // Schema定义参考：
        // https://docs.spring.io/spring-security/site/docs/4.2.x/reference/html/appendix-schema.html
        // https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
        //
        // 由于数据库中的密码进行了加密转码，这样就有可能导致认证失败，因为与用户提交的名文密码不匹配。
        // 这里需要借助passwordEncoder()指定一个密码转码器(encoder)。
        // passwordEncoder()可以接受Spring Security中PasswordEncoder接口的任意实现。
        // auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new Md5PasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    // private class Md5PasswordEncoder implements PasswordEncoder {
    //
    // @Override
    // public String encode(CharSequence rawPassword) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public boolean matches(CharSequence rawPassword, String encodedPassword)
    // {
    // // TODO Auto-generated method stub
    // return false;
    // }
    // }
}
