package Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
			ps=cx.conectar().prepareStatement("INSERT INTO Usuario VALUES(null,?,?,?)");
			ps.setString(1, user.getUser());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNombre());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}

}
