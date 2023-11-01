package Interface.Tabs.Clientes.Tabs;
import javax.swing.*;

import Managers.ClientManager;
import Models.Client.Client;

public class Buscar extends JPanel {
    private static ClientManager clientManager = ClientManager.getInstance();

    public Buscar() {
        String clients = "";

        for (Client client : clientManager.getClientsPf()) {
            clients += client.toString() + "\n";
        }

        add(new JLabel(clients));
    }
}

