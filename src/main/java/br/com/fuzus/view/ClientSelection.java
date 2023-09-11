package br.com.fuzus.view;

import br.com.fuzus.controller.services.ClientService;
import br.com.fuzus.controller.services.OrderService;
import br.com.fuzus.controller.services.imp.ClientServiceImp;
import br.com.fuzus.model.Client;
import br.com.fuzus.model.Order;
import br.com.fuzus.view.util.CreateFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ClientSelection {
    private JButton selectButton;
    private JTable clientTable;
    private JPanel mainPanel;
    private JTextField inputNovoClient;
    private JButton includeClientButton;
    private JButton continueButton;

    private List<Client> clients;

    private Order order;

    private final OrderService orderService;
    private final ClientService clientService;

    public ClientSelection(JFrame frame, OrderService order) {
        this.orderService = order;
        this.clientService = new ClientServiceImp();
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
        clients = clientService.getAll();
        populateTable(clients);
    }

    private void setListeners(){
        includeClientButton.addActionListener(e -> {
            if(inputNovoClient.getText() != null && !inputNovoClient.getText().isEmpty()) {
                clientService.createClient(inputNovoClient.getText());
                getClients();
                JOptionPane.showMessageDialog(null, "Cliente criado com sucesso!");
                inputNovoClient.setText("");
            }
        });
        selectButton.addActionListener(e -> {
            var index = clientTable.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(null, "Selecione um cliente da lista");
                return;
            }
            order = orderService.createOrder(clients.get(index));
            JOptionPane.showMessageDialog(null, String.format("Cliente %s selecionado", clients.get(index).getName()));
        });
        continueButton.addActionListener(e -> {
            if (order.getClient() == null || order.getClient().getName().isEmpty()){
                JOptionPane.showMessageDialog(null, "Selecione um Cliente");
                return;
            }
            new OrderDetails(CreateFrame.create("Detalhes do pedido", JFrame.DISPOSE_ON_CLOSE), orderService, order);
        });
    }
}
