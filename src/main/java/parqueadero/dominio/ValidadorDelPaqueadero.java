package parqueadero.dominio;

public class ValidadorDelPaqueadero {
	
	public static final int CUPOS_CARROS = 20;
	public static final int CUPOS_MOTOS = 10;
	
	public boolean placaQueComienzanPorA (String placa){
		return (placa.charAt(0) != 'A') ? true : false;
	}
	
	public boolean disponibilidadDelParqueaderoDeCarro (int cantidadCarro) {
		return cantidadCarro < CUPOS_CARROS ? true : false;
	}
	
	public boolean disponibilidadDelParqueaderoDeMoto (int cantidadMoto) {
		return cantidadMoto < CUPOS_MOTOS ? true : false ;
	}
}
