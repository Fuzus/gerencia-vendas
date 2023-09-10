package br.com.fuzus;

import br.com.fuzus.view.util.CreateFrame;
import br.com.fuzus.view.*;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new MainPage(CreateFrame.create("Gerencias de Vendas", JFrame.EXIT_ON_CLOSE));
    }
}
