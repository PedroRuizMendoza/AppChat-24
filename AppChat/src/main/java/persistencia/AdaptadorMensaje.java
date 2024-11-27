package persistencia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Grupo;
import modelo.Mensaje;
import modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;





public class AdaptadorMensaje implements MensajeDAO{

	
	private static AdaptadorMensaje unicaInstancia = null;
	private static ServicioPersistencia servPersistencia;

	private AdaptadorMensaje() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public static AdaptadorMensaje getInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AdaptadorMensaje();
		return unicaInstancia;
	}
	
	@Override
	public void registrarMensaje(Mensaje mensaje) {
		Entidad eMensaje = new Entidad();
		boolean existe = true;

		// Si la entidad está registrada no la registra de nuevo
		try {
			eMensaje = servPersistencia.recuperarEntidad(mensaje.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) {
			return;
		}

		// registrar primero los atributos que son objetos
		// registrar usuario emisor del mensaje
		registrarSiNoExisteUsuario(mensaje.getEmisor());

		// registrar contacto receptor del mensaje
		registrarSiNoExistenContactosoGrupos(mensaje.getReceptor());

		// Atributos propios del usuario
		eMensaje.setNombre("mensaje");

		// Se guarda el grupo receptor o el contacto, segun convenga
		boolean grupo = false;
		if (mensaje.getReceptor() instanceof Grupo) {
			grupo = true;
		}

		eMensaje.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad("texto", mensaje.getTexto()),
				new Propiedad("receptor", String.valueOf(mensaje.getReceptor().getCodigo())),
				new Propiedad("togroup", String.valueOf(grupo)),
				new Propiedad("emisor", String.valueOf(mensaje.getEmisor().getCodigo())))));

		// Registrar entidad mensaje
		eMensaje = servPersistencia.registrarEntidad(eMensaje);

		// Identificador unico
		mensaje.setCodigo(eMensaje.getId());
		
		// Guardamos en el pool
		PoolDAO.getInstancia().addObjeto(mensaje.getCodigo(), mensaje);
	}
	
	@Override
	public Mensaje recuperarMensaje(int codigo) {
		// Si la entidad esta en el pool la devuelve directamente
		if (PoolDAO.getInstancia().contiene(codigo))
			return (Mensaje) PoolDAO.getInstancia().getObjeto(codigo);

		// si no, la recupera de la base de datos
		// recuperar entidad
		Entidad eMensaje = servPersistencia.recuperarEntidad(codigo);
		
		// recuperar propiedades que no son objetos
		// fecha
		String texto = servPersistencia.recuperarPropiedadEntidad(eMensaje, "texto");
		LocalDateTime hora = LocalDateTime.parse(servPersistencia.recuperarPropiedadEntidad(eMensaje, "hora"));
		Contacto receptor = null;
		Boolean toGroup = Boolean.valueOf(servPersistencia.recuperarPropiedadEntidad(eMensaje, "togroup"));
		Usuario emisor = null;

		Mensaje mensaje = new Mensaje(texto, hora);
		mensaje.setCodigo(codigo);

		// Metemos el usuario en el pool antes de llamar a otros
		// adaptadores
		PoolDAO.getInstancia().addObjeto(codigo, mensaje);

		// recuperar propiedades que son objetos llamando a adaptadores
		// mensaje
		// Usuario emisor
		AdaptadorUsuario adaptadorU = AdaptadorUsuario.getInstancia();
		int codigoUsuario = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, "emisor"));
		emisor = adaptadorU.recuperarUsuario(codigoUsuario);
		mensaje.setEmisor(emisor);

		// Contacto o grupo receptor
		int codigoContacto = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, "receptor"));
		if (toGroup) {
			AdaptadorGrupo adaptadorG = AdaptadorGrupo.getInstancia();
			receptor = adaptadorG.recuperarGrupo(codigoContacto);
		} else {
			AdaptadorContactoIndividual adaptadorC = AdaptadorContactoIndividual.getInstancia();
			receptor = adaptadorC.recuperarContacto(codigoContacto);
		}
		mensaje.setReceptor(receptor);

		// devolver el objeto mensaje con todo
		return mensaje;
	}
	

	
	// -------------------Funciones auxiliares-----------------------------
	private void registrarSiNoExistenContactosoGrupos(List<Contacto> contactos) {
		AdaptadorContactoIndividual adaptadorContactos = AdaptadorContactoIndividual.getInstancia();
		AdaptadorGrupo adaptadorGrupos = AdaptadorGrupo.getInstancia();
		contactos.stream().forEach(c -> {
			if (c instanceof ContactoIndividual) {
				adaptadorContactos.registrarContacto((ContactoIndividual) c);
			} else {
				adaptadorGrupos.registrarGrupo((Grupo) c);
				
			}
		});
		
	}
	
	private void registrarSiNoExistenContactosoGrupos(Contacto contacto) {
		LinkedList<Contacto> contactos = new LinkedList<>();
		contactos.add(contacto);
		registrarSiNoExistenContactosoGrupos(contactos);
	}
	
	
	private void registrarSiNoExisteUsuario(Usuario emisor) {
		AdaptadorUsuario.getInstancia().registrarUsuario(emisor);
	}
	private String obtenerCodigosContactosIndividual(List<ContactoIndividual> contactosIndividuales) {
		return contactosIndividuales.stream().map(c -> String.valueOf(c.getCodigo())).reduce("", (l, c) -> l + c + " ")
				.trim();
	}
	
	
	
}
