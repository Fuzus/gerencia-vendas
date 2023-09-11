package br.com.fuzus.controller.db;

import br.com.fuzus.model.Product;
import br.com.fuzus.utlis.PropertiesUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDAO {
    private static DbConnection conn;
    private final Properties properties;

    public ProductDAO(){
        properties = new PropertiesUtils().getProps();
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        conn = DbConnection.getInstance();
        try {
            conn.createStatement(properties.getProperty("product.sql.getAll"));
            while(conn.getResultSet().next()){
                products.add(new Product(
                        conn.getResultSet().getLong("id"),
                        conn.getResultSet().getString("description"),
                        conn.getResultSet().getBigDecimal("price"),
                        conn.getResultSet().getInt("stock")
                    )
                );
            }
            DbConnection.closeInstance();
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product getProductById(Long id) {
        Product product = null;
        String query = properties.getProperty("product.sql.getById");
        conn = DbConnection.getInstance();
        try {
            conn.createStatement(query);
            conn.getStatement().setLong(1, id);
            if (conn.getResultSet().next()) {
                product = new Product(
                        conn.getResultSet().getLong("id"),
                        conn.getResultSet().getString("description"),
                        conn.getResultSet().getBigDecimal("price"),
                        conn.getResultSet().getInt("stock"));
            }
            DbConnection.closeInstance();
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertProduct(Product product) {
        boolean result;
        String query = properties.getProperty("product.sql.insert");
        conn = DbConnection.getInstance();
        try {
            conn.createStatement(query);
            conn.getStatement().setString(1, product.getDescription());
            conn.getStatement().setBigDecimal(2, product.getPrice());
            conn.getStatement().setInt(3, product.getStock());
            result = conn.getStatement().executeUpdate() > 0;
            DbConnection.closeInstance();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeStock(Product product) {
        String updateQuery = properties.getProperty("product.sql.updateStock");
        conn = DbConnection.getInstance();
        try {
            conn.createStatement(updateQuery);
            conn.getStatement().setInt(1, product.getStock());
            conn.getStatement().setLong(2, product.getId());
            conn.getStatement().executeUpdate();
            DbConnection.closeInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
