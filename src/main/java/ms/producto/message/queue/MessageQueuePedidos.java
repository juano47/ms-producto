package ms.producto.message.queue;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageQueuePedidos implements Serializable {
	private int cantidad;
	private int productoId;
}