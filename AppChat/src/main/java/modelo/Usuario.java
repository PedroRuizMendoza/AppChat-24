package modelo;

import java.util.List;

import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;


public class Usuario {
	private int codigo;
	private String nombre;
	private String apellidos;
	private String contraseña;
	private String telefono;
	private String saludo;
	private JDateChooser fecha;
	private ImageIcon imagen;
	private List<Contacto> contactos;
	
	
	
	
	
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
	
	
	
	
	
}
