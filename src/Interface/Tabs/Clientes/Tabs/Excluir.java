package Interface.Tabs.Clientes.Tabs;

import javax.swing.*;

import Managers.ClientManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Excluir extends JPanel {
    private JRadioButton cpfRadioButton, cnpjRadioButton, nomeRadioButton;
    private JTextField inputField;
    private JButton excluirButton;
    private JLabel inputLabel;

    public Excluir() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel descricaoLabel = new JLabel("Selecione o método de deleção:");
        add(descricaoLabel);

        cpfRadioButton = new JRadioButton("CPF");
        cnpjRadioButton = new JRadioButton("CNPJ");
        nomeRadioButton = new JRadioButton("Nome");

        ButtonGroup group = new ButtonGroup();
        group.add(cpfRadioButton);
        group.add(cnpjRadioButton);
        group.add(nomeRadioButton);
        cpfRadioButton.setSelected(true);

        add(cpfRadioButton);
        add(cnpjRadioButton);
        add(nomeRadioButton);

        inputField = new JTextField(30);

        inputLabel = new JLabel("CPF: ");

        excluirButton = new JButton("Excluir");

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();

                try {
                    if (cpfRadioButton.isSelected()) {
                        ClientManager.getInstance().deleteByCpf(input);
                    } else if (cnpjRadioButton.isSelected()) {
                        ClientManager.getInstance().deleteByCnpj(input);
                    } else if (nomeRadioButton.isSelected()) {
                        ClientManager.getInstance().deleteByName(input);
                    }

                    JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso");
                    inputField.setText("");
                } catch (Error error) {
                    JOptionPane.showMessageDialog(null, error.getMessage());
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir cliente");
                }
            }
        });

        cnpjRadioButton.addActionListener(e -> {
            if (cnpjRadioButton.isSelected()) {
                inputLabel.setText("CNPJ: ");
                inputField.setText("");
            }
        });

        cpfRadioButton.addActionListener(e -> {
            if (cpfRadioButton.isSelected()) {
                inputLabel.setText("CPF: ");
                inputField.setText("");
            }
        });

        nomeRadioButton.addActionListener(e -> {
            if (nomeRadioButton.isSelected()) {
                inputLabel.setText("Nome: ");
                inputField.setText("");
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(excluirButton);

        add(inputPanel);
    }
}
