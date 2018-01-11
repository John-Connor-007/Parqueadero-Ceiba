package parqueadero.dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import parqueadero.dominio.reposistorio.RepositorioVehiculo;
import parqueadero.entidad.VehiculoEntity;



@Repository("webService.dominio.ReglaConsultarVehiculos")
public class ReglaConsultarVehiculos {

	@Autowired
	@Qualifier("webService.dominio.reposistorio.RepositorioVehiculo")
	private RepositorioVehiculo repositorioVehiculo;
	
	public List<VehiculoEntity> consultarLosVehiculosParqueados (){
		return repositorioVehiculo.obtenerTodosLosVehiculoParqueados();
	}
}
