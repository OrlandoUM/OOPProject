package model;

import java.util.*;

public class User {
	
	public int id;
	public String nome;
	public String email;
	public String senha;
	ArrayList<Fone> fone = new ArrayList<Fone>(); 
	
	public void setFone(int ddd, String numero, String tipo) {
		Fone f = new Fone();
		f.setDdd(ddd);
		f.setNumero(numero);
		f.setTipo(tipo);
		this.fone.add(f);
	}
	
	public ArrayList<Fone> getFone() {
		return this.fone;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
