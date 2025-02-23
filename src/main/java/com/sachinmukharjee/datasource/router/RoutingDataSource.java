package com.sachinmukharjee.datasource.router;

import com.sachinmukharjee.datasource.router.constants.RouteMode;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

  private static final ThreadLocal<RouteMode> contextHolder = new ThreadLocal<>();

  @Override
  protected Object determineCurrentLookupKey() {
    return contextHolder.get();
  }

  public static void setReadOnlyReplica() {
    contextHolder.set(RouteMode.REPLICA);
  }

  public static void clearContext() {
    contextHolder.remove();
  }
}
