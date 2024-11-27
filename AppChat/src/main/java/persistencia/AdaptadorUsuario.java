package persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;

import beans.Entidad;
import beans.Propiedad;
import modelo.ContactoIndividual;
import modelo.Descuento;
import modelo.Grupo;
import modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuario implements UsuarioDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuario unicaInstancia = null;
	 
	private AdaptadorUsuario() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	
	public static AdaptadorUsuario getInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AdaptadorUsuario();
		return unicaInstancia;
	}
	
	@Override
	public void registrarUsuario(Usuario usuario) {
		Entidad eUsuario = new Entidad();
		boolean existe = true;

		// Si la entidad está registrada no la registra de nuevo
		try {
			eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) {
			return;
		}

		eUsuario.setNombre("usuario");
		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad("nombre", usuario.getNombre()),
				new Propiedad("fechanacimiento", usuario.getFecha().toString()),
				new Propiedad("telefono", String.valueOf(usuario.getTelefono())),
				new Propiedad("contraseña", usuario.getContraseña()),
				new Propiedad("premium", String.valueOf(usuario.isPremium())),
				new Propiedad("saludo", usuario.getSaludo()))));
		// Registrar entidad usuario
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
	}

	
	
	@Override
	public Usuario recuperarUsuario(int codigo) {
		// Si la entidad esta en el pool la devuelve directamente
		if (PoolDAO.getInstancia().contiene(codigo))
			return (Usuario) PoolDAO.getInstancia().getObjeto(codigo);

		// si no, la recupera de la base de datos
		// recuperar entidad
		Entidad eUser = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		// fecha
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUser, "nombre");
		LocalDate fechaNacimiento = LocalDate
				.parse(servPersistencia.recuperarPropiedadEntidad(eUser, "fechanacimiento"));
		int telefono = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eUser, "telefono"));
		String nick = servPersistencia.recuperarPropiedadEntidad(eUser, "nick");
		String password = servPersistencia.recuperarPropiedadEntidad(eUser, "password");
		Boolean premium = Boolean.valueOf(servPersistencia.recuperarPropiedadEntidad(eUser, "premium"));
		String saludo = servPersistencia.recuperarPropiedadEntidad(eUser, "saludo");
		String descuento = servPersistencia.recuperarPropiedadEntidad(eUser, "descuento");
		Descuento descuentoOpt = null;
		if (!descuento.isEmpty()) {
			try {
				descuentoOpt = (Descuento) Class.forName(descuento).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		String pathImages = servPersistencia.recuperarPropiedadEntidad(eUser, "imagenes");
		List<ImageIcon> imagenes;
		imagenes = obtenerImagenesDesdePath(pathImages);


		Usuario usuario = new Usuario(nombre, "", password, telefono, saludo, null, imagenes, null);
		usuario.setCodigo(codigo);

		// Metemos el usuario en el pool antes de llamar a otros
		// adaptadores
		PoolDAO.getInstancia().addObjeto(codigo, usuario);


		// Contactos que el usuario tiene
		List<ContactoIndividual> contactos = obtenerContactosDesdeCodigos(
				servPersistencia.recuperarPropiedadEntidad(eUser, "contactos"));

		for (ContactoIndividual c : contactos)
			usuario.addContacto(c);

		// Grupos que el usuario tiene
		List<Grupo> grupos = obtenerGruposDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUser, "grupos"));

		for (Grupo g : grupos)
			usuario.addGrupo(g);

		// devolver el objeto usuario con todo
		return usuario;
	}
	
	
	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		List<Usuario> usuarios = new LinkedList<>();
		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");

		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}
		return usuarios;
	}


	private List<Grupo> obtenerGruposDesdeCodigos(String codigos) {
		List<Grupo> grupos = new LinkedList<>();
		StringTokenizer strTok = new StringTokenizer(codigos, " ");
		AdaptadorGrupo adaptadorG = AdaptadorGrupo.getInstancia();
		while (strTok.hasMoreTokens()) {
			grupos.add(adaptadorG.recuperarGrupo(Integer.valueOf((String) strTok.nextElement())));
		}
		return grupos;
	}

	private List<ContactoIndividual> obtenerContactosDesdeCodigos(String codigos) {
		List<ContactoIndividual> contactos = new LinkedList<>();
		StringTokenizer strTok = new StringTokenizer(codigos, " ");
		AdaptadorContactoIndividual adaptadorC = AdaptadorContactoIndividual.getInstancia();
		while (strTok.hasMoreTokens()) {
			contactos.add(adaptadorC.recuperarContacto(Integer.valueOf((String) strTok.nextElement())));
		}
		return contactos;
	}

	private String obtenerPathImagenes(List<ImageIcon> imagenes) {
		String paths = imagenes.stream().map(i -> String.valueOf(i.getDescription())).reduce("", (l, c) -> l + c + "?").trim();
		return paths.substring(0, paths.length() - 1);
	}
	
	private List<ImageIcon> obtenerImagenesDesdePath(String pathImages) {
		List<ImageIcon> imagenes = new LinkedList<>();
		StringTokenizer strTok = new StringTokenizer(pathImages, "?");
		while (strTok.hasMoreTokens()) {
			String path = (String) strTok.nextElement();
			ImageIcon imagen = new ImageIcon(AdaptadorUsuario.class.getResource(path));
			imagen.setDescription(path);
			imagenes.add(imagen);
		}
		return imagenes;
	}

}
