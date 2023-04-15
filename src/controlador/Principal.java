package controlador;

import vista.Login;
import vista.Vista;
import vista.JPanelListaFuncionalidades;
import vista.JPanelListaUsuarios;
import vista.JPanelListaRoles;
import vista.LoginRegistro;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Principal().iniciar();

	}

	private void iniciar() {
		// TODO Auto-generated method stub
		
		/* Se instancian las clases */
		Login login = new Login();
		LoginRegistro loginRegistro = new LoginRegistro();
		Vista vistaPrincipal = new Vista();
		JPanelListaUsuarios jPanelListaUsuarios = new JPanelListaUsuarios();
		JPanelListaRoles jPanelListaRoles = new JPanelListaRoles();
		JPanelListaFuncionalidades jPanelListaFuncionalidades = new JPanelListaFuncionalidades();
		Coordinador miCoordinador = new Coordinador();
		
		/* Se establecen las relaciones entre clases */
		login.setCoordinador(miCoordinador);
		loginRegistro.setCoordinador(miCoordinador);
		vistaPrincipal.setCoordinador(miCoordinador);
		jPanelListaUsuarios.setCoordinador(miCoordinador);
		jPanelListaRoles.setCoordinador(miCoordinador);
		jPanelListaFuncionalidades.setCoordinador(miCoordinador);
		
		/* Se establecen las relaciones con la clase Coordinador*/
		miCoordinador.setLogin(login);
		miCoordinador.setLoginRegistro(loginRegistro);
		miCoordinador.setVista(vistaPrincipal);
		miCoordinador.setJPanelListaUsuarios(jPanelListaUsuarios);
		miCoordinador.setJPanelListaRoles(jPanelListaRoles);
		miCoordinador.setJPanelListaFuncionalidades(jPanelListaFuncionalidades);
		
		miCoordinador.mostrarLogin();
		
	}

}
