package Interface.Tabs.Clientes;

import javax.swing.*;

import Interface.Tabs.Clientes.Tabs.Buscar;
import Interface.Tabs.Clientes.Tabs.Criar;

import java.awt.*;

public class Clientes extends JPanel {
    public Clientes() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Buscar", new Buscar());
        tabbedPane.addTab("Criar", new Criar());

        tabbedPane.setMaximumSize(new Dimension(500, 500));

        add(tabbedPane, BorderLayout.CENTER);
    }
}
