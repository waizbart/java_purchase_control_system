package Interface.Tabs.Clientes.Tabs;

import java.util.ArrayList;

import javax.swing.*;

import Managers.ClientManager;
import Models.Client.Client;

public class Buscar extends JPanel {
    private static ClientManager clientManager = ClientManager.getInstance();

    public Buscar() {
        ArrayList<Client> clients = clientManager.getClients();
        ArrayList<String> rows = new ArrayList<String>();

        for (Client client : clients) {
            rows.add(client.toString());
        }

        JList<String> lista = new JList<>(rows.toArray(new String[rows.size()]));

        JScrollPane scroll = new JScrollPane(lista);

        add(scroll);

        JButton reloadBtn = new JButton("Recarregar");

        reloadBtn.addActionListener(e -> {
            ArrayList<Client> clients2 = clientManager.getClients();
            ArrayList<String> rows2 = new ArrayList<String>();

            for (Client client : clients2) {
                rows2.add(client.toString());
            }

            lista.setListData(rows2.toArray(new String[rows2.size()]));
        });

        add(reloadBtn);
    }
}
