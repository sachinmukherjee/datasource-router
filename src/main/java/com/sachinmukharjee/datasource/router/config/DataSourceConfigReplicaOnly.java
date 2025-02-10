package com.sachinmukharjee.datasource.router.config;

import com.sachinmukharjee.datasource.router.RoutingDataSource;
import com.sachinmukharjee.datasource.router.constants.AppConstants;
import com.sachinmukharjee.datasource.router.constants.RouteMode;
import com.sachinmukharjee.datasource.router.properties.HikariPrimaryConfig;
import com.sachinmukharjee.datasource.router.properties.HikariReplicaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@Configuration
@ConditionalOnProperty(name = AppConstants.DATABASE_MODE, havingValue = "REPLICA")
public class DataSourceConfigReplicaOnly {

  @Bean
  DataSource dataSource(HikariReplicaConfig hikariReplicaConfig) {
    log.debug("Creating DataSoruce for Replica Source");
    RoutingDataSource routingDataSource = new RoutingDataSource();

    Map<Object, Object> targetDataSources =
        Map.of(RouteMode.REPLICA, hikariReplicaConfig.dataSource());

    routingDataSource.setTargetDataSources(targetDataSources);
    routingDataSource.setDefaultTargetDataSource(targetDataSources);

    return hikariReplicaConfig.dataSource();
  }
}
