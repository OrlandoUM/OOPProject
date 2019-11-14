package controller;

import java.sql.Connection;
import java.util.ArrayList;
import dao.UsuarioDAO;
import model.Fone;
import model.User;

public class Controle {
	
	/*public static void main(String[] argv) { 
		
		//UsuarioDAO usrDAO = new UsuarioDAO();
		
		//User user = new User();
		//User u;
		
		//user.setNome("Orlando");
		//user.setEmail("orlando_uchoa@hotmail.com");
		//user.setSenha("123");
		
		//usrDAO.insertUser(user);
		//u = usrDAO.selectUser("rlando_uchoa@hotmail.com");
		//System.out.println(u.getId());
		//System.out.println(u.getNome());
		//System.out.println(u.getEmail());
		//System.out.println(u.getSenha());
		//usrDAO.deleteUser(u);
		
		ArrayList<User> list = usrDAO.selectAllUsers();
		for(User u: list) {
			System.out.println(u.id);
			System.out.println(u.nome);
			System.out.println(u.email);
			System.out.println(u.senha);
			System.out.println("\n");
		}
		
	}*/
	
	UsuarioDAO usrDAO = new UsuarioDAO();
	User user = new User();
	ArrayList<User> list = new ArrayList<User>(); 
	
	
	public int efetuarLogin(String email, String senha) {
		int b = (-1);
		list = usrDAO.selectUser(email);
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getEmail().contentEquals(email)) {
					b = 1; // true
					break;
				}
			}
		}
		if(b == 1) {
			if(list.get(0).getSenha().contentEquals(senha)) {
				b = 1;
			}
			else {
				b = 0;
			}
		}
		
		return b;
		
	}

	public ArrayList<User> consulta(String email) {
		ArrayList<User> lista = new ArrayList<User>();
		lista = usrDAO.selectUser(email);
		
		return lista;
	}
	
	public ArrayList<User> listagem(){
		ArrayList<User> lista = new ArrayList<User>();
		
		lista = usrDAO.selectAllUsers();
		
		return lista;
	}
	
	public boolean incluirUsuario(User user) {
		boolean b, b1, bAux = true;
		ArrayList<User> u = new ArrayList<User>();
		int n;
		ArrayList<Fone> telefonesDoUsuario = new ArrayList<Fone>();
		
		b = usrDAO.insertUser(user);
		u = consulta(user.getEmail());
		
		telefonesDoUsuario = user.getFone();
		
		n = telefonesDoUsuario.size();
		for(int i = 0; i < n; i++) {
			b1 = usrDAO.insertFone(u.get(0).getId(), telefonesDoUsuario.get(i).getDdd(), telefonesDoUsuario.get(i).getNumero(), telefonesDoUsuario.get(i).getTipo());
			bAux = bAux && b1;
		}
		return (b && bAux);
	}
	
	public int alterarUsuario(String s, int id, int opcao) {
		int ret;
		
		ret = usrDAO.updateUser(s, id, opcao);
		
		return ret;
	}
	
	public ArrayList<Fone> mostrarTelefonesdoUsuario (int id){
		ArrayList<Fone> list = new ArrayList<Fone>();
		
		list = usrDAO.selectFoneNumbers(id);
		
		return list;
	}
	
	public int alterarFone(Fone f) {
		int ret;
		
		ret = usrDAO.updateFone(f);
		
		return ret;
	}
	
	public boolean removeUsuario(int id) {
		boolean ret, ret2;
		
		ret2 = usrDAO.deleteFone(id);
		ret = usrDAO.deleteUser(id);
		
		return (ret&&ret2);
	}
	
	
}
