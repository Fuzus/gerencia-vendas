package br.com.fuzus.controller.services.imp;

import br.com.fuzus.controller.db.ClientDAO;
import br.com.fuzus.controller.services.ClientService;
import br.com.fuzus.model.Client;

import java.util.List;

public class ClientServiceImp implements ClientService {

    private ClientDAO clientDAO;

    public ClientServiceImp(){
        this.clientDAO = new ClientDAO();
    }

    @Override
    public List<Client> getAll() {
        return clientDAO.getAllClients();
    }

    @Override
    public void createClient(String name) {
        clientDAO.addClient(new Client(null, name));
    }
}
