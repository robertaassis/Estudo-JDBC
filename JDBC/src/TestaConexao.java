import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// biblioteca de mysql ta em referenced libraries
public class TestaConexao {
    public static void main(String[] args) throws SQLException {
    	ConnectionFactory criaConexao = new ConnectionFactory();
        Connection connection = criaConexao.recuperarConexao();
        
        connection.close();
    }
}
