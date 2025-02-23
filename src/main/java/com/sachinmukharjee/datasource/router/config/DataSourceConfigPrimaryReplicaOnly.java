package com.sachinmukharjee.datasource.router.config;

import com.sachinmukharjee.datasource.router.RoutingDataSource;
import com.sachinmukharjee.datasource.router.constants.AppConstants;
import com.sachinmukharjee.datasource.router.constants.RouteMode;
import com.sachinmukharjee.datasource.router.properties.HikariPrimaryConfig;
import com.sachinmukharjee.datasource.router.properties.HikariReplicaConfig;
import java.util.Map;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty(name = AppConstants.DATABASE_MODE, havingValue = "REPLICA_AND_PRIMARY")
public class DataSourceConfigPrimaryReplicaOnly {

  @Bean
  DataSource dataSource(
      HikariPrimaryConfig hikariPrimaryConfig, HikariReplicaConfig hikariReplicaConfig) {
    log.debug("Creating DataSoruce for Primary & Replica Source");
    RoutingDataSource routingDataSource = new RoutingDataSource();

    var primaryDataSource = hikariPrimaryConfig.buildDataSource();
    var replicaDataSource = hikariReplicaConfig.buildDataSource();

    Map<Object, Object> targetDataSources =
        Map.of(
            RouteMode.PRIMARY, primaryDataSource,
            RouteMode.REPLICA, replicaDataSource);

    routingDataSource.setTargetDataSources(targetDataSources);
    routingDataSource.setDefaultTargetDataSource(primaryDataSource);

    return primaryDataSource;
  }
}
