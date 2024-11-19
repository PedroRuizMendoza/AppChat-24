package vistas;
import modelo.Mensaje;
import modelo.Usuario;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
public class MensajeCellRenderer extends JPanel implements ListCellRenderer<Mensaje> {
    private JLabel nombreLabel;
    private JLabel mensajeLabel;
    private JLabel iconoLabel;
    private JButton agregarContactoBtn;

    public MensajeCellRenderer() {
        setLayout(new BorderLayout(5, 5));
        setBorder(new EmptyBorder(5, 5, 5, 5));

        iconoLabel = new JLabel();
        nombreLabel = new JLabel();
        mensajeLabel = new JLabel();
        agregarContactoBtn = new JButton("+");
 
        // Icono del perfil a la izquierda
        add(iconoLabel, BorderLayout.WEST);

        // Panel para los detalles de mensaje
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(nombreLabel, BorderLayout.NORTH);
        infoPanel.add(mensajeLabel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.CENTER);

        // Botón de "agregar" si solo tiene número
        add(agregarContactoBtn, BorderLayout.EAST);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Mensaje> list, Mensaje mensaje, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        // Establece el nombre o el número del contacto
        Usuario emisor = mensaje.getEmisor();
        nombreLabel.setText(emisor.getNombre());
        // Configura el texto del mensaje
        mensajeLabel.setText(mensaje.getTexto());

        // Icono del contacto (se puede usar una URL para el avatar)
        try {
            URL imageUrl = new URL("https://robohash.org/" + emisor + "?size=50x50");
            Image image = ImageIO.read(imageUrl);
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            iconoLabel.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
            iconoLabel.setIcon(null);
        }

        // Configura la visibilidad del botón de "agregar"
        agregarContactoBtn.setVisible(mensaje.getReceptor() != null && mensaje.getReceptor().getNombre().isEmpty());

        // Configuración de selección
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}