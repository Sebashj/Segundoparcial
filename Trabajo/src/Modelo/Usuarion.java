package Modelo;

public class Usuarion {
	int id;
	String user;
	String password;
	String nombre;
	public Usuarion() {
		
	}
	
	public Usuarion(int id, String user, String password, String nombre) {
		super();
		this.id = id;
		this.user = user;
		this.password = password;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
