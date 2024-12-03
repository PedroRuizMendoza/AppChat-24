package modelo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.swing.ImageIcon;



public class Usuario {
	private static final double PRECIO_PREMIUM = 20.00;
	private static final LocalDate FECHA_JOVEN = LocalDate.of(2003, 1, 1);

	private int codigo;
	private String nombre;
	private String apellidos;
	private String contraseña;
	private String telefono;
	private String saludo;
	private LocalDate fecha = FECHA_JOVEN;
	private List<ImageIcon> imagen;
	private List<ContactoIndividual> contactos = new LinkedList<ContactoIndividual>() ;
	private boolean premium = false;
	private Optional<Descuento> descuento;
	 

	
	public Usuario(String nombre, String apellidos, String contraseña, String telefono, String saludo,
			LocalDate fecha, List<ImageIcon> imagenes, List<ContactoIndividual> contactos) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.contraseña = contraseña;
		this.telefono = telefono;
		this.saludo = saludo;
		this.fecha = fecha;
		this.imagen = imagenes;
		this.contactos = contactos;


	}
	public Usuario(ImageIcon icono, String nombre, LocalDate fecha, String numTelefono, String apellidos,
			String password, String saludo) {
		this(nombre, apellidos, password, numTelefono, saludo, fecha, new LinkedList<>(Arrays.asList(icono)) ,new LinkedList<ContactoIndividual>());
				
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

	public LocalDate getFecha() {
		System.out.println(fecha);

		if (fecha == null)
			return LocalDate.now();
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public List<ContactoIndividual> getContactos() {
		return contactos;
	}

	public void setContactos(List<ContactoIndividual> contactos) {
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
	

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int addProfilePhoto(ImageIcon icon) {
		imagen.add(icon);
		return imagen.size();
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

	//public void addGrupo(Grupo g) {
		//contactos.add(g);
	//}
	
	
	public boolean hasIndividualContact(String telefono) {
		return contactos.stream().anyMatch(c -> c instanceof ContactoIndividual && c.getNombre().equals(telefono));
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(telefono);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(telefono, other.telefono);
	}

	

	
	
}
