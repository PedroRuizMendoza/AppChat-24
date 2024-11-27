package persistencia;

import modelo.Grupo;

public interface GrupoDao {

	Grupo recuperarGrupo(int codigo);

	void registrarGrupo(Grupo group);

}
