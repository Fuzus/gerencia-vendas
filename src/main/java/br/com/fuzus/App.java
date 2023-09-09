package br.com.fuzus;

import br.com.fuzus.view.ProductList;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JFrame frame = new JFrame("ProductList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(512,264);
        frame.setVisible(true);
        ProductList productList = new ProductList(frame);
    }
}
