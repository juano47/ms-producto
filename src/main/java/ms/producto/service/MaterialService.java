package ms.producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;

import ms.producto.dao.MaterialDTO;
import ms.producto.domain.Material;

public interface MaterialService {

	Optional<Material> buscarPorId(Integer id);

	List<Material> findAll();

	Material save(Material material);

	void update(Integer id, Material nuevo);

	void delete(Integer id);

	Optional<Material> findByNombre(String nombre);
	
	List<MaterialDTO> findByStockActualBetween(Integer min, Integer max);

	List<MaterialDTO> findByPrecioLessThanEqual(Double precio);

}
