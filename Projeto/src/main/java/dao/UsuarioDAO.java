/*
	
	boolean execute(); -- Qlqr instrução SQL
	ResultSet executeQuery(); -- retorna o objeto gerado pela consulta
	int executeUpdate(); -- apenas INSERT, UPDATE e DELETE. Retorna a quantidade de atualizações

*/
package dao;

import jdbc.CNXJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.User;
import model.Fone;

public class UsuarioDAO implements CRUD {

	/*
	 * public static void main(String[] argv) { //PARA TESTE DE CONEXÃO
	 * 
	 * Connection c = CNXJDBC.connect();
	 * 
	 * if(c == null) { System.out.println("NÃO CONECTOU"); }else {
	 * System.out.println("CONECTOU"); }
	 * 
	 * }
	 */
	private final String SELECT_ALL = "SELECT * FROM Usuario A LEFT JOIN Telefone B ON A.id = B.idUsuario";
	private final String SELECT = "SELECT * FROM Usuario A LEFT JOIN Telefone B ON A.id = B.idUsuario WHERE email=?";
	private final String SELECT_FONES = "SELECT * FROM Telefone WHERE idUsuario=?";
	private final String INSERT_USER = "INSERT INTO Usuario (nome, email, senha) VALUES (?,?,?)";
	private final String INSERT_FONE = "INSERT INTO Telefone (idUsuario, ddd, numero, tipo) VALUES (?,?,?,?)";
	private final String UPDATE_NOME = "UPDATE Usuario SET nome=? WHERE id=?";
	private final String UPDATE_SENHA = "UPDATE Usuario SET senha=? WHERE id=?";
	private final String UPDATE_EMAIL = "UPDATE Usuario SET email=? WHERE id=?";
	private final String UPDATE_FONE = "UPDATE Telefone SET DDD=?, NUMERO=?, TIPO=? WHERE ID = ?";
	private final String DELETE_USER = "DELETE FROM Usuario WHERE id=?";
	private final String DELETE_TELEFONE = "DELETE FROM Telefone WHERE idUsuario=?";

	private PreparedStatement pst = null;

	
	public boolean insertUser(User user) {
		boolean ret = true;
		Connection c = CNXJDBC.connect();
		try {
			pst = c.prepareStatement(INSERT_USER);
			pst.setString(1, user.getNome());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getSenha());
			ret = pst.execute();
			pst.close();
			c.close();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		} finally {
			CNXJDBC.closeConnection();
		}
		return ret;
	}

	
	public boolean insertFone(int idUsuario, int ddd, String telefone, String tipo) {
		boolean ret = true;
		Connection c = CNXJDBC.connect();
		try {
			pst = c.prepareStatement(INSERT_FONE);
			pst.setInt(1, idUsuario);
			pst.setInt(2, ddd);
			pst.setString(3, telefone);
			pst.setString(4, tipo);
			ret = pst.execute();
			pst.close();
			c.close();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		} finally {
			CNXJDBC.closeConnection();
		}
		return ret;
	}

	
	public ArrayList<User> selectAllUsers() {
		ArrayList<User> allUsers = new ArrayList<User>();
		Connection c = CNXJDBC.connect();
		User u;
		try {
			pst = c.prepareStatement(SELECT_ALL);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setId(rs.getInt("id"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
				u.setFone(rs.getInt("ddd"), rs.getString("numero"), rs.getString("tipo"));
				allUsers.add(u);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			System.out.println("Erro ao executar Statment " + e.toString());
		} finally {
			CNXJDBC.closeConnection();
		}

		return allUsers;

	}

	
	public ArrayList<User> selectUser(String email) {
		ArrayList<User> allUsers = new ArrayList<User>();
		User u = new User();
		Connection c = CNXJDBC.connect();
		try {
			pst = c.prepareStatement(SELECT);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				u.setId(rs.getInt("id"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
				u.setFone(rs.getInt("ddd"), rs.getString("numero"), rs.getString("tipo"));
				allUsers.add(u);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			System.out.println("Erro ao executar Statment " + e.toString());
		} finally {
			CNXJDBC.closeConnection();
		}

		return allUsers;

	}

	
	public int updateUser(String s, int id, int option) {
		int ret = 0;
		Connection c = CNXJDBC.connect();
		try {
			if(option == 1) {
				pst = c.prepareStatement(UPDATE_NOME);
			}else if(option == 2){
				pst = c.prepareStatement(UPDATE_SENHA);
			}else {
				pst = c.prepareStatement(UPDATE_EMAIL);
			}
			pst.setString(1, s);
			pst.setInt(2, id);
			ret = pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		} finally {
			CNXJDBC.closeConnection();
		}
		return ret;
	}

	
	public boolean deleteUser(int id) {
		boolean ret = false;
		Connection c = CNXJDBC.connect();
		try {
			pst = c.prepareStatement(DELETE_USER);
			pst.setInt(1, id);
			ret = pst.execute();
			pst.close();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		} finally {
			CNXJDBC.closeConnection();
		}
		return ret;

	}
	
	
	public ArrayList<Fone> selectFoneNumbers(int idUsuario){
		ArrayList<Fone> list = new ArrayList<Fone>();
		Fone f;
		Connection c = CNXJDBC.connect();
		try {
			pst = c.prepareStatement(SELECT_FONES);
			pst.setInt(1, idUsuario);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				f = new Fone();
				f.setIdUser(rs.getInt("IDUSUARIO"));
				f.setDdd(rs.getInt("DDD"));
				f.setNumero(rs.getString("NUMERO"));
				f.setTipo(rs.getString("TIPO"));
				f.setIdFone(rs.getInt("ID"));
				list.add(f);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			System.out.println("Erro ao executar Statment " + e.toString());
		} finally {
			CNXJDBC.closeConnection();
		}
		
		return list;
	}
	
	
	public int updateFone(Fone f) { //ddd, numero, tipo, id
		int ret = 0;
		Connection c = CNXJDBC.connect();
		try {
			pst = c.prepareStatement(UPDATE_FONE);
			pst.setInt(1, f.getDdd());
			pst.setString(2, f.getNumero());
			pst.setString(3, f.getTipo());
			pst.setInt(4, f.getIdFone());
			ret = pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		} finally {
			CNXJDBC.closeConnection();
		}
		return ret;
	}
	
	
	public boolean deleteFone(int id) {
		boolean ret = false;
		Connection c = CNXJDBC.connect();
		try {
			pst = c.prepareStatement(DELETE_TELEFONE);
			pst.setInt(1, id);
			ret = pst.execute();
			pst.close();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		} finally {
			CNXJDBC.closeConnection();
		}
		return ret;
	}

}
