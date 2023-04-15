package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import modelo.Funcionalidad;
import modelo.Rol;

public class DAOFuncionalidad {
    
	private static final String INSERT_FUNCIONALIDAD = "INSERT INTO FUNCIONALIDAD (ID_FUNCIONALIDAD, NOMBRE, DESCRIPCION) VALUES (?,?,?)";
	private static final String SEC_FUNCIONALIDAD = "SELECT SEQ_ID_Funcionalidad.NEXTVAL FROM DUAL";
	private static final String ALL_FUNCIONALIDADES = "SELECT * FROM FUNCIONALIDAD";
	private static final String UPDATE_FUNCIONALIDAD = "UPDATE FUNCIONALIDAD SET NOMBRE=?, DESCRIPCION=? WHERE ID_FUNCIONALIDAD=?";
	private static final String DELETE_FUNCIONALIDAD ="DELETE FROM FUNCIONALIDAD WHERE ID_FUNCIONALIDAD=?";
	
	public static boolean agregarFuncionalidad(Funcionalidad f) {

		try {

			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(INSERT_FUNCIONALIDAD);

			int id = obtenerID() + 1;
			statement.setInt(1, id);
			statement.setString(2, f.getNombre());
			statement.setString(3, f.getDescripcion());

			int Retorno = statement.executeUpdate();
			System.out.println("Se ha ingresado " + Retorno + "Funcionalidad");
			return Retorno > 0;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
		
	}
	
	public static boolean modificarFuncionalidad(Funcionalidad f) {
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(UPDATE_FUNCIONALIDAD);
		
			
			statement.setInt(3, f.getIdFuncionalidad());
			statement.setString(1, f.getNombre());
			statement.setString(2, f.getDescripcion());
		
			statement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public static LinkedList<Funcionalidad> findAllFuncionalidad(){
		LinkedList<Funcionalidad> funcionalidades = new LinkedList<>();
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_FUNCIONALIDADES);
			
			ResultSet resultado = statement.executeQuery();
			
			while (resultado.next()) {
				int idFuncionalidad = resultado.getInt("ID_FUNCIONALIDAD");
				String nombre = resultado.getString("NOMBRE");
				String descripcion = resultado.getString("DESCRIPCION");
				
			Funcionalidad f = new Funcionalidad (idFuncionalidad,nombre,descripcion);
			funcionalidades.add(f);
				
				
			}
			return funcionalidades;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean eliminarFuncionalidad(int id) {   
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(DELETE_FUNCIONALIDAD);
			 
			statement.setInt(1, id);
			statement.executeUpdate(); 
			statement.close();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	public static int obtenerID() {
		int res=0;
		ResultSet resultado = null;      
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(SEC_FUNCIONALIDAD);
			resultado = sentencia.executeQuery();	
			    
			while (resultado.next()){				
				 res = resultado.getInt(1);
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}		
			
			return res;
	 	}

}
