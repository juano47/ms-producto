package ms.producto.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ms.producto.domain.Unidad;

@Repository
public interface UnidadRepository extends JpaRepository<Unidad, Integer> {

	Optional<Unidad> findByDescripcion(String desc);
	

}