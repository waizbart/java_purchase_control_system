package Interface.Tabs.Produtos.Tabs;

import java.util.Date;

import javax.swing.*;

import Managers.ClientManager;
import Models.Client.Address;
import Models.Client.ClientPF;
import Models.Client.ClientPJ;

public class Criar extends JPanel {
        public Criar() {
                GroupLayout layout = new GroupLayout(this);

                JLabel nome = new JLabel("Nome:");
                JTextField nomeInput = new JTextField();

                JLabel tipoPessoa = new JLabel("Tipo de Pessoa:");

                JRadioButton pessoaFisica = new JRadioButton("Pessoa Física");
                JRadioButton pessoaJuridica = new JRadioButton("Pessoa Jurídica");

                ButtonGroup tipoPessoaInput = new ButtonGroup();
                tipoPessoaInput.add(pessoaFisica);
                tipoPessoaInput.add(pessoaJuridica);
                pessoaFisica.setSelected(true);

                JLabel cpfCnpjLabel = new JLabel("CPF:");
                JTextField cpfCnpjInput = new JTextField();

                JLabel razaoSocialLabel = new JLabel("Razão Social:");
                JTextField razaoSocialInput = new JTextField();
                razaoSocialInput.setVisible(false);
                razaoSocialLabel.setVisible(false);

                JLabel numDiasOuParcelasLabel = new JLabel("Número máximo de parcelas:");
                JTextField numDiasOuParcelasInput = new JTextField();

                JLabel ruaLabel = new JLabel("Rua:");
                JTextField ruaInput = new JTextField();

                JLabel numeroLabel = new JLabel("Número:");
                JTextField numeroInput = new JTextField();

                JLabel bairroLabel = new JLabel("Bairro:");
                JTextField bairroInput = new JTextField();

                JLabel cepLabel = new JLabel("CEP:");
                JTextField cepInput = new JTextField();

                JLabel cidadeLabel = new JLabel("Cidade:");
                JTextField cidadeInput = new JTextField();

                JLabel estadoLabel = new JLabel("Estado:");
                JTextField estadoInput = new JTextField();

                JButton salvar = new JButton("Salvar");

                pessoaFisica.addActionListener(e -> {
                        if (pessoaFisica.isSelected()) {
                                cpfCnpjLabel.setText("CPF:");
                                nome.setText("Nome:");
                                numDiasOuParcelasLabel.setText("Número máximo de parcelas:");

                                // hide razao social
                                razaoSocialLabel.setVisible(false);
                                razaoSocialInput.setVisible(false);
                        }
                });

                pessoaJuridica.addActionListener(e -> {
                        if (pessoaJuridica.isSelected()) {
                                cpfCnpjLabel.setText("CNPJ:");
                                numDiasOuParcelasLabel.setText("Número máximo de dias para pagamento:");

                                nome.setText("Nome Fantasia:");

                                // show razao social
                                razaoSocialLabel.setVisible(true);
                                razaoSocialInput.setVisible(true);
                        }
                });

                salvar.addActionListener(
                                e -> {
                                        try {
                                                String nomeCliente = nomeInput.getText();
                                                String cpfCnpj = cpfCnpjInput.getText();
                                                String razaoSocial = razaoSocialInput.getText();
                                                String numDiasOuParcelas = numDiasOuParcelasInput.getText();
                                                String rua = ruaInput.getText();
                                                String numero = numeroInput.getText();
                                                String bairro = bairroInput.getText();
                                                String cep = cepInput.getText();
                                                String cidade = cidadeInput.getText();
                                                String estado = estadoInput.getText();

                                                Address address = new Address(rua, numero, bairro, cep, cidade, estado);

                                                if (pessoaFisica.isSelected()) {
                                                        if (nomeCliente.equals("") || cpfCnpj.equals("")
                                                                        || numDiasOuParcelas.equals("")
                                                                        || rua.equals("")
                                                                        || numero.equals("") || bairro.equals("")
                                                                        || cep.equals("") || cidade.equals("")
                                                                        || estado.equals("")) {
                                                                JOptionPane.showMessageDialog(null,
                                                                                "Preencha todos os campos!");
                                                        } else {
                                                                ClientPF client = new ClientPF(cpfCnpj,
                                                                                Integer.parseInt(numDiasOuParcelas),
                                                                                nomeCliente, new Date(), address);

                                                                ClientManager.getInstance().create(client, "PF");
                                                        }
                                                } else {
                                                        if (nomeCliente.equals("") || cpfCnpj.equals("")
                                                                        || razaoSocial.equals("")
                                                                        || numDiasOuParcelas.equals("")
                                                                        || rua.equals("")
                                                                        || numero.equals("") || bairro.equals("")
                                                                        || cep.equals("") || cidade.equals("")
                                                                        || estado.equals("")) {
                                                                JOptionPane.showMessageDialog(null,
                                                                                "Preencha todos os campos!");
                                                        } else {
                                                                ClientPJ client = new ClientPJ(cpfCnpj, razaoSocial,
                                                                                Integer.parseInt(numDiasOuParcelas),
                                                                                nomeCliente, new Date(), address);

                                                                ClientManager.getInstance().create(client, "PJ");
                                                        }

                                                }

                                                nomeInput.setText("");
                                                cpfCnpjInput.setText("");
                                                razaoSocialInput.setText("");
                                                numDiasOuParcelasInput.setText("");
                                                ruaInput.setText("");
                                                numeroInput.setText("");
                                                bairroInput.setText("");
                                                cepInput.setText("");
                                                cidadeInput.setText("");
                                                estadoInput.setText("");

                                                JOptionPane.showMessageDialog(null, "Cliente criado com sucesso!");
                                        } catch (Exception error) {
                                                JOptionPane.showMessageDialog(null,
                                                                "Erro ao criar cliente! Verifique os campos e tente novamente.");
                                        }

                                });

                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);

                GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
                hGroup.addGroup(layout.createParallelGroup()
                                .addComponent(nome)
                                .addComponent(tipoPessoa)
                                .addComponent(cpfCnpjLabel)
                                .addComponent(razaoSocialLabel)
                                .addComponent(numDiasOuParcelasLabel)
                                .addComponent(ruaLabel)
                                .addComponent(numeroLabel)
                                .addComponent(bairroLabel)
                                .addComponent(cepLabel)
                                .addComponent(cidadeLabel)
                                .addComponent(estadoLabel));
                hGroup.addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                                .addComponent(pessoaFisica)
                                                .addComponent(pessoaJuridica))
                                .addComponent(nomeInput)
                                .addComponent(cpfCnpjInput)
                                .addComponent(razaoSocialInput)
                                .addComponent(numDiasOuParcelasInput)
                                .addComponent(ruaInput)
                                .addComponent(numeroInput)
                                .addComponent(bairroInput)
                                .addComponent(cepInput)
                                .addComponent(cidadeInput)
                                .addComponent(estadoInput)
                                .addComponent(salvar));
                layout.setHorizontalGroup(hGroup);

                GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(tipoPessoa)
                                .addComponent(pessoaFisica)
                                .addComponent(pessoaJuridica));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nome)
                                .addComponent(nomeInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(cpfCnpjLabel)
                                .addComponent(cpfCnpjInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(razaoSocialLabel)
                                .addComponent(razaoSocialInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(numDiasOuParcelasLabel)
                                .addComponent(numDiasOuParcelasInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(ruaLabel)
                                .addComponent(ruaInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(numeroLabel)
                                .addComponent(numeroInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(bairroLabel)
                                .addComponent(bairroInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(cepLabel)
                                .addComponent(cepInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(cidadeLabel)
                                .addComponent(cidadeInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(estadoLabel)
                                .addComponent(estadoInput));
                vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(salvar));
                layout.setVerticalGroup(vGroup);

                this.setLayout(layout);
        }
}