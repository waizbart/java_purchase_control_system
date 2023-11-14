package Interface;

import javax.swing.*;

import Interface.Tabs.Compras.Compras;
import Interface.Tabs.Produtos.Produtos;
import Interface.Tabs.Clientes.Clientes;

public class Menu {
    public Menu() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistema de compras - Organizações Tabajara");

            JTabbedPane tabbedPane = new JTabbedPane();

            tabbedPane.addTab("Clientes", new Clientes());
            tabbedPane.addTab("Produtos", new Produtos());
            tabbedPane.addTab("Compras", new Compras());

            frame.add(tabbedPane);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(false);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
