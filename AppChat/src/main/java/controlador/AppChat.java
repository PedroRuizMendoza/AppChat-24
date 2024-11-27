package controlador;


import modelo.CatalogoUsuario;
import modelo.Contacto;
import modelo.Usuario;
import persistencia.AdaptadorContactoIndividual;
import persistencia.AdaptadorGrupo;
import persistencia.ContactoIndividualDAO;
import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.GrupoDao;
import persistencia.MensajeDAO;
import persistencia.UsuarioDAO;



public class AppChat {
	// Instancia del controlador.
	private static AppChat unicaInstancia = null;

	// Adaptadores
	private GrupoDao adaptadorGrupo;
	private ContactoIndividualDAO adaptadorContactoIndividual;
	private MensajeDAO adaptadorMensaje;
	private UsuarioDAO adaptadorUsuario;
	// Catálogos
	private CatalogoUsuario catalogoUsuarios;

	// Nuestro usuario.
	private Usuario usuarioActual;

	// Chat seleccionado
	private Contacto chatActual;

	
	
		private AppChat() {
			inicializarAdaptadores(); // debe ser la primera linea para evitar error de sincronización
		}

		
		// Aplicamos el patrón Singleton.
		// Consiguiendo de esta forma que exista una única instancia de la clase
		// controlador que es accesible globalmente.
		public static AppChat getInstancia() {
			if (unicaInstancia == null)
				unicaInstancia = new AppChat();
			return unicaInstancia;
		}
		
		// Inicializamos los adaptadores
		private void inicializarAdaptadores() {
			FactoriaDAO factoria = null;
			try {
				factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
			adaptadorGrupo = factoria.getGrupoDAO();
			adaptadorContactoIndividual = factoria.getContactoIndividualDAO();
			adaptadorMensaje = factoria.getMensajeDAO();
			adaptadorUsuario = factoria.getUserDAO();
		}
	
		
		// Inicializamos los catálogos
		private void inicializarCatalogos() {
			catalogoUsuarios = CatalogoUsuario.getUnicaInstancia();
		}
		
		

		public boolean iniciarSesion(String nick, String password) {
			if (nick.isEmpty() || password.isEmpty())
				return false;

			Usuario cliente = catalogoUsuarios.getUsuario(nick);
			if (cliente == null)
				return false;

			// Si la password esta bien inicia sesion
			if (cliente.getContraseña().equals(password)) {
				usuarioActual = cliente;

			}
			return false;
		}
		
		
	public static boolean login(String telefono, String contraseña) {
		 
		return true;
		
	}
	


	
	
	
}
