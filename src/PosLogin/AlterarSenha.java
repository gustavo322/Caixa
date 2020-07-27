
package PosLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import novaconta.Conta;
import sql.SqlAlterarSenha;

public class AlterarSenha extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Conta conta = new Conta();
    private JPanel contentPane;
    private JPasswordField txtsenha1;
    private JPasswordField txtsenha2;
    private JTextField SenhaAtual;

    public AlterarSenha() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("Alterar Senha");
        btnNewButton.addActionListener(new ActionListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int senha1 = Integer.parseInt(txtsenha1.getText());
                    int senha2 = Integer.parseInt(txtsenha2.getText());
                    int senha = Integer.parseInt(SenhaAtual.getText());

                    int tamSenha1 = txtsenha1.getText().length();
                    int tamSenha2 = txtsenha2.getText().length();

                    if (tamSenha1 == 5 && tamSenha2 == 5) {
                        if (SqlAlterarSenha.checkSenha(senha, conta.getCPF()) == true) {
                            if (senha1 == senha2) {
                                if (senha1 != SqlAlterarSenha.getSenha() || senha1 != SqlAlterarSenha.getSenha2() || senha1 != SqlAlterarSenha.getSenha3()) 
                                {
                                	SqlAlterarSenha.setasenha2igualSenha(conta.getCPF());
                                    SqlAlterarSenha.setasenha3igualSenha2(conta.getCPF());
                                    SqlAlterarSenha.alterarSenha(senha1, conta.getCPF());
                                    JOptionPane.showMessageDialog(null, "Senha alterada com sucesso");	
                                	
                                }
                                else {                              	
                                    JOptionPane.showMessageDialog(null, "Esta senha foi utilizada recentemente");                                                                  
                                }
                            } 
                            else {
                                JOptionPane.showMessageDialog(null, "Não foi possivel alterar a senha. A nova senha digitada deve ser identica a senha de confirma��o");

                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Senha atual incorreta");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "A senha deve conter 5 numeros!");
                    }
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel alterar a senha, existem campos obrigatórios não informados");
                }
                txtsenha1.setText("");
                SenhaAtual.setText("");
                txtsenha2.setText("");
            }
        });
        btnNewButton.setBounds(139, 166, 139, 23);
        contentPane.add(btnNewButton);

        JLabel lblNovaSenha = new JLabel("Nova Senha");
        lblNovaSenha.setBounds(140, 68, 86, 14);
        contentPane.add(lblNovaSenha);

        JLabel lblDigiteNovamente = new JLabel("Digite Novamente");
        lblDigiteNovamente.setBounds(140, 106, 110, 14);
        contentPane.add(lblDigiteNovamente);

        txtsenha1 = new JPasswordField(5);
        txtsenha1.setBounds(139, 82, 139, 20);
        contentPane.add(txtsenha1);

        txtsenha2 = new JPasswordField(5);
        txtsenha2.setBounds(139, 123, 139, 20);
        contentPane.add(txtsenha2);

        JButton btnNewButton1 = new JButton("Voltar");
        btnNewButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                TelaMenu tel = new TelaMenu();
                tel.setVisible(true);
                tel.setTitle("Menu Inicial");

            }
        });
        btnNewButton1.setBounds(139, 204, 139, 23);
        contentPane.add(btnNewButton1);

        SenhaAtual = new JPasswordField(5);
        SenhaAtual.setBounds(139, 46, 139, 20);
        contentPane.add(SenhaAtual);
        SenhaAtual.setColumns(10);

        JLabel lblSenhaAtual = new JLabel("Senha Atual");
        lblSenhaAtual.setBounds(140, 31, 86, 14);
        contentPane.add(lblSenhaAtual);
    }
}
