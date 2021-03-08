package com.tp.cozubu.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @apiNote cozubu 데이터 베이스 설정
 * @author seungglee
 *
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.tp.cozubu.model.dao", sqlSessionFactoryRef = "cozubuSqlSessionFactory")
public class CozubuDataSourceConfig extends HikariConfig {
    
    @Bean(name = "cozubuHikariDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.cozubu")
    public DataSource dataSourceCreator() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name="cozubuDataSource")
    public DataSource dataSource(@Qualifier("cozubuHikariDataSource") DataSource dataSource) {
        return new LazyConnectionDataSourceProxy(dataSource);
    }

    @Bean(name = "cozubuSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("cozubuDataSource") DataSource dataSource,
            ApplicationContext context) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(context.getResources("classpath:mybatis/mapper/*.xml"));
        sqlSessionFactory.setConfigLocation(context.getResource("classpath:mybatis/Mybatis-config.xml"));
        // jar or war 파일로 배포할때 typealias 오류 문제 해결
        sqlSessionFactory.setVfs(SpringBootVFS.class);
        return sqlSessionFactory.getObject();
    }
    
    @Bean(name = "cozubuSqlSession")
    public SqlSessionTemplate sqlSession(@Qualifier("cozubuSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    @Bean(name = "cozubuTransactionManager")
    public PlatformTransactionManager cozubuTransactionManager(@Autowired @Qualifier("cozubuDataSource") DataSource secondaryDataSource) {
        return new DataSourceTransactionManager(secondaryDataSource);
    }
    
}
