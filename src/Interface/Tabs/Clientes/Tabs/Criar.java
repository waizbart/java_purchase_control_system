package Interface.Tabs.Clientes.Tabs;

import javax.swing.*;

public class Criar extends JPanel {
    public Criar() {
        GroupLayout layout = new GroupLayout(this);

        JLabel nome = new JLabel("Nome:");
        JTextField nomeInput = new JTextField();


        JLabel tipoPessoa = new JLabel("Tipo de Pessoa:");

        JRadioButtonMenuItem pessoaFisica = new JRadioButtonMenuItem("Pessoa Física");
        JRadioButtonMenuItem pessoaJuridica = new JRadioButtonMenuItem("Pessoa Jurídica");

        ButtonGroup tipoPessoaInput = new ButtonGroup();

        tipoPessoaInput.add(pessoaFisica);
        tipoPessoaInput.add(pessoaJuridica);



        JLabel cpf = new JLabel("CPF:");
        JTextField cpfInput = new JTextField();

        JLabel email = new JLabel("Email:");
        JTextField emailInput = new JTextField();

        JLabel telefone = new JLabel("Telefone:");
        JTextField telefoneInput = new JTextField();

        JButton salvar = new JButton("Salvar");

        JButton cancelar = new JButton("Cancelar");

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nome)
                                .addComponent(cpf)
                                .addComponent(email)
                                .addComponent(telefone))
                        .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nomeInput)
                                .addComponent(cpfInput)
                                .addComponent(emailInput)
                                .addComponent(telefoneInput)))
                .addGroup(layout.createSequentialGroup()
                        .addGap(10)
                        .addComponent(salvar)
                        .addGap(10)
                        .addComponent(cancelar)
                        .addGap(10)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nome)
                        .addComponent(nomeInput))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cpf)
                        .addComponent(cpfInput))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(email)
                        .addComponent(emailInput))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(telefone)
                        .addComponent(telefoneInput))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(salvar)
                        .addComponent(cancelar))
                .addGap(10));

        this.setLayout(layout);

    }
}
