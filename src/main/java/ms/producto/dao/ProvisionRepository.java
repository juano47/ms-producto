package ms.producto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ms.producto.domain.Provision;

@Repository
public interface ProvisionRepository extends JpaRepository<Provision, Integer>{

}
