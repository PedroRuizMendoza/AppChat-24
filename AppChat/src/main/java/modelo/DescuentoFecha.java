package modelo;

public class DescuentoFecha implements Descuento{

	@Override
	public double getDescuento(double precioInicial) {
		return 0.6 * precioInicial;
	}

}
