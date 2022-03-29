import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public class TestaInsercao {
	 public static void main(String[] args) throws SQLException {
		 ConnectionFactory factory = new ConnectionFactory();
	     Connection connection = factory.recuperarConexao();
	     
	     Statement stm = connection.createStatement();
	     
	  // vai retornar false, pq insert nao retorna nada; LOGO NAO É INTERESSANTE USAR
	    // boolean resultado = stm.execute("INSERT INTO PRODUTO (nome, descricao) VALUES ('mouse', 'Mouse sem fio')"); 
	     
	      // interessante usar pois como é insert irá retornar a chave gerada
	     stm.execute("INSERT INTO PRODUTO (nome, descricao) VALUES ('mouse', 'Mouse sem fio')", Statement.RETURN_GENERATED_KEYS);
	     
	     ResultSet rst = stm.getGeneratedKeys(); // retorna o id gerado
	     
	     while(rst.next()) {
	    	 Integer id = rst.getInt(1); // tem 2 getInt, um que você coloca a coluna direto que vc quer, ex: "NOME", outro vc coloca o numero da coluna. Como id é a primeira coluna, por isso fica 1
	    	 System.out.println("O id criado foi: " + id);
	     }
	 
	     connection.close();
	 }
     
}
