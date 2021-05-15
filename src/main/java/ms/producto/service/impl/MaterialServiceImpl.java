package ms.producto.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ms.producto.dao.MaterialRepository;
import ms.producto.dao.UnidadRepository;
import ms.producto.service.MaterialService;
import ms.producto.domain.Material;
import ms.producto.domain.Unidad;
import ms.producto.dto.MaterialDTO;

@Service
public class MaterialServiceImpl implements MaterialService{

	@Autowired
	MaterialRepository materialRepo;

	@Autowired
	UnidadRepository unidadRepo;

	@Override
	public Optional<Material> buscarPorId(Integer id) {
		Optional<Material> material = this.materialRepo.findById(id);
		if(material.isPresent())
			return material;

		return Optional.empty();
	}

	@Override
	public Optional<Material> findByNombre(String nombre) {
		Optional<Material> material = this.materialRepo.findByNombre(nombre);

		if(material.isPresent())
			return material;

		return Optional.empty();
	}

	@Override
	public List<MaterialDTO> findByPrecioBetween(Double min, Double max) {

		Page<Material> pagina = null;

		if(min != null & max != null)
			pagina = this.materialRepo.findByPrecioBetween(min, max, PageRequest.of(0,20));
		else if(min != null) {
			pagina = this.materialRepo.findByPrecioGreaterThanEqual(min, PageRequest.of(0,20));
		}
		else
			pagina = this.materialRepo.findByPrecioLessThanEqual(max, PageRequest.of(0,20));

		if(pagina.hasContent())
			return pagina.stream().map(m -> new MaterialDTO(m.getId(),m.getDescripcion(),m.getPrecio(), m.getStockActual()))
					.collect(Collectors.toList());
		return null;
	}

	@Override
	public List<MaterialDTO> findByStockActualBetween(Integer min, Integer max) {

		Page<Material> pagina = null;

		if(min != null & max != null)
			pagina = this.materialRepo.findByStockActualBetween(min, max, PageRequest.of(0,20));
		else if(min != null) {
			pagina = this.materialRepo.findByStockActualGreaterThanEqual(min, PageRequest.of(0,20));
		}
		else
			pagina = this.materialRepo.findByStockActualLessThanEqual(max, PageRequest.of(0,20));

		if(pagina.hasContent())
			return pagina.stream().map(m -> new MaterialDTO(m.getId(),m.getDescripcion(),m.getPrecio(), m.getStockActual()))
					.collect(Collectors.toList());
		return null;
	}


	@Override
	public Material save(Material nuevo) {

		Optional<Unidad> unidadMaterial = this.unidadRepo.findById(nuevo.getUnidad().getId());
		
		if(unidadMaterial.isPresent() && unidadMaterial.get().getDescripcion().equals(nuevo.getUnidad().getDescripcion())){

			if(nuevo.getId() != null) {
				Optional<Material> material = this.materialRepo.findById(nuevo.getId());

				if(material.isPresent())
					return this.materialRepo.save(nuevo);
				else
					throw new RuntimeException("Material no encontrado");
			}
			else
				return this.materialRepo.save(nuevo);
		}
		else
			throw new RuntimeException("La unidad asignada al material no existe");
	}

	@Override
	public void update(Integer id, Material nuevo) throws RuntimeException {
		Optional<Material> material = materialRepo.findById(id);

		if(material.isPresent()) {
			this.materialRepo.save(nuevo);
		}
		else 
			throw new RuntimeException("Material no encontrado");
	}

	@Override
	public void delete(Integer id) {

		Optional<Material> material;
		material = materialRepo.findById(id);

		if(material.isPresent()) {
			this.materialRepo.deleteById(id);
		}
		else
			throw new RuntimeException("Material no encontrado");

	}

	@Override
	public List<Material> findAll() {
		return this.materialRepo.findAll();
	}


}
