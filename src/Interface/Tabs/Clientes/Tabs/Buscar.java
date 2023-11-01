package Interface.Tabs.Clientes.Tabs;
import javax.swing.*;

import Managers.ClientManager;

public class Buscar extends JPanel {
    private static ClientManager clientManager = ClientManager.getInstance();

    public Buscar() {
        add(new JLabel("Buscar"));
    }
}

