package com.sachinmukharjee.datasource.router.properties;

import com.sachinmukharjee.datasource.router.constants.AppConstants;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.sql.DataSource;

@Slf4j
@ConfigurationProperties(prefix = AppConstants.REPLICA_CONFIG_PREFIX)
@ConditionalOnProperty(name = "app.core.db.replica.jdbc_url")
public class HikariReplicaConfig {

    @Value("app.core.db.replica.max_pool_size")
    private int MAX_POOL_SIZE;

    @Value("app.core.db.replica.min_pool_size")
    private int MIN_POOL_SIZE;

    @Value("app.core.db.replica.connection_timeout")
    private long CONNECTION_TIMEOUT;

    @Value("app.core.db.replica.validation_timeout")
    private long VALIDATION_TIMEOUT;

    // create DataSourceObject
    public DataSource dataSource() {
        log.debug("Constructing Replica DataSource");
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setMaximumPoolSize(MAX_POOL_SIZE);
        dataSource.setMinimumIdle(MIN_POOL_SIZE);
        dataSource.setConnectionTimeout(CONNECTION_TIMEOUT);
        dataSource.setValidationTimeout(VALIDATION_TIMEOUT);
        return dataSource;
    }
}
