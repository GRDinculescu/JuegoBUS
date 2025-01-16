import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Juego {
    private List<Pasajero> pasajeros;
    private final Mundo m;
    private int pasajerosSubidos;
    private int pasajerosBajados;
    private final Bus bus;
    private final Puntos puntos;

    public Juego(Mundo m, Puntos puntos){
        this.m = m;
        this.bus = m.bus;
        this.puntos = puntos;
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
        bus.moverse(parada);
        System.out.printf("\nPasajeros restantes: %d\nNombre: %s - Pasajeros %d%n",
                m.getPasajerosTotales(), parada.getNombre(), parada.getPasajeros());

        // Si hay pasajeros en el bus bajan
        if (bus.getPasajeros() != 0){
            pasajerosBajan(bus);
            System.out.printf("[-] Han bajado [%d] pasajeros%n", pasajerosBajados);
        } else {
            System.out.println("No hay pasajeros para bajar");
        }

        if (!bus.getModoPuertas()) {
            while (true) {
                System.out.println("Presione [C] para abrir las puertas\n[M] para irse (reduce 2 punto por cada persona en la estacion\ny 1 por cada tiquet no cobrado)");
                String opcion = sn.nextLine();
                if (opcion.equalsIgnoreCase("C")){
                    bus.activarPuertas();
                    break;
                } else if (opcion.equalsIgnoreCase("M")){
                    System.out.println("Elegiste irte. Seras penalizado por este comportamiento");
                    puntos.pasajerosDejadosAtras(parada.getPasajeros());
                    puntos.ticketsNoVendidos();
                    break;
                } else { System.out.println("Opcion no valida"); }
            }
        }

        if (bus.getPuertas()){
            // Si hay pasajeros en la estacion suben
            if (parada.getPasajeros() != 0){
                pasajerosSuben(parada, bus);
                System.out.printf("[+] Han subido [%d] pasajeros%n", pasajerosSubidos);
            } else {
                System.out.println("No hay pasajeros para subir");
            }
            System.out.printf("Hay [%s] pasajeros en el bus%n", bus.getPasajeros());
        }
    }

    public void pasajerosSuben(Parada parada, Bus bus){
        pasajerosSubidos = 0;
        puntos.ticketsVendidos();
        while (true){
            if (bus.getPasajeros() < bus.getCapacidadMaxima()){
                if(parada.getPasajeros() != 0){
                    bus.subePasajero();
                    puntos.subePasajero();
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
        m.reatarPasajeros(pasajerosBajados);
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
