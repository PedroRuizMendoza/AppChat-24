package modelo;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;

public class Usuario {
	private static final double PRECIO_PREMIUM = 20.00;
	private static final LocalDate FECHA_JOVEN = LocalDate.of(2003, 1, 1);

	private int codigo;
	private String nombre;
	private String apellidos;
	private String contraseña;
	private String telefono;
	private String saludo;
	private JDateChooser fecha;
	private ImageIcon imagen;
	private List<Contacto> contactos;
	private boolean premium = false;
	private Optional<Descuento> descuento;

	public Usuario(String nombre, String apellidos, String contraseña, String telefono, String saludo,
			JDateChooser fecha, ImageIcon imagen, List<Contacto> contactos) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.contraseña = contraseña;
		this.telefono = telefono;
		this.saludo = saludo;
		this.fecha = fecha;
		this.imagen = imagen;
		this.contactos = contactos;

		if (descuento == null) {
			// Si es joven descuento para jovenes
			if (((ChronoLocalDate) fecha).isAfter(FECHA_JOVEN)) {
				descuento = Optional.ofNullable(new DescuentoFecha());
			}
		}
	}

	public Usuario(String nombre) {
		this.nombre = nombre;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getSaludo() {
		return saludo;
	}

	public void setSaludo(String saludo) {
		this.saludo = saludo;
	}

	public JDateChooser getFecha() {
		return fecha;
	}

	public void setFecha(JDateChooser fecha) {
		this.fecha = fecha;
	}

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}

	public List<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}

	public void setDescuento(Descuento descuento) {
		this.descuento = Optional.ofNullable(descuento);
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium() {
		premium = true;
	}
	

	public double getPrecio() {
		if (descuento.isPresent()) {
			return descuento.get().getDescuento(PRECIO_PREMIUM);
		} else
			return PRECIO_PREMIUM;
	}

	public void addContacto(ContactoIndividual c) {
		contactos.add(c);
	}

	public void addGrupo(Grupo g) {
		contactos.add(g);
	}

}
