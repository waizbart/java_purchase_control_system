package Interface.Tabs.Produtos.Tabs;

import java.util.Date;

import javax.swing.*;

import Managers.ProductManager;
import Models.Product.Product;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

public class Criar extends JPanel {
        public Criar() {
                GroupLayout layout = new GroupLayout(this);

                JLabel codigo = new JLabel("* Codigo:");
                JTextField codigoInput = new JTextField();

                JLabel nome = new JLabel("* Nome:");
                JTextField nomeInput = new JTextField();

                JLabel descricao = new JLabel("* Descrição:");
                JTextField descricaoInput = new JTextField();

                JLabel preco = new JLabel("* Preço (Ex.: 49.99):");
                JTextField precoInput = new JTextField();

                JLabel data = new JLabel("Data de validade:");
                UtilDateModel model = new UtilDateModel();
                JDatePicker datePicker = new JDatePicker(model);

                JButton salvar = new JButton("Salvar");

                salvar.addActionListener(
                                e -> {
                                        try {
                                                if (codigoInput.getText().isEmpty() || nomeInput.getText().isEmpty()
                                                                || descricaoInput.getText().isEmpty()
                                                                || precoInput.getText().isEmpty()) {
                                                        JOptionPane.showMessageDialog(null,
                                                                        "Preencha todos os campos obrigatórios!");
                                                        return;
                                                }

                                                Product product = new Product(
                                                                codigoInput.getText(),
                                                                nomeInput.getText(),
                                                                descricaoInput.getText(),
                                                                Double.parseDouble(precoInput.getText()),
                                                                (Date) datePicker.getModel().getValue());

                                                ProductManager productManager = ProductManager.getInstance();
                                                productManager.create(product);

                                                JOptionPane.showMessageDialog(null, "Produto criado com sucesso!");

                                                codigoInput.setText("");
                                                nomeInput.setText("");
                                                descricaoInput.setText("");
                                                precoInput.setText("");
                                                datePicker.getModel().setValue(null);

                                        } catch (Error error) {
                                                JOptionPane.showMessageDialog(null,
                                                                "Verifique os campos e tente novamente.");
                                        } catch (Exception error) {
                                                JOptionPane.showMessageDialog(null, "Erro ao criar produto.");
                                        }
                                });

                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);

                GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
                hGroup.addGroup(layout.createParallelGroup()
                                .addComponent(codigo)
                                .addComponent(nome)
                                .addComponent(descricao)
                                .addComponent(preco)
                                .addComponent(data)
                                .addComponent(salvar));
                hGroup.addGroup(layout.createParallelGroup()
                                .addComponent(codigoInput)
                                .addComponent(nomeInput)
                                .addComponent(descricaoInput)
                                .addComponent(precoInput)
                                .addComponent(datePicker)
                                .addComponent(salvar));
                layout.setHorizontalGroup(hGroup);

                GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(codigo)
                                .addComponent(codigoInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nome)
                                .addComponent(nomeInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(descricao)
                                .addComponent(descricaoInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(preco)
                                .addComponent(precoInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(data)
                                .addComponent(datePicker));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(salvar));
                layout.setVerticalGroup(vGroup);

                this.setLayout(layout);
        }
}