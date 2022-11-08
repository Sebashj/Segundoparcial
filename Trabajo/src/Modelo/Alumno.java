package Modelo;

public class Alumno {
	int id;
	String nombre;
	int grupo;
	String correo;
	String semestre;
	
	public Alumno() {
		
	}
	
	public Alumno(int id, String nombre, int grupo, String correo, String semestre) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.grupo = grupo;
		this.correo = correo;
		this.semestre = semestre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

}
