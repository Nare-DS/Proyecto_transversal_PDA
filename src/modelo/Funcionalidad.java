package modelo;

public class Funcionalidad {
	
	private int idFuncionalidad;
	private String nombre;
	private String descripcion;
	
	public Funcionalidad (int idFuncionalidad, String nombre , String descripcion) {
		this.idFuncionalidad=idFuncionalidad;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Funcionalidad () {
		
	}
	
	public Funcionalidad (String nombre , String descripcion) {
		
		this.nombre = nombre;
		this.descripcion = descripcion;
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
	
	public int getIdFuncionalidad() {
		return idFuncionalidad;
	}

	public void setIdFuncionalidad(int idFuncionalidad) {
		this.idFuncionalidad = idFuncionalidad;
	}
	
	public boolean acceso (Persona p) {
		return false;
		
	}
}
