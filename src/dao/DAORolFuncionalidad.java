package dao;

	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.LinkedList;

	import modelo.Funcionalidad;
	import modelo.Rol;

public class DAORolFuncionalidad {
	
	    private static final String INSERT_ROL_FUNCION = "INSERT INTO ROL_FUNCION (ID_ROL_FUNCION, ID_ROL, ID_FUNCION) VALUES (SEQ_ID_ROL_FUNCION.NEXTVAL,?,?)";
	    private static final String DELETE_ROL_FUNCION = "DELETE FROM ROL_FUNCION WHERE ID_ROL_FUNCION=?";
	    private static final String FIND_ROLES_BY_FUNCION = "SELECT r.* FROM ROL r JOIN ROL_FUNCION rf ON r.ID_ROL = rf.ID_ROL WHERE rf.ID_FUNCION=?";
	    private static final String FIND_FUNCIONALIDADES_BY_ROL = "SELECT NOMBRE, DESCRIPCION FROM FUNCIONALIDAD f INNER JOIN ROL_FUNCION rf ON f.ID_FUNCIONALIDAD = rf.ID_FUNCION WHERE rf.ID_ROL=?";
	    private static final String DELETE_ROLES_BY_FUNCION = "DELETE FROM ROL_FUNCION WHERE ID_FUNCION=?";
	    private static final String DELETE_FUNCIONALIDADES_BY_ROL = "DELETE FROM ROL_FUNCION WHERE ID_ROL=?";

	    public static LinkedList<Funcionalidad> obtenerFuncionalidadesXRol(int idRol) {
	    	LinkedList<Funcionalidad> funcionalidades = null;
	    	try {
	    		funcionalidades = new LinkedList<Funcionalidad>();
	    		
	            PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(FIND_FUNCIONALIDADES_BY_ROL);
	            statement.setInt(1, idRol);
	            
	            ResultSet res = statement.executeQuery();
	            
	            while (res.next()) {
	            	Funcionalidad aux = new Funcionalidad();
	            	aux.setNombre(res.getString("NOMBRE"));
	            	aux.setDescripcion(res.getString("DESCRIPCION"));
	            	
	            	funcionalidades.add(aux);	 
	            }
	            return funcionalidades;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
			
	    }
	    
	    public static boolean agregarRolFuncion(int idRol, int idFuncion) {
	        try {
	            PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(INSERT_ROL_FUNCION);
	            statement.setInt(1, idRol);
	            statement.setInt(2, idFuncion);
	            int retorno = statement.executeUpdate();
	            System.out.println("Se ha ingresado " + retorno + " registro en ROL_FUNCION");
	            return retorno > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	  
	    public static boolean eliminarRolFuncion(int idRolFuncion) {
	        try {
	            PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(DELETE_ROL_FUNCION);
	            statement.setInt(1, idRolFuncion);
	            int retorno = statement.executeUpdate();
	            System.out.println("Se ha eliminado " + retorno + " registro en ROL_FUNCION");
	            return retorno > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public static LinkedList<Rol> findRolesByFuncion(int idFuncion) {
	        LinkedList<Rol> roles = new LinkedList<>();
	        try {
	            PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(FIND_ROLES_BY_FUNCION);
	            statement.setInt(1, idFuncion);
	            ResultSet resultado = statement.executeQuery();
	            while (resultado.next()) {
	                int idRol = resultado.getInt("ID_ROL");
	                String nombre = resultado.getString("NOMBRE");
	                String descripcion = resultado.getString("DESCRIPCION");
	                Rol r = new Rol(idRol, nombre, descripcion);
	                roles.add(r);
	            }
	            return roles;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
}
