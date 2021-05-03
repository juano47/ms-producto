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
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="PRD_MATERIAL", schema = "MS_PRD", uniqueConstraints = {@UniqueConstraint(columnNames={"nombre"})})
public class Material {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_material")
	private Integer id;
	private String nombre;
	private String descripcion;
	private Double precio;
	private Integer stockActual;
	private Integer stockMinimo;
    @ManyToOne
    @JoinColumn(name = "id_unidad")
	private Unidad unidad;
}
