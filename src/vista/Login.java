package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtClave;
	private Coordinador miCoordinador;

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Login");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(147, 58, 86, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtClave = new JTextField();
		txtClave.setBounds(147, 111, 86, 20);
		contentPane.add(txtClave);
		txtClave.setColumns(10);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String usuario = txtUsuario.getText();
				String clave = txtClave.getText();

				try {

					miCoordinador.mostrarVistaPrincipal(usuario, clave);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnIngresar.setBounds(53, 182, 89, 23);
		contentPane.add(btnIngresar);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		btnSalir.setBounds(215, 182, 89, 23);
		contentPane.add(btnSalir);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(69, 61, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setBounds(69, 117, 68, 14);
		contentPane.add(lblNewLabel_1);

		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				miCoordinador.mostrarVentanaRegistro();

			}
		});
		btnRegistrarse.setBounds(335, 227, 89, 23);
		contentPane.add(btnRegistrarse);
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;

	}
}
