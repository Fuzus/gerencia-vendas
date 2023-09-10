package br.com.fuzus.view.util;

import javax.swing.*;

public class CreateFrame {
    public static JFrame create(String title, int closeOperation) {
        final JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(closeOperation);
        frame.setSize(512,264);
        frame.setVisible(true);
        return frame;
    }
}
