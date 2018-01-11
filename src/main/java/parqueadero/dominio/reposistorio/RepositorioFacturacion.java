package parqueadero.dominio.reposistorio;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import parqueadero.entidad.FacturacionEntity;


@Repository("webService.dominio.reposistorio.RepositorioFacturacion")
public interface RepositorioFacturacion extends JpaRepository<FacturacionEntity, Serializable> {
	
	@Query("select f from FacturacionEntity f where f.idVehiculo = :idVehiculo ")
	FacturacionEntity salidaDelVehiculo(@Param("idVehiculo")int idVehiculo);
}
