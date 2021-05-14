package ms.producto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.producto.dao.UnidadRepository;
import ms.producto.domain.Unidad;
import ms.producto.service.UnidadService;

@Service
public class UnidadServiceImpl implements UnidadService {

	@Autowired
	UnidadRepository unidadRepo;
	
	@Override
	public Optional<Unidad> buscarPorId(Integer id) {
		Optional<Unidad> unidad = this.unidadRepo.findById(id);
		if(unidad.isPresent())
			return unidad;

		return Optional.empty();
	}

	@Override
	public List<Unidad> findAll() {
		return this.unidadRepo.findAll();
	}

	@Override
	public Unidad save(Unidad nuevo) {

		if(nuevo.getId() != null) {
			Optional<Unidad> unidad = this.unidadRepo.findById(nuevo.getId());

			if(unidad.isPresent()) 
				return this.unidadRepo.save(nuevo);
			else
				throw new RuntimeException("Unidad no encontrada");
		}
		else
			return this.unidadRepo.save(nuevo);
	}

	@Override
	public void update(Integer id, Unidad nuevo) {
		Optional<Unidad> unidad = unidadRepo.findById(id);

		if(unidad.isPresent()) {
			this.unidadRepo.save(nuevo);
		}
		else 
			throw new RuntimeException("Unidad no encontrada");
	}

	@Override
	public void delete(Integer id) {

		Optional<Unidad> unidad;
		unidad = unidadRepo.findById(id);

		if(unidad.isPresent()) {
			this.unidadRepo.deleteById(id);
		}
		else
			throw new RuntimeException("Unidad no encontrada");

	}


	@Override
	public Optional<Unidad> findByDescripcion(String descripcion) {
		Optional<Unidad> unidad = this.unidadRepo.findByDescripcion(descripcion);

		if(unidad.isPresent())
			return unidad;

		return Optional.empty();
	}

}
