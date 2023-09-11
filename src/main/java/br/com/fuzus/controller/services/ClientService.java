package br.com.fuzus.controller.services;

import br.com.fuzus.model.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAll();
    void createClient(String name);
}
