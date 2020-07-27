
package sql;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Conexao {

    // M�todo de conex�o
    public Connection conectar() {

        // Atributo
        Connection c = null;

        // Executar Conex�o
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost/CaixaEletronico", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na conexão" + e.getMessage());
        }

        // Reorno
        return c;

    }

}
