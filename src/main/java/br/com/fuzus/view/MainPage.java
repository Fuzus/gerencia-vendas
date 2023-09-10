package br.com.fuzus.view;

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

    public void setListeners(){
        buyButton.addActionListener(e -> {
            new ProductList(createJframe());
        });
        listOrderButton.addActionListener(e -> {
            new OrderList(createJframe());
        });
    }

    private JFrame createJframe() {
        final JFrame frame = new JFrame("Lista de produtos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(512, 256);
        frame.setVisible(true);
        return frame;
    }
}
