package com.sachinmukharjee.datasource.router.config;

import com.sachinmukharjee.datasource.router.RoutingDataSource;
import com.sachinmukharjee.datasource.router.constants.AppConstants;
import com.sachinmukharjee.datasource.router.constants.RouteMode;
import com.sachinmukharjee.datasource.router.properties.HikariReplicaConfig;
import java.util.Map;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty(name = AppConstants.DATABASE_MODE, havingValue = "REPLICA")
public class DataSourceConfigReplicaOnly {

  @Bean
  DataSource dataSource(HikariReplicaConfig hikariReplicaConfig) {
    log.debug("Creating DataSoruce for Replica Source");
    RoutingDataSource routingDataSource = new RoutingDataSource();

    var replicaDataSource = hikariReplicaConfig.buildDataSource();

    Map<Object, Object> targetDataSources = Map.of(RouteMode.REPLICA, replicaDataSource);

    routingDataSource.setTargetDataSources(targetDataSources);
    routingDataSource.setDefaultTargetDataSource(replicaDataSource);

    return replicaDataSource;
  }
}
