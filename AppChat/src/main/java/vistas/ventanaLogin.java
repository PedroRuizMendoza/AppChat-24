package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import java.awt.BorderLayout;
import java.awt.Font;
import controlador.AppChat;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class ventanaLogin {

	JFrame ventanalogin;
	private JFormattedTextField textField_telefono;
	private JPasswordField passwordField;
	private AppChat controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaLogin window = new ventanaLogin();
					window.ventanalogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); 
				} 
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ventanaLogin() {

		controlador = AppChat.getInstancia();
		initialize();
	}	
	
	private void login() {
		// Comprobamos que el login haya sido satisfactorio
		String telefono = textField_telefono.getText();
		char[] password = passwordField.getPassword();

		if (controlador.iniciarSesion(telefono, String.valueOf(password))) {
			VentanaMain main = new VentanaMain();
			main.setVisible(true);
			ventanalogin.setVisible(false);
		}
		else {
			 JOptionPane.showMessageDialog(ventanalogin, "Por favor ingrese un LOGIN valido.", "Error en Login", JOptionPane.ERROR_MESSAGE);	
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ventanalogin = new JFrame();
		ventanalogin.setTitle("AppChat");
		ventanalogin.setForeground(SystemColor.info);
		ventanalogin.setIconImage(Toolkit.getDefaultToolkit().getImage(ventanaLogin.class.getResource("/imagenes/comunicacion (1).png")));
		ventanalogin.setBounds(100, 100, 632, 341);
		ventanalogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanalogin.setMinimumSize(new Dimension(600, 400));//Establece un tamaño mínimo para la ventana
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.info);
		panel.setForeground(new Color(255, 255, 255));
		ventanalogin.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton botonLogin = new JButton("Login");
		botonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		

		botonLogin.setForeground(new Color(0, 0, 128));
		botonLogin.setBackground(new Color(240, 255, 255));
		botonLogin.setIcon(new ImageIcon(ventanaLogin.class.getResource("/imagenes/seguridad-movil.png")));
		botonLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(botonLogin);
		
		JButton botonRegistrar = new JButton("Registrar");
		botonRegistrar.setBackground(new Color(255, 240, 245));
		botonRegistrar.setForeground(new Color(0, 0, 128));
		botonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cierra la ventana login
				ventanalogin.setVisible(false);
				// Abre la nueva ventana
				ventanaRegistro registro = new ventanaRegistro();
				registro.setVisible(true);
				
				
			}
		});
		botonRegistrar.setIcon(new ImageIcon(ventanaLogin.class.getResource("/imagenes/nueva-etiqueta.png")));
		botonRegistrar.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(botonRegistrar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.info);
		panel_1.setForeground(SystemColor.activeCaption);
		ventanalogin.getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{209, 272, 130, 0};
		gbl_panel_1.rowHeights = new int[]{50, 0, 0, 44, 0, 0, 46, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_3_Imagen = new JLabel("");
		lblNewLabel_3_Imagen.setIcon(new ImageIcon(ventanaLogin.class.getResource("/imagenes/comunicacion (1).png")));
		GridBagConstraints gbc_lblNewLabel_3_Imagen = new GridBagConstraints();
		gbc_lblNewLabel_3_Imagen.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3_Imagen.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3_Imagen.gridx = 0;
		gbc_lblNewLabel_3_Imagen.gridy = 1;
		panel_1.add(lblNewLabel_3_Imagen, gbc_lblNewLabel_3_Imagen);
		
		JLabel lblNewLabel_Appchat = new JLabel("AppChat");
		lblNewLabel_Appchat.setBackground(new Color(255, 255, 255));
		lblNewLabel_Appchat.setForeground(new Color(0, 64, 128));
		lblNewLabel_Appchat.setFont(new Font("Tahoma", Font.BOLD, 41));
		GridBagConstraints gbc_lblNewLabel_Appchat = new GridBagConstraints();
		gbc_lblNewLabel_Appchat.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_Appchat.gridx = 1;
		gbc_lblNewLabel_Appchat.gridy = 1;
		panel_1.add(lblNewLabel_Appchat, gbc_lblNewLabel_Appchat);
		
		JLabel lblNewLabel_1 = new JLabel("Telefono:");
		lblNewLabel_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 4;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_telefono = new JFormattedTextField();
		GridBagConstraints gbc_textField_telefono = new GridBagConstraints();
		gbc_textField_telefono.insets = new Insets(0, 0, 5, 5);
		gbc_textField_telefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_telefono.gridx = 1;
		gbc_textField_telefono.gridy = 4;
		panel_1.add(textField_telefono, gbc_textField_telefono);
		textField_telefono.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña:");
		lblNewLabel_2.setForeground(new Color(220, 20, 60));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 5;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 5;
		panel_1.add(passwordField, gbc_passwordField);
	}

	
	
	
	
	
}
