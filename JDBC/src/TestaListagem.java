import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;


public class TestaListagem {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexao();
        
       PreparedStatement stm = connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
       stm.execute(); // retorna true se retornar uma lista; se nao retornar nada, volta falso
       ResultSet rst = stm.getResultSet(); // retorna os resultados 
       
       while(rst.next()) { // enquanto tiver o proximo; vai percorrer a lista de resultados que volta do banco
    	   Integer id = rst.getInt("ID"); // puxa o valor da coluna ID
    	   System.out.println(id);
    	   String nome = rst.getString("NOME");
    	   System.out.println(nome);
    	   String descricao = rst.getString("DESCRICAO");
    	   System.out.println(descricao);
       }
       
       
       connection.close();
    }
}
