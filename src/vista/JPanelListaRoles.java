package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import controlador.Coordinador;
import dao.DAORol;
import modelo.Rol;

public class JPanelListaRoles extends JPanel {

	DefaultTableModel tableModel;
	JTable table;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextArea txtDescripcion;
	private Coordinador miCoordinador;


	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	public JPanelListaRoles() {
		
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
		lblNewLabel.setBounds(10, 36, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Descripcion:");
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
				modificarRol();
				mostrarDatos();
				panel.revalidate();

			}
		});

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarRol();
				mostrarDatos();
				panel.revalidate();
			}
		});
		btnEliminar.setBounds(479, 57, 89, 23);
		panel.add(btnEliminar);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarRol();
				mostrarDatos();
				panel.revalidate();
			}
		});
		btnAgregar.setBounds(479, 107, 89, 23);
		panel.add(btnAgregar);

	}

	// METODOS MODIFICAR - ELIMINAR - AGREGAR //
	private void modificarRol() {
		
		int id = Integer.parseInt(txtId.getText());
		String nombre = txtNombre.getText();
		String descripcion = txtDescripcion.getText();
		Rol r = new Rol(id, nombre, descripcion);
		DAORol.modificarRol(r);
	}

	private void eliminarRol() {

		int id = Integer.parseInt(txtId.getText());
		DAORol.eliminarRol(id);
	}

	private void agregarRol() {
		
		String nombre = txtNombre.getText();
		String descripcion = txtDescripcion.getText();
		Rol r = new Rol(nombre, descripcion);
		DAORol.agregarRol(r);
	}

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
				
			}
		});
	}

	// MUESTRA LOS DATOS DE LA TABLA

	private void mostrarDatos() {

		LinkedList<modelo.Rol> listaRoles = dao.DAORol.findAllRol();
		DefaultTableModel modeloTabla = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);

		try {
			for (modelo.Rol rol : listaRoles) {
				Object[] fila = new Object[3];
				fila[0] = rol.getId();
				fila[1] = rol.getNombre();
				fila[2] = rol.getDescripcion();

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
}
