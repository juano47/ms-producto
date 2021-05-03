package ms.producto.service;

import java.util.List;
import java.util.Optional;

import ms.producto.domain.Material;

public interface MaterialService {

	Optional<Material> buscarPorId(Integer id);

	List<Material> findAll();

	Material save(Material material);

	void update(Integer id, Material nuevo);

	void delete(Integer id);

	Optional<Material> findByNombre(Optional<String> nombre);

	Optional<Material> findByPrecio(Optional<Double> precio);

}
