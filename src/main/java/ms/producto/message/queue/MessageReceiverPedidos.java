package ms.producto.message.queue;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;
import ms.producto.domain.Material;
import ms.producto.service.MaterialService;

@Log4j2
@Component
public class MessageReceiverPedidos {
	
	@Autowired
	MaterialService materialService;
	
	@RabbitListener(queues = "${rabbitmq.queue.pedidos}")
	public void processMessage(String message) throws JsonMappingException, JsonProcessingException {
		log.info("Mensaje recibido desde cola peddidos ", message);
		ObjectMapper mapper = new ObjectMapper();
		List<MessageQueuePedidos> messageList = mapper.readValue(message, new TypeReference<List<MessageQueuePedidos>>(){});
		messageList.forEach(detalle -> {
			Optional<Material> materialOpt = materialService.buscarPorId(detalle.getProductoId());
			if(materialOpt.isPresent()) {
				Material material = materialOpt.get();
				material.setStockActual(material.getStockActual()-detalle.getCantidad());
				materialService.update(material.getId(), material);
			}
		});
	}
}
