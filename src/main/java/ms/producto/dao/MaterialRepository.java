package ms.producto.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ms.producto.domain.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

	Page<Material> findByPrecioLessThanEqual(Double precio, Pageable page);

	Optional<Material> findByNombre(String nombre);
	
	Page<Material> findByStockActualBetween(Integer stockMin, Integer StockMax, Pageable page);

	Page<Material> findByStockActualGreaterThanEqual(Integer min, Pageable page);

	Page<Material> findByStockActualLessThanEqual(Integer max, Pageable page);

	Page<Material> findByPrecioBetween(Double min, Double max, Pageable of);

	Page<Material> findByPrecioGreaterThanEqual(Double min, Pageable of);

}