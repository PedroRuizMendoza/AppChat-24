package controlador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;

import modelo.CatalogoUsuario;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Usuario;
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
        inicializarAdaptadores(); // Debe ser la primera línea para evitar error de sincronización
        inicializarCatalogos();
    }

    // Aplicamos el patrón Singleton.
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
            System.err.println("Error al obtener la instancia de la factoria DAO: " + e.getMessage());
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
       adaptadorUsuario.recuperarTodosUsuarios().stream().forEach(u -> catalogoUsuarios.addUsuario(u));

    }
 
    
    
    
    
    
    public boolean iniciarSesion(String telefono, String contraseña) {
        if (telefono.isEmpty() || contraseña.isEmpty()) {
            System.out.println("Teléfono o contraseña están vacíos.");
            return false;
        }

        System.out.println("Intentando iniciar sesión con teléfono: " + telefono);

        // Obtener el usuario opcional desde el catálogo
        Optional<Usuario> cliente = catalogoUsuarios.getUsuarioNumTelf(telefono);

		if (cliente == null || cliente.get().getTelefono() == null) {
			System.out.println("sale por cliente == null " + telefono);
			return false;
		}
		// Si la password esta bien inicia sesion
		if (cliente.get().getContraseña().equals(contraseña)) {
			usuarioActual = cliente.get();
			 System.out.println("Cuenta iniciada: " + telefono);
			return true;
		}
		System.out.println("sale por  no cliente == null y tampco coinciden las contraseñas " + telefono);
		return false;
	}
    

    // Registro el usuario. Devuelvo false si el teléfono ya está en uso
    public boolean crearCuenta(ImageIcon imagen, String nombre, String apellidos, String password,
            String numTelefono, LocalDate fechaNacimiento, String saludo) {

        Usuario nuevoUsuario = new Usuario(imagen, nombre, fechaNacimiento, numTelefono, apellidos, password, saludo);
        System.out.println("Intentando registrar usuario: " + numTelefono);

        if (!catalogoUsuarios.contains(nuevoUsuario)) {
            System.out.println("Usuario no encontrado en el catálogo. Registrando...");

            // Conexion con la persistencia
            adaptadorUsuario.registrarUsuario(nuevoUsuario);
            catalogoUsuarios.addUsuario(nuevoUsuario);
            System.out.println("Usuario registrado exitosamente: " + numTelefono);


            return iniciarSesion(numTelefono, password);
            
        } else {
            System.out.println("El teléfono ya está en uso: " + numTelefono);
            return false;
        }
    
}
	public ContactoIndividual crearContacto(String nombre, String numTelefono) {
		// Si no tiene el contacto guardado lo guarda
		if (!usuarioActual.hasIndividualContact(nombre)) {
			Optional<Usuario> usuarioOpt = catalogoUsuarios.getUsuarioNumTelf(numTelefono);

			if (usuarioOpt.isPresent()) {
				ContactoIndividual nuevoContacto = new ContactoIndividual(nombre, numTelefono, usuarioOpt.get());
				usuarioActual.addContacto(nuevoContacto);

				adaptadorContactoIndividual.registrarContacto(nuevoContacto);

				adaptadorUsuario.modificarUsuario(usuarioActual);
				return nuevoContacto;
			}
		}
		return null;
	}
	
	public List<Contacto> getContactosUsuarioActual() {
		if (usuarioActual == null)
			return new LinkedList<Contacto>();
		Usuario u = catalogoUsuarios.getUsuario(usuarioActual.getCodigo());
		return u.getContactos();
	}
	
   
}
