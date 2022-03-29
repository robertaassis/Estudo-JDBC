import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// classe de conexao com banco; se eu mudar algo no banco (como senha) eu só preciso alterar aqui
// toda vez que ela for chamada, é porque queremos a conexao com banco

public class ConnectionFactory {
	public Connection recuperarConexao() throws SQLException {
		return  DriverManager.getConnection("jdbc:mysql://localhost/loja_virtual?useTimeZone=true&serverTimezone=UTC","admin","root");
	}

}
