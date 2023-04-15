package modelo;

public class Rol {
	
	private int idRol;
	private String nombre;
	private String descripcion;
	
	public Rol (int idRol, String nombre , String descripcion) {
		this.idRol = idRol;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Rol(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getId() {
		return idRol;
	}

	public void setId(int idRol) {
		this.idRol = idRol;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}
