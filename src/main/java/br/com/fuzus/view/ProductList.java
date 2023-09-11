package br.com.fuzus.view;

import br.com.fuzus.controller.services.OrderService;
import br.com.fuzus.controller.services.ProductService;
import br.com.fuzus.controller.services.imp.OrderServiceImp;
import br.com.fuzus.controller.services.imp.ProductServiceImp;
import br.com.fuzus.model.Order;
import br.com.fuzus.model.OrderProduct;
import br.com.fuzus.model.Product;
import br.com.fuzus.model.Status;
import br.com.fuzus.view.util.CreateFrame;

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
    private final OrderService orderService;
    private final ProductService productService;


    public ProductList(JFrame frame, ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
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
        return this.productService.getProducts();
    }

    private void setListeners() {
        buyButton.addActionListener(e -> {
            int index = productsTable.getSelectedRow();
            Product product = products.get(index);
            int quantityInt = Integer.parseInt(quantity.getText());
            if (quantityInt < 1){
                JOptionPane.showMessageDialog(null, "Selecione uma quantidade valida");
                return;
            }
            this.orderService.addProduct(new OrderProduct(product, quantityInt));
            JOptionPane.showMessageDialog(null, "Produto adcionado com sucesso!");
        });
        nextButton.addActionListener(e -> {
            new ClientSelection(CreateFrame.create("Selecionar Cliente", JFrame.DISPOSE_ON_CLOSE), this.orderService);
        });
    }
}
