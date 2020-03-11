
package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexãoSQl {

    @SuppressWarnings("unused")
    public Connection Conecta() {
        Connection c = null;

        String usuario = "sa";
        String senha = "12345678";
        String url = "jdbc:sqlserver://pcbnu006845:1433;databaseName=ERP_VAREJO" + ";user=" + usuario + ";password=" + senha + ";";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conexao = DriverManager.getConnection(url);
            JOptionPane.showMessageDialog(null, "Conexão God");
        } catch (ClassNotFoundException e1) {
            // Erro caso o driver JDBC não foi instalado
            e1.printStackTrace();
        } catch (SQLException e) {
            // Erro caso haja problemas para se conectar ao banco de dados
            e.printStackTrace();
        }
        return c;
    }
}
