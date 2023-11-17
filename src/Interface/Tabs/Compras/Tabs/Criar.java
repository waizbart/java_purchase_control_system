package Interface.Tabs.Compras.Tabs;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import Managers.ProductManager;
import Managers.PurchaseManager;

import Models.Client.Client;
import Models.Product.Product;
import Models.Purchase.Purchase;
import Models.Purchase.PurchaseItem;

public class Criar extends JPanel {
        private JList<String> productsList;
        private static ProductManager productManager = ProductManager.getInstance();
        private static PurchaseManager purchaseManager = PurchaseManager.getInstance();
        private ArrayList<Product> products;

        public Criar() {
                GroupLayout layout = new GroupLayout(this);

                JLabel id = new JLabel("* ID:");
                JTextField idInput = new JTextField();

                JLabel clientCpfCnpj = new JLabel("* CPF/CNPJ do cliente:");
                JTextField clientCpfCnpjInput = new JTextField();

                products = productManager.getProducts();

                ArrayList<String> rows = new ArrayList<>();

                for (Product product : products) {
                        rows.add(product.toString());
                }

                JLabel productsLabel = new JLabel("* Produtos:");
                productsList = new JList<>(rows.toArray(new String[rows.size()]));
                JScrollPane productsScrollPane = new JScrollPane(productsList);

                JButton salvar = new JButton("Salvar");

                salvar.addActionListener(
                                e -> {
                                        try {
                                                int idValue = Integer.parseInt(idInput.getText());
                                                String clientCpfCnpjValue = clientCpfCnpjInput.getText();
                                                ArrayList<PurchaseItem> selectedItems = new ArrayList<>();

                                                if (clientCpfCnpjValue.length() != 11
                                                                && clientCpfCnpjValue.length() != 14) {
                                                        throw new Error("CPF/CNPJ inválido.");
                                                }

                                                for (int index : productsList.getSelectedIndices()) {
                                                        selectedItems.add(new PurchaseItem(1, products.get(index)));
                                                }

                                                if (selectedItems.isEmpty())
                                                        throw new Error("Selecione ao menos um produto.");

                                                Purchase purchase = new Purchase(idValue, new Date(),
                                                                clientCpfCnpjValue,
                                                                selectedItems.toArray(new PurchaseItem[selectedItems
                                                                                .size()]));

                                                purchaseManager.create(purchase);

                                                JOptionPane.showMessageDialog(null, "Compra criada com sucesso.");

                                                idInput.setText("");
                                                clientCpfCnpjInput.setText("");
                                                productsList.clearSelection();

                                        } catch (Error error) {
                                                JOptionPane.showMessageDialog(null, error.getMessage());
                                        } catch (Exception error) {
                                                JOptionPane.showMessageDialog(null, "Erro ao criar produto.");
                                        }
                                });

                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);

                GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
                hGroup.addGroup(layout.createParallelGroup()
                                .addComponent(id)
                                .addComponent(clientCpfCnpj)
                                .addComponent(productsLabel)
                                .addComponent(salvar));
                hGroup.addGroup(layout.createParallelGroup()
                                .addComponent(idInput)
                                .addComponent(clientCpfCnpjInput)
                                .addComponent(productsScrollPane)
                                .addComponent(salvar));
                layout.setHorizontalGroup(hGroup);

                GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(id)
                                .addComponent(idInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(clientCpfCnpj)
                                .addComponent(clientCpfCnpjInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(productsLabel)
                                .addComponent(productsScrollPane));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(salvar));
                layout.setVerticalGroup(vGroup);

                this.setLayout(layout);
        }
}