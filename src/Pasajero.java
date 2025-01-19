import java.util.Random;

// Aqui se genera un pasajero aleatorio

public class Pasajero {
    private final int cantidad;
    public final Ticket ticket;
    private Parada paradaDeseada;
    private int intentos;

    public Pasajero(Parada parada){
        Random r = new Random();
        intentos = r.nextInt(3, 6) * Info.intentosMulty;
        this.cantidad = r.nextInt(0, 5);
        ticket = cantidad != 0 ? new Ticket() : null; // Si la cantidad de tickets es 0, estara pagando con tarjeta, no se sumaran puntos
    }

    public void reducirIntentos(){ // Si no es la estacion que deseaba reducira intentos hasta llegar a 0
        if (intentos > 0) {
            intentos -= 1;
        } else { // Si no quedan mas intenteos reducira puntos
            Info.puntos -= 1;
        }
    }

    public int getIntentos(){ return intentos; }
    public String getNombreParada(){ return paradaDeseada.getNombre(); }
    public int getCantidad() { return cantidad; }
    public void setParadaDeseada(Parada parada){ this.paradaDeseada = parada; }
    public Parada getParadaDeseada() { return paradaDeseada; }

    @Override
    public String toString() {
        if (cantidad != 0){
            return ticket+ " | Cantidad: "+cantidad;
        } else {
            return "Paga con tarjeta";
        }
    }


}
