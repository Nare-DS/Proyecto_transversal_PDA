package controlador;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.DAOPersona;
import dao.DAORolFuncionalidad;
import modelo.Funcionalidad;
import modelo.Persona;
import vista.Login;
import vista.LoginRegistro;
import vista.Vista;
import vista.JPanelListaFuncionalidades;
import vista.JPanelListaUsuarios;
import vista.JPanelListaRoles;

public class Coordinador {

	private Login login;
	private LoginRegistro loginRegistro;
	private Vista vistaPrincipal;
	private JPanelListaUsuarios jPanelListaUsuarios;
	private JPanelListaRoles jPanelListaRoles;
	private JPanelListaFuncionalidades jPanelListaFuncionalidades;
	private static Persona usuarioLogeado = null;

	public void setLogin(Login login) {
		this.login = login;

	}

	public void setLoginRegistro(LoginRegistro loginRegistro) {
		this.loginRegistro = loginRegistro;

	}

	public void setVista(Vista vista) {
		this.vistaPrincipal = vista;

	}

	public void setJPanelListaUsuarios(JPanelListaUsuarios jPanelListaUsuarios) {
		this.jPanelListaUsuarios = jPanelListaUsuarios;

	}

	public void setJPanelListaRoles(JPanelListaRoles jPanelListaRoles) {
		this.jPanelListaRoles = jPanelListaRoles;

	}

	public void setJPanelListaFuncionalidades(JPanelListaFuncionalidades jPanelListaFuncionalidades) {
		this.jPanelListaFuncionalidades = jPanelListaFuncionalidades;

	}

	public void mostrarLogin() {
		login.setLocationRelativeTo(null);
		login.setVisible(true);

	}

	public void mostrarVentanaRegistro() {
		loginRegistro.setLocationRelativeTo(null);
		loginRegistro.setVisible(true);

	}

	public void mostrarVistaPrincipal(String mail, String clave) throws SQLException {
		
		Persona persona = DAOPersona.findPersona(mail, clave);
		usuarioLogeado = persona;
		if (persona != null) {
			vistaPrincipal.setLocationRelativeTo(null);
			vistaPrincipal.setVisible(true);
			login.dispose();
			
			JOptionPane.showMessageDialog(null, "Bienvenido: " + persona.getNombre1() + " " + persona.getApellido1());
		} else {
			JOptionPane.showMessageDialog(null, "Error al ingresar, verifique sus datos");

		}

	}

	public void RegistrarPersona(Persona p) {

		DAOPersona.agregarPersona(p);
		if (p != null) {
			JOptionPane.showMessageDialog(null, "Bienvenido: " + p.getNombre1());
		} else {
			JOptionPane.showMessageDialog(null, "Verifique sus datos");
		}
	}

	// METODOS QUE LLAMAN A LOS JPANEL Y AGREGAN AL JFRAME //

	public void mostrarJPanelListaUsuarios() {

		JPanel panelDinamico = vistaPrincipal.getPanelDinamico();
		jPanelListaUsuarios.setSize(902, 700);
		jPanelListaUsuarios.setLocation(0, 0);
		panelDinamico.setSize(902, 700);
		panelDinamico.removeAll();
		panelDinamico.add(jPanelListaUsuarios, BorderLayout.CENTER);
		panelDinamico.revalidate();
		panelDinamico.repaint();
	}

	public void mostrarJPanelFuncionalidades() {
		JPanel panelDinamico = vistaPrincipal.getPanelDinamico();
		jPanelListaFuncionalidades.setSize(902, 700);
		jPanelListaFuncionalidades.setLocation(0, 0);
		panelDinamico.setSize(902, 700);
		panelDinamico.removeAll();
		panelDinamico.add(jPanelListaFuncionalidades, BorderLayout.CENTER);
		panelDinamico.revalidate();
		panelDinamico.repaint();

	}

	public void mostrarJPanelRoles() {
		JPanel panelDinamico = vistaPrincipal.getPanelDinamico();
		jPanelListaRoles.setSize(902, 700);
		jPanelListaRoles.setLocation(0, 0);
		panelDinamico.setSize(902, 700);
		panelDinamico.removeAll();
		panelDinamico.add(jPanelListaRoles, BorderLayout.CENTER);
		panelDinamico.revalidate();
		panelDinamico.repaint();
		
		// TERMINAN METODOS QUE LLAMAN A LOS JPANEL Y AGREGAN AL JFRAME //

	}

	public static boolean evaluarPermisos(String funcAcceder) {
		int i = 0;
		boolean tengoPermiso = false;

		LinkedList<Funcionalidad> funcionalidades = DAORolFuncionalidad.obtenerFuncionalidadesXRol(usuarioLogeado.getRol().getId());

		while (!tengoPermiso && i < funcionalidades.size()) {
			if (funcionalidades.get(i).getNombre().equals(funcAcceder)) {
				tengoPermiso = true;
			} else
				i++;
		}
		return tengoPermiso;
	}
	
}