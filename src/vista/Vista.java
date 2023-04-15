package vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;

import controlador.Coordinador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;

public class Vista extends JFrame {

	private Coordinador miCoordinador;
	private JPanel panelDinamico;
	private JButton btnCompras;
	private JButton btnVentas;
	private JButton btnSueldos;
	private JButton btnCuentasCorrientes;
	private JButton btnControlDeInventario;
	private JButton btnRoles;
	private JButton btnListaDeFuncionalidades;
	private JButton btnListaDeUsuarios;
	private JLabel lblDBStatus;

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	public Vista() {

		JPanel background = new JPanel();
		background.setBackground(new Color(0, 128, 192));
		background.setBounds(0, 0, 1084, 711);
		getContentPane().add(background, BorderLayout.NORTH);
		background.setLayout(null);
		setTitle("Sistema Meissa Empleados");

		panelDinamico = new JPanel();
		panelDinamico.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelDinamico.setBackground(new Color(0, 128, 192));
		panelDinamico.setBounds(182, 11, 902, 700);
		background.add(panelDinamico);
		panelDinamico.setLayout(new BorderLayout());

		setSize(1100, 750);
		setResizable(false);
		getContentPane().setLayout(null);

		btnCompras = new JButton("Compras");
		btnCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean tengoPermiso = Coordinador.evaluarPermisos("Compras");

				if (tengoPermiso) {
					JOptionPane.showMessageDialog(null, "Accediste a Compras");
				} else {
					JOptionPane.showMessageDialog(null, "No tienes permisos");
				}

			}
		});
		btnCompras.setBounds(10, 54, 162, 23);
		background.add(btnCompras);

		btnVentas = new JButton("Ventas");
		btnVentas.setBounds(10, 88, 162, 23);
		background.add(btnVentas);
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean tengoPermiso = Coordinador.evaluarPermisos("Ventas");

				if (tengoPermiso) {
					JOptionPane.showMessageDialog(null, "Accediste a Ventas");
				} else {
					JOptionPane.showMessageDialog(null, "No tienes permisos");
				}

			}
		});

		btnSueldos = new JButton("Sueldos");
		btnSueldos.setBounds(10, 122, 162, 23);
		background.add(btnSueldos);
		btnSueldos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean tengoPermiso = Coordinador.evaluarPermisos("Sueldos");

				if (tengoPermiso) {
					JOptionPane.showMessageDialog(null, "Accediste a Sueldos");
				} else {
					JOptionPane.showMessageDialog(null, "No tienes permisos");
				}

			}
		});

		btnCuentasCorrientes = new JButton("Cuentas Corrientes");
		btnCuentasCorrientes.setBounds(10, 156, 162, 23);
		background.add(btnCuentasCorrientes);
		btnCuentasCorrientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean tengoPermiso = Coordinador.evaluarPermisos("Cuentas Corrientes");

				if (tengoPermiso) {
					JOptionPane.showMessageDialog(null, "Accediste a Cuentas Corrientes");
				} else {
					JOptionPane.showMessageDialog(null, "No tienes permisos");
				}

			}
		});

		btnControlDeInventario = new JButton("Control de Inventario");
		btnControlDeInventario.setBounds(10, 190, 162, 23);
		background.add(btnControlDeInventario);
		btnControlDeInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean tengoPermiso = Coordinador.evaluarPermisos("Control de Inventario");

				if (tengoPermiso) {
					JOptionPane.showMessageDialog(null, "Accediste a Control de Inventario");
				} else {
					JOptionPane.showMessageDialog(null, "No tienes permisos");
				}

			}
		});

		btnRoles = new JButton("Lista de Roles");
		btnRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean tengoPermiso = Coordinador.evaluarPermisos("Lista de Roles");

				if (tengoPermiso) {
					miCoordinador.mostrarJPanelRoles();
				} else {
					JOptionPane.showMessageDialog(null, "No tienes permisos");
				}

			}
		});
		btnRoles.setBounds(10, 258, 162, 23);
		background.add(btnRoles);

		btnListaDeFuncionalidades = new JButton("Lista de Funcionalidades");
		btnListaDeFuncionalidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean tengoPermiso = Coordinador.evaluarPermisos("Lista de Funcionalidades");

				if (tengoPermiso) {
					miCoordinador.mostrarJPanelFuncionalidades();
				} else {
					JOptionPane.showMessageDialog(null, "No tienes permisos");
				}
			}
		});
		btnListaDeFuncionalidades.setBounds(10, 292, 162, 23);
		background.add(btnListaDeFuncionalidades);

		btnListaDeUsuarios = new JButton("Lista de Usuarios");
		btnListaDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean tengoPermiso = Coordinador.evaluarPermisos("Lista de Usuarios");

				if (tengoPermiso) {
					miCoordinador.mostrarJPanelListaUsuarios();
				} else {
					JOptionPane.showMessageDialog(null, "No tienes permisos");
				}
			}
		});
		btnListaDeUsuarios.setBounds(10, 224, 162, 23);
		background.add(btnListaDeUsuarios);
		
	}

	public JPanel getPanelDinamico() {
		return panelDinamico;
	}
}