package ms.producto.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;

import ms.producto.domain.Unidad;

@SpringBootTest
@Profile("testing")
@TestMethodOrder(OrderAnnotation.class)
class UnidadRepositoryTest {
	
	@Autowired
	UnidadRepository unidadRepo;
	
	@Test
	@Order(1)
	@Sql({"/data.sql"})
	void testInsertarUnidadesConExito() {
		List<Unidad> unidadesDisponibles = unidadRepo.findAll();
		assertTrue(unidadesDisponibles.size() == 5);
	}
	
	@Test
	@Order(2)
	void testInsertarUnidadConError() {
		Unidad unaUnidad = new Unidad();
		unaUnidad.setDescripcion("m2");
		Throwable t = assertThrows(Exception.class,
				() -> unidadRepo.save(unaUnidad));
		System.out.println(t.getCause().toString());
	}

}
