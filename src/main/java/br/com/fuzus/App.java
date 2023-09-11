package br.com.fuzus;

import br.com.fuzus.controller.db.ProductDAO;
import br.com.fuzus.utlis.SqlScriptRunner;
import br.com.fuzus.view.util.CreateFrame;
import br.com.fuzus.view.MainPage;

import javax.swing.*;

public class App
{
    public static void main( String[] args )
    {
        new SqlScriptRunner().validateDatabase();
        new MainPage(CreateFrame.create("Gerencias de Vendas", JFrame.EXIT_ON_CLOSE));

    }
}
