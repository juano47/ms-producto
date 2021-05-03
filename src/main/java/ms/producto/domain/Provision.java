package ms.producto.domain;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="PRD_PROVISION", schema = "MS_PRD")
public class Provision {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_provision")
	private Integer id;
	@Column(nullable = false)
	private LocalDate fechaProvision;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "provision", orphanRemoval = true)
	@JsonManagedReference
	private List<DetalleProvision> detalle;
}
