import java.util.Random;

public class Pasajero {
    private final int cantidad;
    private final Ticket ticket;

    public Pasajero(){
        Random r = new Random();
        this.cantidad = r.nextInt(0, 5);
        if (cantidad != 0){
            ticket = new Ticket();
        } else {
            ticket = null;
        }
    }

    public int getCantidad() { return cantidad; }

    @Override
    public String toString() {
        if (cantidad != 0){
            return ticket+ " | Cantidad: "+cantidad;
        } else {
            return "Paga con tarjeta";
        }
    }


}
