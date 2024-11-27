package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import modelo.ContactoIndividual;
import modelo.Grupo;
import modelo.Mensaje;
import modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;


public class AdaptadorGrupo implements GrupoDao {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorGrupo unicaInstancia = null;

	private AdaptadorGrupo() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public static AdaptadorGrupo getInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AdaptadorGrupo();
		return unicaInstancia;
	}
	
	
	
	@Override
	public Grupo recuperarGrupo(int codigo) {
		// Si la entidad esta en el pool la devuelve directamente
		if (PoolDAO.getInstancia().contiene(codigo))
			return (Grupo) PoolDAO.getInstancia().getObjeto(codigo);

		// Sino, la recupera de la base de datos
		// Recuperamos la entidad
		Entidad eGroup = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		String nombre = null;
		nombre = servPersistencia.recuperarPropiedadEntidad(eGroup, "nombre");

		Grupo group = new Grupo(nombre, new LinkedList<Mensaje>(), new LinkedList<ContactoIndividual>(), null);
		group.setCodigo(codigo);

		// Metemos al grupo en el pool antes de llamar a otros adaptadores
		PoolDAO.getInstancia().addObjeto(codigo, group);
		
		// Mensajes que el grupo tiene
		List<Mensaje> mensajes = obtenerMensajesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eGroup, "mensajesRecibidos"));
		for (Mensaje m : mensajes)
			group.sendMessage(m);
				
		// Contactos que el grupo tiene
		List<ContactoIndividual> contactos = obtenerIntegrantesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eGroup, "integrantes"));
		for (ContactoIndividual c : contactos)
			group.addIntegrante(c);

		// Obtener admin
		group.cambiarAdmin(obtenerUsuarioDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eGroup, "admin")));

		// Devolvemos el objeto grupo
		return group;
	}
	
	

	@Override
	public void registrarGrupo(Grupo group) {
		Entidad eGroup = new Entidad();
		boolean existe = true;

		// Si la entidad está registrada no la registra de nuevo
		try {
			eGroup = servPersistencia.recuperarEntidad(group.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe)
			return;

		// Registramos primero los atributos que son objetos
		// Registrar los mensajes del grupo
		registrarSiNoExistenMensajes(group.getMensajesEnviados());

		// Registramos los contactos del grupo si no existen (Integrantes)
		registrarSiNoExistenContactos(group.getParticipantes());

		// Registramos a nuestro usuario administrador si no existe.
		registrarSiNoExisteAdmin(group.getAdmin());

		// Atributos propios del grupo
		eGroup.setNombre("grupo");
		eGroup.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(new Propiedad("nombre", group.getNombre()),
				new Propiedad("mensajesRecibidos", obtenerCodigosMensajesRecibidos(group.getMensajesEnviados())),
				new Propiedad("integrantes", obtenerCodigosContactosIndividual(group.getParticipantes())),
				new Propiedad("admin", String.valueOf(group.getAdmin().getCodigo())))));

		// Registrar entidad usuario
		eGroup = servPersistencia.registrarEntidad(eGroup);

		// Identificador unico
		group.setCodigo(eGroup.getId());
		
		// Guardamos en el pool
		PoolDAO.getInstancia().addObjeto(group.getCodigo(), group);
	}
	
	// ---------------------------------Funciones auxiliares.--------------------------
	
	private List<Mensaje> obtenerMensajesDesdeCodigos(String codigos) {
		List<Mensaje> mensajes = new LinkedList<>();
		StringTokenizer strTok = new StringTokenizer(codigos, " ");
		AdaptadorMensaje adaptadorMensajes = AdaptadorMensaje.getInstancia();
		while (strTok.hasMoreTokens()) {
			mensajes.add(adaptadorMensajes.recuperarMensaje(Integer.valueOf((String) strTok.nextElement())));
		}
		return mensajes;
	}
	
	private List<ContactoIndividual> obtenerIntegrantesDesdeCodigos(String codigos) {
		List<ContactoIndividual> contactos = new LinkedList<>();
		StringTokenizer strTok = new StringTokenizer(codigos, " ");
		AdaptadorContactoIndividual adaptadorC = AdaptadorContactoIndividual.getInstancia();
		while (strTok.hasMoreTokens()) {
			contactos.add(adaptadorC.recuperarContacto(Integer.valueOf((String) strTok.nextElement())));
		}
		return contactos;
	}
	
	private Usuario obtenerUsuarioDesdeCodigo(String codigo) {
		AdaptadorUsuario adaptadorUsuarios = AdaptadorUsuario.getInstancia();
		return adaptadorUsuarios.recuperarUsuario(Integer.valueOf(codigo));
	}
	
	private void registrarSiNoExistenMensajes(List<Mensaje> messages) {
		AdaptadorMensaje adaptadorMensajes = AdaptadorMensaje.getInstancia();
		messages.stream().forEach(m -> adaptadorMensajes.registrarMensaje(m));
	}
	private void registrarSiNoExistenContactos(List<ContactoIndividual> contacts) {
		AdaptadorContactoIndividual adaptadorContactos = AdaptadorContactoIndividual.getInstancia();
		contacts.stream().forEach(c -> adaptadorContactos.registrarContacto(c));
	}

	private void registrarSiNoExisteAdmin(Usuario admin) {
		AdaptadorUsuario adaptadorUsuarios = AdaptadorUsuario.getInstancia();
		adaptadorUsuarios.registrarUsuario(admin);
	}
	
	private String obtenerCodigosMensajesRecibidos(List<Mensaje> mensajesRecibidos) {
		return mensajesRecibidos.stream().map(m -> String.valueOf(m.getCodigo())).reduce("", (l, m) -> l + m + " ")
				.trim();
	}
	private String obtenerCodigosContactosIndividual(List<ContactoIndividual> contactosIndividuales) {
		return contactosIndividuales.stream().map(c -> String.valueOf(c.getCodigo())).reduce("", (l, c) -> l + c + " ")
				.trim();
	}
	
}
