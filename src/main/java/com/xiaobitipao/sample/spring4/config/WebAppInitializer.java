package com.xiaobitipao.sample.spring4.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * <pre>
     * 映射一个或多个路径到DispatcherServlet上。
     * </pre>
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    /**
     * getRootConfigClasses方法返回的带有@Configuration注解的类将会用来配置ContextLoaderListener创建的应用上下文中的bean。
     * ContextLoaderListener用于加载应用中的其他bean。
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    /**
     * getServletConfigClasses方法返回的带有@Configuration注解的类将会用来定义DispatcherServlet应用上下文中的bean。
     * DispatcherServlet加载包含Web组件的bean，包含控制器，视图解析器以及处理器映射。
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    /**
     * <pre>
     * 设置过滤器，且该过滤器只会映射到DispatcherServlet上。
     * 如果想映射到其他URL路径的话，需要重写onStartUp方法，并在其中进行过滤器的定义和注册。
     * </pre>
     */
    @Override
    protected Filter[] getServletFilters() {

        // request，response编码过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return new Filter[] { characterEncodingFilter };
    }

    /**
     * <pre>
     * 在AbstractAnnotationConfigDispatcherServletInitializer将DispatcherServlet注册到Servlet容器之后，
     * 就会调用customizeRegistration(),并将Servlet注册后得到的Registration.Dynamic传递近来，
     * 通过重载cunstomizeRegistration()方法，可以对DispatcherServlet进行额外的配置。
     * </pre>
     */
    @Override
    protected void customizeRegistration(Dynamic registration) {

        // 配置对multipart请求的支持，使Spring MVC可以处理multipart请求和文件上传
        String location = "C:\\tmp\\upload\\tmp";
        System.out.println(location);
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location, 2 * 1024 * 1024, 4 * 1024 * 1024, 0);
        registration.setMultipartConfig(multipartConfigElement);
    }
}
