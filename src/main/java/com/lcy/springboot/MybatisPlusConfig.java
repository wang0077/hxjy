package com.lcy.springboot;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.io.IOException;


@Configuration
public class MybatisPlusConfig implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType(environment.getProperty("mybatis.dbType"));// mysql
        return page;
    }


    @Bean
    public DruidDataSource dataSource() {

        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(environment.getProperty("citizencloud.url"));
        datasource.setDriverClassName(environment.getProperty("citizencloud.driverName"));
        datasource.setUsername(environment.getProperty("citizencloud.username"));
        datasource.setPassword(environment.getProperty("citizencloud.password"));
        datasource.setInitialSize(Integer.parseInt(environment.getProperty("citizencloud.initialSize")));
        datasource.setMinIdle(Integer.parseInt(environment.getProperty("citizencloud.minIdle")));
        datasource.setMaxWait(Long.parseLong(environment.getProperty("citizencloud.maxWait")));
        datasource.setTimeBetweenEvictionRunsMillis(Long.parseLong(environment.getProperty("citizencloud.timeBetweenEvictionRunsMillis")));
        datasource.setMinEvictableIdleTimeMillis(Long.parseLong(environment.getProperty("citizencloud.minEvictableIdleTimeMillis")));
        datasource.setMaxActive(Integer.parseInt(environment.getProperty("citizencloud.maxActive")));
        datasource.setRemoveAbandoned(Boolean.parseBoolean(environment.getProperty("citizencloud.removeAbandoned")));
        datasource.setRemoveAbandonedTimeout(Integer.parseInt(environment.getProperty("citizencloud.removeAbandonedTimeout")));
        datasource.setLogAbandoned(Boolean.parseBoolean(environment.getProperty("citizencloud.logAbandoned")));
        return datasource;
    }

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(
            PaginationInterceptor paginationInterceptor,
            DruidDataSource dataSource) {
        MybatisSqlSessionFactoryBean sqlSqlSession = new MybatisSqlSessionFactoryBean();
        sqlSqlSession.setDataSource(dataSource);

//		Resource[] mapperLocations = null;
//
//		try {
//
//			String proFilePath = System.getProperty("user.dir")
//					+ File.separator + "mapperxml";
//			File file = new File(proFilePath);
//
//			if (!file.exists()) {
//				URL url = MybatisPlusConfig.class.getClassLoader().getResource(
//						"");
//				proFilePath = url.getPath() + "mapperxml";
//			}
//			file = new File(proFilePath);
//
//			if (!file.exists()) {
//				System.out.println("初始化mapperxml失败");
//				return null;
//			}
//			mapperLocations = new PathMatchingResourcePatternResolver()
//					.getResources("file:" + proFilePath + "/*.xml");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
        sqlSqlSession.setTypeAliasesPackage(environment
                .getProperty("mybatis.aliasesPackege"));// "com.innochina.platform.linkservice.autogenerator.entity");
        sqlSqlSession.setPlugins(new Interceptor[] { paginationInterceptor });
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSqlSession.setMapperLocations(resolver.getResources(environment.getProperty("mybatis.mapper-locations")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        GlobalConfiguration config = new GlobalConfiguration();
        config.setIdType(2);
        System.out.println("["+environment.getProperty("mybatis.dbType")+"]");
        config.setDbType(environment.getProperty("mybatis.dbType"));// mysql

        sqlSqlSession.setGlobalConfig(config);
        return sqlSqlSession;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfig = new MapperScannerConfigurer();
        scannerConfig.setBasePackage(environment
                .getProperty("mybatis.mapperBasePackage"));// "com.innochina.platform.linkservice.autogenerator.mapper");
        scannerConfig.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return scannerConfig;
    }

    public ResourceBundleMessageSource getMessageSource() throws Exception {
        ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
        rbms.setDefaultEncoding("UTF-8");
        rbms.setBasename(environment
                .getProperty("spring.messages.basename"));
        return rbms;
    }

    @Bean
    public Validator getValidator() throws Exception {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(getMessageSource());
        return validator;
    }
}
