package br.com.fuzus.domain.ports.out;

import br.com.fuzus.domain.entities.Client;

public interface ClientRepository {
    void createClient(Client client);

    Client findClientById(Long id);

}
