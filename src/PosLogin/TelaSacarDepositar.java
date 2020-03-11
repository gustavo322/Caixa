
package PosLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import NovaConta.Conta;
import SQL.CriarTabelas;
import SQL.SqlConsultarSaldo;

public class TelaSacarDepositar extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    Conta conta = new Conta();
    public JTextField txtvalor;

    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     */
    public TelaSacarDepositar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtvalor = new JTextField();
        txtvalor.setBounds(155, 51, 86, 20);
        contentPane.add(txtvalor);
        txtvalor.setColumns(10);

        JButton btnSacar = new JButton("Sacar");
        btnSacar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SqlConsultarSaldo.ConsultarSaldo(Conta.getIdConta());

                    int valor = Integer.parseInt(txtvalor.getText());
                    if (valor >= 0) {
                        if (valor > SqlConsultarSaldo.ValorMaximo || valor < SqlConsultarSaldo.ValorMinimo) {
                            JOptionPane.showMessageDialog(btnSacar, "Valor a ser sacado é maior que o valor permitido de " + SqlConsultarSaldo.ValorMaximo);
                        } else if (valor > SqlConsultarSaldo.Saldo) {
                            JOptionPane.showMessageDialog(btnSacar, "Valor a ser sacado é maior que o valor em conta ");
                        }

                        else {

                            SqlConsultarSaldo.SacarSadlo(Conta.getIdConta(), valor);
                            JOptionPane.showMessageDialog(btnSacar, "Saque de $ " + valor + " foi efetuado com sucesso");
                            CriarTabelas cria = new CriarTabelas();
                            cria.inserirLog(conta.getnumConta(), "Saque", valor);

                        }
                    } else {
                        JOptionPane.showMessageDialog(btnSacar, "Valor de saque inválido");

                    }

                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(btnSacar, "Valor de saque não foi informado");
                }
            }
        });
        btnSacar.setBounds(112, 83, 89, 23);
        contentPane.add(btnSacar);

        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    SqlConsultarSaldo.ConsultarSaldo(Conta.getIdConta());

                    int valor = Integer.parseInt(txtvalor.getText());
                    if (valor >= 0) {

                        if (valor > SqlConsultarSaldo.ValorMaximo || valor < SqlConsultarSaldo.ValorMinimo) {
                            JOptionPane.showMessageDialog(null, "Valor a ser depostitado é maior que o valor permitido de " + SqlConsultarSaldo.ValorMaximo);

                        } else {
                            SqlConsultarSaldo.DepositarSaldo(Conta.getIdConta(), valor);
                            JOptionPane.showMessageDialog(null, "Depósito de $ " + valor + " foi efetuado com sucesso");
                            CriarTabelas cria = new CriarTabelas();
                            cria.inserirLog(conta.getnumConta(), "Depósito", valor);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Valor a ser Depósitado inválido ");

                    }
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Valor a ser Depósitado não foi informado ");
                }

            }
        });
        btnDepositar.setBounds(211, 83, 89, 23);
        contentPane.add(btnDepositar);

        JLabel lblNewLabel = new JLabel("Informe o valor:");
        lblNewLabel.setBounds(155, 34, 97, 14);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("Voltar");
        btnNewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                TelaMenu tel = new TelaMenu();
                tel.setVisible(true);
                tel.setTitle("Menu Inicial");

            }
        });
        btnNewButton.setBounds(155, 158, 86, 23);
        contentPane.add(btnNewButton);

    }
}
