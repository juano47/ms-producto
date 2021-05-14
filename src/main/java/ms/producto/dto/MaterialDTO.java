package ms.producto.dto;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MaterialDTO{

	private Integer id;
	private String nombre;
	private String descripcion;
	private Double precio;
	private Integer stockActual;
	
	public MaterialDTO(Integer id, String descripcion, Double precio) {
		this.id = id;
		this.descripcion = descripcion;
		this.precio = precio;
		
	}

	public MaterialDTO(Integer id, String descripcion, Double precio, Integer stockActual) {
		this.id = id;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stockActual = stockActual;
	}

}
