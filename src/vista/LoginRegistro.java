package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.Coordinador;
import dao.DAORol;
import modelo.Persona;
import modelo.Rol;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class LoginRegistro extends JFrame {
	private JTextField txtNombre;
	private JTextField txtSegNombre;
	private JTextField txtApellido;
	private JTextField txtSegApellido;
	private JTextField txtDocumento;
	private JTextField txtFecNac;
	private JTextField txtMail;
	private JTextField txtClave;
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private Coordinador miCoordinador;
	
	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;}

	public LoginRegistro() {
		
		getContentPane().setLayout(null);
		setSize(500, 700);
		setTitle("Registro");
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(81, 48, 46, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Seg Nombre:");
		lblNewLabel_1.setBounds(81, 108, 62, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Apellido:");
		lblNewLabel_2.setBounds(81, 168, 46, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Seg Apellido:");
		lblNewLabel_3.setBounds(81, 228, 74, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Documento:");
		lblNewLabel_4.setBounds(81, 288, 62, 14);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Mail:");
		lblNewLabel_5.setBounds(81, 408, 46, 14);
		getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Clave:");
		lblNewLabel_6.setBounds(81, 468, 46, 14);
		getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Fec Nacimiento:");
		lblNewLabel_7.setBounds(81, 348, 81, 14);
		getContentPane().add(lblNewLabel_7);

		txtNombre = new JTextField();
		txtNombre.setBounds(175, 42, 86, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtSegNombre = new JTextField();
		txtSegNombre.setBounds(175, 102, 86, 20);
		getContentPane().add(txtSegNombre);
		txtSegNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setBounds(175, 162, 86, 20);
		getContentPane().add(txtApellido);
		txtApellido.setColumns(10);

		txtSegApellido = new JTextField();
		txtSegApellido.setBounds(175, 222, 86, 20);
		getContentPane().add(txtSegApellido);
		txtSegApellido.setColumns(10);

		txtDocumento = new JTextField();
		txtDocumento.setBounds(175, 282, 86, 20);
		getContentPane().add(txtDocumento);
		txtDocumento.setColumns(10);

		txtFecNac = new JTextField();
		txtFecNac.setBounds(175, 342, 86, 20);
		getContentPane().add(txtFecNac);
		txtFecNac.setColumns(10);

		txtMail = new JTextField();
		txtMail.setBounds(175, 402, 86, 20);
		getContentPane().add(txtMail);
		txtMail.setColumns(10);

		txtClave = new JTextField();
		txtClave.setBounds(175, 462, 86, 20);
		getContentPane().add(txtClave);
		txtClave.setColumns(10);
		
		JComboBox boxRol = new JComboBox();
		boxRol.setToolTipText("");
		boxRol.setBounds(247, 596, 205, 23);
		getContentPane().add(boxRol);
		DefaultComboBoxModel model = new DefaultComboBoxModel(obtenerNombreRoles().toArray());
		boxRol.setModel(model);
		
		JLabel lblNewLabel_8 = new JLabel("Seleccione su Rol inicial");
		lblNewLabel_8.setBounds(105, 600, 156, 14);
		getContentPane().add(lblNewLabel_8);
	

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = txtNombre.getText();
				String segNombre = txtSegNombre.getText();
				String apellido = txtApellido.getText();
				String segApellido = txtSegApellido.getText();
				String documento = txtDocumento.getText();
				String mail = txtMail.getText();
				String clave = txtClave.getText();
				java.sql.Date fecNac = null;
				Rol rol = (Rol) obtenerRolXNombre(boxRol.getSelectedItem().toString());

				try {
					SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

					fecNac = new java.sql.Date(formatoFecha.parse(txtFecNac.getText()).getTime());
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				Persona p = new Persona(documento, apellido, segApellido, nombre, segNombre, fecNac, clave,
						mail,rol);
				miCoordinador.RegistrarPersona(p);
			}
		});
		btnRegistrar.setBounds(54, 521, 89, 23);
		getContentPane().add(btnRegistrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		btnCancelar.setBounds(198, 521, 89, 23);
		getContentPane().add(btnCancelar);}
		
	
	private LinkedList<String> obtenerNombreRoles() {
		LinkedList<Rol> listaRoles = DAORol.findAllRol();
		LinkedList<String> nombreRol = new LinkedList<String>();

		for (Rol rol : listaRoles) {

			nombreRol.add(rol.getNombre());

		}
		return nombreRol;
	}
	
	private Rol obtenerRolXNombre(String rolABuscar) {
		Rol rolEncontrado = null;
		LinkedList<Rol> listaRoles = DAORol.findAllRol();

		int i = 0;
		boolean encontre = false;

		while (!encontre && i < listaRoles.size()) {
			String nombreActual = listaRoles.get(i).getNombre();
			if (nombreActual.equals(rolABuscar)) {
				encontre = true;
			} else {
				i++;
			}
		}

		if (encontre) {
			rolEncontrado = listaRoles.get(i);

		} else {
			System.out.println("Rol no encontrado");
		}

		return rolEncontrado;
	}
}
