package ms.producto.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ms.producto.domain.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

	Page<Material> findByPrecioLessThanEqual(Double precio, PageRequest page);

	Optional<Material> findByNombre(String nombre);
	
	Page<Material> findByStockActualBetween(Integer stockMin, Integer StockMax, PageRequest page);

	Page<Material> findByStockActualGreaterThanEqual(Integer min, PageRequest page);

	Page<Material> findByStockActualLessThanEqual(Integer max, PageRequest page);

}