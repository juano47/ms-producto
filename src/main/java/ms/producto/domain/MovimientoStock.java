package ms.producto.domain;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoStock {
	private Integer id;
	private DetallePedido detallePedido;
	private DetalleProvision detalleProvision;
	private Material material;
	private Integer cantidadEntrada;
	private Integer cantidadSalida;
	private Instant fecha;
}
