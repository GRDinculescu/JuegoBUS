import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mundo {
    Random r = new Random();
    public boolean pidenTicket;
    public List<Parada> paradas;
    public Bus bus;

    private int stopId;
    private String dificultad;
    private int totalPuntos;
    private int pasajerosTotales;

    public Mundo(int dificultad){
        totalPuntos = stopId = 0;
        paradas = new ArrayList<>();
        switch (dificultad){
            case 1 -> easy();
            case 2 -> normal();
            case 3 -> hard();
            default -> System.out.println("No existe esa dificultad");
        }
    }

    public int getPasajerosTotales() { return pasajerosTotales; }
    public int getTotalPuntos() { return totalPuntos; }

    @Override
    public String toString() {
        StringBuilder mensaje = new StringBuilder("\nDificultad: " + dificultad + "\n");
        mensaje.append("Capacidad maxima del bus: ").append(bus.getCapacidadMaxima()).append("\n");
        mensaje.append("Puntos totales obtenibles: ").append(totalPuntos).append("\n");
        mensaje.append("Pasajeros totales generados: ").append(pasajerosTotales).append("\n\n");
        for (Parada p : paradas){ // Obtiene la info de todas las paradas
            mensaje.append(p).append("\n");
        }
        return mensaje.toString();
    }

    private void easy(){
        dificultad = "Facil";
        for (int i = 0; i < r.nextInt(5, 11); i++) { // Genera de 5 a 10 paradas
            Parada p = new Parada(5, stopId);
            stopId += 1;
            paradas.add(p);
            totalPuntos += p.getPasajeros() * 2;
            pasajerosTotales += p.getPasajeros();
        }
        bus = new Bus(20, true);
        pidenTicket = false;
    }

    private void normal(){
        dificultad = "Normal";
        for (int i = 0; i < r.nextInt(10, 16); i++) { // Genera de 10 a 15 paradas
            Parada p = new Parada(10, stopId);
            stopId += 1;
            paradas.add(p);
            totalPuntos += p.getPasajeros() + p.getBilletes();
            pasajerosTotales += p.getPasajeros();
        }
        bus = new Bus(60, false);
        pidenTicket = true;
    }

    private void hard(){
        dificultad = "Dificil";
        for (int i = 0; i < r.nextInt(15, 26); i++) { // Genera de 15 a 25 paradas
            Parada p = new Parada(15, stopId); // Genera la parada
            stopId += 1; // Aumenta el id global
            paradas.add(p); // Agrega la parada a la lista
            totalPuntos += p.getPasajeros() + p.getBilletes();
            pasajerosTotales += p.getPasajeros();
        }
        bus = new Bus(150, false);
        pidenTicket = true;
    }
}
