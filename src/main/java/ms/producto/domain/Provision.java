package ms.producto.domain;

import java.time.Instant;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Provision {
	private Integer id;
	private Instant fechaProvision;
	private List<DetalleProvision> detalle;
}
