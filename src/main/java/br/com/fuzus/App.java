package br.com.fuzus;

import br.com.fuzus.controller.db.ProductDAO;
import br.com.fuzus.view.util.CreateFrame;
import br.com.fuzus.view.MainPage;

import javax.swing.*;

public class App
{
    public static void main( String[] args )
    {
//        new MainPage(CreateFrame.create("Gerencias de Vendas", JFrame.EXIT_ON_CLOSE));
        ProductDAO productDAO = new ProductDAO();
        var products = productDAO.getAllProducts();
        products.forEach(System.out::println);
    }
}
