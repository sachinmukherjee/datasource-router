package com.sachinmukharjee.datasource.router.config;

import com.sachinmukharjee.datasource.router.RoutingDataSource;
import com.sachinmukharjee.datasource.router.constants.AppConstants;
import com.sachinmukharjee.datasource.router.constants.RouteMode;
import com.sachinmukharjee.datasource.router.properties.HikariPrimaryConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@Configuration
@ConditionalOnProperty(name = AppConstants.DATABASE_MODE, havingValue = "PRIMARY")
public class DataSourceConfigPrimaryOnly {

  @Bean
  DataSource dataSource(HikariPrimaryConfig hikariPrimaryConfig) {
    log.debug("Creating DataSoruce for Primary Source");
    RoutingDataSource routingDataSource = new RoutingDataSource();

    Map<Object, Object> targetDataSources =
        Map.of(RouteMode.PRIMARY, hikariPrimaryConfig.dataSource());

    routingDataSource.setTargetDataSources(targetDataSources);
    routingDataSource.setDefaultTargetDataSource(targetDataSources);

    return hikariPrimaryConfig.dataSource();
  }
}
