package persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

	


	
	
	

}
