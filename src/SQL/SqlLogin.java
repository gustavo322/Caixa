package SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import NovaConta.Conta;

public class SqlLogin {

    Conta conta = new Conta();

    public boolean checklogin(String CPF, int SENHA, int Tipo_Conta) {
        boolean check = false;

        // Conexao
        Conexao c = new Conexao();

        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("SELECT * FROM conta WHERE CPF=? AND Senha=? AND Tipo_Conta = ?");
            pstmt.setString(1, CPF);
            pstmt.setLong(2, SENHA);
            pstmt.setLong(3, Tipo_Conta);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                check = true;
                Conta.setIdConta(rs.getInt("id_conta"));
                conta.setUsuario(rs.getString("Nome"));
                conta.setTipoconta(rs.getInt("Tipo_Conta"));
                conta.setCPF(rs.getString("CPF"));
                conta.setnumConta(rs.getInt("Num_conta"));
                Conta.numContaGeraCPF = (rs.getInt("Num_conta"));
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return check;
    }

    public boolean checklogin1(String CPF, int NumConta, int Tipo_Conta) {
        boolean check = false;

        // Conexao
        Conexao c = new Conexao();

        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("SELECT * FROM conta WHERE CPF=? and Num_Conta=? and Tipo_Conta=?");
            pstmt.setString(1, CPF);
            pstmt.setLong(2, NumConta);
            pstmt.setLong(3, Tipo_Conta);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                check = true;

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return check;
    }

    public boolean checkcontaAtiva(int NumConta) {
        boolean check = false;

        // Conexao
        Conexao c = new Conexao();

        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("SELECT * FROM conta WHERE Num_Conta=? && status != 0");
            pstmt.setLong(1, NumConta);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                //Conta.setStatus(rs.getString("status"));
                check = true;

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return check;
    }

    public boolean checkcontaBloc(int NumConta) {
        boolean check = false;

        // Conexao
        Conexao c = new Conexao();

        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("SELECT * FROM conta WHERE Num_Conta=? && Bloc >= 3");
            pstmt.setLong(1, NumConta);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                check = true;

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return check;
    }

    public boolean checktoken(int token) {
        boolean check = false;

        // Conexao
        Conexao c = new Conexao();

        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("SELECT * FROM conta WHERE token = ?");
            pstmt.setLong(1, token);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                check = true;

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return check;
    }
}
