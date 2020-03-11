
package SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class SqlAlterarSenha {

    public static int Senha;
    public static int Senha2;
    public static int Senha3;

    public static boolean checkSenha(int SENHA, String CPF) {
        boolean check = false;

        // Conexao
        Conexao c = new Conexao();

        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("SELECT * FROM conta WHERE Senha=? AND CPF=?");
            pstmt.setLong(1, SENHA);
            pstmt.setString(2, CPF);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Senha = rs.getInt("Senha");
                Senha2 = rs.getInt("Senha2");
                Senha3 = rs.getInt("Senha3");

                check = true;

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return check;
    }

    // Cadastrar produto
    public static void AlterarSenha(int SenhaNova, String CPF) {

        // SQL
        String sql = "UPDATE conta SET Senha=? WHERE CPF=?";

        // Conexao
        Conexao c = new Conexao();

        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement(sql);
            pstmt.setLong(1, SenhaNova);
            pstmt.setString(2, CPF);

            pstmt.execute();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void Setasenha2igualSenha(String CPF) {

        // SQL
        String sql = "UPDATE conta SET Senha2=" + Senha + " WHERE CPF=?";

        // Conexao
        Conexao c = new Conexao();

        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement(sql);
            pstmt.setString(1, CPF);

            pstmt.execute();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void Setasenha3igualSenha2(String CPF) {

        // SQL
        String sql = "UPDATE conta SET Senha3=" + Senha2 + " WHERE CPF=?";

        // Conexao
        Conexao c = new Conexao();

        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement(sql);
            pstmt.setString(1, CPF);

            pstmt.execute();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
