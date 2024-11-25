package persistencia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import modelo.Contacto;
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
	
	

	
	// -------------------Funciones auxiliares-----------------------------
	private void registrarSiNoExistenContactosoGrupos(List<Contacto> contactos) {
	/*	AdaptadorIndividualContactTDS adaptadorContactos = AdaptadorIndividualContactTDS.getInstancia();
		AdaptadorGroupTDS adaptadorGrupos = AdaptadorGroupTDS.getInstancia();
		contactos.stream().forEach(c -> {
			if (c instanceof IndividualContact) {
				adaptadorContactos.registrarContacto((IndividualContact) c);
			} else {
				adaptadorGrupos.registrarGrupo((Grupo) c);
				
			}
		});
		*/
	}
	
	private void registrarSiNoExistenContactosoGrupos(Contacto contacto) {
		LinkedList<Contacto> contactos = new LinkedList<>();
		contactos.add(contacto);
		registrarSiNoExistenContactosoGrupos(contactos);
	}
	
	
	private void registrarSiNoExisteUsuario(Usuario emisor) {
		AdaptadorUsuario.getInstancia().registrarUsuario(emisor);
	}
	
	
	
	
}
