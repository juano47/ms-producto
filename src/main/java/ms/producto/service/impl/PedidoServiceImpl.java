package ms.producto.service.impl;


import org.springframework.stereotype.Service;

import ms.producto.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Override
	public boolean hayPedidos(String cuit, Integer id) {
		return true;
	}

}
