
package TeleLogin;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import NovaConta.Conta;
import PosLogin.TelaMenu;
import SQL.ConexaoSQL;
import SQL.SqlConsultarSaldo;
import SQL.SqlLogin;
import Utils.ValidaCPF;

public class TelaDeLogin extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    int numdeTent;
    public JPanel contentPane;
    public JTextField txtlogin;
    public JPasswordField Senha;
    public String Ativo = "A";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    TelaDeLogin frame = new TelaDeLogin();
                    frame.setVisible(true);
                    frame.setTitle("Caixa Eletrônico");
                    //CriarTabelas sql = new CriarTabelas();
                    //sql.criarTabelaConta();
                    //sql.criarTabelaLog();
                    //ConexaoSQL sql = new ConexaoSQL();
                    //sql.Conecta();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TelaDeLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 412, 305);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtlogin = new JTextField();
        txtlogin.setHorizontalAlignment(SwingConstants.LEFT);
        txtlogin.setToolTipText("");
        txtlogin.setBounds(77, 77, 86, 20);
        contentPane.add(txtlogin);
        txtlogin.setColumns(10);

        Senha = new JPasswordField(5);
        Senha.setBounds(215, 77, 86, 20);
        contentPane.add(Senha);

        JComboBox<String> Tipo = new JComboBox<String>();
        Tipo.setBounds(130, 120, 124, 20);
        contentPane.add(Tipo);
        Tipo.addItem("Conta Salário");
        Tipo.addItem("Conta Poupança");
        Tipo.addItem("Conta Corrente");

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(145, 153, 89, 23);
        contentPane.add(btnLogin);

        JButton ZeraContador = new JButton("Conta Bloqueada?");
        ZeraContador.setBounds(120, 180, 150, 23);
        contentPane.add(ZeraContador);

        JLabel lblDigiteSeuCpf = new JLabel("Digite seu CPF");
        lblDigiteSeuCpf.setBounds(77, 60, 88, 14);
        contentPane.add(lblDigiteSeuCpf);

        JLabel lblDigiteSuaSenha = new JLabel("Digite sua senha");
        lblDigiteSuaSenha.setBounds(215, 60, 99, 14);
        contentPane.add(lblDigiteSuaSenha);

        JLabel lblNewLabel = new JLabel("Tipo de Conta");
        lblNewLabel.setBounds(130, 105, 124, 14);
        contentPane.add(lblNewLabel);

        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {
                    String CPF = txtlogin.getText();
                    @SuppressWarnings("deprecation")
                    int senha = Integer.parseInt(Senha.getText());
                    int Tipo_Conta = Tipo.getSelectedIndex();
                    SqlLogin sql = new SqlLogin();
                    if (ValidaCPF.isCPF(CPF) == true) {
                        if (sql.checklogin(CPF, senha, Tipo_Conta) == true) {

                            if (sql.checkcontaAtiva(Conta.numConta) == true) {
                                if (sql.checkcontaBloc(Conta.numConta) == true) {
                                    JOptionPane.showMessageDialog(null, "Sua conta está Bloqueada!");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Login efetuado com sucesso");
                                    SqlConsultarSaldo.RemovCont(CPF, Tipo_Conta);
                                    dispose();
                                    TelaMenu tel = new TelaMenu();
                                    tel.setVisible(true);
                                    tel.setTitle("Menu Inicial");

                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Sua conta está inativa!");

                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Login ou senha inválido!");
                            SqlConsultarSaldo.AdicionaCont(CPF, Tipo_Conta);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "CPF Inválido");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Login ou senha Inválido!");
                }

            }
        });
        ZeraContador.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String CPF = (JOptionPane.showInputDialog(ZeraContador, "Digite seu CPF"));
                    int token = Integer.parseInt(JOptionPane.showInputDialog(ZeraContador, "Digite seu token para desbloquear"));
                    int Tipo_Conta = Tipo.getSelectedIndex();

                    SqlLogin sql = new SqlLogin();
                    if (sql.checktoken(token) == true) {
                        SqlConsultarSaldo.RemovCont(CPF, Tipo_Conta);
                    } else {
                        JOptionPane.showMessageDialog(null, "Token Invalido!");

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Foram digitados dados Inválidos!");
                }

            }
        });
    }
}
