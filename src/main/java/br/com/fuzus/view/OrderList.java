package br.com.fuzus.view;

import br.com.fuzus.model.Client;
import br.com.fuzus.model.Order;
import br.com.fuzus.model.Status;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OrderList {
    private JTable orderTable;
    private JButton detailsButton;
    private JPanel mainPanel;

    private final List<Order> orders;

    public OrderList(JFrame frame) {
        frame.setContentPane(mainPanel);
        orderTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{
                "Id",
                "valor total",
                "Data da compra",
                "Cliente"
        }));
        orders = getOrders();
        populateTable(orders);
    }

    private void populateTable(List<Order> orders) {
        DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
        for (Order order : orders) {
            model.addRow(new String[] {
                    order.getId().toString(),
                    order.getTotalValue().toString(),
                    order.getDate().format(
                            new DateTimeFormatterBuilder()
                                    .appendPattern(("dd/MM/yyy-HH:mm"))
                                    .toFormatter()),
                    order.getClient().getName()
            });
        }
    }

    private void setListeners() {
        detailsButton.addActionListener(e-> {
            var index = orderTable.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
            Long id = Long.parseLong((String) model.getValueAt(index, 0));
            var order = orders.stream().filter(x -> x.getId().equals(id)).findFirst();
            if (order.isPresent()) {
                //TODO: criar tela de detalhes
            }
        });
    }

    private List<Order> getOrders() {
        return Arrays.asList(new Order(1L, LocalDateTime.now(), new Client(1L, "Gabriel"),new BigDecimal(550), Status.DRAFT));
    }
}