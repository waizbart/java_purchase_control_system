package Interface;

import javax.swing.*;

import Interface.Tabs.Compras.Compras;
import Interface.Tabs.Produtos.Produtos;
import Interface.Tabs.Clientes.Clientes;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Managers.ClientManager;
import Managers.ProductManager;
import Managers.PurchaseManager;

public class Menu {
    private static ClientManager clientManager = ClientManager.getInstance();
    private static ProductManager productManager = ProductManager.getInstance();
    private static PurchaseManager purchaseManager = PurchaseManager.getInstance();

    public Menu() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistema de compras - Organizações Tabajara");

            JTabbedPane tabbedPane = new JTabbedPane();

            tabbedPane.addTab("Compras", new Compras());
            tabbedPane.addTab("Clientes", new Clientes());
            tabbedPane.addTab("Produtos", new Produtos());

            JPanel bottomPanel = new JPanel(new BorderLayout());

            bottomPanel.add(tabbedPane, BorderLayout.CENTER);

            JButton btnSaveAndExit = new JButton("Salvar e Sair");
            btnSaveAndExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int choice = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Sair",
                            JOptionPane.YES_NO_OPTION);

                    if (choice == JOptionPane.YES_OPTION) {

                        clientManager.closeAndSave();
                        productManager.closeAndSave();
                        purchaseManager.closeAndSave();

                        System.exit(0);
                    }
                }
            });

            bottomPanel.add(btnSaveAndExit, BorderLayout.SOUTH);

            frame.add(bottomPanel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
