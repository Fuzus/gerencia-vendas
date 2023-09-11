package br.com.fuzus.utlis;

import br.com.fuzus.controller.db.DbConnection;
import br.com.fuzus.controller.db.ProductDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class SqlScriptRunner {

    private void run(){
        try (var queries = getClass().getResourceAsStream("/db/create_tables.sql")) {
            String s = "";
            StringBuilder stringBuilder = new StringBuilder();
            if (queries == null) {
                throw new RuntimeException("Queries is null");
            }

            BufferedReader bf = new BufferedReader(new InputStreamReader(queries));
            while ((s = bf.readLine()) != null){
                stringBuilder.append(s);
            }
            bf.close();

            String[] queriesSplit = stringBuilder.toString().split(";");
            runQueries(queriesSplit);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private void runQueries(String[] queriesSplit) {
        var conn = DbConnection.getInstance();
        try {
            for (String query : queriesSplit) {
                conn.createStatement(query);
                conn.getStatement().execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateDatabase() {
        ProductDAO productDAO = new ProductDAO();
        try{
            productDAO.getAllProducts();
        }catch (RuntimeException e) {
            run();
        }
    }
}
