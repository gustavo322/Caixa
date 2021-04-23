
package SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Classes.Conta;

public class SqlConsultarSaldo {

    public static int Saldo;
    public static int ValorMaximo;
    public static int ValorMinimo;

    public static void ConsultarSaldo(int idConta) {
        // Conexao
        Conexao c = new Conexao();
        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("SELECT * FROM `conta` where id_conta =?");
            pstmt.setLong(1, idConta);
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Saldo = rs.getInt("Saldo");
                ValorMaximo = rs.getInt("Valor_maximo");
                ValorMinimo = rs.getInt("Valor_minimo");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void DepositarSaldo(int idConta, int Valor) {
        // Conexao
        Conexao c = new Conexao();
        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("Update `conta` set saldo = saldo + ? where id_conta =?");
            pstmt.setLong(2, idConta);
            pstmt.setLong(1, Valor);
            pstmt.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void AdicionaCont(String CPF, int tipConta) {
        // Conexao
        Conexao c = new Conexao();
        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("Update `conta` set Bloc = Bloc + 1 where CPF =? && Tipo_Conta =?");
            pstmt.setString(1, CPF);
            pstmt.setLong(2, tipConta);
            pstmt.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void RemovCont(String CPF, int tipConta) {
        // Conexao
        Conexao c = new Conexao();
        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("Update `conta` set Bloc = 0 where CPF =? && Tipo_Conta =?");
            pstmt.setString(1, CPF);
            pstmt.setLong(2, tipConta);
            pstmt.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void SacarSadlo(int idConta, int Valor) {
        // Conexao
        Conexao c = new Conexao();
        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("Update `conta` set saldo = saldo - ? where id_conta =? ");
            pstmt.setLong(2, idConta);
            pstmt.setLong(1, Valor);
            pstmt.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void SacaSaldoDeTransferenciaParaOutroCPF(int idConta, int Valor) {
        // Conexao
        Conexao c = new Conexao();
        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("Update `conta` set saldo = (saldo - ?)-5 where id_conta =? ");
            pstmt.setLong(2, idConta);
            pstmt.setLong(1, Valor);
            pstmt.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void SacaSaldoDeTransferenciaParaMesmoCPF(int idConta, int Valor) {
        // Conexao
        Conexao c = new Conexao();
        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("Update `conta` set saldo = saldo - ? where id_conta =? ");
            pstmt.setLong(2, idConta);
            pstmt.setLong(1, Valor);
            pstmt.execute();
            CriarTabelas cria = new CriarTabelas();
            cria.inserirLog(Conta.numContaGeraCPF, "Transaferencia Saida", Valor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void TransfereSaldo(int NumConta1, int Valor, String CPF) {
        // Conexao
        Conexao c = new Conexao();
        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("Update `conta` set saldo = saldo + ? where Num_conta = ? and CPF = ?");
            pstmt.setLong(2, NumConta1);
            pstmt.setLong(1, Valor);
            pstmt.setString(3, CPF);
            pstmt.execute();
            CriarTabelas cria = new CriarTabelas();
            cria.inserirLog(NumConta1, "Transaferencia Entrada", Valor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
