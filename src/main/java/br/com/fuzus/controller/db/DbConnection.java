package br.com.fuzus.controller.db;

import br.com.fuzus.utlis.PropertiesUtils;

import java.sql.*;
import java.util.Properties;

public class DbConnection {

    private static DbConnection instance;
    private static Connection conn;
    private static PreparedStatement stmt;
    private static ResultSet rs;

    private DbConnection() {
        try {
            Properties properties = new PropertiesUtils().getProps();
            conn = DriverManager.getConnection(
                    properties.getProperty("sql.datasource.url"),
                    properties.getProperty("sql.datasource.username"),
                    properties.getProperty("sql.datasource.password")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void createStatement(String query) throws SQLException {
        if (query == null) {
            throw new RuntimeException("Query não pode ser null");
        }
        stmt = conn.prepareStatement(query);
    }

    public PreparedStatement getStatement() {
        return stmt;
    }

    public void createResultSet() throws SQLException {
        if (stmt == null) {
            throw new RuntimeException("Statement não pode ser null");
        }
        rs = stmt.executeQuery();
    }

    public ResultSet getResultSet() throws SQLException {
        if (rs == null) {
            createResultSet();
        }
        return rs;
    }

    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public static void closeInstance() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
            rs = null;
            stmt = null;
            conn = null;
            instance = null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
