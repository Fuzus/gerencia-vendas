package br.com.fuzus.view;

import br.com.fuzus.model.Client;
import br.com.fuzus.model.Order;
import br.com.fuzus.model.OrderProduct;
import br.com.fuzus.model.Status;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.List;

public class OrderDetails {
    private JTable orderTable;
    private JPanel mainPanel;
    private JLabel orderDate;
    private JLabel totalValue;
    private JLabel clientName;
    private JLabel status;
    private Order order;

    public OrderDetails(JFrame frame) {
        frame.setContentPane(mainPanel);
        orderTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{
                "Descricao",
                "Quantidade",
                "preco",
                "valor total"
        }));
        order = getOrder();
        orderDate.setText(order.getDate().format(
                new DateTimeFormatterBuilder()
                        .appendPattern("dd/MM/yyyy HH:mm:ss")
                        .toFormatter()));
        totalValue.setText("R$" + order.getTotalValue());
        clientName.setText(order.getClient().getName());
        status.setText(order.getStatus().name());
        populateTable(order.getPurchasedProducts());
    }

    private void populateTable(List<OrderProduct> purchasedProducts) {
        DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
        for (OrderProduct product : purchasedProducts){
            model.addRow(new String[]{
                    product.getProductDescription(),
                    String.valueOf(product.getQuantity()),
                    "R$ " + String.valueOf(product.getPrice()),
                    "R$ " + String.valueOf(product.getTotal())
            });
        }
    }

    private Order getOrder() {
        return order = new Order(
                1L,
                LocalDateTime.now(),
                new Client(1L, "Gabriel"),
                new BigDecimal(550),
                Status.CONFIRMED,
                Arrays.asList(
                        new OrderProduct(1L, "Teste", 50, new BigDecimal("35.15")),
                        new OrderProduct(2L, "teste2", 44, new BigDecimal("55.99"))
                )
        );
    }
}
