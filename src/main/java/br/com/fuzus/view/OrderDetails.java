package br.com.fuzus.view;

import br.com.fuzus.controller.services.OrderService;
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
    private JLabel statusLbl;
    private JButton confirmOrderButton;
    private JButton refundOderButton;
    private final OrderService orderService;
    private Order order;

    public OrderDetails(JFrame frame, OrderService orderService, Order order) {
        this.orderService = orderService;
        this.order = order;
        frame.setContentPane(mainPanel);
        this.orderTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{
                "Descricao",
                "Quantidade",
                "preco",
                "valor total"
        }));
        if (order.getPurchasedProducts().isEmpty()){
           this.order = getOrder();
        } else {
            this.order = order;
        }
        this.orderDate.setText(this.order.getDate().format(
                new DateTimeFormatterBuilder()
                        .appendPattern("dd/MM/yyyy HH:mm:ss")
                        .toFormatter()));
        this.totalValue.setText("R$" + this.order.getTotalValue());
        this.clientName.setText(this.order.getClient().getName());
        this.statusLbl.setText(this.order.getStatus().name());
        this.populateTable(this.order.getPurchasedProducts());
        this.setButtonsEnabled();
        this.setListeners();
    }

    private void setButtonsEnabled() {
        switch (this.order.getStatus()) {
            case DRAFT -> {
                refundOderButton.setEnabled(false);
                confirmOrderButton.setEnabled(true);
            }
            case CONFIRMED -> {
                confirmOrderButton.setEnabled(false);
                refundOderButton.setEnabled(true);
            }
            default -> {
                confirmOrderButton.setEnabled(false);
                refundOderButton.setEnabled(false);
            }
        }
    }

    private void populateTable(List<OrderProduct> purchasedProducts) {
        DefaultTableModel model = (DefaultTableModel) this.orderTable.getModel();
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
        return this.orderService.detailOrder(order.getId());
    }

    private void setListeners() {
        this.confirmOrderButton.addActionListener(e -> {
            this.orderService.confirmOrder(order.getId());
            changeStatus(Status.CONFIRMED);
        });
        this.refundOderButton.addActionListener(e -> {
            this.orderService.refundOrder(order.getId());
            changeStatus(Status.REFUNDED);
        });
    }

    private void changeStatus(Status status) {
        this.order.setStatus(status);
        this.statusLbl.setText(status.name());
        this.setButtonsEnabled();
    }
}
