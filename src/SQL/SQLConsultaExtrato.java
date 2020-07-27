
package sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class SQLConsultaExtrato {

    public static void SQLConsultaExtrato(int numConta) {
        // Conexao
        Conexao c = new Conexao();
        // Comandos SQL
        try {
            PreparedStatement pstmt = c.conectar().prepareStatement("SELECT * FROM `log` where log_NumConta =?");
            pstmt.setLong(1, numConta);
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
}
