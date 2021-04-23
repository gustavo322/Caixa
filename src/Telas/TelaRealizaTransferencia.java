
package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Classes.Conta;
import SQL.SqlConsultarSaldo;
import SQL.SqlLogin;

public class TelaRealizaTransferencia extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtvalor;
    private JTextField txtConta;
    Conta conta = new Conta();

    public TelaRealizaTransferencia() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtvalor = new JTextField();
        txtvalor.setBounds(117, 23, 183, 20);
        contentPane.add(txtvalor);
        txtvalor.setColumns(10);

        txtConta = new JTextField();
        txtConta.setBounds(117, 63, 183, 20);
        contentPane.add(txtConta);
        txtConta.setColumns(10);

        JComboBox<String> Tipo_Conta = new JComboBox<String>();
        Tipo_Conta.setBounds(117, 100, 183, 20);
        contentPane.add(Tipo_Conta);

        Tipo_Conta.addItem("Conta Salario");
        Tipo_Conta.addItem("Conta Poupança");
        Tipo_Conta.addItem("Conta Corrente");

        JButton btnRealizarTransferencia = new JButton("Realizar Transferencia");
        btnRealizarTransferencia.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    SqlLogin sql = new SqlLogin();
                    int valor = Integer.parseInt(txtvalor.getText());
                    int Tipo = Tipo_Conta.getSelectedIndex();
                    int NumConta1 = Integer.parseInt(txtConta.getText());
                    SqlConsultarSaldo.ConsultarSaldo(Conta.getIdConta());
                    if (valor > SqlConsultarSaldo.ValorMaximo || valor < SqlConsultarSaldo.ValorMinimo) {
                        JOptionPane.showMessageDialog(null, "Valor a ser transferido é maior que o valor permitido de " + SqlConsultarSaldo.ValorMaximo);
                    } else {

                        if (valor >= 0) {
                            if (valor <= SqlConsultarSaldo.Saldo) {

                                if (sql.checklogin1(conta.getCPF(), NumConta1, Tipo) == true) {
                                    if (Tipo != conta.getTipoconta()) {
                                        SqlConsultarSaldo.SacaSaldoDeTransferenciaParaMesmoCPF(Conta.getIdConta(), valor);
                                        SqlConsultarSaldo.TransfereSaldo(NumConta1, valor, conta.getCPF());
                                        JOptionPane.showMessageDialog(null, "Transfêrencia de $ " + valor + " realizada com sucesso", "Transfêrencia", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Não é possivel realizar a transferência para a mesma conta em que você está logado");

                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro, CPF não possui vinculo a conta informada");
                                }

                            } else {

                                JOptionPane.showMessageDialog(null, "Valor a ser transferido de $" + valor + " maior que saldo em conta");
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Valor Inválido para transferencia.");

                        }

                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro, Valor Inválido para transferencia");
                }
                txtConta.setText("");
                txtvalor.setText("");

            }
        });
        btnRealizarTransferencia.setBounds(117, 131, 183, 23);
        contentPane.add(btnRealizarTransferencia);

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
        btnNewButton.setBounds(117, 165, 183, 23);
        contentPane.add(btnNewButton);

        JLabel lblNewLabel = new JLabel("Valor a ser transferido");
        lblNewLabel.setBounds(117, 9, 155, 14);
        contentPane.add(lblNewLabel);
        JLabel lblNewLabel_2 = new JLabel("Numero da Conta");
        lblNewLabel_2.setBounds(117, 48, 155, 14);
        contentPane.add(lblNewLabel_2);

        //	JLabel lblNewLabel_3 = new JLabel("Atenção! Cada Transfêrencia");
        //	lblNewLabel_3.setBounds(117, 202, 183, 14);
        //	contentPane.add(lblNewLabel_3);

        //	JLabel lblNewLabel_4 = new JLabel("para outro CPF será cobrado");
        //	lblNewLabel_4.setBounds(117, 216, 183, 14);
        //	contentPane.add(lblNewLabel_4);

        //	JLabel lblNewLabel_5 = new JLabel("uma taxa de 5 reais");
        //	lblNewLabel_5.setBounds(117, 228, 183, 14);
        //	contentPane.add(lblNewLabel_5);

    }
}
