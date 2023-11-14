package Interface.Tabs.Compras.Tabs;

import java.util.ArrayList;
import javax.swing.*;
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
            purchases = purchaseManager.getPurchases();
            ArrayList<String> rows2 = new ArrayList<>();

            for (Purchase purchase : purchases) {
                rows2.add(purchase.toString());
            }

            lista.setListData(rows2.toArray(new String[rows2.size()]));
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        JPanel reloadPanel = new JPanel();
        reloadPanel.setLayout(new BoxLayout(reloadPanel, BoxLayout.X_AXIS));
        reloadPanel.add(Box.createHorizontalGlue());
        reloadPanel.add(reloadBtn);
        reloadPanel.add(Box.createHorizontalGlue());

        add(searchPanel);
        add(scroll);
        add(reloadPanel);
    }
}
