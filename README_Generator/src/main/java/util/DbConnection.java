package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    private static Connection connection;

    private DbConnection() {
    }

    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                Properties prop = new Properties();

                InputStream is = DbConnection.class
                        .getClassLoader()
                        .getResourceAsStream("db.properties");

                if (is == null) {
                    throw new RuntimeException("找不到 db.properties");
                }

                prop.load(is);

                String driver = prop.getProperty("db.driver");
                String url = prop.getProperty("db.url");
                String username = prop.getProperty("db.username");
                String password = prop.getProperty("db.password");

                Class.forName(driver);

                connection = DriverManager.getConnection(
                        url,
                        username,
                        password);

            }

        } catch (IOException e) {

            throw new RuntimeException("讀取設定檔失敗", e);

        } catch (SQLException e) {

            throw new RuntimeException("資料庫連線失敗", e);

        } catch (ClassNotFoundException e) {

            throw new RuntimeException("找不到 JDBC Driver", e);

        }

        return connection;

    }

}