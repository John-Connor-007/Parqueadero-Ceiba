package parqueadero;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parqueadero.dominio.ReglaConsultarVehiculos;
import parqueadero.dominio.ReglaVehiculoEntrada;
import parqueadero.dominio.ReglaVehiculoSalida;
import parqueadero.dominio.Vehiculo;
import parqueadero.entidad.VehiculoEntity;

@RestController
@RequestMapping("/api")
public class ControllerServices {

	@Autowired
	@Qualifier("webService.dominio.ReglaVehiculoEntrada")
	private ReglaVehiculoEntrada reglaVehiculoEntrada;
	
	@Autowired
	@Qualifier("webService.dominio.ReglaVehiculoSalida")
	private ReglaVehiculoSalida reglaVehiculoSalida;
	
	@Autowired
	@Qualifier("webService.dominio.ReglaConsultarVehiculos")
	private ReglaConsultarVehiculos reglaConsultarVehiculos;
	
	@PostMapping("/ingresarVehiculoAlParqueadero")
	public void insert(@RequestBody Vehiculo vehiculo) throws Exception {
		reglaVehiculoEntrada.permitirEntradaDeVehiculo(vehiculo);	
	}
		
	@PostMapping("/consultarLosVehiculosParqueados")
	public List<VehiculoEntity> consultarLosVehiculosParqueados(){
		return reglaConsultarVehiculos.consultarLosVehiculosParqueados();
	}
	

	@PostMapping("/sacarElVehiculoDelParqueadero")
	public void insert(@RequestBody int id) throws Exception {
		reglaVehiculoSalida.sacarVehiculoDelParqueadero(id);	
	}
}

	