package ms.producto.service;

import java.util.List;
import java.util.Optional;

import ms.producto.domain.Material;
import ms.producto.dto.MaterialDTO;

public interface MaterialService {

	Optional<Material> buscarPorId(Integer id);

	List<Material> findAll();

	Material save(Material material);

	void update(Integer id, Material nuevo);

	void delete(Integer id);

	Optional<Material> findByNombre(String nombre);
	
	List<MaterialDTO> findByStockActualBetween(Integer min, Integer max);

	List<MaterialDTO> findByPrecioBetween(Double double1, Double double2);


}
