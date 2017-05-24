package com.xiaobitipao.sample.spring4.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 读取properties文件映射为Java类。
 * 如果不存在环境变量[spring.profiles.active]的话，使用dev，
 * 如果不存在环境变量[spring.profiles.active]的话，使用该环境变量的值，如prod。
 */
@Component
@PropertySource("classpath:application-${spring.profiles.active:dev}.properties")
public class PropertiesConf {

    @Value("${jdbc.driverClassName}")
    public String DB_JDBC_DRIVER_CLASSNAME;

    @Value("${jdbc.url}")
    public String DB_JDBC_URL;

    @Value("${jdbc.username}")
    public String DB_JDBC_USERNAME;

    @Value("${jdbc.password}")
    public String DB_JDBC_PASSWORD;

    @Value("${jdbc.initialSize}")
    public int DB_JDBC_INITIAL_SIZE;

    @Value("${jdbc.maxTotal}")
    public int DB_JDBC_MAX_TOTAL;

    @Value("${multipart.location}")
    public String MULTIPART_LOCATION;

    @Value("${multipart.location.real}")
    public String MULTIPART_LOCATION_REAL;

    @Value("${multipart.maxFileSize}")
    public long MULTIPART_MAX_FILE_SIZE;

    @Value("${multipart.maxRequestSize}")
    public long MULTIPART_MAX_REQUEST_SIZE;

    @Value("${multipart.fileSizeThreshold}")
    public long MULTIPART_FILE_SIZE_THRESHOLD;
}
