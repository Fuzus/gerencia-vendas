package br.com.fuzus.view;

import br.com.fuzus.model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ClientSelection {
    private JButton selectButton;
    private JTable clientTable;
    private JPanel mainPanel;
    private JTextField inputNovoClient;
    private JButton includeClientButton;
    private JButton continueButton;

    private final List<Client> clients = new ArrayList<>();

    public ClientSelection(JFrame frame) {
        frame.setContentPane(mainPanel);
        clientTable.setModel(new DefaultTableModel(new Object[][]{}, new String[] {"Nome"}));
        getClients();
        populateTable(clients);
        setListeners();
    }

    private void populateTable(List<Client> clients) {
        DefaultTableModel model = (DefaultTableModel) clientTable.getModel();
        model.setRowCount(0);
        for (Client client : clients) {
            model.addRow(new String[]{
                    client.getName()
            });
        }
    }

    private void getClients() {
        clients.add(new Client(1L, "Matheus"));
    }

    private void setListeners(){
        includeClientButton.addActionListener(e -> {
            if(inputNovoClient.getText() != null && !inputNovoClient.getText().isEmpty()) {
                clients.add(new Client((long) (clients.size() + 1), inputNovoClient.getText()));
                populateTable(clients);
            }
        });
        selectButton.addActionListener(e -> {
            //TODO: Adcionar o cliente ao pedido
        });
        continueButton.addActionListener(e -> {
            //TODO: TELA DE DETALHES DO PEDIDO
        });
    }
}
