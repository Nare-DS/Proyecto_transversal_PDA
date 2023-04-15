package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import controlador.Coordinador;
import dao.DAOFuncionalidad;
import dao.DAORol;
import dao.DAORolFuncionalidad;
import modelo.Funcionalidad;
import modelo.Rol;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class JPanelListaFuncionalidades extends JPanel {

	DefaultTableModel tableModel;
	JTable table;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextArea txtDescripcion;
	private Coordinador miCoordinador;
	private JComboBox boxRol;
	private int id;
	private int obtenerRolEnTabla;

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	public JPanelListaFuncionalidades() {
		
		crearTabla();
		mostrarDatos();
		seleccionarCampos();

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 902, 700);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel);
		panel.setLayout(null);

		JLabel lblDocumento = new JLabel("ID:");
		lblDocumento.setBounds(10, 11, 70, 14);
		panel.add(lblDocumento);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(10, 36, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Descripcion:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(10, 61, 70, 14);
		panel.add(lblNewLabel_1);

		txtId = new JTextField();
		txtId.setBounds(90, 8, 27, 20);
		txtId.setColumns(10);
		txtId.setEditable(false);
		panel.add(txtId);

		txtNombre = new JTextField();
		txtNombre.setBounds(90, 33, 210, 20);
		txtNombre.setColumns(10);
		panel.add(txtNombre);

		txtDescripcion = new JTextArea();
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setBounds(90, 58, 210, 72);
		txtDescripcion.setColumns(10);
		panel.add(txtDescripcion);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(479, 7, 89, 23);
		panel.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarFuncionalidad();
				mostrarDatos();
				panel.revalidate();
			}
		});

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarFuncionalidad();
				mostrarDatos();
				panel.revalidate();
			}
		});
		btnEliminar.setBounds(479, 57, 89, 23);
		panel.add(btnEliminar);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarFuncionalidad();
				mostrarDatos();
				panel.revalidate();
			}
		});
		btnAgregar.setBounds(479, 107, 89, 23);
		panel.add(btnAgregar);
		
		boxRol = new JComboBox();
		boxRol.setBounds(90, 141, 210, 20);
		panel.add(boxRol);
		DefaultComboBoxModel model = new DefaultComboBoxModel(obtenerNombreRoles().toArray());
		boxRol.setModel(model);
		
		JLabel lblNewLabel_2 = new JLabel("Rol:");
		lblNewLabel_2.setBounds(10, 147, 46, 14);
		panel.add(lblNewLabel_2);
		
		JButton btnAsignar = new JButton("Asignar");
		btnAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Rol rol = (Rol) obtenerRolXNombre(boxRol.getSelectedItem().toString());
			DAORolFuncionalidad.agregarRolFuncion(rol.getId(),obtenerRolEnTabla );
	        System.out.println("La funcion ID: " + obtenerRolEnTabla + " Ahora se encuentra en el Rol: " + rol.getNombre());
			
			}
		});
		btnAsignar.setBounds(310, 140, 89, 23);
		panel.add(btnAsignar);

	}

	// METODOS MODIFICAR - ELIMINAR - AGREGAR //
	private void modificarFuncionalidad() {
		
		int id = Integer.parseInt(txtId.getText());
		String nombre = txtNombre.getText();
		String descripcion = txtDescripcion.getText();
		Funcionalidad f = new Funcionalidad(id, nombre, descripcion);
		DAOFuncionalidad.modificarFuncionalidad(f);
	}

	private void eliminarFuncionalidad() {

		int id = Integer.parseInt(txtId.getText());
		DAOFuncionalidad.eliminarFuncionalidad(id);
	}

	private void agregarFuncionalidad() {
		
		String nombre = txtNombre.getText();
		String descripcion = txtDescripcion.getText();
		Funcionalidad f = new Funcionalidad (nombre, descripcion);
		DAOFuncionalidad.agregarFuncionalidad(f);
	}
	
//	private void asignarRolAFuncionalidad() {
//		Rol rol = (Rol) obtenerRolXNombre(boxRol.getSelectedItem().toString());
//		
////		table.addMouseListener(new MouseAdapter() {
////		    public void mouseClicked(MouseEvent e) {
////		        int fila = table.getSelectedRow();
////		        id = (int) table.getValueAt(fila, 0);   
////		    }
////		});
//		DAORolFuncionalidad.agregarRolFuncion(rol.getId(),id );
//        System.out.println(rol.getId()+" "+id);
//	}

	// TERMINAN METODOS MODIFICAR - ELIMINAR - AGREGAR//

	// PERMITE A LA TABLA PODER SELECCIONAR UN CAMPO Y RELLENA LOS CAMPOS DE TEXTO

	private void seleccionarCampos() {

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRowIndex = table.getSelectedRow();
				TableModel model = table.getModel();
				String id = model.getValueAt(selectedRowIndex, 0).toString();
				String nombre = model.getValueAt(selectedRowIndex, 1).toString();
				String descripcion = model.getValueAt(selectedRowIndex, 2).toString();
				txtId.setText(id);
				txtNombre.setText(nombre);
				txtDescripcion.setText(descripcion);
		        obtenerRolEnTabla = (int) table.getValueAt(selectedRowIndex, 0);  
				 
				
			}
		});
	}

	// MUESTRA LOS DATOS DE LA TABLA

	private void mostrarDatos() {

		LinkedList<Funcionalidad> listaFuncionalidades = DAOFuncionalidad.findAllFuncionalidad();
		DefaultTableModel modeloTabla = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);

		try {
			for (Funcionalidad funcionalidad : listaFuncionalidades) {
				Object[] fila = new Object[3];
				fila[0] = funcionalidad.getIdFuncionalidad();
				fila[1] = funcionalidad.getNombre();
				fila[2] = funcionalidad.getDescripcion();

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
		tableModel.addColumn("ID");
		tableModel.addColumn("Nombre");
		tableModel.addColumn("Descripcion");
		setLayout(null);
		table = new JTable(tableModel);
		table.setBounds(1, 1, 880, 0);
		add(table);
		JScrollPane scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setBounds(10, 216, 882, 473);
		scrollPane_1.setViewportView(table);
		add(scrollPane_1);
		table.setDefaultEditor(Object.class, null);
		TableColumnModel columnModel = table.getColumnModel();	
		columnModel.getColumn(0).setPreferredWidth(27);
		columnModel.getColumn(1).setPreferredWidth(250);
		columnModel.getColumn(2).setPreferredWidth(605);
		
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
