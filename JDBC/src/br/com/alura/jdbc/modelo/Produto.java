package br.com.alura.jdbc.modelo;

public class Produto {
	private Integer id;
	private String nome;
	private String descricao;
	
	public Produto(String nome, String descricao) { // se a pessoa instanciar colocando só 2 parametros chama esse
		this.nome = nome;
		this.descricao = descricao;
	}

	public Produto(Integer id, String nome, String descricao) { // se a pessoa instanciar colocando 3 parametros chama esse
		this.id=id;
		this.nome=nome;
		this.descricao = descricao;
	}
	
	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setId(Integer id) {
		this.id=id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("O produto criado foi: %d, %s, %s", this.id,this.nome,this.descricao);
	}
	
	
	
}
