package br.com.alura.jdbc.dao;

import java.sql.*;

import java.util.*;

import br.com.alura.jdbc.modelo.Produto;


// tudo que é referente ao acesso de banco de dados em relação ao objeto fica aqui nessa classe DAO - data access object
public class ProdutoDAO {

		private Connection connection;
		
		public ProdutoDAO(Connection connection) {
			this.connection = connection;
		}
		
		public void salvarProduto(Produto produto) throws SQLException {
			String sql = "INSERT INTO PRODUTO(NOME, DESCRICAO) VALUES (?,?)";
			
			try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.setString(1,  produto.getNome());
				pstm.setString(2, produto.getDescricao());
				
				pstm.execute(); // insere no banco
				
				try(ResultSet rst = pstm.getGeneratedKeys()){ // pega as chaves primarias que acabaram de ser inseridas no banco
					while(rst.next()) {
						produto.setId(rst.getInt(1)); // pega o valor do id, que é colocado automaticamente ja que é AUTO INCREMENT e seta ele como o id do produto
					}
				}
				
				
			}
		}
		
		public List<Produto> listar() throws SQLException{
			List<Produto> produtos = new ArrayList<Produto>();
			String sql = "SELECT * FROM PRODUTO";
			
			try(PreparedStatement pstm = connection.prepareStatement(sql)){
				pstm.execute();
				
				try(ResultSet rst = pstm.getResultSet()){
					while(rst.next()) { // percorre a lista
						Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3)); // recupera o produto; chama segundo construtor
						/* EX: o primeiro produto do banco de dados é id=1, nome=tv, descricao=tv totvs. Quando instancia ele acima, ele coloca esses valores nos 
						 atributos da classe Produto, ou seja,this.id=1, this.nome=tv, this.descricao = tv totvs */
						produtos.add(produto); // adiciona ele na lista de produtos
					}
				}
			}
			return produtos; // retorna a lista de produtos
		}

}

