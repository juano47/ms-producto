package ms.producto.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ms.producto.domain.Material;
import ms.producto.dto.MaterialDTO;
import ms.producto.service.MaterialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/producto")
@Api(value = "MaterialController", description = "Permite gestionar los materiales de la empresa")
public class ProductoController {

	@Autowired
	MaterialService materialService;

	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Busca un material por id")
	public ResponseEntity<Material> materialPorId(@PathVariable Integer id){

		Optional<Material> material = materialService.buscarPorId(id);
		return ResponseEntity.of(material);
	}

	@GetMapping(params = {"minPrecio", "maxPrecio"})
	@ApiOperation(value = "Busca un material por rango de precio")
	public ResponseEntity<?> materialPorPrecioEntre(@RequestParam Optional<Double> minPrecio, Optional<Double> maxPrecio){

		List<MaterialDTO> materiales = null;

		if(minPrecio.isPresent() && maxPrecio.isPresent()) {
			materiales = materialService.findByPrecioBetween(minPrecio.get(), maxPrecio.get());
		}
		else if(minPrecio.isPresent()) {
			materiales = materialService.findByPrecioBetween(minPrecio.get(), null);
		}
		else if(maxPrecio.isPresent()) {
			materiales = materialService.findByPrecioBetween(null, maxPrecio.get());
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Debe especificar un rango de precio");

		return ResponseEntity.ok(materiales);
	}

	@GetMapping(params = {"minStock", "maxStock"})
	@ApiOperation(value = "Busca un material por rango de stock")
	public ResponseEntity<?> materialPorStockEntre(@RequestParam Optional<Integer> minStock, Optional<Integer> maxStock){

		List<MaterialDTO> materiales = null;

		if(minStock.isPresent() && maxStock.isPresent()) {
			materiales = materialService.findByStockActualBetween(minStock.get(), maxStock.get());
		}
		else if(minStock.isPresent()) {
			materiales = materialService.findByStockActualBetween(minStock.get(), null);
		}
		else if(maxStock.isPresent()) {
			materiales = materialService.findByStockActualBetween(null, maxStock.get());
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Debe especificar un rango de stock");

		return ResponseEntity.ok(materiales);
	}

	@GetMapping(params = "nombre")
	@ApiOperation(value = "Busca un material por nombre")
	public ResponseEntity<?> materialPorNombre(@RequestParam Optional<String> nombre){

		if(nombre.isPresent()) {
			Optional<Material> material = materialService.findByNombre(nombre.get());
			return ResponseEntity.of(material);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Debe ingresar un nombre");
	}


	@PostMapping
	@ApiOperation(value = "Da de alta un nuevo material")
	public ResponseEntity<String> crear(@RequestBody Material material) {
		
		if(material.getUnidad() == null || material.getUnidad().getId() == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Debe especificar la unidad del material");
		}
		else if(material.getNombre() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Debe especificar el nombre del material.");
		}
		try {
			material = materialService.save(material);
		}		catch (DataIntegrityViolationException e1) {			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e1.getMostSpecificCause().toString());
		}
		catch(Exception e2) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e2.getMessage());

		} 

		return ResponseEntity.ok("Material Creado");
	}

	@PutMapping(path = "/{id}")
	@ApiOperation(value = "Actualiza un material")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Actualizado correctamente"),
			@ApiResponse(code = 401, message = "No autorizado"),
			@ApiResponse(code = 403, message = "Prohibido"),
			@ApiResponse(code = 404, message = "El ID no existe")
	})
	public ResponseEntity<?> actualizar(@RequestBody Material nuevo,  @PathVariable Integer id){

		try {
			materialService.update(id, nuevo);
		}
		catch (DataIntegrityViolationException e1) {			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e1.getMostSpecificCause().toString());
		}
		catch(Exception e2) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e2.getMessage());

		} 
		return ResponseEntity.ok(nuevo);
	}

	@DeleteMapping(path = "/{id}")
	@ApiOperation(value = "Elimina un material")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Eliminado correctamente"),
			@ApiResponse(code = 401, message = "No autorizado"),
			@ApiResponse(code = 403, message = "Prohibido"),
			@ApiResponse(code = 404, message = "El ID no existe")
	})
	public ResponseEntity<?> borrar(@PathVariable Integer id){

		try {
			materialService.delete(id);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		} 
		return ResponseEntity.ok("Material "+id+" borrado con éxito");
	}
	
	@GetMapping
	@ApiOperation(value = "Retorna lista de materiales")
	public ResponseEntity<List<Material>> todos(){
		List<Material> materiales = materialService.findAll();
		return ResponseEntity.ok(materiales);
	}
}