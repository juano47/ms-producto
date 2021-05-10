package ms.producto.message.queue;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import ms.producto.domain.DetallePedido;

@Log4j2
@Component
public class MessageReceiverPedidos {
	
	@RabbitListener(queues = "${rabbitmq.queue.pedidos}")
	public void processMessage(DetallePedido detallesPedidos) {
	log.warn("LLEGO MENSAJE {}", detallesPedidos);
	}
}
