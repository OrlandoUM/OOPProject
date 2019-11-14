package dao;

import java.util.ArrayList;

import model.Fone;
import model.User;

public interface CRUD {
	
	public boolean insertUser(User user);
	public boolean insertFone(int idUsuario, int ddd, String telefone, String tipo);
	public ArrayList<User> selectAllUsers();
	public ArrayList<User> selectUser(String email);
	public int updateUser(String s, int id, int option);
	public ArrayList<Fone> selectFoneNumbers(int idUsuario);
	public boolean deleteUser(int id);
	public boolean deleteFone(int id);
	public int updateFone(Fone f);
}
