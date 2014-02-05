package com.angkorteam.demo;

import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import com.itrustcambodia.pluggable.core.AbstractWebApplication;
import com.jolbox.bonecp.BoneCPDataSource;

public class WebApplication extends AbstractWebApplication {

    /**
     * 
     */
    private static final long serialVersionUID = 6497238745001441937L;

    @Override
    protected DataSource getDataSource() {
        org.apache.wicket.resource.Properties prop = this.getResourceSettings()
                .getPropertiesFactory()
                .load(WebApplication.class, "datasource");

        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(prop.getString("driverClass"));
        dataSource.setJdbcUrl(prop.getString("jdbcUrl"));
        dataSource.setUsername(prop.getString("username"));
        dataSource.setPassword(prop.getString("password"));
        dataSource.setIdleConnectionTestPeriod(60, TimeUnit.SECONDS);
        dataSource.setIdleMaxAgeInSeconds(240);
        dataSource.setMaxConnectionsPerPartition(30);
        dataSource.setMinConnectionsPerPartition(10);
        dataSource.setPartitionCount(6);
        dataSource.setAcquireIncrement(10);
        dataSource.setStatementsCacheSize(100);
        dataSource.setCloseConnectionWatch(true);
        return dataSource;
    }

}
