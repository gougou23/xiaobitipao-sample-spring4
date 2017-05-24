package com.xiaobitipao.sample.spring4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * 根配置，并启动组建扫描。
 * 
 * EnableAspectJAutoProxy:启用AspectJ自动代理。该注解会为使用@AspectJ注解的Bean创建一个代理。
 * 
 * @author xiaobitipao
 * @since 1.0
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan({ "com.xiaobitipao.sample.spring4.conf" })
@Import({ DBConfig.class, SecurityConfig.class })
public class RootConfig {

}
