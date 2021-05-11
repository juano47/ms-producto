package ms.producto.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ms.producto.dao.MaterialRepository;
import ms.producto.service.MaterialService;
import ms.producto.service.PedidoService;
import ms.producto.domain.Material;
import ms.producto.dto.MaterialDTO;

@Service
public class MaterialServiceImpl implements MaterialService{

	@Autowired
	MaterialRepository materialRepo;

	@Autowired
	PedidoService pedidoService;

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
	public List<MaterialDTO> findByPrecioLessThanEqual(Double precio) {

		Page<Material> pagina = (Page<Material>) this.materialRepo.findByPrecioLessThanEqual(precio, PageRequest.of(1,20));
		
		if(pagina.hasContent())
		return pagina.stream().map(m -> new MaterialDTO(m.getId(),m.getDescripcion(),m.getPrecio()))
				.collect(Collectors.toList());
		return null;

	}

	@Override
	public List<MaterialDTO> findByStockActualBetween(Integer min, Integer max) {
		
		Page<Material> pagina = (Page<Material>) this.materialRepo.findByStockActualBetween(min, max, PageRequest.of(1,20));
		if(pagina.hasContent())
		return pagina.stream().map(m -> new MaterialDTO(m.getId(),m.getDescripcion(),m.getPrecio(), m.getStockActual()))
				.collect(Collectors.toList());
		return null;
	}


	@Override
	public Material save(Material nuevo) {

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
		// TODO Auto-generated method stub
		return null;
	}


}
