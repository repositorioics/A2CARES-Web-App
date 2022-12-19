package ni.org.ics.webapp.domain.utils;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;

public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("a2cares_web");
        dataSource.setPassword("a2caresoBj-1");
        //dataSource.setPassword("123456");
        //dataSource.setServerName("141.211.217.99");
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("a2cares");
        dataSource.setUseSSL(false);
        dataSource.setCharacterEncoding("UTF-8");
        dataSource.setServerTimezone("UTC");

        return dataSource.getConnection();
    }
}
