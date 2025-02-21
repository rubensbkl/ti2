package model;

public class Usuario {
	private int id;
	private String nome;
	private String login;
	private String senha;
	private char sexo;
	
	public Usuario() {
		this.id = -1;
		this.nome = "";
		this.login = "";
		this.senha = "";
		this.sexo = '*';
	}
	
	public Usuario(int id, String nome, String login, String senha, char sexo) {
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.sexo = sexo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Usuario [ID = " + id + ", nome = " + nome + ", login = " + login + ", senha = " + senha + ", sexo = " + sexo + "]";
	}
}
