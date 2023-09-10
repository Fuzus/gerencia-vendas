package br.com.fuzus;

import br.com.fuzus.view.ClientSelection;
import br.com.fuzus.view.OrderList;
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
        JFrame frame = new JFrame("Gerencias de Vendas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(512,264);
        frame.setVisible(true);
        new ClientSelection(frame);
    }
}
