package persistencia;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import beans.Entidad;
import beans.Propiedad;
import modelo.Contacto;
import modelo.ContactoIndividual;
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

		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());

		if (eUsuario != null) {
			return;
		}

		eUsuario = new Entidad();

		eUsuario.setNombre("usuario");
		eUsuario.setPropiedades(new ArrayList<>(Arrays.asList(new Propiedad("nombre", usuario.getNombre()),
				new Propiedad("apellidos", usuario.getApellidos()), new Propiedad("password", usuario.getContraseña()),
				new Propiedad("telefono", usuario.getTelefono()), new Propiedad("saludo", usuario.getSaludo()),
				new Propiedad("fecha", usuario.getFecha().toString()),
				new Propiedad("contactos", obtenerCodigosContactoIndividual(usuario.getContactos())),
				new Propiedad("imagen", obtenerPathImagen(usuario.getProfilePhoto())),
				new Propiedad("premium", String.valueOf(usuario.isPremium())))));

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
		System.out.println(servPersistencia.recuperarPropiedadEntidad(eUser, "nombre"));
		System.out.println(servPersistencia.recuperarPropiedadEntidad(eUser, "apellidos"));
		System.out.println(servPersistencia.recuperarPropiedadEntidad(eUser, "telefono"));
		System.out.println(servPersistencia.recuperarPropiedadEntidad(eUser, "saludo"));
		System.out.println(servPersistencia.recuperarPropiedadEntidad(eUser, "codigo"));
		System.out.println(servPersistencia.recuperarPropiedadEntidad(eUser, "fecha"));
		System.out.println(servPersistencia.recuperarPropiedadEntidad(eUser, "imagen"));

		// recuperar propiedades que no son objetos
		// fecha

		String nombre = servPersistencia.recuperarPropiedadEntidad(eUser, "nombre");
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUser, "apellidos");
		String password = servPersistencia.recuperarPropiedadEntidad(eUser, "password");
		String telefono = servPersistencia.recuperarPropiedadEntidad(eUser, "telefono");
		String saludo = servPersistencia.recuperarPropiedadEntidad(eUser, "saludo");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate fecha = LocalDate.parse(servPersistencia.recuperarPropiedadEntidad(eUser, "fecha"));
		Boolean premium = Boolean.valueOf(servPersistencia.recuperarPropiedadEntidad(eUser, "premium"));
		String pathImages = servPersistencia.recuperarPropiedadEntidad(eUser, "imagen");

		ImageIcon imagen = obtenerImagenDesdePath(pathImages);

		Usuario usuario = new Usuario(nombre, apellidos, password, telefono, saludo, fecha, imagen, new LinkedList<>());
		usuario.setCodigo(codigo);

		// Metemos el usuario en el pool antes de llamar a otros
		// adaptadores
		PoolDAO.getInstancia().addObjeto(codigo, usuario);

// Contactos que el usuario tiene
		List<ContactoIndividual> contactos = obtenerContactosDesdeCodigos(
				servPersistencia.recuperarPropiedadEntidad(eUser, "contactos"));

		for (ContactoIndividual c : contactos)
			usuario.addContacto(c);

		/*
		 * // Grupos que el usuario tiene List<Grupo> grupos =
		 * obtenerGruposDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUser,
		 * "grupos"));
		 * 
		 * for (Grupo g : grupos) usuario.addGrupo(g);
		 */
		// devolver el objeto usuario con todo
		return usuario;
	}

	@Override
	public void modificarUsuario(Usuario user) {
		Entidad eUser = servPersistencia.recuperarEntidad(user.getCodigo());

		// Se da el cambiazo a las propiedades del usuario
		servPersistencia.eliminarPropiedadEntidad(eUser, "nombre");
		servPersistencia.anadirPropiedadEntidad(eUser, "nombre", user.getNombre());
		servPersistencia.eliminarPropiedadEntidad(eUser, "fecha");
		servPersistencia.anadirPropiedadEntidad(eUser, "fecha", user.getFecha().toString());
		servPersistencia.eliminarPropiedadEntidad(eUser, "telefono");
		servPersistencia.anadirPropiedadEntidad(eUser, "telefono", user.getTelefono());
		servPersistencia.eliminarPropiedadEntidad(eUser, "apellidos");
		servPersistencia.anadirPropiedadEntidad(eUser, "apellidos", user.getApellidos());
		servPersistencia.eliminarPropiedadEntidad(eUser, "password");
		servPersistencia.anadirPropiedadEntidad(eUser, "password", user.getContraseña());
		// servPersistencia.eliminarPropiedadEntidad(eUser, "imagenes");
		// servPersistencia.anadirPropiedadEntidad(eUser, "imagenes",
		// obtenerPathImagenes(user.getProfilePhotos()));
		servPersistencia.eliminarPropiedadEntidad(eUser, "premium");
		servPersistencia.anadirPropiedadEntidad(eUser, "premium", String.valueOf(user.isPremium()));

		servPersistencia.eliminarPropiedadEntidad(eUser, "contactos");
		servPersistencia.anadirPropiedadEntidad(eUser, "contactos",
				obtenerCodigosContactoIndividual(user.getContactos()));
		// servPersistencia.eliminarPropiedadEntidad(eUser, "grupos");
		// servPersistencia.anadirPropiedadEntidad(eUser, "grupos",
		// obtenerCodigosGrupo(user.getContactos()));
		servPersistencia.eliminarPropiedadEntidad(eUser, "saludo");
		servPersistencia.anadirPropiedadEntidad(eUser, "saludo", user.getSaludo());
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		List<Usuario> usuarios = new LinkedList<>();
		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");

		for (Entidad eUsuario : eUsuarios) {
			System.out.println(eUsuario);
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}
		return usuarios;
	}

	private String obtenerCodigosContactoIndividual(List<ContactoIndividual> list) {
		return list.stream().filter(c -> c instanceof ContactoIndividual) // me quedo con los contactos
																			// individuales
				.map(c -> String.valueOf(c.getCodigo())).reduce("", (l, c) -> l + c + " ") // concateno todos los
																							// codigos
				.trim();
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

		// Verificamos que codigos no sea null ni vacío
		if (codigos == null || codigos.isEmpty()) {
			return contactos; // Si es null o vacío, devolvemos una lista vacía
		}

		StringTokenizer strTok = new StringTokenizer(codigos, " ");
		AdaptadorContactoIndividual adaptadorC = AdaptadorContactoIndividual.getInstancia();

		while (strTok.hasMoreTokens()) {
			String token = strTok.nextToken();
			if (token != null && !token.trim().isEmpty()) {
				contactos.add(adaptadorC.recuperarContacto(Integer.valueOf(token)));
			}
		}
		return contactos;
	}

	private String obtenerPathImagen(ImageIcon imagen) {
		if (imagen != null && imagen.getDescription() != null) {
			return imagen.getDescription().trim(); // Devuelve la descripción de la imagen (path)
		}
		return ""; // Devuelve una cadena vacía si la imagen es nula o no tiene descripción
	}

	private ImageIcon obtenerImagenDesdePath(String pathImage) {
		
		if (pathImage != null && !pathImage.trim().isEmpty()) {

			URL url = null;

			try {
				url = new URL(pathImage);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedImage image = null;
			try {
				image = ImageIO.read(url);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Image resizedImage = image.getScaledInstance(75	, 75, Image.SCALE_SMOOTH); // Reescalar la imagen
			ImageIcon icono = new ImageIcon(resizedImage);
			
			return icono;

		}
		return null; // Devuelve null si la ruta es inválida o el recurso no existe
	}

}
