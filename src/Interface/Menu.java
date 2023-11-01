package Interface;

import javax.swing.*;

import Interface.Tabs.Compras;
import Interface.Tabs.Produtos;
import Interface.Tabs.Relatorios;
import Interface.Tabs.Clientes.Clientes;

public class Menu {
    public Menu() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistema de compras - Organizações Tabajara");

            JTabbedPane tabbedPane = new JTabbedPane();

            tabbedPane.addTab("Clientes", new Clientes());
            tabbedPane.addTab("Compras", new Compras());
            tabbedPane.addTab("Produtos", new Produtos());
            tabbedPane.addTab("Relatórios", new Relatorios());

            frame.add(tabbedPane);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 350);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
