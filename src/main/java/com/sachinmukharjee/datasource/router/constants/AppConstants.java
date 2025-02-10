package com.sachinmukharjee.datasource.router.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppConstants {

  public static final String BASE_CONFIG_PATH = "app.core.db";
  public static final String DATABASE_MODE = BASE_CONFIG_PATH + ".mode";
  public static final String PRIMARY_CONFIG_PREFIX = BASE_CONFIG_PATH + ".primary";
  public static final String REPLICA_CONFIG_PREFIX = BASE_CONFIG_PATH + ".replica";

  public static final String PRIMARY = "primary";
  public static final String REPLICA = "replica";

  // DATABASE CONFIGS
  public static final String USERNAME = BASE_CONFIG_PATH + ".%s.username";
  public static final String PASSWORD = BASE_CONFIG_PATH + ".%s.password";
  public static final String JDBC_URL = BASE_CONFIG_PATH + ".%s.jdbc_url";
  public static final String DRIVER_CLASS_NAME = BASE_CONFIG_PATH + ".%s.driver_class_name";
  public static final String CONNECTION_TIMEOUT = BASE_CONFIG_PATH + ".%s.connection_timeout";
  public static final String VALIDATION_TIMEOUT = BASE_CONFIG_PATH + ".%s.validation_timeout";
  public static final String MIN_POOL_SIZE = BASE_CONFIG_PATH + ".%s.min_pool_size";
  public static final String MAX_POOL_SIZE = BASE_CONFIG_PATH + ".%s.max_pool_size";

  public static final String PRIMARY_USERNAME = String.format(USERNAME, PRIMARY);
  public static final String PRIMARY_PASSWORD = String.format(PASSWORD, PRIMARY);
  public static final String PRIMARY_JDBC_URL = String.format(JDBC_URL, PRIMARY);
  public static final String PRIMARY_DRIVER_CLASS_NAME = String.format(DRIVER_CLASS_NAME, PRIMARY);
  public static final String PRIMARY_CONNECTION_TIMEOUT =
      String.format(CONNECTION_TIMEOUT, PRIMARY);
  public static final String PRIMARY_VALIDATION_TIMEOUT =
      String.format(VALIDATION_TIMEOUT, PRIMARY);
  public static final String PRIMARY_MAX_POOL_SIZE = String.format(MAX_POOL_SIZE, PRIMARY);
  public static final String PRIMARY_MIN_POOL_SIZE = String.format(MIN_POOL_SIZE, PRIMARY);
}
