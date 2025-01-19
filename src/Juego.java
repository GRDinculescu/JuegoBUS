import java.util.Scanner;

// Aqui se gestiona la logica del juego

public class Juego {

    public static void inicio(Scanner sn){
        Parada parada;
        StringBuilder mensaje = new StringBuilder("\nElija parada a viajar:\n");
        for (Parada p : Info.paradas){
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
        Info.bus.moverse(parada);
        System.out.printf("Puntos actuales: %d\nPasajeros restantes: %d\nNombre: %s - Pasajeros %d%n",
                Info.puntos, Info.pasajerosRestantes, parada.getNombre(), parada.getPasajeros());

        if (!Info.puertasAutomaticas) {
            while (true) {
                System.out.println("""
                        Presione [C] para abrir las puertas
                        [M] para irse (reduce 2 punto por cada persona en la estacion y 1 por cada tiquet no cobrado)""");
                String opcion = sn.nextLine();
                if (opcion.equalsIgnoreCase("C")){
                    Info.bus.activarPuertas();
                } else if (opcion.equalsIgnoreCase("M")){
                    System.out.println("Elegiste irte. Seras penalizado por este comportamiento");
                    Puntos.getPuntos().pasajerosDejadosAtras(parada.getPasajeros());
                } else {
                    System.out.println("Opcion no valida");
                    continue;
                }
                break;
            }
        }

        if (Info.bus.getPuertas()){
            // Si hay pasajeros en el bus bajan
            if (!Info.pasajerosBus.isEmpty()){
                Info.bus.bajaPasajero();
                System.out.printf("[-] Han bajado [%d] pasajeros%n", Info.pasajerosBajados);
            } else {
                System.out.println("No hay pasajeros para bajar");
            }

            // Si hay pasajeros en la estacion suben
            if (parada.getPasajeros() != 0){
                Info.bus.subePasajero();
                System.out.printf("[+] Han subido [%d] pasajeros%n", Info.pasajerosSubidos);
            } else {
                System.out.println("No hay pasajeros para subir");
            }
            System.out.printf("Hay [%s] pasajeros en el bus%n", Info.pasajerosBus.size());
            System.out.println("\nLos pasajeros quieren llegar a:");
            for (Pasajero p : Info.pasajerosBus){
                System.out.printf("%s en %d viajes o menos%n", p.getNombreParada(), p.getIntentos());
            }
        }

        if (Info.puertasAutomaticas){ Info.bus.activarPuertas(); }

        while (Info.bus.getPuertas()){
            System.out.println("Presiona [F] para cerrar las puetas");
            String input = sn.nextLine();
            if (input.equalsIgnoreCase("f")){
                Info.bus.activarPuertas();
            } else if (input.equalsIgnoreCase("m")) {
                System.out.println("No puedes irte con las puertas abiertas");
            } else {
                System.out.println("Boton no reconocido");
            }
        }
    }

    public static Parada findParada(int parada){
        for (Parada p : Info.paradas){
            if (p.getId() == parada){
                return p;
            }
        }
        return null;
    }
}
