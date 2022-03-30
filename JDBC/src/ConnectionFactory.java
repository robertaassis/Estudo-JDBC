import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

// classe de conexao com banco; se eu mudar algo no banco (como senha) eu só preciso alterar aqui
// toda vez que ela for chamada, é porque queremos a conexao com banco

public class ConnectionFactory {
	
	public DataSource dataSource;
	
	
	/* pool de conexões - técnica simples para evitar esse constante "abre-fecha" de conexões com banco de dados é manter um determinado número delas sempre abertas (um "pool" de conexões) 
	 e simplesmente reusá-las quando necessário, dessa forma você diminui tanto o gasto de recursos da máquina quanto o tempo de resposta da sua aplicação */
	public ConnectionFactory() { // pool de conexões do mysql
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimeZone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("admin");
		comboPooledDataSource.setPassword("root");
		
		comboPooledDataSource.setMaxPoolSize(15); // carrega pool com 15 conexoes
		
		this.dataSource = comboPooledDataSource;
	}
	
	public Connection recuperarConexao() throws SQLException {
		return this.dataSource.getConnection(); 
	}

}
