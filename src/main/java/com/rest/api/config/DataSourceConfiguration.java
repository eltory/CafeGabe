package com.rest.api.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * @author lsh
 *
 */
@Configuration
public class DataSourceConfiguration {

	private Environment env;

	@Value("${spring.datasource.url}")
	private String dbUrl;

	/**
	 * 
	 * @return
	 */
	@Bean
	@Profile("postgres")
	public DataSource postgresDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(dbUrl);
		return new HikariDataSource(config);
	}

}
