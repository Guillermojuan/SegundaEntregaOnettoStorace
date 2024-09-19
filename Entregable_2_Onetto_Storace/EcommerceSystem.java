import java.util.concurrent.*;

public class EcommerceSystem {
    // Pool de hilos compartido para todos los pedidos
    private final ExecutorService executorService;

    // Cola de prioridad que maneja tanto urgentes como normales
    private final PriorityBlockingQueue<Pedido> queue;

    // Constructor para inicializar el pool y la cola
    public EcommerceSystem(int numThreads) {
        this.executorService = Executors.newFixedThreadPool(numThreads);
        this.queue = new PriorityBlockingQueue<>();
    }

    // Método para agregar pedidos a la cola de prioridad
    public void agregarPedido(Pedido pedido) {
        queue.add(pedido); // Añade el pedido a la cola de prioridad
    }

    // Método para procesar pedidos con prioridad
    public void procesarPedidos() {
        while (!queue.isEmpty()) {
            Pedido pedido = queue.poll();  // Saca el pedido con mayor prioridad
            executorService.submit(() -> procesarPedido(pedido)); // Procesa el pedido en paralelo
        }
    }

    // Método para procesar un pedido individual (igual que antes)
    private void procesarPedido(Pedido pedido) {
        try {
            System.out.println("Procesando pago para el pedido " + pedido.getId());
            procesarPago(pedido);

            System.out.println("Empaquetando el pedido " + pedido.getId());
            empacarPedido(pedido);

            System.out.println("Enviando el pedido " + pedido.getId());
            enviarPedido(pedido);
        } catch (InterruptedException e) {
            System.err.println("Error procesando el pedido " + pedido.getId() + ": " + e.getMessage());
        }
    }

    // Simulación del procesamiento de pago
    private void procesarPago(Pedido pedido) throws InterruptedException {
        Thread.sleep(1000); // 1 segundo de espera
        System.out.println("Pago procesado para el pedido " + pedido.getId());
    }

    // Simulación del empaquetado de un pedido
    private void empacarPedido(Pedido pedido) throws InterruptedException {
        Thread.sleep(2000); // 2 segundos de espera
        System.out.println("Pedido " + pedido.getId() + " empaquetado.");
    }

    // Simulación del envío de un pedido
    private void enviarPedido(Pedido pedido) throws InterruptedException {
        Thread.sleep(1500); // 1.5 segundos de espera
        System.out.println("Pedido " + pedido.getId() + " enviado.");
    }

    // Método para cerrar el ExecutorService de manera ordenada
    public void shutdown() {
        executorService.shutdown();
    }
}
