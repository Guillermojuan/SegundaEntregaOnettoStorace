public class EcommerceSystemTest {
    public static void main(String[] args) {
        // Crear el sistema de comercio electrónico con un pool de 10 hilos
        EcommerceSystem ecommerceSystem = new EcommerceSystem(10);

        // Agregar 4 pedidos urgentes
        ecommerceSystem.agregarPedido(new Pedido(1, true)); // Pedido urgente
        ecommerceSystem.agregarPedido(new Pedido(2, true)); // Pedido urgente
        ecommerceSystem.agregarPedido(new Pedido(3, true)); // Pedido urgente
        ecommerceSystem.agregarPedido(new Pedido(4, true)); // Pedido urgente

        // Agregar 6 pedidos normales
        for (int i = 5; i <= 10; i++) {
            ecommerceSystem.agregarPedido(new Pedido(i, false)); // Pedido normal
        }

        // Procesar los pedidos
        ecommerceSystem.procesarPedidos();

        // Cerrar el sistema de manera ordenada
        ecommerceSystem.shutdown();
    }
}

