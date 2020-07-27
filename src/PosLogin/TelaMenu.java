
package PosLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import novaconta.CadastraConta;
import novaconta.Conta;
import sql.SqlConsultarSaldo;
import TeleLogin.TelaDeLogin;
import Utils.GeradordePDF;

public class TelaMenu extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;

    public TelaMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setEditable(false);
        textField.setBounds(295, 39, 117, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Exibir Saldo");
        btnNewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                SqlConsultarSaldo.ConsultarSaldo(Conta.getIdConta());

                textField.setText(String.valueOf(SqlConsultarSaldo.Saldo + " $"));
            }
        });
        btnNewButton.setBounds(295, 70, 117, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Realizar Saque Ou Depósito");
        btnNewButton_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                TelaSacarDepositar tel = new TelaSacarDepositar();
                tel.setVisible(true);
                tel.setTitle("Realizar Saque ou Depósito");
            }
        });
        btnNewButton_1.setBounds(10, 36, 238, 23);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Alterar Senha");
        btnNewButton_2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AlterarSenha alt = new AlterarSenha();
                alt.setVisible(true);
                alt.setTitle("Alteração de Senha");

            }
        });
        btnNewButton_2.setBounds(10, 95, 238, 23);
        contentPane.add(btnNewButton_2);

        JButton btnRealizarTransferencia = new JButton("Realizar Transferência");
        btnRealizarTransferencia.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                TelaRealizaTransferencia tel = new TelaRealizaTransferencia();
                tel.setVisible(true);
                tel.setTitle("Realizar Transferência");
            }
        });
        btnRealizarTransferencia.setBounds(10, 65, 238, 23);
        contentPane.add(btnRealizarTransferencia);

        JButton btnNewButton_3 = new JButton("Cadastrar Nova Conta");
        btnNewButton_3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CadastraConta tel = new CadastraConta();
                tel.setVisible(true);
                tel.setTitle("Cadastrar Conta");
            }
        });
        btnNewButton_3.setBounds(10, 230, 238, 23);

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                TelaDeLogin tel = new TelaDeLogin();
                tel.setVisible(true);
                tel.setTitle("Caixa Eletrônico");

            }
        });
        btnSair.setBounds(335, 228, 89, 23);
        contentPane.add(btnSair);

        JLabel lblNewLabel = new JLabel("Seja Bem vindo(a) " + Conta.usuario);
        lblNewLabel.setBounds(10, 11, 402, 14);
        contentPane.add(lblNewLabel);

        JButton btnNewButton_4 = new JButton("Consultar extrato");
        btnNewButton_4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                int num = 0;

                GeradordePDF.GeraPDF(Conta.numContaGeraCPF, num);
                num = num + 1;
            }
        });
        btnNewButton_4.setBounds(10, 125, 238, 23);
        contentPane.add(btnNewButton_4);

        if (Conta.getIdConta() == 1) {
            contentPane.add(btnNewButton_3);
        }
    }
}
