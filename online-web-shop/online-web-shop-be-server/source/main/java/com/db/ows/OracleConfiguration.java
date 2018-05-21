package com.db.ows;

import javax.naming.NamingException;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class OracleConfiguration {
	
	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate()
			throws IllegalArgumentException, NamingException {
		return new NamedParameterJdbcTemplate(dataSource());
	}

	@Bean
	public JdbcTemplate normalJdbcTemplate()
			throws IllegalArgumentException, NamingException {
		return new JdbcTemplate(dataSource());
	}

	
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public javax.sql.DataSource dataSource() throws IllegalArgumentException,
			NamingException {
		/*JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
		dataSource.setJndiName(jndiName);
		dataSource.setExposeAccessContext(true);
		Properties environment = new Properties();
		environment.put("java.naming.security.principal", jdbcUserName);
		environment.put("java.naming.security.credentials", jdbcUserPassword);
		dataSource.setJndiEnvironment(environment);
		dataSource.afterPropertiesSet();
		return (DataSource) dataSource.getObject();*/
		
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.url(dbUrl);
//        dataSourceBuilder.username(username);
//        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();   
	}
	
	
}
