package ms.producto.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="PRD_MOVIMIENTO_STOCK", schema = "MS_PRD")
public class MovimientoStock {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_movimiento_stock")
	private Integer id;
	@OneToOne(cascade = CascadeType.ALL) //Solo lectura
	@JoinColumn(name = "id_detalle_pedido")
	private DetallePedido detallePedido;
	@OneToOne
	@JoinColumn(name = "id_detalle_provision")
	private DetalleProvision detalleProvision;
	@ManyToOne
	@JoinColumn(name = "id_material", nullable = false)
	private Material material;
	private Integer cantidadEntrada;
	private Integer cantidadSalida;
	private LocalDate fecha;
}
