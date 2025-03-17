package com.springboot.jdbc.Config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySource("classpath:application.properties")
public class JdbcConfiguration 
{
	@Bean(name="springdatasource")
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource datasource()
	{
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="springjdbc")
	public JdbcTemplate getJdbcTemplate(@Qualifier("springdatasource") DataSource datasource)
	{
		try 
		{
			return new JdbcTemplate(datasource);
		} 
		catch (IllegalArgumentException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}

