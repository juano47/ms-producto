package ms.producto.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ms.producto.domain.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

	List<Material> findByPrecioLessThanEqual(Double precio, PageRequest page);

	Optional<Material> findByNombre(String nombre);
	
	List<Material> findByStockActualBetween(Integer stockMin, Integer StockMax, PageRequest page);

}