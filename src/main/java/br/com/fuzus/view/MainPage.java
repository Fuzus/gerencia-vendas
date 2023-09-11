package br.com.fuzus.view;

import br.com.fuzus.controller.services.OrderService;
import br.com.fuzus.controller.services.ProductService;
import br.com.fuzus.controller.services.imp.OrderServiceImp;
import br.com.fuzus.controller.services.imp.ProductServiceImp;
import br.com.fuzus.view.util.CreateFrame;

import javax.swing.*;

public class MainPage {
    private JButton buyButton;
    private JButton listOrderButton;
    private JPanel mainPanel;

    private final ProductService productService;
    private final OrderService orderService;

    public MainPage(JFrame frame) {
        this.productService = new ProductServiceImp();
        this.orderService = new OrderServiceImp(productService);
        frame.setContentPane(mainPanel);
        frame.setSize(512,256);
        setListeners();
    }

    public void setListeners() {
        buyButton.addActionListener(e -> {
            new ProductList(CreateFrame.create("Lista de produtos", JFrame.DISPOSE_ON_CLOSE), productService, orderService);
        });
        listOrderButton.addActionListener(e -> {
            new OrderList(CreateFrame.create("Lista de pedidos", JFrame.DISPOSE_ON_CLOSE), orderService);
        });
    }
}
