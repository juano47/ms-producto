package ms.producto.service;

import java.util.List;
import java.util.Optional;

import ms.producto.domain.Unidad;

public interface UnidadService {

	Optional<Unidad> buscarPorId(Integer id);

	List<Unidad> findAll();

	Unidad save(Unidad material);

	void update(Integer id, Unidad nuevo);

	void delete(Integer id);

	Optional<Unidad> findByDescripcion(String descripcion);
	

}
