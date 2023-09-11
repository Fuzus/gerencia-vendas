package br.com.fuzus.controller.db;

import br.com.fuzus.model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        conn = DbConnection.getInstance().getConnection();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM produtos");
            while(rs.next()){
                products.add(new Product(
                        rs.getLong("id"),
                        rs.getString("description"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock")
                    )
                );
            }
            rs.close();
            stmt.close();
            DbConnection.closeInstance();
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
