package ms.producto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.producto.dao.MovimientoStockRepository;
import ms.producto.domain.MovimientoStock;
import ms.producto.service.MovimientoStockService;

@Service
public class MovimientoStockServiceImpl implements MovimientoStockService{
	
	@Autowired
	MovimientoStockRepository movimientoStockRepository;

	@Override
	public MovimientoStock save(MovimientoStock movimientoStock) {
		return movimientoStockRepository.save(movimientoStock);
		
	}

}
