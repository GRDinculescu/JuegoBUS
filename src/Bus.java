// Aqui se crea el bus

import java.util.Random;

public class Bus {
    final private int capacidadMaxima;
    private boolean puertasAbiertas;

    /**
     * Constructor del bus, con capacidad maxima, nPasajeros inician a 0
     * @param capacidadMaxima Requiere capacidad maxima de nPasajeros
     */
    public Bus(int capacidadMaxima){
        this.puertasAbiertas = false;
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getCapacidadMaxima() { return capacidadMaxima; } // Devuelve la capacidad maxima de pasajeros en el bus
    public boolean getPuertas(){ return puertasAbiertas; } // Devuelve si las puertas estan o no abiertas

    public void subePasajero(){
        if (!Info.paradaActual.pasajerosParada.isEmpty()){ // Si la parada actual no esta vacia
            for (Pasajero p : Info.paradaActual.pasajerosParada){ // Por cada pasajero en la parada
                if (Info.pasajerosBus.size() < capacidadMaxima){ // Si el bus no esta lleno
                    p.setParadaDeseada(Info.paradaActual); // Le establece una parada deseada
                    if (Info.dificultad > 1){ // Si la dificultad es mayor a 1 (facil)
                        if (Taquillera.getTaquilla().darTicket(p)){ // Y el ticket estaba bien
                            Puntos.getPuntos().ticketsVendidos(p); // Se suma un punto
                        } else {
                            Puntos.getPuntos().ticketMalo(); // Se resta un punto
                        }
                    }
                    Puntos.getPuntos().subePasajero();
                    Info.pasajerosBus.add(p); // Agrega el pasajero a la lista del bus
                    Info.paradaActual.pasajerosParada.remove(p); // Borra el pasajero de la lista de la parada
                } else {
                    System.out.println("El bus esta lleno.\nYa no se podran recolectar mas puntos hasta que bajen pasajeros.");
                    return;
                }
            }
        } else {
            System.out.println("No hay pasajeros en esta parada. Avanze a otra.");
        }
    }

    public void bajaPasajero(){
        Info.pasajerosBajados = 0; // Se reinicia el contador de los pasajeros bajados a 0
        if (!Info.pasajerosBus.isEmpty()){ // Si hay pasajeros en el bus
            for (Pasajero p : Info.pasajerosBus){
                if (Info.paradaActual == p.getParadaDeseada()){ // Si es su parada bajara
                    Info.pasajerosBus.remove(p); // Quitar de la lista del bus (Se borra de memoria porque no se referencia mas)
                    Info.pasajerosBajados += 1; // Se suman los pasajeros que han bajado
                    Info.pasajerosRestantes -= 1; // Se resta el pasajero del marcador global
                } else {
                    p.reducirIntentos(); // Si no es su parada, se le reducen los intentos en los que el jugador debe llegar a su parada
                }
            }
        }
    }

    public void activarPuertas(){
        puertasAbiertas = !puertasAbiertas; // Abrir o cerrar puertas
        System.out.println(puertasAbiertas ? "\n[+] Puertas abiertas\n" : "\n[-] Puertas cerradas\n");
    }
    public void moverse(Parada parada){
        if (Info.puertasAutomaticas && puertasAbiertas){ // Si esta activado el modo de puertas automaticas se cerraran solas
            activarPuertas();
        }

        if (puertasAbiertas){ // Si las puertas estan abiertas, el bus no se movera
            System.out.println("No puedes moverte. Las puertas estan abiertas.");
            return;
        }
        System.out.println("Moviendose...");
        Info.paradaActual = parada;
        System.out.printf("Te has movido a %s%n", Info.paradaActual.getNombre());

        if (Info.puertasAutomaticas && !puertasAbiertas){ // Si esta activado el modo de puertas automaticas se abrirarn solas
            activarPuertas();
        } else {
            System.out.println("Abre las puetas para que los pasajeros pasen");
        }
    }
}
