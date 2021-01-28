
package SQL;

import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class CriarTabelas {

    public static void criarTabelaConta() {

        String sql1 = "CREATE TABLE `Conta` (";
        sql1 += "`id_conta` INT NOT NULL AUTO_INCREMENT,";
        sql1 += "`Num_conta` INT(12) NOT NULL,";
        sql1 += "`Nome` varchar(150) NOT NULL,";
        sql1 += "`CPF` varchar(12) NOT NULL,";
        sql1 += "`status` varchar(3) NOT NULL,";
        sql1 += "`Senha` int(5) NOT NULL,";
        sql1 += "`Senha2` int(5) ,";
        sql1 += "`Senha3` int(5) ,";
        sql1 += "`Tipo_Conta` int(2) NOT NULL,";
        sql1 += "`Saldo` int(50) NULL,";
        sql1 += "`Valor_maximo` int(5) NOT NULL,";
        sql1 += "`Valor_minimo` int(5) NOT NULL,";
        sql1 += "PRIMARY KEY (`id_conta`)";
        sql1 += ")";

        // Conex�o
        Conexao c = new Conexao();

        // Eexecutar comando sql
        try {
            Statement stmt = c.conectar().createStatement();
            stmt.execute(sql1);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }

    public void inserirCadastro(int NumConta, String CPF, String Nome, int Senha, int Tipo_Conta, int Saldo, int valorMaximo, int valorMinimo, String status) {

        // SQL
        String sql = "INSERT INTO Conta (`Num_conta`,`CPF`,`Nome`,`Senha`, `Tipo_Conta`, `Saldo`, `Valor_maximo`, `Valor_minimo`, `status`) VALUES  (?,?,?,?,?,?,?,?,?)";

        // Conexao
        Conexao c = new Conexao();

        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement(sql);
            pstmt.setLong(1, NumConta);
            pstmt.setString(2, CPF);
            pstmt.setString(3, Nome);
            pstmt.setLong(4, Senha);
            pstmt.setLong(5, Tipo_Conta);
            pstmt.setLong(6, Saldo);
            pstmt.setLong(7, valorMaximo);
            pstmt.setLong(8, valorMinimo);
            pstmt.setString(9, status);

            pstmt.execute();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void inserirLog(int log_NumConta, String log_transacao, int log_valor) {

        // SQL
        String sql = "INSERT INTO log (`log_NumConta`, `log_transacao`, `log_valor`,`log_data`)" + "VALUES  (?,?,?,NOW())";

        // Conexao
        Conexao c = new Conexao();

        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement(sql);
            pstmt.setLong(1, log_NumConta);
            pstmt.setString(2, log_transacao);
            pstmt.setLong(3, log_valor);

            pstmt.execute();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro inserir os logs. Entre em contato com a agencia mais próxima");
        }
    }

}
