package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.Alumno;

public class DaoAlumno {
	Conexion cx =null;
	
	public DaoAlumno() {
		cx=new Conexion();
	}
	
	public boolean insertarAlumno(Alumno user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO alumnos VALUES(null,?,?,?,?)");
			ps.setString(1, user.getNombre());
			ps.setInt(2, user.getGrupo());
			ps.setString(3, user.getCorreo());
			ps.setString(4, user.getSemestre());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public ArrayList<Alumno> fetchAlumnos(){
		ArrayList<Alumno> lista=new ArrayList<Alumno>();
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM alumnos");
			rs=ps.executeQuery();
			while(rs.next()) {
				Alumno u=new Alumno();
				u.setId(rs.getInt("idalumno"));
				u.setNombre(rs.getString("nombre"));
				u.setGrupo(rs.getInt("grupo"));
				u.setCorreo(rs.getString("correo"));
				u.setSemestre(rs.getString("semestre"));
				lista.add(u);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lista;
		
	}
	public boolean eliminarAlumno(int idalumno) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("DELETE FROM alumnos WHERE idalumno=?");
			ps.setInt(1, idalumno);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean editarAlumno(Alumno user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("UPDATE alumnos SET correo=?, semestre=?, nombre=?, grupo=?  WHERE idalumno=?");
			ps.setString(1, user.getNombre());
			ps.setInt(2, user.getGrupo());
			ps.setString(3, user.getCorreo());
			ps.setString(4, user.getSemestre());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}

}
