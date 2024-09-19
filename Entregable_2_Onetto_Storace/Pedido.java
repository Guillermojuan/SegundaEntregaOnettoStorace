public class Pedido implements Comparable<Pedido> {
    private final int id;
    private final boolean urgente;

    // Constructor
    public Pedido(int id, boolean urgente) {
        this.id = id;
        this.urgente = urgente;
    }

    // Getters para acceder a los atributos del pedido
    public int getId() {
        return id;
    }

    public boolean isUrgente() {
        return urgente;
    }

    // Este método es clave para que la PriorityBlockingQueue funcione correctamente
    @Override
    public int compareTo(Pedido otroPedido) {
        // Los pedidos urgentes tienen mayor prioridad (devuelve -1 para urgentes)
        if (this.urgente && !otroPedido.urgente) {
            return -1;
        } else if (!this.urgente && otroPedido.urgente) {
            return 1;
        } else {
            return 0; // Si ambos son urgentes o ambos son normales, tienen la misma prioridad
        }
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", urgente=" + urgente +
                '}';
    }
}
