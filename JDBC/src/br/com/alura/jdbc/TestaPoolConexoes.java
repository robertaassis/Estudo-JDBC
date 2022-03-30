package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.SQLException;

public class TestaPoolConexoes {
	public static void main(String[] args) throws SQLException {
	
		ConnectionFactory factory = new ConnectionFactory();
	    
		for(int i=0;i<20;i++) { // como eu coloquei maximo de pool como 15, ele nao abre 20, abre no máximo 15. As outras  requisições esperarão o processamento das que estão ocupando os espaços já abertos ficar livre
			factory.recuperarConexao(); // conexao com banco
			System.out.println("conexão de numero: "+ i);
		}
		
	}
	 

}
