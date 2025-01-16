import java.util.ArrayList;
import java.util.List;

public class Bus {
    final private int capacidadMaxima;
    private boolean puertasAbiertas;
    private final boolean puertasAutomaticas;
    private Parada paradaActual;
    public List<Pasajero> pasajeros = new ArrayList<>();

    /**
     * Constructor del bus, con capacidad maxima, nPasajeros inician a 0
     * @param capacidadMaxima Requiere capacidad maxima de nPasajeros
     */
    public Bus(int capacidadMaxima, boolean puertasAutomaticas){
        this.puertasAbiertas = false;
        this.puertasAutomaticas = puertasAutomaticas;
        this.capacidadMaxima = capacidadMaxima;
    }

    public boolean getModoPuertas() { return puertasAutomaticas; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public int getPasajeros() { return pasajeros.size(); }
    public boolean getPuertas(){
        return this.puertasAbiertas;
    }

    public void subePasajero(){
        pasajeros.add(paradaActual.pasajeros.getFirst());
        paradaActual.pasajeros.removeFirst();
    }
    public void bajaPasajero(){
        if (!pasajeros.isEmpty()){
            pasajeros.removeFirst();
        }
    }
    public void activarPuertas(){
        puertasAbiertas = !puertasAbiertas;
        System.out.println(puertasAbiertas ? "[+] Puertas abiertas" : "\n[-] Puertas cerradas");
    }
    public void moverse(Parada parada){
        if (puertasAutomaticas){
            puertasAbiertas = false;
            System.out.println("\n[-] Puertas cerradas");
        }

        if (puertasAbiertas){
            System.out.println("No puedes moverte. Las puertas estan abiertas.");
            return;
        }
        System.out.println("Moviendose...");
        paradaActual = parada;
        System.out.printf("Te has movido a %s%n", paradaActual.getNombre());

        if (puertasAutomaticas){
            puertasAbiertas = true;
            System.out.println("[+] Puertas abiertas");
        } else {
            System.out.println("Abre las puetas para que los pasajeros pasen");
        }
    }
}
