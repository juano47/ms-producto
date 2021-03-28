package ms.producto.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetallePedido {
	private Integer id;
	private Material material;
	private Integer cantidad;
}
