package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Contacto;
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
import javax.swing.AbstractListModel;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import tds.BubbleText;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultComboBoxModel;

public class VentanaMain extends JFrame {

	private JPanel contentPane;
	private JPanel chatPanel;  // Panel para mostrar el chat
	private JTextField textField;

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
<<<<<<< HEAD
	} 
=======
	}
	//hola a todos messi
>>>>>>> branch 'main' of https://github.com/PedroRuizMendoza/AppChat-24.git

	/**
	 * Create the frame.
	 */
	public VentanaMain() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaMain.class.getResource("/imagenes/comunicacion (1).png")));
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Telefono/Contacto", "Telefono", "Contacto"}));
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
		
		JButton btnNewButton_2 = new JButton("Contactos");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setIcon(new ImageIcon(VentanaMain.class.getResource("/imagenes/reunion.png")));
		Panelbotonera.add(btnNewButton_2);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		Panelbotonera.add(horizontalGlue);
		
		JButton btnNewButton_3 = new JButton("Premium");
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_3.setIcon(new ImageIcon(VentanaMain.class.getResource("/imagenes/signo-de-dolar.png")));
		Panelbotonera.add(btnNewButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(VentanaMain.class.getResource("/imagenes/gente (1).png")));
		Panelbotonera.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel(" Usuario Actual");
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
        
        
        mensajesPrueba.add(new Mensaje(new Usuario("Emisor1"), new Contacto("Receptor"), "Hola,¿Que tal?"));
        mensajesPrueba.add(new Mensaje(new Usuario("Emisor2"), new Contacto("Receptor"), "Nos vemos mañana!!"));
        mensajesPrueba.add(new Mensaje(new Usuario("Emisor3"), new Contacto("Receptor"), "Terminaste el proyecto???"));
        

        
        listMensajes.setListData(mensajesPrueba.toArray(new Mensaje[0]));
        panelMensajes.add(new JScrollPane(listMensajes), BorderLayout.CENTER);

        // Configurar el panel de chat
        chatPanel = new JPanel();
        chatPanel.setForeground(new Color(255, 255, 240));
        chatPanel.setBackground(new Color(253, 254, 231));
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
        gbl_panelEnviarMensaje.columnWidths = new int[]{259, 0, 0, 0, 0, 0};
        gbl_panelEnviarMensaje.rowHeights = new int[]{0, 0};
        gbl_panelEnviarMensaje.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelEnviarMensaje.rowWeights = new double[]{0.0, Double.MIN_VALUE};
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
                    textField.setText("");  // Limpiar el campo de texto
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
	
	

    private void mostrarMensajeEnChat(Mensaje mensaje) {
        // Limpia el panel del chat antes de mostrar el mensaje seleccionado
        chatPanel.removeAll();

        // Crear una burbuja de texto para el mensaje
        BubbleText bubble = new BubbleText(chatPanel, mensaje.getTexto(), Color.GRAY, mensaje.getEmisor().getNombre(), BubbleText.RECEIVED,16);

        // Añadir la burbuja de texto al panel del chat
        chatPanel.add(bubble);

        // Refrescar el panel de chat
        chatPanel.revalidate();
        chatPanel.repaint();   	
	}
    
    
    private void enviarMensaje(String texto) {
        // Crear una burbuja de texto para el mensaje enviado
        BubbleText bubble = new BubbleText(chatPanel, texto, Color.green, "Yo", BubbleText.SENT,16);

        // Añadir la burbuja de texto al panel del chat
        chatPanel.add(bubble);

        // Refrescar el panel de chat
        chatPanel.revalidate();
        chatPanel.repaint();
    }
   
}
