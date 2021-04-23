
package Telas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import SQL.CriarTabelas;
import Utils.ValidaCPF;

public class CadastraConta extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNumConta;
    private JTextField txtCPF;
    private JTextField txtSaldoInicial;
    private JTextField txtValorMaximo;
    private JTextField txtValorMinimo;
    private JPasswordField passwordField;
    private JTextField txtNome;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    CadastraConta frame = new CadastraConta();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public CadastraConta() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 311);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtNumConta = new JTextField();
        txtNumConta.setBounds(60, 56, 138, 20);
        contentPane.add(txtNumConta);
        txtNumConta.setColumns(10);

        txtCPF = new JTextField();
        txtCPF.setColumns(10);
        txtCPF.setBounds(59, 94, 138, 20);
        contentPane.add(txtCPF);

        txtSaldoInicial = new JTextField();
        txtSaldoInicial.setColumns(10);
        txtSaldoInicial.setBounds(233, 56, 137, 20);
        contentPane.add(txtSaldoInicial);

        txtValorMaximo = new JTextField();
        txtValorMaximo.setColumns(10);
        txtValorMaximo.setBounds(232, 94, 137, 20);
        contentPane.add(txtValorMaximo);

        txtValorMinimo = new JTextField();
        txtValorMinimo.setColumns(10);
        txtValorMinimo.setBounds(233, 134, 137, 20);
        contentPane.add(txtValorMinimo);

        passwordField = new JPasswordField();
        passwordField.setBounds(60, 134, 138, 20);
        contentPane.add(passwordField);

        JButton btnNewButton2 = new JButton("Voltar");
        btnNewButton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                TelaMenu tel = new TelaMenu();
                tel.setVisible(true);
            }
        });
        btnNewButton2.setBounds(280, 239, 113, 23);
        contentPane.add(btnNewButton2);

        JComboBox<String> tipo = new JComboBox <String>();
        tipo.setBounds(157, 240, 113, 20);
        contentPane.add(tipo);
        tipo.addItem("Conta Salario");
        tipo.addItem("Conta Poupança");
        tipo.addItem("Conta Corrente");

        JLabel lblNewLabel = new JLabel("Numero da Conta");
        lblNewLabel.setBounds(60, 42, 138, 14);
        contentPane.add(lblNewLabel);

        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setBounds(59, 79, 138, 14);
        contentPane.add(lblCpf);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(60, 119, 138, 14);
        contentPane.add(lblSenha);

        JLabel lblSaldoInicial = new JLabel("Saldo Inicial Conta Corrente");
        lblSaldoInicial.setBounds(233, 42, 176, 14);
        contentPane.add(lblSaldoInicial);

        JLabel lblValorMaximoPara = new JLabel("Valor Máximo Para Saque");
        lblValorMaximoPara.setBounds(232, 79, 166, 14);
        contentPane.add(lblValorMaximoPara);

        JLabel lblValorMinimoPara = new JLabel("Valor Minimo Para Saque");
        lblValorMinimoPara.setBounds(233, 119, 166, 14);
        contentPane.add(lblValorMinimoPara);

        txtNome = new JTextField();
        txtNome.setBounds(59, 20, 311, 20);
        contentPane.add(txtNome);
        txtNome.setColumns(10);

        JLabel lblNomeCompletoDo = new JLabel("Nome Completo do Titular");
        lblNomeCompletoDo.setBounds(60, 5, 310, 14);
        contentPane.add(lblNomeCompletoDo);

        JButton btnNewButton = new JButton("Cadastrar");

        btnNewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numConta = Integer.parseInt(txtNumConta.getText());
                    String cpf = txtCPF.getText();
                    String nome = txtNome.getText();
                    int saldo = Integer.parseInt(txtSaldoInicial.getText());
                    int valorMaximo = Integer.parseInt(txtValorMaximo.getText());
                    int valorMinimo = Integer.parseInt(txtValorMinimo.getText());
                    @SuppressWarnings("deprecation")
                    int senha = Integer.parseInt(passwordField.getText());
                    String status = null;
                    int tipoConta = tipo.getSelectedIndex();
                    
                    if (ValidaCPF.isCPF(cpf) == true) {

                        CriarTabelas cria = new CriarTabelas();
                        cria.inserirCadastro(numConta, cpf, nome, senha, tipoConta, saldo, valorMaximo, valorMinimo, status);
                        cria.inserirLog(numConta, "Criação da Conta", saldo);
                        JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "CPF inv�lido!");
                    }
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Erro ao inserir o cadastro, tente novamente mais tarde");
                }
                txtCPF.setText("");
                txtNome.setText("");
                txtNumConta.setText("");
                txtSaldoInicial.setText("");
                txtValorMaximo.setText("");
                txtValorMinimo.setText("");
                passwordField.setText("");

            }
        });
        btnNewButton.setBounds(34, 239, 113, 23);
        contentPane.add(btnNewButton);
    }
}
