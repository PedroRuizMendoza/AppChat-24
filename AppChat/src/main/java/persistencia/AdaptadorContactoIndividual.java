package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Mensaje;
import modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;



public class AdaptadorContactoIndividual implements ContactoIndividualDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorContactoIndividual unicaInstancia = null;

	private AdaptadorContactoIndividual() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public static AdaptadorContactoIndividual getInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AdaptadorContactoIndividual();
		return unicaInstancia;
	}
	
	@Override
	public void registrarContacto(ContactoIndividual contacto) {
		Entidad eContact = new Entidad();
		boolean existe = true;

		// Si la entidad está registrada no la registra de nuevo
		try {
			eContact = servPersistencia.recuperarEntidad(contacto.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe)
			return;

		// Registramos primero los atributos que son objetos
		// Registrar los mensajes del contacto
		registrarSiNoExistenMensajes(contacto.getMensajesEnviados());

		// Registramos al usuario correspondiente al contacto si no existe.
		registrarSiNoExisteUser(contacto.getUsuario());

		// Atributos propios del contacto
		eContact.setNombre("contacto");
		eContact.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("nombre", contacto.getNombre()),
				new Propiedad("movil", String.valueOf(contacto.getMovil())),
				new Propiedad("mensajesRecibidos", obtenerCodigosMensajesRecibidos(contacto.getMensajesEnviados())),
				new Propiedad("usuario", String.valueOf(contacto.getUsuario().getCodigo())))));

		// Registrar entidad usuario
		eContact = servPersistencia.registrarEntidad(eContact);

		// Identificador unico
		contacto.setCodigo(eContact.getId());
		
		// Guardamos en el pool
		PoolDAO.getInstancia().addObjeto(contacto.getCodigo(), contacto);
	}
	
	
	//-------------------- Funciones auxiliares.---------------------------------
	
		private void registrarSiNoExistenMensajes(List<Mensaje> messages) {
			AdaptadorMensaje adaptadorMensajes = AdaptadorMensaje.getInstancia();
			messages.stream().forEach(m -> adaptadorMensajes.registrarMensaje(m));
		}

		private void registrarSiNoExisteUser(Usuario admin) {
			AdaptadorUsuario adaptadorUsuarios = AdaptadorUsuario.getInstancia();
			adaptadorUsuarios.registrarUsuario(admin);
		}

		private String obtenerCodigosMensajesRecibidos(List<Mensaje> mensajesRecibidos) {
			return mensajesRecibidos.stream().map(m -> String.valueOf(m.getCodigo())).reduce("", (l, m) -> l + m + " ")
					.trim();
		}


	

	 
}
