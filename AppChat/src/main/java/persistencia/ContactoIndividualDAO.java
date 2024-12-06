package persistencia;


import modelo.ContactoIndividual;

public interface ContactoIndividualDAO {


	void registrarContacto(ContactoIndividual contacto);

	ContactoIndividual recuperarContacto(int codigo);



}
