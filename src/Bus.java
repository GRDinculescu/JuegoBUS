import java.util.List;

public class Bus {
    final private int capacidadMaxima;
    private int nPasajeros;
    private boolean puertasAbiertas;
    private final boolean puertasAutomaticas;
    private Parada paradaActual;
    private List<Pasajero> pasajeros;
    private List<Parada> paradas;

    /**
     * Constructor del bus, con capacidad maxima, nPasajeros inician a 0
     * @param capacidadMaxima Requiere capacidad maxima de nPasajeros
     */
    public Bus(int capacidadMaxima, boolean puertasAutomaticas, List<Parada> paradas){
        this.puertasAbiertas = false;
        this.puertasAutomaticas = puertasAutomaticas;
        this.capacidadMaxima = capacidadMaxima;
        this.nPasajeros = 0;
        this.paradas = paradas;
    }

    public int getCapacidadMaxima() { return capacidadMaxima; }
    public int getPasajeros() { return nPasajeros; }
    public boolean getPuertas(){
        return this.puertasAbiertas;
    }

    public void subePasajero(Pasajero pasajero){
        this.nPasajeros += 1;
        pasajeros.add(pasajero);
    }
    public void bajaPasajero(){ this.nPasajeros -= 1; }
    public void activarPuertas(){ puertasAbiertas = !puertasAbiertas; }
    public void moverse(int parada){
        if (puertasAutomaticas){
            puertasAbiertas = false;
        }
        if (puertasAbiertas){
            System.out.println("No puedes moverte. Las puertas estan abiertas.");
            return;
        }
        paradaActual = findParada(parada);
        System.out.printf("Te has movido a %s%n", paradaActual.getNombre());
    }

    public Parada findParada(int parada){
        for (Parada p : paradas){
            if (p.getId() == parada){
                return p;
            }
        }
        System.out.println("Parada no esta en la lista.");
        return null;
    }
}
