package ms.producto.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="PED_DETALLE_PEDIDO", schema = "MS_PED")
public class DetallePedido {
	@Id //No indico la strategy, es solo lectura
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_material")
	private Material material;
	private Integer cantidad;
}
