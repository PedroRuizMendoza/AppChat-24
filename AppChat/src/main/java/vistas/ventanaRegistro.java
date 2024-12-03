package vistas;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controlador.AppChat;


import java.awt.SystemColor;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ventanaRegistro extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Nombre;
	private JTextField textField_Apellidos;
	private JTextField textField_tlf;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextArea textArea_saludo;
	private JLabel lblNewLabel_3;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private Component horizontalGlue;
	private JLabel imagenMostrada;
	private JLabel lblNewLabel_8;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JButton btnNewButton_2;

	private JTextField textFieldUrl; // Campo para ingresar la URL de la imagen

	

	/**
	 * Create the frame.
	 */
	public ventanaRegistro() {
		setTitle("AppChat");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ventanaRegistro.class.getResource("/imagenes/comunicacion (1).png")));
		setBounds(100, 100, 775, 472);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBackground(new Color(249, 252, 197));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setMinimumSize(new Dimension(950, 500));
		
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 202, 264, 149, 182, 20, 0};
		gbl_contentPane.rowHeights = new int[]{10, 0, 0, 0, 0, 0, 118, 0, 15, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
        
        // Agrega el campo de texto para la URL
        JLabel lblUrl = new JLabel("URL de la Imagen:");
        lblUrl.setForeground(new Color(5, 5, 120));
        lblUrl.setFont(new Font("Miriam Libre", Font.BOLD, 20));
        GridBagConstraints gbc_lblUrl = new GridBagConstraints();
        gbc_lblUrl.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblUrl.insets = new Insets(0, 0, 5, 5);
        gbc_lblUrl.gridx = 1;
        gbc_lblUrl.gridy = 7; // Ajusta la posición según sea necesario
        contentPane.add(lblUrl, gbc_lblUrl);
        
        textFieldUrl = new JTextField();
        GridBagConstraints gbc_textFieldUrl = new GridBagConstraints();
        gbc_textFieldUrl.gridwidth = 3;
        gbc_textFieldUrl.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldUrl.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldUrl.gridx = 2;
        gbc_textFieldUrl.gridy = 7; // Ajusta la posición según sea necesario
        contentPane.add(textFieldUrl, gbc_textFieldUrl);
        textFieldUrl.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setForeground(new Color(5, 5, 120));
		lblNewLabel.setFont(new Font("Miriam Libre", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		textField_Nombre = new JTextField();
		GridBagConstraints gbc_textField_Nombre = new GridBagConstraints();
		gbc_textField_Nombre.gridwidth = 3;
		gbc_textField_Nombre.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Nombre.gridx = 2;
		gbc_textField_Nombre.gridy = 1;
		contentPane.add(textField_Nombre, gbc_textField_Nombre);
		textField_Nombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Apellidos:");
		lblNewLabel_1.setForeground(new Color(5, 5, 120));
		lblNewLabel_1.setFont(new Font("Miriam Libre", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_Apellidos = new JTextField();
		GridBagConstraints gbc_textField_Apellidos = new GridBagConstraints();
		gbc_textField_Apellidos.gridwidth = 3;
		gbc_textField_Apellidos.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Apellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Apellidos.gridx = 2;
		gbc_textField_Apellidos.gridy = 2;
		contentPane.add(textField_Apellidos, gbc_textField_Apellidos);
		textField_Apellidos.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Telefono:");
		lblNewLabel_2.setForeground(new Color(5, 5, 120));
		lblNewLabel_2.setFont(new Font("Miriam Libre", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField_tlf = new JTextField();
		GridBagConstraints gbc_textField_tlf = new GridBagConstraints();
		gbc_textField_tlf.gridwidth = 3;
		gbc_textField_tlf.insets = new Insets(0, 0, 5, 5);
		gbc_textField_tlf.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_tlf.gridx = 2;
		gbc_textField_tlf.gridy = 3;
		contentPane.add(textField_tlf, gbc_textField_tlf);
		textField_tlf.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Contraseña:");
		lblNewLabel_4.setForeground(new Color(5, 5, 120));
		lblNewLabel_4.setFont(new Font("Miriam Libre", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 4;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 4;
		contentPane.add(passwordField, gbc_passwordField);
		
		lblNewLabel_5 = new JLabel("Contraseña:");
		lblNewLabel_5.setForeground(new Color(5, 5, 120));
		lblNewLabel_5.setFont(new Font("Miriam Libre", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 3;
		gbc_lblNewLabel_5.gridy = 4;
		contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 4;
		gbc_passwordField_1.gridy = 4;
		contentPane.add(passwordField_1, gbc_passwordField_1);
		
		lblNewLabel_6 = new JLabel("Fecha:");
		lblNewLabel_6.setForeground(new Color(5, 5, 120));
		lblNewLabel_6.setFont(new Font("Miriam Libre", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 5;
		contentPane.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		// Crear el JDateChooser
		JDateChooser dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.gridx = 2; // La misma columna que el JTextField
		gbc_dateChooser.gridy = 5; // La misma fila que la etiqueta "Fecha"
		contentPane.add(dateChooser, gbc_dateChooser);
		
		lblNewLabel_3 = new JLabel("Saludo:");
		lblNewLabel_3.setForeground(new Color(5, 5, 120));
		lblNewLabel_3.setFont(new Font("Miriam Libre", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_3.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 6;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 6;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		textArea_saludo = new JTextArea();
		scrollPane.setViewportView(textArea_saludo);
		
		lblNewLabel_8 = new JLabel("Imagen:");
		lblNewLabel_8.setForeground(new Color(5, 5, 120));
		lblNewLabel_8.setFont(new Font("Miriam Libre", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 3;
		gbc_lblNewLabel_8.gridy = 6;
		contentPane.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		imagenMostrada = new JLabel("");
		imagenMostrada.setIcon(new ImageIcon(ventanaRegistro.class.getResource("/imagenes/usuario (1).png")));
		GridBagConstraints gbc_imagenMostrada = new GridBagConstraints();
		gbc_imagenMostrada.insets = new Insets(0, 0, 5, 5);
		gbc_imagenMostrada.gridx = 4;
		gbc_imagenMostrada.gridy = 6;
		contentPane.add(imagenMostrada, gbc_imagenMostrada);
		
		panel = new JPanel();
		panel.setBackground(new Color(249, 252, 197));
		panel.setForeground(new Color(255, 255, 225));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 8;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		btnNewButton = new JButton("Cancelar");
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Cierra esta ventana
				ventanaLogin window = new ventanaLogin();
				window.ventanalogin.setVisible(true);
				
			}
		});
		btnNewButton.setForeground(new Color(64, 0, 64));
		btnNewButton.setBackground(new Color(254, 218, 230));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(btnNewButton);
		
		horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		
		btnNewButton_1 = new JButton("Aceptar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
			    
				String nombre = textField_Nombre.getText();
		        String apellidos = textField_Apellidos.getText();
		        String telefono = textField_tlf.getText();
		        String password1 = new String(passwordField.getPassword());
		        String password2 = new String(passwordField_1.getPassword());
		        String saludo = textArea_saludo.getText();
		        LocalDate fecha = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		        

		        //Nombre y apellidos no deben estar vacíos y deben contener solo letras
		        if (nombre.isEmpty() || !nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
		            JOptionPane.showMessageDialog(contentPane, "Por favor, ingrese un NOMBRE válido.", "Error en Nombre", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        if (apellidos.isEmpty() || !apellidos.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
		            JOptionPane.showMessageDialog(contentPane, "Por favor, ingrese APELLIDOS válidos.", "Error en Apellidos", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        //Teléfono no debe estar vacío, debe contener solo dígitos y 9 caracteres
		        if(telefono.isEmpty() || !telefono.matches("\\d{9}")) {
		            JOptionPane.showMessageDialog(contentPane, "Por favor, ingrese TELEFONO válido.", "Error en Telefono", JOptionPane.ERROR_MESSAGE);
		            return;		
		        }
		        //Contraseñas deben coincidir y no estar vacías
		        if (password1.isEmpty() || password2.isEmpty()) {
		            JOptionPane.showMessageDialog(contentPane, "Por favor, ingrese ambas contraseñas.", "Error en Contraseña", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        if (!password1.equals(password2)) {
		            JOptionPane.showMessageDialog(contentPane, "Las contraseñas no coinciden. Por favor, verifique.", "Error en Contraseña", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        // Saludo no debe estar vacío
		        if (saludo.isEmpty()) {
		            JOptionPane.showMessageDialog(contentPane, "Por favor, ingrese un Saludo.", "Error en Saludo", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        if(fecha == null) {
		            JOptionPane.showMessageDialog(contentPane, "Por favor, ingrese una Fecha.", "Error en fecha", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Si todos los campos son válidos
		        JOptionPane.showMessageDialog(contentPane, "Los datos son válidos.", "Validación Exitosa", JOptionPane.INFORMATION_MESSAGE);

		        

		     // Registra al usuario 
				boolean creada = AppChat.getInstancia().crearCuenta((ImageIcon) (imagenMostrada.getIcon()),
					nombre, apellidos ,password1, telefono, fecha, saludo);
				if (!creada) {
					// textFieldUser.setBackground(WRONG_INPUT_COLOR);
		            JOptionPane.showMessageDialog(contentPane, "El Usuario ya existe en el sistema.", "Error en Crear usuario", JOptionPane.ERROR_MESSAGE);
		            
							
				} else {
					// Cierra la ventana actual
					dispose(); // Cierra esta ventana
					// Abre la nueva ventana
					JFrame principalWindow = new VentanaMain();
					principalWindow.setVisible(true);
				}
				
			}
		});
		btnNewButton_1.setForeground(new Color(64, 0, 64));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Cargar Imagen");
		btnNewButton_2.setBackground(new Color(240, 255, 255));
		btnNewButton_2.setForeground(new Color(0, 0, 102));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String path = textFieldUrl.getText();  // Obtener la URL ingresada por el usuario
				
				URL url = null;
				try {
					url = new URL(path);
				} catch (MalformedURLException e2) {
					JOptionPane.showMessageDialog(contentPane, "La URL ingresada no es válida. Por favor, verifica la URL.", "Error de URL", JOptionPane.WARNING_MESSAGE);
		        }
				try {
					BufferedImage image = ImageIO.read(url);
					Image resizedImage = image.getScaledInstance(128, 128, Image.SCALE_SMOOTH); // Reescalar la imagen
					ImageIcon icono = new ImageIcon(resizedImage);
					icono.setDescription(path);
		            imagenMostrada.setIcon(icono); //Mostrar la nueva imagen donde estaba la anterior
		            
				} catch (IOException e1) {
		            // Muestra un mensaje de error si hay un problema al cargar la imagen
		            JOptionPane.showMessageDialog(contentPane, "No se pudo cargar la imagen desde la URL proporcionada.\n Compruebe su conexion a internet", "Error de Carga", JOptionPane.WARNING_MESSAGE);
		        }

				

			} 
		});
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 4;
		gbc_btnNewButton_2.gridy = 8;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
		
	

        
        
    
	
	
	}
}
