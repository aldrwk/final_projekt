package com.spring.final_project.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

public static final String MASTER_DATASOURCE = "masterDataSource";
	public static final String SLAVE_DATASOURCE = "slaveDataSource";

	@Bean(MASTER_DATASOURCE)
	@ConfigurationProperties(prefix = "spring.datasource.master")
	public DataSource masterDataSource(){
		return DataSourceBuilder.create().build();
//				getDataSource(defaultDriverClassName, defaultJdbcUrl, defaultJdbcUserName, defaultJdbcPassword);
	}
	@Bean(SLAVE_DATASOURCE)
	@ConfigurationProperties(prefix = "spring.datasource.slave")
	public DataSource slaveDataSource(){
		return DataSourceBuilder.create().build();
	}

	@Bean
	@DependsOn({MASTER_DATASOURCE, SLAVE_DATASOURCE})
	public DataSource routingDataSource(
			@Qualifier(MASTER_DATASOURCE) DataSource masterDataSource,
			@Qualifier(SLAVE_DATASOURCE) DataSource slaveDataSource) {

		RoutingDataSource routingDataSource = new RoutingDataSource();

		Map<Object, Object> datasourceMap = new HashMap<>() {
			{
				put("master", masterDataSource);
				put("slave", slaveDataSource);
			}
		};

		routingDataSource.setTargetDataSources(datasourceMap);
		routingDataSource.setDefaultTargetDataSource(masterDataSource);

		return routingDataSource;
	}

	@Bean
	@Primary
	@DependsOn("routingDataSource")
	public LazyConnectionDataSourceProxy dataSource(DataSource routingDataSource) {
		return new LazyConnectionDataSourceProxy(routingDataSource);
	}


	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
