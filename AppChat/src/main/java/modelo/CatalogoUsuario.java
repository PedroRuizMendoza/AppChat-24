package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import controlador.AppChat;
import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.UsuarioDAO;

public class CatalogoUsuario {
	private Map<String, Usuario> usuarios;
	private static CatalogoUsuario unicaInstancia;

	private FactoriaDAO dao;
	private UsuarioDAO adaptadorUsuario;
	
	
	private CatalogoUsuario() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorUsuario = dao.getUserDAO();
			usuarios = new HashMap<String, Usuario>();
			this.cargarCatalogo();
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}
 
	
	public static CatalogoUsuario getUnicaInstancia() {
        if (unicaInstancia == null)
            unicaInstancia = new CatalogoUsuario();
        return unicaInstancia;
	}
	public Usuario getUsuario(int codigo) {
		return usuarios.values().stream().filter(u -> u.getCodigo() == codigo).findAny().orElse(null);
	}
	// Devuelve todos los usuarios
	public List<Usuario> getUsuarios() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		for (Usuario u : usuarios.values())
			lista.add(u);
		return lista;
	}

	public Optional<Usuario> getUsuarioNumTelf(String numTelefono) {
	    return Optional.of(usuarios.values().stream()
	                   .filter(u -> u.getTelefono().equals(numTelefono)) 
	                   .findAny().orElse(null));
	}

	
	
	public void addUsuario(Usuario user) {
		usuarios.put(user.getNombre(), user);
	}

	public void removeUsuario(Usuario user) {
		usuarios.remove(user.getNombre());
	}
	 
	
	// Recupera todos los usuarios para trabajar con ellos en memoria
	private void cargarCatalogo() throws DAOException {
		List<Usuario> usuariosBD = adaptadorUsuario.recuperarTodosUsuarios();
		for (Usuario user : usuariosBD)
			usuarios.put(user.getNombre(), user);
	}
	
	public boolean contains(Usuario usuario) {
		return usuarios.containsValue(usuario);
	}
	
	
	
}
