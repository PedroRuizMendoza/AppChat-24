package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

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

	private JFrame frmAppchat;
	private JFormattedTextField textField_usuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaLogin window = new ventanaLogin();
					window.frmAppchat.setVisible(true);
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAppchat = new JFrame();
		frmAppchat.setTitle("AppChat");
		frmAppchat.setForeground(SystemColor.info);
		frmAppchat.setIconImage(Toolkit.getDefaultToolkit().getImage(ventanaLogin.class.getResource("/imagenes/comunicacion (1).png")));
		frmAppchat.setBounds(100, 100, 632, 341);
		frmAppchat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAppchat.setMinimumSize(new Dimension(600, 400));//Establece un tamaño mínimo para la ventana
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.info);
		panel.setForeground(new Color(255, 255, 255));
		frmAppchat.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton botonLogin = new JButton("Login");
		
		botonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String telefono = textField_usuario.getText();
				String contraseña = new String(passwordField.getPassword());
				
				boolean estaLogueado = AppChat.login(telefono,contraseña);
				if(estaLogueado) {
					VentanaMain main = new VentanaMain();
					main.setVisible(true);
					frmAppchat.setVisible(false);
				}
				else {
					 JOptionPane.showMessageDialog(frmAppchat, "Por favor ingrese un LOGIN valido.", "Error en Login", JOptionPane.ERROR_MESSAGE);	
				}
				
				
				
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
				ventanaRegistro registro = new ventanaRegistro();
				registro.setVisible(true);
				frmAppchat.setVisible(false);
				
			}
		});
		botonRegistrar.setIcon(new ImageIcon(ventanaLogin.class.getResource("/imagenes/nueva-etiqueta.png")));
		botonRegistrar.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(botonRegistrar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.info);
		panel_1.setForeground(SystemColor.activeCaption);
		frmAppchat.getContentPane().add(panel_1, BorderLayout.CENTER);
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
		
		textField_usuario = new JFormattedTextField();
		GridBagConstraints gbc_textField_usuario = new GridBagConstraints();
		gbc_textField_usuario.insets = new Insets(0, 0, 5, 5);
		gbc_textField_usuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_usuario.gridx = 1;
		gbc_textField_usuario.gridy = 4;
		panel_1.add(textField_usuario, gbc_textField_usuario);
		textField_usuario.setColumns(10);
		
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
