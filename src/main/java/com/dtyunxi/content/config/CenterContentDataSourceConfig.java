/**
 * @(#)RootContextConfig.java 1.0 2016年7月27日
 * <p>
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi.content.config;
import com.alibaba.druid.pool.DruidDataSource;
import com.dtyunxi.ds.BaseMapper;
import com.dtyunxi.huieryun.registry.api.IRegistryService;
import com.dtyunxi.huieryun.registry.vo.DataSourceVo;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.dtyunxi.newretail.center.content.mapper"}, sqlSessionFactoryRef = "centerContentSqlSessionFactoryBean")
public class CenterContentDataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(CenterContentDataSourceConfig.class);
    public static final String TRANSACTION_MANAGER = "centerContentDataSourceTransactionManager";

    @Autowired
    private IRegistryService centerContentRegistryService;
    
   
    @Bean(name = "centerContentDataSource", destroyMethod = "close")
    @Primary
    public DataSource centerContentDataSource() throws SQLException {
    	
    
    	
        DataSourceVo dataSourceVo = centerContentRegistryService.getObject("huieryun.newretail.service.content.datasourcevo", DataSourceVo.class);
        logger.debug("Set data source as {}", dataSourceVo);

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dataSourceVo.getJdbcUrl());
        dataSource.setUsername(dataSourceVo.getJdbcUserName());
        dataSource.setPassword(dataSourceVo.getJdbcUserPassword());
        dataSource.setInitialSize(dataSourceVo.getInitialSize()); // 初始化连接大小
        dataSource.setMaxActive(dataSourceVo.getMaxActive()); // 连接池最大使用连接数量
        dataSource.setMinIdle(dataSourceVo.getMinIdle()); // 连接池最小空闲
        dataSource.setMaxWait(dataSourceVo.getMaxWait()); // 获取连接最大等待时间
        dataSource.setValidationQuery(dataSourceVo.getValidationQuery());
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(60000); // 间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(25200000); // 一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(1800); // 1800秒，也就是30分钟
        dataSource.setLogAbandoned(true); // 关闭abanded连接时输出错误日志
        dataSource.setFilters("mergeStat"); // 监控数据库

        String[] sqls = {"set names utf8mb4;"};
        dataSource.setConnectionInitSqls(Arrays.asList(sqls));

        dataSource.init();

        return dataSource;
    }

    @Bean(name = "centerContentSqlSessionFactoryBean")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier(value = "centerContentDataSource") DataSource dataSource) throws Exception {
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setMapUnderscoreToCamelCase(true);
        config.setLogImpl(StdOutImpl.class);

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfiguration(config);

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "false");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加分页插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager centerContentDataSourceTransactionManager(@Qualifier(value = "centerContentDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
