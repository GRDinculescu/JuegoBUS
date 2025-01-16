import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Juego {
    private List<Pasajero> pasajeros;
    private final Mundo m;
    private int pasajerosSubidos;
    private int pasajerosBajados;

    public Juego(Mundo m){
        this.m = m;
    }

    public void inicio(Scanner sn){
        Parada parada;
        StringBuilder mensaje = new StringBuilder("\nElija parada a viajar:\n");
        for (Parada p : m.paradas){
            mensaje.append("[").append(p.getId()).append("] ").append(p.getNombre()).append("\n");
        }
        while (true){
            parada = findParada(Funciones.getInt(sn, mensaje.toString(), "Valor invalido"));
            if (parada == null){
                System.out.println("Parada no esta en la lista.");
                continue;
            }
            break;
        }
        m.bus.moverse(parada);
        System.out.printf("\nNombre: %s - Pasajeros %d%n", parada.getNombre(), parada.getPasajeros());

        // Si hay pasajeros en el bus bajan
        if (m.bus.getPasajeros() != 0){
            pasajerosBajan(m.bus);
            System.out.printf("[-] Han bajado [%d] pasajeros%n", pasajerosBajados);
        } else {
            System.out.println("No hay pasajeros para bajar");
        }

        // Si hay pasajeros en la estacion suben
        if (parada.getPasajeros() != 0){
            pasajerosSuben(parada, m.bus);
            System.out.printf("[+] Han subido [%d] pasajeros%n", pasajerosSubidos);
            System.out.println(m.bus.pasajeros);
        } else {
            System.out.println("No hay pasajeros para subir");
        }
        System.out.printf("Hay [%s] pasajeros en el bus%n", m.bus.getPasajeros());
    }

    public void pasajerosSuben(Parada parada, Bus bus){
        pasajerosSubidos = 0;
        while (true){
            if (bus.getPasajeros() < bus.getCapacidadMaxima()){
                if(parada.getPasajeros() != 0){
                    bus.subePasajero();
                    pasajerosSubidos += 1;
                } else {
                    return;
                }
            } else {
                System.out.println("No pueden subir mas pasajeros.\nNo se recolectaran mas puntos hasta que no bajen pasajeros\nViaja a otras estaciones para que bajen");
                return;
            }
        }
    }
    public void pasajerosBajan(Bus bus){
        pasajerosBajados = 0;
        Random r = new Random();
        int pasajerosBajan = r.nextInt(0, bus.getPasajeros());
        for (int i = 0; i <= pasajerosBajan; i++){
            bus.bajaPasajero();
            pasajerosBajados += 1;
        }
    }

    public Parada findParada(int parada){
        for (Parada p : m.paradas){
            if (p.getId() == parada){
                return p;
            }
        }
        return null;
    }
}
