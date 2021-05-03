package ms.producto.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ms.producto.domain.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

	Optional<Material> findByPrecio(Optional<Double> precio);

	Optional<Material> findByNombre(Optional<String> nombre);
	
	Optional<Material> findByStockActualBetween(Optional<Integer> stockMin, Optional<Integer> StockMax);

}