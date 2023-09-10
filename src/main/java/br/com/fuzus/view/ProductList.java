package br.com.fuzus.view;

import br.com.fuzus.model.Order;
import br.com.fuzus.model.OrderProduct;
import br.com.fuzus.model.Product;
import br.com.fuzus.model.Status;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductList {
    private JPanel panelMain;
    private JTable productsTable;
    private JButton buyButton;
    private JButton nextButton;
    private JTextField quantity;
    private final List<Product> products;
    private final List<OrderProduct> orderProducts = new ArrayList<>();
    private Order order;


    public ProductList(JFrame frame) {
        frame.setContentPane(panelMain);
        productsTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Produto", "Preço", "Stock"}));
        products = getProducts();
        populateTable(products);
        setListeners();
    }

    private void populateTable(List<Product> products) {
        DefaultTableModel model = (DefaultTableModel) productsTable.getModel();
        for (Product prod : products) {
            model.addRow(new String[]{
                    prod.getDescription(),
                    "R$ " + prod.getPrice().toString().replaceAll("\\.", ","),
                    String.valueOf(prod.getStock())
            });
        }
        ;
    }

    private List<Product> getProducts() {
        Product product1 = new Product(1L, "description", new BigDecimal(10), 5);
        Product product2 = new Product(2L, "description2", new BigDecimal("10.53"), 10);
        Product product3 = new Product(2L, "description2", new BigDecimal("10.53"), 10);
        return new ArrayList<>(Arrays.asList(product1, product2, product3, product3, product3, product3, product3));
    }

    private void setListeners() {
        buyButton.addActionListener(e -> {
            int index = productsTable.getSelectedRow();
            Product product = products.get(index);
            Integer quantityInt = Integer.valueOf(quantity.getText());
            orderProducts.add(new OrderProduct(product, quantityInt));
        });
        nextButton.addActionListener(e -> {
            order = new Order(null, LocalDateTime.now(), null, null, Status.DRAFT);
            order.getPurchasedProducts().addAll(orderProducts);
            JFrame frame = new JFrame("Selecionar Cliente");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(512,264);
            frame.setVisible(true);
            new ClientSelection(frame, order);
        });
    }
}
