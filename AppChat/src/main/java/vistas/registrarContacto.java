package vistas;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JTextField;

import controlador.AppChat;
import modelo.Contacto;
import modelo.ContactoIndividual;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class registrarContacto extends JFrame {
	private JTextField textField_Nombre;
	private JTextField textField_Telefono;
	private DefaultListModel<ContactoIndividual> modelContacts;
	
	
	
	public registrarContacto(DefaultListModel<Contacto> modelo) {
		setResizable(false);
		
		getContentPane().setBackground(new Color(254, 254, 207));
		getContentPane().setForeground(new Color(250, 252, 203));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{105, 114, 0, 0, 0, 0, 0, 0, 50, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 7, -21, 110, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2.setIcon(new ImageIcon(registrarContacto.class.getResource("/imagenes/comunicacion (1).png")));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Registrar Contacto");
		lblNewLabel_3.setForeground(new Color(0, 0, 102));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 26));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.gridwidth = 2;
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 2;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("Nombre: ");
		lblNewLabel.setForeground(new Color(158, 1, 5));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 4;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		textField_Nombre = new JTextField();
		GridBagConstraints gbc_textField_Nombre = new GridBagConstraints();
		gbc_textField_Nombre.gridwidth = 2;
		gbc_textField_Nombre.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Nombre.gridx = 2;
		gbc_textField_Nombre.gridy = 4;
		getContentPane().add(textField_Nombre, gbc_textField_Nombre);
		textField_Nombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Telefono: ");
		lblNewLabel_1.setForeground(new Color(158, 1, 5));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 6;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_Telefono = new JTextField();
		GridBagConstraints gbc_textField_Telefono = new GridBagConstraints();
		gbc_textField_Telefono.gridwidth = 2;
		gbc_textField_Telefono.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Telefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Telefono.gridx = 2;
		gbc_textField_Telefono.gridy = 6;
		getContentPane().add(textField_Telefono, gbc_textField_Telefono);
		textField_Telefono.setColumns(10);
		
		JButton btnNewButton_Aceptar = new JButton("Aceptar");
		btnNewButton_Aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addContact();
			}
		});
		btnNewButton_Aceptar.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_btnNewButton_Aceptar = new GridBagConstraints();
		gbc_btnNewButton_Aceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_Aceptar.gridx = 2;
		gbc_btnNewButton_Aceptar.gridy = 7;
		getContentPane().add(btnNewButton_Aceptar, gbc_btnNewButton_Aceptar);
		
		JButton btnNewButton_cancelar = new JButton("Cancelar");
		btnNewButton_cancelar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton_cancelar = new GridBagConstraints();
		gbc_btnNewButton_cancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_cancelar.gridx = 3;
		gbc_btnNewButton_cancelar.gridy = 7;
		getContentPane().add(btnNewButton_cancelar, gbc_btnNewButton_cancelar);
	
	
	}

	/**
	 * Comprueba errores y añade el contacto si está todo correcto
	 */
	private void addContact() {
		// Comprobamos que los datos son correctos

		// Creamos el contacto
		ContactoIndividual nuevoContacto = AppChat.getInstancia().crearContacto(textField_Nombre.getText(), textField_Telefono.getText());
		if (nuevoContacto == null) {
			// No se ha podido crear el usuario
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(registrarContacto.this, "The contact is already saved or its user does not exist",
					"Error", JOptionPane.ERROR_MESSAGE);
		} else {
			// Usuario creado
			modelContacts.add(modelContacts.size(), nuevoContacto);
			JOptionPane.showMessageDialog(registrarContacto.this, "Contact added successfully", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		}
		System.out.println("No se crea ningun contacto");
	}

	public void setMinimumSize(int i, int j) {
		// TODO Auto-generated method stub
		
	}
	
}
