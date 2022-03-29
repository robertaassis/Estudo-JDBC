import java.sql.*;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		String nome = "Mouse'"; // sql injection
		String descricao = "Mouse sem fio); delete * from produto;"; // sql injection
		ConnectionFactory factory = new ConnectionFactory();
	     Connection connection = factory.recuperarConexao();
	     
	     
	     // ele evita sql injection, ou seja, evita a manipulação do codigo mysql.
	    /* ex: mandam em um formulario na descricao o valor "Mouse; delete * from produto". Se colocassemos isso direto na query,
	      iria apagar tudo que tem no banco. Usando prepareStatement, ele trata tudo como se fosse uma string */
	     PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (? ,?)", Statement.RETURN_GENERATED_KEYS);
	     
		 stm.setString(1, nome); // seta no primeiro ? o nome
		 stm.setString(2, descricao); // seta no segundo ? a descricao
	     stm.execute();
	     
	     ResultSet rst = stm.getGeneratedKeys(); // retorna o id gerado
	     
	     while(rst.next()) {
	    	 Integer id = rst.getInt(1); // tem 2 getInt, um que você coloca a coluna direto que vc quer, ex: "NOME", outro vc coloca o numero da coluna. Como id é a primeira coluna, por isso fica 1
	    	 System.out.println("O id criado foi: " + id);
	     }

	}

}
