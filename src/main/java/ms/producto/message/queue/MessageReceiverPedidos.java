package ms.producto.message.queue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
import ms.producto.domain.DetallePedido;
import ms.producto.domain.DetalleProvision;
import ms.producto.domain.Material;
import ms.producto.domain.MovimientoStock;
import ms.producto.domain.Provision;
import ms.producto.service.MaterialService;
import ms.producto.service.MovimientoStockService;
import ms.producto.service.ProvisionService;

@Log4j2
@Component
public class MessageReceiverPedidos {
	
	@Autowired
	MaterialService materialService;
	
	@Autowired
	ProvisionService provisionService;
	
	@Autowired
	MovimientoStockService movimientoStockService;
	
	@RabbitListener(queues = "${rabbitmq.queue.pedidos}")
	public void processMessage(String message) throws JsonMappingException, JsonProcessingException {
		log.info("Mensaje recibido desde cola peddidos ", message);
		ObjectMapper mapper = new ObjectMapper();
		List<MessageQueuePedidos> messageList = mapper.readValue(message, new TypeReference<List<MessageQueuePedidos>>(){});
		
		List<DetalleProvision> detalleProvisionList = new ArrayList<>();
		messageList.forEach(detalle -> {
			Optional<Material> materialOpt = materialService.buscarPorId(detalle.getProductoId());
			if(materialOpt.isPresent()) {
				Material material = materialOpt.get();
				//actualizar stock del material
				material.setStockActual(material.getStockActual()-detalle.getCantidad());
				materialService.update(material.getId(), material);
				
				
				//chequear stock minimo
				if(material.getStockActual() < material.getStockMinimo()) {
					
					//cargar nuevo detalle de provision en lista de detalles de provision
					DetalleProvision detalleProvision = new DetalleProvision();
					detalleProvision.setCantidad(detalle.getCantidad());
					detalleProvision.setMaterial(material);
					detalleProvisionList.add(detalleProvision);	
					
					//registrar movimiento de stock positivo
					MovimientoStock movimientoStock = new MovimientoStock();
					movimientoStock.setFecha(LocalDate.now());
					movimientoStock.setMaterial(material);
					movimientoStock.setDetalleProvision(detalleProvision);
					movimientoStock.setCantidadEntrada(detalle.getCantidad());
					
					movimientoStockService.save(movimientoStock);
				}
				
				//registrar movimiento de stock negativo
				MovimientoStock movimientoStock = new MovimientoStock();
				movimientoStock.setFecha(LocalDate.now());
				movimientoStock.setMaterial(material);
				movimientoStock.setDetallePedido(
						new DetallePedido(null, material, detalle.getCantidad()));
				movimientoStock.setCantidadSalida(detalle.getCantidad());
				
				movimientoStockService.save(movimientoStock);
			}
		});
		
		if(!detalleProvisionList.isEmpty()) {
			//crear nueva orden de provision por alerta de stock minimo
			Provision ordenProvision = new Provision();
			ordenProvision.setFechaProvision(LocalDate.now());
			ordenProvision.setDetalle(detalleProvisionList);
			
			provisionService.save(ordenProvision);
		}
	}
}
