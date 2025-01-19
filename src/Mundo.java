import java.util.Random;

// Aqui se genera el mundo, con las paradas y los pasajeros

public class Mundo {
    Random r = new Random();

    private String dificultad;

    public static Mundo getMundo() { return mundo; }
    private final static Mundo mundo = new Mundo();
    private Mundo(){
        Info.pidenTicket = Info.dificultad > 1;
        Info.puertasAutomaticas = Info.dificultad == 1;
        Info.intentosMulty = (int) Math.round(Info.dificultad / 2.0);
        switch (Info.dificultad){
            case 1 -> easy();
            case 2 -> normal();
            case 3 -> hard();
        }
    }

    @Override
    public String toString() {
        StringBuilder mensaje = new StringBuilder("\nDificultad: " + dificultad + "\n");
        mensaje.append("Capacidad maxima del bus: ").append(Info.bus.getCapacidadMaxima()).append("\n");
        mensaje.append("Puntos totales obtenibles: ").append(Info.puntosObtenibles).append("\n");
        mensaje.append("Pasajeros totales generados: ").append(Info.pasajerosTotales).append("\n\n");
        for (Parada p : Info.paradas){ // Obtiene la info de todas las paradas
            mensaje.append(p).append("\n");
        }
        return mensaje.toString();
    }

    private void easy(){
        dificultad = "Facil";
        for (int i = 0; i < r.nextInt(5, 11); i++) { // Genera de 5 a 10 paradas
            Parada p = new Parada(5, Info.ultimoID);
            Info.ultimoID += 1;
            Info.paradas.add(p);
            Info.puntosObtenibles += p.getPasajeros() * 2;
            Info.pasajerosTotales += p.getPasajeros();
        }
        Info.pasajerosRestantes = Info.pasajerosTotales;
        Info.bus = new Bus(20);
    }

    private void normal(){
        dificultad = "Normal";
        for (int i = 0; i < r.nextInt(10, 16); i++) { // Genera de 10 a 15 paradas
            Parada p = new Parada(10, Info.ultimoID);
            Info.ultimoID += 1;
            Info.paradas.add(p);
            Info.puntosObtenibles += p.getPasajeros() + p.getBilletes();
            Info.pasajerosTotales += p.getPasajeros();
        }
        Info.pasajerosRestantes = Info.pasajerosTotales;
        Info.bus = new Bus(60);
    }

    private void hard(){
        dificultad = "Dificil";
        for (int i = 0; i < r.nextInt(15, 26); i++) { // Genera de 15 a 25 paradas
            Parada p = new Parada(15, Info.ultimoID); // Genera la parada
            Info.ultimoID += 1; // Aumenta el id global
            Info.paradas.add(p); // Agrega la parada a la lista
            Info.puntosObtenibles += p.getPasajeros() + p.getBilletes();
            Info.pasajerosTotales += p.getPasajeros();
        }
        Info.pasajerosRestantes = Info.pasajerosTotales;
        Info.bus = new Bus(150);
    }
}
