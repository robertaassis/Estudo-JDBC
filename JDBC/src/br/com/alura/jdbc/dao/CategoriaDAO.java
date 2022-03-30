package br.com.alura.jdbc.dao;

import java.sql.*;
import java.util.*;
import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

public class CategoriaDAO {
	private Connection connection;
	
	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Categoria> listar() throws SQLException{
		List<Categoria> categorias = new ArrayList<>();
		
		String sql = "SELECT * FROM CATEGORIA";
		try(PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()){
				while(rst.next()) {
					Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
					categorias.add(categoria);
				}
			}
		}
		return categorias;
	}
	
	public List<Categoria> listarComProdutos() throws SQLException{ // lista os produtos linkados a uma categoria
		Categoria ultima = null;
		List<Categoria> categorias = new ArrayList<>();
		
		String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA AS C INNER JOIN PRODUTO AS P ON P.CATEGORIA_ID=C.ID";
		try(PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()){ // traz 5 colunas, sendo 1 - c.id, 2-c.nome, 3-p.id, etc
				while(rst.next()) {
					if(ultima==null || !ultima.getNome().equals(rst.getString(2))) { // pra nao retornar categorias com mesmo nome. Ex: se fossemos colocar um iphone 8 e um samsung ambos entrariam como eletrodomestico em categoria, ai por isso escreve isso, para que nao haja repetição no banco
						Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
						ultima = categoria;
						categorias.add(categoria);
					}
					Produto produto = new Produto(rst.getInt(3), rst.getString(4), rst.getString(5));
					ultima.adicionar(produto); // cria uma lista de produtos linkados a cada categoria; quando a próxima categoria for chamada, instancia uma nova lista, ja que cada categoria tem uma lista de produtos diferentes
					
				}
			}
		}
		return categorias;
	}

}
