package modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;




public class ContactoIndividual extends Contacto {

	private String movil;
	private Usuario usuario;

	// Constructor.
	public ContactoIndividual(String nombre, String movil, Usuario usuario) {
		super(nombre);
		this.movil = movil;
		this.usuario = usuario;
	}

	public ContactoIndividual(String nombre, LinkedList<Mensaje> mensajes, String movil, Usuario usuario) {
		super(nombre,movil, mensajes);
		this.movil = movil;
		this.usuario = usuario;
	}
	public ContactoIndividual(String nombre) {
		super(nombre);

	}

	public String getMovil() {
		return movil;
	}

	public Usuario getUsuario() {
		return usuario;
	} 
	

	public ContactoIndividual getContacto(Usuario usuario) {
		return this.usuario.getContactos().stream().filter(c -> c instanceof ContactoIndividual)
				.map(c -> (ContactoIndividual) c).filter(c -> c.getUsuario().equals(usuario)).findAny().orElse(null);
	}
	
	@Override
	public List<Mensaje> getMensajesRecibidos(Optional<Usuario> usuario) {
		ContactoIndividual contacto = getContacto(usuario.orElse(null));
		if (contacto != null) {
			return contacto.getMensajesEnviados();
		} else
			return new LinkedList<>();
	}
	
	// Añade al contacto al grupo en cuestion
	public void addGrupo(Grupo grupo) {
		usuario.addGrupo(grupo);
	}
	
	
}
