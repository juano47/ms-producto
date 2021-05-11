package ms.producto.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialDTO implements Serializable{

	public MaterialDTO(Integer id, String descripcion, Double precio) {
		// TODO Auto-generated constructor stub
	}

	public MaterialDTO(Integer id, String descripcion, Double precio, Integer stockActual) {
		// TODO Auto-generated constructor stub
	}

}
