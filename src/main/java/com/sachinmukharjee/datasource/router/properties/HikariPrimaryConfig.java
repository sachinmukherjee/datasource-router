package com.sachinmukharjee.datasource.router.properties;

import com.sachinmukharjee.datasource.router.constants.AppConstants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.sql.DataSource;

@Slf4j
@ConfigurationProperties(prefix = AppConstants.PRIMARY_CONFIG_PREFIX)
@ConditionalOnProperty(name = "app.core.db.primary.jdbc_url")
public class HikariPrimaryConfig extends HikariConfig {

  @Value("app.core.db.primary.max_pool_size")
  private int MAX_POOL_SIZE;

  @Value("app.core.db.primary.min_pool_size")
  private int MIN_POOL_SIZE;

  @Value("app.core.db.primary.connection_timeout")
  private long CONNECTION_TIMEOUT;

  @Value("app.core.db.primary.validation_timeout")
  private long VALIDATION_TIMEOUT;

  public DataSource buildDataSource() {
    log.debug("Constructing Primary DataSource");
    this.setMaximumPoolSize(MAX_POOL_SIZE);
    this.setMaximumPoolSize(MAX_POOL_SIZE);
    this.setMinimumIdle(MIN_POOL_SIZE);
    this.setConnectionTimeout(CONNECTION_TIMEOUT);
    this.setValidationTimeout(VALIDATION_TIMEOUT);
    return new HikariDataSource(this);
  }
}
