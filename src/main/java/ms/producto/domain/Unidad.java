package ms.producto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="PRD_UNIDAD", schema="MS_PRD",
uniqueConstraints = {@UniqueConstraint(columnNames={"descripcion"})})
public class Unidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_unidad")
	private Integer id;
	@Column(nullable = false)
	private String descripcion;
}
