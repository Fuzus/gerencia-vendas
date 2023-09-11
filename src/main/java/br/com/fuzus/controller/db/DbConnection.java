package br.com.fuzus.controller.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static DbConnection instance;
    private static final String URL = "jdbc:postgresql://localhost/gerencia_vendas";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static Connection conn;

    private DbConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public static void closeInstance() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        conn = null;
        instance = null;
    }

}
