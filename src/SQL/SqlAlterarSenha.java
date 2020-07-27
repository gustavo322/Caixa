
package sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class SqlAlterarSenha {

	private static int senha;
	private static int senha2;
    private static int senha3;

    public static boolean checkSenha(int senha, String cpf) {
        boolean check = false;

        // Conexao
        Conexao c = new Conexao();

        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("SELECT * FROM conta WHERE Senha=? AND CPF=?");
            pstmt.setLong(1, senha);
            pstmt.setString(2, cpf);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                setSenha(rs.getInt("Senha"));
                setSenha2(rs.getInt("Senha2"));
                setSenha3(rs.getInt("Senha3"));

                check = true;

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return check;
    }

    // Cadastrar produto
    public static void alterarSenha(int senhanova, String cpf) {

        // SQL
        String sql = "UPDATE conta SET Senha=? WHERE CPF=?";

        // Conexao
        Conexao c = new Conexao();

        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement(sql);
            pstmt.setLong(1, senhanova);
            pstmt.setString(2, cpf);

            pstmt.execute();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void setasenha2igualSenha(String cpf) {

        // SQL
        String sql = "UPDATE conta SET Senha2=" + getSenha() + " WHERE CPF=?";

        // Conexao
        Conexao c = new Conexao();

        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement(sql);
            pstmt.setString(1, cpf);

            pstmt.execute();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void setasenha3igualSenha2(String cpf) {

        // SQL
        String sql = "UPDATE conta SET Senha3=" + getSenha2() + " WHERE CPF=?";

        // Conexao
        Conexao c = new Conexao();

        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement(sql);
            pstmt.setString(1, cpf);

            pstmt.execute();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

	public static int getSenha() {
		return senha;
	}

	public static void setSenha(int senha) {
		SqlAlterarSenha.senha = senha;
	}

	public static int getSenha2() {
		return senha2;
	}

	public static void setSenha2(int senha2) {
		SqlAlterarSenha.senha2 = senha2;
	}

	public static int getSenha3() {
		return senha3;
	}

	public static void setSenha3(int senha3) {
		SqlAlterarSenha.senha3 = senha3;
	}

}
