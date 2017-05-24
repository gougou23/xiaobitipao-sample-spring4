package com.xiaobitipao.sample.spring4.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.xiaobitipao.sample.spring4.conf.PropertiesConf;

/**
 * 数据源配置。
 * 
 * @author xiaobitipao
 * @since 1.0
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.xiaobitipao.sample.spring4.repository")
public class DBConfig {

    @Autowired
    private PropertiesConf propertiesConf;

    /**
     * 生产环境数据源。
     */
    @Bean(name = "dataSource", destroyMethod = "close")
    public BasicDataSource dataSource() {

        System.out.println(propertiesConf.MULTIPART_LOCATION_REAL);
        
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(propertiesConf.DB_JDBC_DRIVER_CLASSNAME);
        ds.setUrl(propertiesConf.DB_JDBC_URL);
        ds.setUsername(propertiesConf.DB_JDBC_USERNAME);
        ds.setPassword(propertiesConf.DB_JDBC_PASSWORD);
        // 初始连接池连接个数
        ds.setInitialSize(propertiesConf.DB_JDBC_INITIAL_SIZE);
        // 同一时间可从池中分配的最大连接数
        ds.setMaxTotal(propertiesConf.DB_JDBC_MAX_TOTAL);

        return ds;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        transactionManager.setRollbackOnCommitFailure(true);

        return transactionManager;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        // sessionFactoryBean.setConfigLocation(new
        // ClassPathResource("/mybatis/mybatis.xml"));
        sessionFactoryBean.setTypeAliasesPackage("com.xiaobitipao.sample.spring4.model");

        return sessionFactoryBean;
    }
}
