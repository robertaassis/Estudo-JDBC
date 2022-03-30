package br.com.alura.jdbc;

import java.sql.*;
import java.util.*;

import br.com.alura.jdbc.dao.CategoriaDAO;
import br.com.alura.jdbc.dao.ProdutoDAO;
import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

public class TestaListagemDeCategorias {

	public static void main(String[] args) throws SQLException{
		
		try(Connection connection = new ConnectionFactory().recuperarConexao()){
			CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
			List<Categoria> listaDeCategorias = categoriaDAO.listarComProdutos();
			listaDeCategorias.stream().forEach(ct -> {
				System.out.println(ct.getNome());
				// printa os produtos ligados a cada categoria (chave estrangeira categoria_id)
				
				 
			  for(Produto produto: ct.getProdutos()) {
				  System.out.println(ct.getNome() + " - " + produto.getNome()); 
			  }
				 
				 
			});
		}
		

	}

}
