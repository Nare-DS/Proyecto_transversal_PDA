package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import modelo.Rol;

public class DAORol {

	private static final String INSERT_ROL = "INSERT INTO ROL (ID_ROL, NOMBRE, DESCRIPCION) VALUES (?,?,?)";
	private static final String SEC_ROL = "SELECT SEQ_ID_Rol.NEXTVAL FROM DUAL";
	private static final String ALL_ROL = "SELECT * FROM ROL";
	private static final String UPDATE_ROL = "UPDATE ROL SET NOMBRE=?, DESCRIPCION=? WHERE ID_ROL=?";
	private static final String DELETE_ROL = "DELETE FROM ROL WHERE ID_ROL=?";
	private static final String FIND_ROL = "SELECT FROM ROL WHERE ID_ROL=?";

	public static boolean agregarRol(Rol r) {

		try {

			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(INSERT_ROL);

			int id = obtenerID() + 1;
			statement.setInt(1, id);
			statement.setString(2, r.getNombre());
			statement.setString(3, r.getDescripcion());

			int Retorno = statement.executeUpdate();
			System.out.println("Se ha ingresado " + Retorno + "Rol");
			return Retorno > 0;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;

		}

	}

	public static boolean modificarRol(Rol r) {
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(UPDATE_ROL);

			statement.setInt(3, r.getId());
			statement.setString(1, r.getNombre());
			statement.setString(2, r.getDescripcion());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public static LinkedList<Rol> findAllRol() {
		LinkedList<Rol> roles = new LinkedList<>();
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_ROL);

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

	public static boolean eliminarRol(int id) {
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(DELETE_ROL);

			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int obtenerID() {
		int res = 0;
		ResultSet resultado = null;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(SEC_ROL);
			resultado = sentencia.executeQuery();

			while (resultado.next()) {
				res = resultado.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static Rol findRol(int idRol) throws SQLException {
	    PreparedStatement statement = DatabaseManager.getConnection().prepareStatement("SELECT * FROM ROL WHERE ID_ROL = ?");
	    statement.setInt(1, idRol);
	    ResultSet resultado = statement.executeQuery();

	    if (resultado.next()) {
	        Rol rol = new Rol(resultado.getInt("ID_ROL"), resultado.getString("NOMBRE"), resultado.getString("DESCRIPCION"));
	        return rol;
	    } else {
	        return null;
	    }
	}
	
}
