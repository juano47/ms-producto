package ms.producto.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Material {
	private Integer id;
	private String nombre;
	private String descripcion;
	private Double precio;
	private Integer stockActual;
	private Integer stockMinimo;
}
