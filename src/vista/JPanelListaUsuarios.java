package vista;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controlador.Coordinador;
import dao.DAOPersona;
import dao.DAORol;
import modelo.Persona;
import modelo.Rol;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class JPanelListaUsuarios extends JPanel {
	DefaultTableModel tableModel;
	DefaultComboBoxModel combomodel;
	JTable table;
	private JTextField txtDocumento;
	private JTextField txtNombre;
	private JTextField txtSegNombre;
	private JTextField txtFecNac;
	private JTextField txtApellido;
	private JTextField txtSegApellido;
	private JTextField txtClave;
	private JTextField txtMail;
	private JComboBox boxRol;
	private Coordinador miCoordinador;

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	/**
	 * Create the panel.
	 */
	public JPanelListaUsuarios() {

		crearTabla();
		mostrarDatos();
		seleccionarCampos();

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 902, 700);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel);
		panel.setLayout(null);

		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(10, 11, 70, 14);
		panel.add(lblDocumento);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10, 36, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Seg Nombre:");
		lblNewLabel_1.setBounds(10, 61, 70, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Fec Nacimiento:");
		lblNewLabel_2.setBounds(10, 86, 86, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Apellido:");
		lblNewLabel_3.setBounds(292, 11, 46, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Seg Apellido:");
		lblNewLabel_4.setBounds(292, 36, 70, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Clave:");
		lblNewLabel_5.setBounds(292, 61, 46, 14);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Mail:");
		lblNewLabel_6.setBounds(292, 86, 46, 14);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Rol:");
		lblNewLabel_7.setBounds(292, 111, 46, 14);
		panel.add(lblNewLabel_7);

		txtDocumento = new JTextField();
		txtDocumento.setBounds(90, 8, 86, 20);
		txtDocumento.setColumns(10);
		panel.add(txtDocumento);

		txtNombre = new JTextField();
		txtNombre.setBounds(90, 33, 86, 20);
		txtNombre.setColumns(10);
		panel.add(txtNombre);

		txtSegNombre = new JTextField();
		txtSegNombre.setBounds(90, 58, 86, 20);
		txtSegNombre.setColumns(10);
		panel.add(txtSegNombre);

		txtFecNac = new JTextField();
		txtFecNac.setBounds(90, 83, 86, 20);
		txtFecNac.setColumns(10);
		panel.add(txtFecNac);

		txtApellido = new JTextField();
		txtApellido.setBounds(359, 8, 86, 20);
		txtApellido.setColumns(10);
		panel.add(txtApellido);

		txtSegApellido = new JTextField();
		txtSegApellido.setBounds(359, 33, 86, 20);
		txtSegApellido.setColumns(10);
		panel.add(txtSegApellido);

		txtClave = new JTextField();
		txtClave.setBounds(359, 58, 86, 20);
		txtClave.setColumns(10);
		panel.add(txtClave);

		txtMail = new JTextField();
		txtMail.setBounds(359, 83, 86, 20);
		txtMail.setColumns(10);
		panel.add(txtMail);

		boxRol = new JComboBox();
		boxRol.setBounds(314, 107, 155, 22);
		panel.add(boxRol);
		DefaultComboBoxModel model = new DefaultComboBoxModel(obtenerNombreRoles().toArray());
		boxRol.setModel(model);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(479, 7, 89, 23);
		panel.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarPersona();
				mostrarDatos();
				panel.revalidate();
			}
		});

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarPersona();
				mostrarDatos();
				panel.revalidate();
			}
		});
		btnEliminar.setBounds(479, 57, 89, 23);
		panel.add(btnEliminar);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarPersona();
				mostrarDatos();
				panel.revalidate();
			}
		});
		btnAgregar.setBounds(479, 107, 89, 23);
		panel.add(btnAgregar);

	}
	// METODOS MODIFICAR - ELIMINAR - AGREGAR //

	private void modificarPersona() {

		String nombre = txtNombre.getText();
		String segNombre = txtSegNombre.getText();
		String apellido = txtApellido.getText();
		String segApellido = txtSegApellido.getText();
		String documento = txtDocumento.getText();
		String mail = txtMail.getText();
		String clave = txtClave.getText();
		// Obtener el objeto seleccionado del combo box
		Rol rol = (Rol) obtenerRolXNombre(boxRol.getSelectedItem().toString());
		java.sql.Date fecNac = null;
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

			fecNac = new java.sql.Date(formatoFecha.parse(txtFecNac.getText()).getTime());
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		Persona p = new Persona(documento, apellido, segApellido, nombre, segNombre, fecNac, clave, mail, rol);

		DAOPersona.edit(p);

	}

	private void eliminarPersona() {

		String documento = txtDocumento.getText();
		DAOPersona.eliminarPersona(documento);
	}

	private void agregarPersona() {

		String nombre = txtNombre.getText();
		String segNombre = txtSegNombre.getText();
		String apellido = txtApellido.getText();
		String segApellido = txtSegApellido.getText();
		String documento = txtDocumento.getText();
		String mail = txtMail.getText();
		String clave = txtClave.getText();
		Rol rol = (Rol) obtenerRolXNombre(boxRol.getSelectedItem().toString());
		java.sql.Date fecNac = null;
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

			fecNac = new java.sql.Date(formatoFecha.parse(txtFecNac.getText()).getTime());
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

			fecNac = new java.sql.Date(formatoFecha.parse(txtFecNac.getText()).getTime());
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		Persona p = new modelo.Persona(documento, apellido, segApellido, nombre, segNombre, fecNac, clave, mail, rol);

		DAOPersona.agregarPersona(p);

	}

	// TERMINAN METODOS MODIFICAR - ELIMINAR - AGREGAR //

	// PERMITE A LA TABLA PODER SELECCIONAR UN CAMPO Y RELLENA LOS CAMPOS DE TEXTO
	private void seleccionarCampos() {

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int fila = table.getSelectedRow();
				if (fila >= 0) {
					txtDocumento.setText(table.getValueAt(fila, 0) != null ? table.getValueAt(fila, 0).toString() : "");
					txtNombre.setText(table.getValueAt(fila, 1) != null ? table.getValueAt(fila, 1).toString() : "");
					txtSegNombre.setText(table.getValueAt(fila, 2) != null ? table.getValueAt(fila, 2).toString() : "");
					txtApellido.setText(table.getValueAt(fila, 3) != null ? table.getValueAt(fila, 3).toString() : "");
					txtSegApellido
							.setText(table.getValueAt(fila, 4) != null ? table.getValueAt(fila, 4).toString() : "");
					txtClave.setText(table.getValueAt(fila, 6) != null ? table.getValueAt(fila, 6).toString() : "");
					txtMail.setText(table.getValueAt(fila, 7) != null ? table.getValueAt(fila, 7).toString() : "");
					String rol = (String) table.getValueAt(fila, 8);
					boxRol.setSelectedItem(rol);
					java.sql.Date fechaNacSql = (java.sql.Date) table.getValueAt(fila, 5);
					java.util.Date fechaNacUtil = (java.util.Date) fechaNacSql;
					SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
					String fechaNacStr = formatoFecha.format(fechaNacUtil);
					txtFecNac.setText(fechaNacStr);
				}
			}
		});
	}

	// MUESTRA LOS DATOS DE LA TABLA

	private void mostrarDatos() {

		LinkedList<modelo.Persona> listaPersonas = dao.DAOPersona.findAll();
		DefaultTableModel modeloTabla = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);

		try {
			for (modelo.Persona persona : listaPersonas) {
				Object[] fila = new Object[9];
				fila[0] = persona.getDocumento();
				fila[1] = persona.getNombre1();
				fila[2] = persona.getNombre2();
				fila[3] = persona.getApellido1();
				fila[4] = persona.getApellido2();
				fila[5] = persona.getFechaNac();
				fila[6] = persona.getClave();
				fila[7] = persona.getMail();
				fila[8] = persona.getRol().getNombre();
				modeloTabla.addRow(fila);

			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Out of bounds");
		}
	}

	// CREA LA TABLA CON EL DISENO DE COLUMNAS
	private void crearTabla() {

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Documento");
		tableModel.addColumn("Nombre");
		tableModel.addColumn("Seg Nombre");
		tableModel.addColumn("Apellido");
		tableModel.addColumn("Seg Apellido");
		tableModel.addColumn("Fecha Nac");
		tableModel.addColumn("Clave");
		tableModel.addColumn("Mail");
		tableModel.addColumn("Rol");
		setLayout(null);
		table = new JTable(tableModel);
		table.setBounds(1, 1, 880, 0);
		add(table);
		JScrollPane scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setBounds(10, 216, 882, 473);
		scrollPane_1.setViewportView(table);
		add(scrollPane_1);
		table.setDefaultEditor(Object.class, null);

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

	private LinkedList<String> obtenerNombreRoles() {
		LinkedList<Rol> listaRoles = DAORol.findAllRol();
		LinkedList<String> nombreRol = new LinkedList<String>();

		for (Rol rol : listaRoles) {

			nombreRol.add(rol.getNombre());

		}
		return nombreRol;
	}
}
