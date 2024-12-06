package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.AppChat;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Mensaje;
import modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import tds.BubbleText;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class VentanaMain extends JFrame{

	private JPanel contentPane;
	private JPanel chatPanel; // Panel para mostrar el chat
	private JTextField textField;
	private AppChat controlador;
	private JList<Contacto> listaContactos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMain frame = new VentanaMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaMain() {
		setResizable(false);
		controlador = AppChat.getInstancia();

		// Se extraen los contactos del usuario
		List<ContactoIndividual> contactos = controlador.getContactosUsuarioActual();
		// Creamos el modelo
		final DefaultListModel<Contacto> modelContacts = new DefaultListModel<>();
		// Rellenamos el modelo
		// contactos.stream().forEach(c -> modelContacts.addElement(c));
		listaContactos = new JList<>(modelContacts);

		setIconImage(
				Toolkit.getDefaultToolkit().getImage(VentanaMain.class.getResource("/imagenes/comunicacion (1).png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 988, 679);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel Panelbotonera = new JPanel();
		Panelbotonera.setBackground(new Color(255, 250, 250));
		Panelbotonera.setForeground(new Color(0, 0, 0));
		contentPane.add(Panelbotonera, BorderLayout.NORTH);
		Panelbotonera.setLayout(new BoxLayout(Panelbotonera, BoxLayout.X_AXIS));

		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contactos.stream().forEach(t -> comboBox.addItem(t));
		comboBox.setBackground(new Color(255, 255, 255));

		Panelbotonera.add(comboBox);

		JButton Boton = new JButton("");
		Boton.setBackground(new Color(255, 255, 255));
		Boton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		Boton.setIcon(new ImageIcon(VentanaMain.class.getResource("/imagenes/enviar.png")));
		Panelbotonera.add(Boton);

		JButton BotonBuscar = new JButton("");
		BotonBuscar.setBackground(new Color(255, 255, 255));
		BotonBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		BotonBuscar.setIcon(new ImageIcon(VentanaMain.class.getResource("/imagenes/lupa.png")));
		Panelbotonera.add(BotonBuscar);

		JButton boton_registrarContacto = new JButton("Añadir Contacto");
		boton_registrarContacto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				registrarContacto window = new registrarContacto(modelContacts);
				window.setSize(600, 400);
				window.setLocationRelativeTo(null);
				window.setVisible(true);
				
				
					
			}
		});
		boton_registrarContacto.setBackground(new Color(255, 255, 255));
		boton_registrarContacto.setFont(new Font("Tahoma", Font.BOLD, 13));
		boton_registrarContacto.setIcon(new ImageIcon(VentanaMain.class.getResource("/imagenes/reunion.png")));
		Panelbotonera.add(boton_registrarContacto);

		JButton btnNewButton_Premium = new JButton("Premium");
		btnNewButton_Premium.setBackground(new Color(255, 255, 255));
		btnNewButton_Premium.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_Premium.setIcon(new ImageIcon(VentanaMain.class.getResource("/imagenes/signo-de-dolar.png")));
		Panelbotonera.add(btnNewButton_Premium);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		Panelbotonera.add(horizontalGlue);

		JLabel lblNewLabel_1_Imagen = new JLabel(" ");
		lblNewLabel_1_Imagen.setIcon(controlador.setImagen());
		//lblNewLabel_1_Imagen.resize(25, 25);
		Panelbotonera.add(lblNewLabel_1_Imagen);

		JLabel lblNewLabel = new JLabel(controlador.getNombreUsuario());
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		Panelbotonera.add(lblNewLabel);

		// Panel para los mensajes
		JPanel panelMensajes = new JPanel();
		panelMensajes.setBackground(new Color(230, 230, 250));
		contentPane.add(panelMensajes, BorderLayout.WEST);
		panelMensajes.setLayout(new BorderLayout(0, 0));

		// Creación del JList de mensajes
		JList listMensajes = new JList<>();
		listMensajes.setForeground(new Color(0, 0, 0));
		listMensajes.setBackground(new Color(9, 6, 98));
		listMensajes.setCellRenderer(new MensajeCellRenderer());

		// Crear algunos mensajes de prueba
		List<Mensaje> mensajesPrueba = new ArrayList<>();

		mensajesPrueba.add(new Mensaje("Hola,¿Que tal?", LocalDateTime.now(), new Usuario("Emisor1"),
				new ContactoIndividual("Receptor")));
		mensajesPrueba.add(new Mensaje("Que hacees hoy?", LocalDateTime.now(), new Usuario("Emisor1"),
				new ContactoIndividual("Receptor2")));
		mensajesPrueba.add(new Mensaje("terminaste el proyecto?", LocalDateTime.now(), new Usuario("Emisor1"),
				new ContactoIndividual("Receptor3")));

		listMensajes.setListData(mensajesPrueba.toArray(new Mensaje[0]));
		panelMensajes.add(new JScrollPane(listMensajes), BorderLayout.CENTER);

		// Configurar el panel de chat
		chatPanel = new JPanel();
		chatPanel.setForeground(new Color(255, 255, 204));
		chatPanel.setBackground(new Color(255, 255, 204));
		chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
		contentPane.add(chatPanel, BorderLayout.CENTER);

		// Crear un JScrollPane para el chatPanel
		JScrollPane chatScrollPane = new JScrollPane(chatPanel);
		chatScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		chatScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// Agregar el JScrollPane al centro de la ventana
		contentPane.add(chatScrollPane, BorderLayout.CENTER);

		JPanel panelEnviarMensaje = new JPanel();
		panelEnviarMensaje.setBackground(new Color(255, 255, 255));
		contentPane.add(panelEnviarMensaje, BorderLayout.SOUTH);
		GridBagLayout gbl_panelEnviarMensaje = new GridBagLayout();
		gbl_panelEnviarMensaje.columnWidths = new int[] { 259, 0, 0, 0, 0, 0 };
		gbl_panelEnviarMensaje.rowHeights = new int[] { 0, 0 };
		gbl_panelEnviarMensaje.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelEnviarMensaje.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelEnviarMensaje.setLayout(gbl_panelEnviarMensaje);

		textField = new JTextField();
		textField.setEnabled(false); // Deshabilitado al inicio
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.SOUTH;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		panelEnviarMensaje.add(textField, gbc_textField);
		textField.setColumns(10);

		JButton btnNewButton_4 = new JButton("Enviar");
		btnNewButton_4.setForeground(new Color(0, 0, 0));
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_4.setEnabled(false); // Deshabilitado al inicio

		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String txtmensaje = textField.getText();
				if (!txtmensaje.isEmpty()) {
					enviarMensaje(txtmensaje);
					textField.setText(""); // Limpiar el campo de texto
				}

			}
		});
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4.gridx = 3;
		gbc_btnNewButton_4.gridy = 0;
		panelEnviarMensaje.add(btnNewButton_4, gbc_btnNewButton_4);

		// Añadir un ListSelectionListener para mostrar la conversación seleccionada
		listMensajes.addListSelectionListener(e -> {
			btnNewButton_4.setEnabled(true);
			textField.setEnabled(true);
			if (!e.getValueIsAdjusting()) {
				Mensaje mensajeSeleccionado = (Mensaje) listMensajes.getSelectedValue();
				if (mensajeSeleccionado != null) {
					mostrarMensajeEnChat(mensajeSeleccionado);
				}
			}
		});
	}
	
    public void editarComboBox(Contacto nuevoContacto, JComboBox comboBox){
    	List<ContactoIndividual> contactos = controlador.getContactosUsuarioActual(); 
    	contactos.stream().filter(contacto ->  IntStream.range(0, comboBox.getItemCount())
    			.mapToObj(comboBox::getItemAt) .noneMatch(existing -> existing.equals(contacto)))
    			.forEach(comboBox::addItem);
    }
    



	private void mostrarMensajeEnChat(Mensaje mensaje) {
		// Limpia el panel del chat antes de mostrar el mensaje seleccionado
		chatPanel.removeAll();

		// Crear una burbuja de texto para el mensaje
		BubbleText bubble = new BubbleText(chatPanel, mensaje.getTexto(), Color.GRAY, mensaje.getEmisor().getNombre(),
				BubbleText.RECEIVED, 16);

		// Añadir la burbuja de texto al panel del chat
		chatPanel.add(bubble);

		// Refrescar el panel de chat
		chatPanel.revalidate();
		chatPanel.repaint();
	}

	private void enviarMensaje(String texto) {
		// Crear una burbuja de texto para el mensaje enviado
		BubbleText bubble = new BubbleText(chatPanel, texto, Color.green, "Yo", BubbleText.SENT, 16);

		// Añadir la burbuja de texto al panel del chat
		chatPanel.add(bubble);

		// Refrescar el panel de chat
		chatPanel.revalidate();
		chatPanel.repaint();
	}

}
