import java.sql.*;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
	    try(Connection connection = factory.recuperarConexao()){ // try with resources; como extends autocloseable ele fecha ja automaticamente sem eu ter que colocar close
	     connection.setAutoCommit(false); // agora eu controlo as transações
	     
	  // try with resources;
	     try (PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (? ,?)", Statement.RETURN_GENERATED_KEYS)) {
		     
		     adicionarVariavel("SmartTV", "45 polegadas", stm);
		     adicionarVariavel("Radio", "Com botão", stm);
		     
		     connection.commit(); // como autoCommit ta falso, eu que tenho que commitar
		     
		    
	     } catch (Exception e) {
	    	 e.printStackTrace();
	    	 System.out.println("ROLLBACK EXECUTADO");
	    	 connection.rollback(); // SE DER EXCEÇÃO NAO COMMITA, É DESCARTADO A TRANSAÇÃO
	    	 
	    	/* isso é importante pois, por exemplo, ao transferir dinheiro pra conta de alguem e por algum acaso der erro, tem que abortar a transação e seu dinheiro retornar pra sua conta,
		     por isso commit e rollback */
	     }
	     
	    
	    }
	     
	     

	}
	
	public static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException{
		stm.setString(1, nome); // seta no primeiro ? o nome
		 stm.setString(2, descricao); // seta no segundo ? a descricao
	     stm.execute();
	     
	     // try with resources;
	     try(ResultSet rst = stm.getGeneratedKeys()){ // retorna o id gerado
	     
	     while(rst.next()) {
	    	 Integer id = rst.getInt(1); // tem 2 getInt, um que você coloca a coluna direto que vc quer, ex: "NOME", outro vc coloca o numero da coluna. Como id é a primeira coluna, por isso fica 1
	    	 System.out.println("O id criado foi: " + id);
	     }
	    
	     }
	}

}
