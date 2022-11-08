package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.Usuarion;

public class DaoUsuario {
	Conexion cx =null;
	
	public DaoUsuario() {
		cx=new Conexion();
	}
	
	public boolean insertarUsuario(Usuarion user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO usuario VALUES(null,?,?,?)");
			ps.setString(1, user.getUser());
			ps.setString(2, convertirSHA256(user.getPassword()));
			ps.setString(3, user.getNombre());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public ArrayList<Usuarion> fetchUsuarios(){
		ArrayList<Usuarion> lista=new ArrayList<Usuarion>();
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM usuario");
			rs=ps.executeQuery();
			while(rs.next()) {
				Usuarion u=new Usuarion();
				u.setId(rs.getInt("ID"));
				u.setUser(rs.getString("User"));
				u.setPassword(rs.getString("password"));
				u.setNombre(rs.getString("nombre"));
				lista.add(u);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lista;
		
	}
	public boolean eliminarUsuario(int id) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("DELETE FROM usuario WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean editarUsuario(Usuarion user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("UPDATE usuario SET user=?, password=?, nombre=? WHERE id=?");
			ps.setString(1, user.getUser());
			ps.setString(2, convertirSHA256(user.getPassword()));
			ps.setString(3, user.getNombre());
			ps.setInt(4, user.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public String convertirSHA256(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} 
		catch (NoSuchAlgorithmException e) {		
			e.printStackTrace();
			return null;
		}
		    
		byte[] hash = md.digest(password.getBytes());
		StringBuffer sb = new StringBuffer();
		    
		for(byte b : hash) {        
			sb.append(String.format("%02x", b));
		}
		    
		return sb.toString();
	}

}
