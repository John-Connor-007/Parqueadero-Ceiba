package parqueadero.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import parqueadero.dominio.reposistorio.RepositorioVehiculo;
import parqueadero.entidad.VehiculoEntity;


@Repository("webService.dominio.ReglaMotoAltoCilindraje")
public class ReglaMotoAltoCilindraje {
	public static final int MOTO_DE_ALTO_CILINDRAJE = 500;
	public static final double SOBRECARGO_ALTO_CILINDRAJE = 2000;
	
	@Autowired
	@Qualifier("webService.dominio.reposistorio.RepositorioVehiculo")
	private RepositorioVehiculo repositorioVehiculo;
	
	public double calcularServicioDeMotodeAltoCilindraje (int idVehiculo) {
		double precio = 0;
		VehiculoEntity vehiculoEntity =	repositorioVehiculo.buscarVehiculoParaSalir(idVehiculo);
		if(vehiculoEntity.getCilindraje() > MOTO_DE_ALTO_CILINDRAJE){
			precio = SOBRECARGO_ALTO_CILINDRAJE;
		}else {
			precio = 0;
		}
		return precio;
	}
	
}
