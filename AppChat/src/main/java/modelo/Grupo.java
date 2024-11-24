package modelo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;





public class Grupo extends Contacto {

	// Properties
	private List<ContactoIndividual> integrantes;
	private Usuario admin;
	
	public Grupo(String nombre, List<ContactoIndividual> contactos, Usuario admin) {
		super(nombre);
		this.integrantes = contactos;
		this.admin = admin;
	}
	
	public List<ContactoIndividual> getParticipantes() {
		return integrantes;
	}

	
	public void setIntegrantes(List<ContactoIndividual> contactos) {
		this.integrantes = contactos;
	}


	@Override
	public List<Mensaje> getMensajesRecibidos(Optional<Usuario> emptyOpt) {
		return this.integrantes.stream().flatMap(c -> c.getUsuario().getContactos().stream())
				.filter(c -> c instanceof Grupo).map(c -> (Grupo) c).filter(g -> this.equals(g))
				.flatMap(g -> g.getMensajesEnviados().stream()).collect(Collectors.toList());
	}
	
	
	
}
