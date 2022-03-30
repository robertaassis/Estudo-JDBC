package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
	     Connection connection = factory.recuperarConexao();
	     
	     PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID>3");
	     
	     stm.execute();
	     
	    Integer linhasModificadas = stm.getUpdateCount(); // retorna quantas linhas foram modificadas depois do statement
	    System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);
	    connection.close();
	}

}
