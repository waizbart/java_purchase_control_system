package Interface.Tabs.Produtos.Tabs;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Managers.ProductManager;
import Models.Product.Product;

public class Buscar extends JPanel {
    private static ProductManager productManager = ProductManager.getInstance();
    private ArrayList<Product> products;
    private JList<String> lista;
    private JTextField searchField;

    public Buscar() {
        products = productManager.getProducts();
        ArrayList<String> rows = new ArrayList<>();

        for (Product product : products) {
            rows.add(product.toString());
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

                for (Product product : products) {
                    if (product.toString().toLowerCase().contains(searchTerm)) {
                        filteredRows.add(product.toString());
                    }
                }

                lista.setListData(filteredRows.toArray(new String[filteredRows.size()]));
            }
        });

        JButton reloadBtn = new JButton("Recarregar");

        reloadBtn.addActionListener(e -> {
            products = productManager.getProducts();
            ArrayList<String> rows2 = new ArrayList<>();

            for (Product product : products) {
                rows2.add(product.toString());
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
