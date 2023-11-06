package Interface.Tabs.Clientes.Tabs;

import javax.swing.*;

import Managers.ClientManager;
import Models.Client.Client;

public class Buscar extends JPanel {
    private static ClientManager clientManager = ClientManager.getInstance();

    public Buscar() {
        for (Client client : clientManager.getClientsPf()) {
            add(new JTable(new Object[][] {
                    { client.getName(), client.getEntryDate(), client.getAddress() }
            }, new String[] { "Nome", "Data de Entrada", "Endereço" }));
        }
    }
}
