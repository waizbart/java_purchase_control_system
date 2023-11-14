package Interface.Tabs.Compras;

import javax.swing.*;

import Interface.Tabs.Compras.Tabs.Buscar;
import Interface.Tabs.Compras.Tabs.Criar;

import java.awt.*;

public class Compras extends JPanel {
    public Compras() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Buscar", new Buscar());
        tabbedPane.addTab("Criar", new Criar());

        add(tabbedPane, BorderLayout.CENTER);
    }
}
