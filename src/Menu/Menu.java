package Menu;

import javax.swing.*;

import Menu.Tabs.Clientes;
import Menu.Tabs.Compras;
import Menu.Tabs.Produtos;
import Menu.Tabs.Relatorios;

public class Menu {
    public Menu() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Janela com Abas");

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
