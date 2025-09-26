package kr.ac.kopo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class RootContext {
	
	@Bean
	DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("book");
		dataSource.setPassword("1234");
		
		return dataSource;
	}
	
	@Bean
	SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		
		sessionFactoryBean.setDataSource(dataSource);
		
		//sessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		
		sessionFactoryBean.setTypeAliasesPackage("kr.ac.kopo.bookshop.model");
		sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mapper/*.xml")); //classpath:mapper/*.xml
		
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		
		configuration.setMapUnderscoreToCamelCase(true);
		sessionFactoryBean.setConfigLocation(null);
		
		return sessionFactoryBean.getObject();
	}
	
	@Bean
	SqlSessionTemplate sessionTemplate(SqlSessionFactory sessionFactory) {
		return new SqlSessionTemplate(sessionFactory);
	}
}
