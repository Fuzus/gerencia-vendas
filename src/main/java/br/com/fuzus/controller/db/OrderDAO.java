package br.com.fuzus.controller.db;

import br.com.fuzus.model.Client;
import br.com.fuzus.model.Order;
import br.com.fuzus.model.OrderProduct;
import br.com.fuzus.model.Status;
import br.com.fuzus.utlis.PropertiesUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OrderDAO {
    private DbConnection conn;
    private final Properties properties;

    public OrderDAO(){
        properties = new PropertiesUtils().getProps();
    }

    public List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<>();
        conn = DbConnection.getInstance();
        try {
            conn.createStatement(properties.getProperty("order.sql.getAll"));
            while (conn.getResultSet().next()){
                orders.add(new Order(
                        conn.getResultSet().getLong("id"),
                        conn.getResultSet().getTimestamp("date").toLocalDateTime(),
                        new Client(conn.getResultSet().getLong("client_id"), conn.getResultSet().getString("name")),
                        conn.getResultSet().getBigDecimal("total_value"),
                        Status.valueOf(conn.getResultSet().getString("status"))
                ));
            }
            DbConnection.closeInstance();
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Order getDetailedOrder(Long id) {
        Order order = null;
        conn = DbConnection.getInstance();
        try {
            conn.createStatement(properties.getProperty("order.sql.getOrderDetails"));
            conn.getStatement().setLong(1, id);
            if (conn.getResultSet().next()){
                order = new Order(
                        conn.getResultSet().getLong("id"),
                        conn.getResultSet().getTimestamp("date").toLocalDateTime(),
                        new Client(conn.getResultSet().getLong("client_id"), conn.getResultSet().getString("name")),
                        conn.getResultSet().getBigDecimal("total_value"),
                        Status.valueOf(conn.getResultSet().getString("status"))
                );

            }
            DbConnection.closeInstance();
            if (order != null) {
                order.setPurchasedProducts(getOrderProducts(id));
            }
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long createOrder(Order order) {
        var generatedId = insertOrder(order);
        insertOrderProducts(generatedId, order.getPurchasedProducts());
        return generatedId;
    }

    public boolean updateStatus(Long id, Status status){
        boolean result;
        conn = DbConnection.getInstance();
        try {
            conn.createStatement(properties.getProperty("order.sql.updateStatus"));
            conn.getStatement().setString(1, status.name());
            conn.getStatement().setLong(2, id);
            result = conn.getStatement().executeUpdate() > 0;
            DbConnection.closeInstance();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertOrderProducts(Long id, List<OrderProduct> purchasedProducts) {
        String query = properties.getProperty("order.sql.insertOrderProducts");
        conn = DbConnection.getInstance();
        try {
            for (OrderProduct purchasedProduct : purchasedProducts) {
                conn.createStatement(query);
                conn.getStatement().setLong(1, purchasedProduct.getProductId());
                conn.getStatement().setString(2, purchasedProduct.getProductDescription());
                conn.getStatement().setLong(3, id);
                conn.getStatement().setInt(4, purchasedProduct.getQuantity());
                conn.getStatement().setBigDecimal(5, purchasedProduct.getPrice());
                conn.getStatement().setBigDecimal(6, purchasedProduct.getTotal());
                conn.getStatement().executeUpdate();
            }
            DbConnection.closeInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Long insertOrder(Order order) {
        var generatedId = 0L;
        String query = properties.getProperty("order.sql.insertOrder");
        conn = DbConnection.getInstance();
        try {
            conn.createStatement(query);
            conn.getStatement().setLong(1, order.getClient().getId());
            conn.getStatement().setTimestamp(2, Timestamp.valueOf(order.getDate()));
            conn.getStatement().setBigDecimal(3, order.getTotalValue());
            conn.getStatement().setString(4, order.getStatus().name());
            conn.getStatement().executeUpdate();
            ResultSet rs = conn.getStatement().getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt("id");
            }
            DbConnection.closeInstance();
            return generatedId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<OrderProduct> getOrderProducts(Long id) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        conn = DbConnection.getInstance();
        try {
            conn.createStatement(properties.getProperty("order.sql.getOrderProducts"));
            conn.getStatement().setLong(1, id);
            while (conn.getResultSet().next()){
                orderProducts.add(new OrderProduct(
                        conn.getResultSet().getLong("product_id"),
                        conn.getResultSet().getString("product_description"),
                        conn.getResultSet().getInt("quantity"),
                        conn.getResultSet().getBigDecimal("price")
                ));
            }
            DbConnection.closeInstance();
            return orderProducts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
