package com.xiaobitipao.sample.spring4.config;

import java.io.IOException;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * EnableWebMvc：启用MVC
 */
@Configuration
@EnableWebMvc
@ComponentScan({ "com.xiaobitipao.sample.spring4" })
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * <pre>
     * 使用Apache的Tiles视图定义布局。 
     * TilesConfigurer bean，负责定位和加载Tile定义并协调生成Tiles。
     * </pre>
     * 
     * @return TilesConfigurer
     */
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tiles = new TilesConfigurer();
        tiles.setDefinitions("/WEB-INF/layout/tiles.xml");
        // 开发环境可以启用刷新功能
        // tiles.setCheckRefresh(true);
        return tiles;
    }

    /**
     * <pre>
     * 使用Apache的Tiles定义视图解析器代替JSP视图解析器。 
     * TilesViewResolver，负责将逻辑视图名解析为Tile定义。
     * </pre>
     * 
     * @return TilesViewResolver
     */
    @Bean
    public ViewResolver viewResolver() {

        // 配置JSP视图解析器
        // 由于使用Apache的Tiles代替，所以这里不使用JSP视图解析器
        // InternalResourceViewResolver viewResolver = new
        // InternalResourceViewResolver();
        // viewResolver.setPrefix("/WEB-INF/views/");
        // viewResolver.setSuffix(".jsp");

        // 使用Apache的Tiles不需要配置prefix和suffix，因为在tiles.xml中已经配置过
        TilesViewResolver viewResolver = new TilesViewResolver();
        return viewResolver;
    }

    /**
     * <pre>
     * 配置静态资源的处理。 
     * 通过调用DefaultServletHandlerConfigurer的enable方法，
     * 要求DispatcherServlet将对静态资源的请求转发到Servlet容器中默认的Sevlet上，
     * 而不是使用DispatcherServlet本身来处理此类请求。
     * </pre>
     */
    // @Override
    // public void
    // configureDefaultServletHandling(DefaultServletHandlerConfigurer
    // configurer) {
    // configurer.enable();
    // }

    /**
     * <pre>
     * 配置静态资源的处理。 
     * 当调用[/resources/**]下的资源时，转向[/resources/]目录
     * </pre>
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public MessageSource messageSource() {

        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * 给validator校验提供Resource
     * 
     * @return MessageSource
     */
    @Bean
    public MessageSource validatemessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("validation/ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * 定义validator校验（引用validatemessageSource()）
     */
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setProviderClass(HibernateValidator.class);
        validator.setValidationMessageSource(validatemessageSource());
        return validator;
    }

    /**
     * 定义文件上传功能的MultipartResolver。
     * 
     * @return StandardServletMultipartResolver
     * @throws IOException
     */
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        return new StandardServletMultipartResolver();
    }

    // @Bean
    // public MultipartConfigElement multipartConfigElement() {
    // MultipartConfigElement multipartConfigElement = new
    // MultipartConfigElement(env.getRequiredProperty("dev.multipart.location"),
    // env.getRequiredProperty("dev.multipart.maxFileSize", Integer.class),
    // env.getRequiredProperty("dev.multipart.maxRequestSize", Integer.class),
    // env.getRequiredProperty("dev.multipart.fileSizeThreshold",
    // Integer.class));
    // return multipartConfigElement;
    // }
}
