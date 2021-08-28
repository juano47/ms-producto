package ms.producto.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ms.producto.domain.Material;
import ms.producto.domain.Unidad;
import ms.producto.service.UnidadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/unidad")
@Api(value = "UnidadController", description = "Permite gestionar las unidades de los materiales")
public class UnidadController {

	@Autowired
	UnidadService unidadService;

	@HystrixCommand(fallbackMethod = "unidadVacio")
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Busca una unidad por id")
	public ResponseEntity<Unidad> unidadPorId(@PathVariable Integer id){
		Optional<Unidad> unidad = unidadService.buscarPorId(id);
		return ResponseEntity.of(unidad);
	}

	@HystrixCommand(fallbackMethod = "unidadVacio")
	@GetMapping(params = "descripcion")
	@ApiOperation(value = "Busca una unidad por su descripcion")
	public ResponseEntity<?> unidadPorDescripcion(@RequestParam Optional<String> descripcion){

		if(descripcion.isPresent()) {
			Optional<Unidad> unidad = unidadService.findByDescripcion(descripcion.get());
			return ResponseEntity.of(unidad);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Debe ingresar una descripcion");
	}

	@HystrixCommand(fallbackMethod = "errorServidor")
	@PostMapping
	@ApiOperation(value = "Da de alta una nueva unidad")
	public ResponseEntity<String> crear(@RequestBody Unidad unidad) {

		if(unidad.getDescripcion() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Debe especificar la descripcion de la unidad.");
		}
		try {
			unidad = unidadService.save(unidad);
		}		catch (DataIntegrityViolationException e1) {			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e1.getMostSpecificCause().toString());
		}
		catch(Exception e2) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e2.getMessage());

		} 

		return ResponseEntity.ok("Unidad Creada");
	}

	@HystrixCommand(fallbackMethod = "unidadVacio")
	@PutMapping(path = "/{id}")
	@ApiOperation(value = "Actualiza un unidad")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Actualizado correctamente"),
			@ApiResponse(code = 401, message = "No autorizado"),
			@ApiResponse(code = 403, message = "Prohibido"),
			@ApiResponse(code = 404, message = "El ID no existe")
	})
	public ResponseEntity<?> actualizar(@RequestBody Unidad nuevo,  @PathVariable Integer id){

		try {
			unidadService.update(id, nuevo);
		}
		catch (DataIntegrityViolationException e1) {			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e1.getMostSpecificCause().toString());
		}
		catch(Exception e2) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e2.getMessage());

		} 
		return ResponseEntity.ok(nuevo);
	}

	@HystrixCommand(fallbackMethod = "errorServidor")
	@DeleteMapping(path = "/{id}")
	@ApiOperation(value = "Elimina una unidad")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Eliminada correctamente"),
			@ApiResponse(code = 401, message = "No autorizado"),
			@ApiResponse(code = 403, message = "Prohibido"),
			@ApiResponse(code = 404, message = "El ID no existe")
	})
	public ResponseEntity<?> borrar(@PathVariable Integer id){

		try {
			unidadService.delete(id);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		} 
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping
	@ApiOperation(value = "Retorna lista de unidades")
	public ResponseEntity<List<Unidad>> todas(){
		List<Unidad> unidades = unidadService.findAll();
		return ResponseEntity.ok(unidades);
	}

	public ResponseEntity<Unidad> unidadVacio(){
		return ResponseEntity.ok(new Unidad());
	}

	public ResponseEntity<List<Unidad>> listVacia(){
		return ResponseEntity.ok(new ArrayList<>());
	}

	public ResponseEntity<String> errorServidor(){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Microservicio no disponible- Intentelo más tarde");
	}
}