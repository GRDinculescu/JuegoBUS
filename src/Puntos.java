// Aqui se gestionan los puntos

public class Puntos {
    private final boolean isEasy;

    public static Puntos getPuntos() { return puntos; }

    private static final Puntos puntos = new Puntos();

    private Puntos(){
        Info.puntos = 0;
        this.isEasy = Info.dificultad == 1;
    }

    public void subePasajero(){ Info.puntos += isEasy ? 2 : 1; }

    public void ticketsVendidos(Pasajero p){ Info.puntos += p.ticket == null ? 0 : 1; }

    public void pasajerosDejadosAtras(int pasajeros){ Info.puntos -= pasajeros * 3; }

    public void ticketMalo(){
        Info.puntos -= 1;
        System.out.println("El tiquet estaba mal");
    }
}
