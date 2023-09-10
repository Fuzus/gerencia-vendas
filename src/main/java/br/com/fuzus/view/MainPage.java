package br.com.fuzus.view;

import br.com.fuzus.view.util.CreateFrame;

import javax.swing.*;

public class MainPage {
    private JButton buyButton;
    private JButton listOrderButton;
    private JPanel mainPanel;

    public MainPage(JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setSize(512,256);
        setListeners();
    }

    public void setListeners() {
        buyButton.addActionListener(e -> {
            new ProductList(CreateFrame.create("Lista de produtos", JFrame.DISPOSE_ON_CLOSE));
        });
        listOrderButton.addActionListener(e -> {
            new OrderList(CreateFrame.create("Lista de pedidos", JFrame.DISPOSE_ON_CLOSE));
        });
    }
}
