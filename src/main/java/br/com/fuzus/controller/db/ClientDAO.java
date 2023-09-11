package br.com.fuzus.controller.db;

import br.com.fuzus.model.Client;
import br.com.fuzus.utlis.PropertiesUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ClientDAO {

    private DbConnection conn;
    private Properties properties;

    public ClientDAO() {
        properties = new PropertiesUtils().getProps();
    }

    public List<Client> getAllClients(){
        List<Client> clients = new ArrayList<>();
        conn = DbConnection.getInstance();

        try {
            conn.createStatement(properties.getProperty("client.sql.getAll"));
            while (conn.getResultSet().next()){
                clients.add(new Client(
                        conn.getResultSet().getLong("id"),
                        conn.getResultSet().getString("name")
                ));
            }
            DbConnection.closeInstance();
            return clients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addClient(Client client) {
        conn = DbConnection.getInstance();

        try {
            conn.createStatement(properties.getProperty("client.sql.insert"));
            conn.getStatement().setString(1, client.getName());
            conn.getStatement().executeUpdate();
            DbConnection.closeInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
