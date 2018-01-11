package parqueadero.dominio.reposistorio;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import parqueadero.entidad.VehiculoEntity;




@Repository("webService.dominio.reposistorio.RepositorioVehiculo")
public interface RepositorioVehiculo extends JpaRepository<VehiculoEntity, Serializable> {

	@Query("select sum(1) as Suma from VehiculoEntity v where v.estadoVehiculo = true")
	int obtenerCatidadDeVehiculoParqueados ();
	
	@Query("select v from VehiculoEntity v where v.estadoVehiculo = true")
	List<VehiculoEntity> obtenerTodosLosVehiculoParqueados ();
	
	@Query("select v.id from VehiculoEntity v where v.placa = :placa")
	int obtenerElVehiculoQueApenasEntro(@Param("placa") String placa);
		
	@Query("select v from VehiculoEntity v where v.id = :id")
	VehiculoEntity buscarVehiculoParaSalir (@Param("id")int id);
	
	@Query("select v from VehiculoEntity v inner join FacturacionEntity f on v.id = f.idVehiculo where v.id = :id")
	VehiculoEntity filtro (@Param("id")int id);
	
}
