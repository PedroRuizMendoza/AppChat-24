package persistencia;

import java.util.ArrayList;
import java.util.Arrays;

import beans.Entidad;
import beans.Propiedad;
import modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;


public class AdaptadorUsuarioTDS implements UsuarioDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	
	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
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
				new Propiedad("contraseña", usuario.getContraseña())
				//new Propiedad("imagen", obtenerPathImagenes(usuario.getImagen()))		
				)));
		
		// Registrar entidad usuario
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		
		
		
		
		
	}

}
