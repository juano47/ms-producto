package ms.producto.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="PRD_DETALLE_PROVISION", schema = "MS_PRD")
public class DetalleProvision {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_detalle_provision")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_material", nullable = false)
	private Material material;
	private Integer cantidad;
	@ManyToOne
	@JoinColumn(name = "id_provision", nullable = false)
	@JsonBackReference
	private Provision provision;
}
