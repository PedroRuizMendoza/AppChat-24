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

	
	@Override
	public ContactoIndividual recuperarContacto(int codigo) {
		// Si la entidad esta en el pool la devuelve directamente
		if (PoolDAO.getInstancia().contiene(codigo))
			return (ContactoIndividual) PoolDAO.getInstancia().getObjeto(codigo);

		// Sino, la recupera de la base de datos
		// Recuperamos la entidad
		Entidad eContact = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		String nombre = servPersistencia.recuperarPropiedadEntidad(eContact, "nombre");
		 
		String movil = servPersistencia.recuperarPropiedadEntidad(eContact, "movil");
		
		ContactoIndividual contact = new ContactoIndividual(nombre, new LinkedList<Mensaje>(), Integer.valueOf(movil), null);
		contact.setCodigo(codigo);

		// Metemos al contacto en el pool antes de llamar a otros adaptadores
		PoolDAO.getInstancia().addObjeto(codigo, contact);
		
		// Mensajes que el contacto tiene
		List<Mensaje> mensajes = obtenerMensajesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eContact, "mensajesRecibidos"));
		for (Mensaje m : mensajes)
			contact.sendMessage(m);

		// Obtener usuario del contacto
		contact.setUsuario(obtenerUsuarioDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eContact, "usuario")));

		// Devolvemos el objeto contacto
		return contact;
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


	
		private List<Mensaje> obtenerMensajesDesdeCodigos(String codigos) {
			List<Mensaje> mensajes = new LinkedList<>();
			StringTokenizer strTok = new StringTokenizer(codigos, " ");
			AdaptadorMensaje adaptadorMensajes = AdaptadorMensaje.getInstancia();
			while (strTok.hasMoreTokens()) {
				String code = (String) strTok.nextElement();
				mensajes.add(adaptadorMensajes.recuperarMensaje(Integer.valueOf(code)));
			}
			return mensajes;
		}
		
		private Usuario obtenerUsuarioDesdeCodigo(String codigo) {
			AdaptadorUsuario adaptadorUsuarios = AdaptadorUsuario.getInstancia();
			return adaptadorUsuarios.recuperarUsuario(Integer.valueOf(codigo));
		}
	 
}
