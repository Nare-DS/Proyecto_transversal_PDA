package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	
	private static Connection databaseConnection;
	
	// Estoy usando la IP 192.168.1.11 ya que la base de datos se encuentra en la laptop.
	
	private static String CONNECTION_STRING = "jdbc:oracle:thin:@192.168.1.9:1521:xe";
	private static String USUARIO = "PROYECTOTRANSVERSAL";
	private static String CLAVE = "pass";
	
	static {
		databaseConnection = null;
	
	try { //Tenemos vinculada la clase en nuestro proyecto?
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Se encontro el Driver para Oracle DB - Libreria referenciada");
		
		try { // Intentamos instanciar el objeto connection
			
			databaseConnection = DriverManager.getConnection(CONNECTION_STRING,USUARIO,CLAVE);
			System.out.println("Conexion creada con exito, es posible acceder a la base de datos");
		
		} catch (SQLException e) {		
			System.out.println("No logramos instanciar una conexion!");
			e.printStackTrace();
		} // try { // intentamos instanciar el objeto connection
		
		} catch (ClassNotFoundException e) {
			System.out.println("No tienes el driver en tu build-path?");
			e.printStackTrace();
		}	// try { // tenemos vinculada la clase en nuestro proyecto?
}

		public static Connection getConnection(){
			return databaseConnection;
		}		
}    

