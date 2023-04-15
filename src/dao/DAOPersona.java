package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import modelo.Persona;
import modelo.Rol;
//import singletonDBcon.DatabaseManager;

public class DAOPersona {

	private static final String BUSCAR_PERSONA = "SELECT * FROM PERSONA WHERE MAIL=? AND CLAVE=?";

	private static final String INSERT_PERSONA = "INSERT INTO PERSONA (ID_Persona,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,FECHA_NAC,CLAVE,MAIL,ID_ROL) VALUES (?,?,?,?,?,?,?,?,?,?)";

	private static final String BUSCAR_NEXT_ID = "SELECT SEQ_ID_Persona.NEXTVAL FROM DUAL";
	
	private static final String ALL_PERSONAS = "SELECT * FROM PERSONA";

	private static final String UPDATE_PERSONA = "UPDATE PERSONA SET DOCUMENTO=?, APELLIDO1=?, APELLIDO2=?, NOMBRE1=?, NOMBRE2=?, FECHA_NAC=?, CLAVE=?, MAIL=?, ID_ROL=? WHERE DOCUMENTO=?";
	
	private static final String DELETE_PERSONA ="DELETE FROM PERSONA WHERE DOCUMENTO=?";
	
	private static final String QUERY_ID_PERSONA_X_DOCUMENTO ="SELECT ID_PERSONA FROM PERSONA WHERE DOCUMENTO=?";

	public static int obtenerIDxDocumento(String documento) {
		int idEncontrado=-1;
		
		try {

			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(QUERY_ID_PERSONA_X_DOCUMENTO);
			
			statement.setString(1, documento);
			
			ResultSet res = statement.executeQuery();
		
			while(res.next()) {
				idEncontrado=res.getInt("ID_PERSONA");
			}
			return idEncontrado;

		} catch (SQLException e) {
			e.printStackTrace();

			return -1;

		}

	}
	
	public static Persona findPersona(String mail, String clave) throws SQLException {	

		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(BUSCAR_PERSONA);
		statement.setString(1, mail);
		statement.setString(2, clave);
		ResultSet resultado = statement.executeQuery();

		if (resultado.next()) {
			Persona persona = new Persona(null, null, null, null, null, null, null, null, null);
			persona.setDocumento(resultado.getString("DOCUMENTO"));
			persona.setApellido1(resultado.getString("APELLIDO1"));
			persona.setApellido2(resultado.getString("APELLIDO2"));
			persona.setNombre1(resultado.getString("NOMBRE1"));
			persona.setNombre2(resultado.getString("NOMBRE2"));
			persona.setFechaNac(resultado.getDate("FECHA_NAC"));
			persona.setClave(resultado.getString("CLAVE"));
			persona.setMail(resultado.getString("MAIL"));
	        int rolId = resultado.getInt("ID_ROL");
	        Rol rol = DAORol.findRol(rolId);
	        persona.setRol(rol);
			System.out.println("Se encontro la persona en el sistema con Documento: " + persona.getDocumento() +" Ha ingresado con el Rol: "+ persona.getRol().getNombre());

			return persona;
		} else {
			System.out.println("No se encontro la persona en el sistema");
			return null;

		}
	}

	public static boolean agregarPersona(Persona p) {

		try {

			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(INSERT_PERSONA);
			
			statement.setInt(1, nextval());
			statement.setString(2, p.getDocumento());
			statement.setString(3, p.getApellido1());
			statement.setString(4, p.getApellido2());
			statement.setString(5, p.getNombre1());
			statement.setString(6, p.getNombre2());
			statement.setDate(7, p.getFechaNac());
			statement.setString(8, p.getClave());
			statement.setString(9, p.getMail());
			statement.setInt(10, p.getRol().getId());

			int Retorno = statement.executeUpdate();
			System.out.println("Se ha ingresado: "+ p.toString());
			return Retorno > 0;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;

		}

	}
	
	public static LinkedList<Persona> findAll(){
		LinkedList<Persona> personas = new LinkedList<>();
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_PERSONAS);
			
			ResultSet resultado = statement.executeQuery();
			
			while (resultado.next()) {
				int idPersona = resultado.getInt("ID_PERSONA");
				String documento = resultado.getString("DOCUMENTO");
				String apellido1 = resultado.getString("APELLIDO1");
				String apellido2 = resultado.getString("APELLIDO2");
				String nombre1 = resultado.getString("NOMBRE1");
				String nombre2 = resultado.getString("NOMBRE2");
				Date fechaNac = resultado.getDate("FECHA_NAC");
				String clave = resultado.getString("CLAVE");
				String mail = resultado.getString("MAIL");
				int rolId = resultado.getInt("ID_ROL");
			    Rol rol = DAORol.findRol(rolId);
				Persona p = new Persona(documento,apellido1,apellido2,nombre1,nombre2,fechaNac,clave,mail,rol);
				personas.add(p);		       
				
			}
			return personas;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean edit(Persona p) {
		
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(UPDATE_PERSONA);
			
			  statement.setString(1, p.getDocumento());
		        statement.setString(2, p.getApellido1());
		        statement.setString(3, p.getApellido2());
		        statement.setString(4, p.getNombre1());
		        statement.setString(5, p.getNombre2());
		        statement.setDate(6, p.getFechaNac());
		        statement.setString(7, p.getClave());
		        statement.setString(8, p.getMail());
		        statement.setInt(9, p.getRol().getId());
		        statement.setString(10, p.getDocumento());
		        
		     
		        int retorno = statement.executeUpdate();
		        System.out.println("Se ha modificado: " + retorno );
		        return retorno > 0;
		        
		        
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}}
	
	public static boolean eliminarPersona(String documento) {   
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(DELETE_PERSONA);
			 
			statement.setString(1, documento);
			statement.executeUpdate(); 
			statement.close();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	public static int nextval() throws SQLException {

		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(BUSCAR_NEXT_ID);
		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			int nextID_from_seq = rs.getInt(1);
			return nextID_from_seq;
		} else {
			return 0;
		}

	}

}
