package modelo;

import java.sql.Date;

public class Persona {
	

	private String documento;
	private String apellido1;
	private String apellido2;
	private String nombre1;
	private String nombre2;
	private Date fechaNac;
	private String clave;
	private String mail;
	private Rol rol;
	
	public Persona (String documento, String apellido1, String apellido2, String nombre1, String nombre2, Date fecha, String clave, String mail, Rol rol) {
		this.documento = documento;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.fechaNac = (Date) fecha;
		this.clave = clave;
		this.mail = mail;
		this.rol = rol;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	@Override
	public String toString() {
		return "Persona [documento=" + documento + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", nombre1=" + nombre1 + ", nombre2=" + nombre2 + ", fechaNac=" + fechaNac + ", clave=" + clave
				+ ", mail=" + mail + ", rol=" + rol + "]";
	}
}
