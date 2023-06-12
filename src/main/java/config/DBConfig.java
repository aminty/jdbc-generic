package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

    private String URL="jdbc:postgresql://localhost:5432/postgres";
    private String USER="postgres";
    private String PASS="aminty2015";


    public Connection getConnection(){
        Connection connection= null;
        try {
            connection = DriverManager.getConnection(URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
