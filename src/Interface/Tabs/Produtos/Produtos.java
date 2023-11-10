package Interface.Tabs.Produtos;

import javax.swing.*;

import Interface.Tabs.Produtos.Tabs.Buscar;
import Interface.Tabs.Produtos.Tabs.Criar;

import java.awt.*;

public class Produtos extends JPanel {
    public Produtos() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Buscar", new Buscar());
        tabbedPane.addTab("Criar", new Criar());

        add(tabbedPane, BorderLayout.CENTER);
    }
}
