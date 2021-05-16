package ms.producto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.producto.dao.ProvisionRepository;
import ms.producto.domain.Provision;
import ms.producto.service.ProvisionService;

@Service
public class ProvisionServiceImpl implements ProvisionService {

	@Autowired
	ProvisionRepository provisionRepository;
	
	@Override
	public Provision save(Provision provision) {
		return provisionRepository.save(provision);
	}

}
