package Interface.Tabs.Compras.Tabs;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Managers.PurchaseManager;
import Models.Purchase.Purchase;

public class Buscar extends JPanel {
    private static PurchaseManager purchaseManager = PurchaseManager.getInstance();
    private ArrayList<Purchase> purchases;
    private JList<String> lista;
    private JTextField searchField;

    public Buscar() {
        purchases = purchaseManager.getPurchases();

        ArrayList<String> rows = new ArrayList<>();

        for (Purchase purchase : purchases) {
            rows.add(purchase.toString());
        }

        lista = new JList<>(rows.toArray(new String[rows.size()]));
        JScrollPane scroll = new JScrollPane(lista);

        lista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = lista.getSelectedIndex();
                    if (selectedIndex != -1) {
                        Purchase purchase = purchases.get(selectedIndex);
                        int purchaseId = purchase.getId();

                        JPanel changePanel = new JPanel();
                        JTextField newValueField = new JTextField(10);
                        JLabel label = new JLabel("Novo valor pago: ");
                        JButton alterarBtn = new JButton("Alterar");

                        alterarBtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String newValue = newValueField.getText();

                                try {
                                    double newValueDouble = Double.parseDouble(newValue);
                                    purchaseManager.updatePaidValue(purchaseId, newValueDouble);
                                    JOptionPane.showMessageDialog(null, "Valor pago atualizado com sucesso!");

                                    newValueField.setText("");
                                    updateList();

                                    Window window = SwingUtilities.getWindowAncestor(changePanel);
                                    if (window instanceof JDialog) {
                                        ((JDialog) window).dispose();
                                    }
                                } catch (Exception error) {
                                    JOptionPane.showMessageDialog(null, error.getMessage());
                                }
                            }
                        });

                        changePanel.add(label);
                        changePanel.add(newValueField);
                        changePanel.add(alterarBtn);

                        JDialog dialog = new JDialog();
                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog.add(changePanel);
                        dialog.pack();
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                    }
                }
            }
        });

        JButton cheapButton = new JButton("Mostrar compra mais barata");

        cheapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel showPanel = new JPanel();

                Purchase cheapestPurchase = purchaseManager.showMostCheapestPurchase();

                JLabel purchase = new JLabel(cheapestPurchase.toString());

                showPanel.add(purchase);

                JOptionPane.showMessageDialog(null, showPanel);
            }
        });

        JButton expensiveButton = new JButton("Mostrar compra mais cara");

        expensiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel showPanel = new JPanel();

                Purchase cheapestPurchase = purchaseManager.showMostExpensivePurchase();

                JLabel purchase = new JLabel(cheapestPurchase.toString());

                showPanel.add(purchase);

                JOptionPane.showMessageDialog(null, showPanel);
            }
        });

        JButton totalPeerMonthButton = new JButton("Mostrar total por mês");

        totalPeerMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel showPanel = new JPanel();
                showPanel.setLayout(new BoxLayout(showPanel, BoxLayout.Y_AXIS)); // Definindo o layout para orientação
                                                                                 // vertical
                double[] totalPeerMonth = purchaseManager.getTotalValueByMonth();

                JLabel jan = new JLabel("Janeiro: " + totalPeerMonth[0]);
                JLabel fev = new JLabel("Fevereiro: " + totalPeerMonth[1]);
                JLabel mar = new JLabel("Março: " + totalPeerMonth[2]);
                JLabel abr = new JLabel("Abril: " + totalPeerMonth[3]);
                JLabel mai = new JLabel("Maio: " + totalPeerMonth[4]);
                JLabel jun = new JLabel("Junho: " + totalPeerMonth[5]);
                JLabel jul = new JLabel("Julho: " + totalPeerMonth[6]);
                JLabel ago = new JLabel("Agosto: " + totalPeerMonth[7]);
                JLabel set = new JLabel("Setembro: " + totalPeerMonth[8]);
                JLabel out = new JLabel("Outubro: " + totalPeerMonth[9]);
                JLabel nov = new JLabel("Novembro: " + totalPeerMonth[10]);
                JLabel dez = new JLabel("Dezembro: " + totalPeerMonth[11]);

                showPanel.add(jan);
                showPanel.add(fev);
                showPanel.add(mar);
                showPanel.add(abr);
                showPanel.add(mai);
                showPanel.add(jun);
                showPanel.add(jul);
                showPanel.add(ago);
                showPanel.add(set);
                showPanel.add(out);
                showPanel.add(nov);
                showPanel.add(dez);

                JOptionPane.showMessageDialog(null, new JScrollPane(showPanel), "Valores por Mês",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });

        searchField = new JTextField(30);
        JButton searchBtn = new JButton("Buscar");

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText().toLowerCase();
                ArrayList<String> filteredRows = new ArrayList<>();

                for (Purchase purchase : purchases) {
                    if (purchase.toString().toLowerCase().contains(searchTerm)) {
                        filteredRows.add(purchase.toString());
                    }
                }

                lista.setListData(filteredRows.toArray(new String[filteredRows.size()]));
            }
        });

        JButton reloadBtn = new JButton("Recarregar");

        reloadBtn.addActionListener(e -> {
            updateList();
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        JPanel filtersPanel = new JPanel();
        filtersPanel.add(cheapButton);
        filtersPanel.add(expensiveButton);
        filtersPanel.add(totalPeerMonthButton);

        JPanel reloadPanel = new JPanel();
        reloadPanel.setLayout(new BoxLayout(reloadPanel, BoxLayout.X_AXIS));
        reloadPanel.add(Box.createHorizontalGlue());
        reloadPanel.add(reloadBtn);
        reloadPanel.add(Box.createHorizontalGlue());

        add(searchPanel);
        add(filtersPanel);
        add(scroll);
        add(reloadPanel);
    }

    private void updateList() {
        purchases = purchaseManager.getPurchases();

        ArrayList<String> rows = new ArrayList<>();

        for (Purchase purchase : purchases) {
            rows.add(purchase.toString());
        }

        lista.setListData(rows.toArray(new String[rows.size()]));
    }
}
