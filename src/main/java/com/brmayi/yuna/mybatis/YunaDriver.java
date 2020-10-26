package com.brmayi.yuna.mybatis;

import lombok.extern.slf4j.Slf4j;

import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;
import com.mysql.jdbc.jdbc2.Connection;

@Slf4j
public class YunaDriver implements Driver {

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        Connection connection =  new Connection();
        return connection;
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return true;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return true;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return Logger.getLogger("com.brmayi.yuna.mybatis.YunaDriver");
    }
}
