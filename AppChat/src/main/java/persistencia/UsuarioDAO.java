package persistencia;

import java.util.List;

import modelo.Usuario;

public interface UsuarioDAO {

	public void registrarUsuario(Usuario usuario);

	List<Usuario> recuperarTodosUsuarios();

	Usuario recuperarUsuario(int codigo);

	void modificarUsuario(Usuario user);

}
