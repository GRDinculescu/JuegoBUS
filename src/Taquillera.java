import java.util.Scanner;

public class Taquillera {
    public boolean darTicket(Pasajero pasajero){
        Scanner sn = new Scanner(System.in);
        String tipo;
        String duracion;
        int cantidad;

        if (pasajero.ticket != null){
            loop: while (true){
                while (true) {
                    System.out.printf("%nEl pasajero pide un ticket %s%n", pasajero.ticket.getTipoTicket());
                    switch (Funciones.getInt(sn, "Elija:\n[1] Normal\n[2] Tercera edad\n[3] Estudiante", "Opcion invalida")) {
                        case 1 -> tipo = "Normal";
                        case 2 -> tipo = "Anciano";
                        case 3 -> tipo = "Estudiante";
                        default -> {
                            System.out.println("Ese tipo no existe.");
                            continue;
                        }
                    }
                    break;
                }

                while (true) {
                    System.out.printf("El ticket debe tener una duracion de %s%n", pasajero.ticket.getDuracion());
                    switch (Funciones.getInt(sn, "Elija:\n[1] Dia\n[2] Semana\n[3] Diez viajes", "Opcion invalida")) {
                        case 1 -> duracion = "Dia";
                        case 2 -> duracion = "Semana";
                        case 3 -> duracion = "Diez viajes";
                        default -> {
                            System.out.println("Ese tipo no existe.");
                            continue;
                        }
                    }
                    break;
                }

                System.out.printf("El pasajero pide %d copias%n", pasajero.getCantidad());
                cantidad = Funciones.getInt(sn, "Inserte cantidad", "Cantidad invalida");

                System.out.printf("El pasajero pide %d tickets %s de %s%n", pasajero.getCantidad(),
                        pasajero.ticket.getTipoTicket(), pasajero.ticket.getDuracion());
                System.out.printf("Tu le vas a entregar %d tickets %s de %s%n", pasajero.getCantidad(),
                        pasajero.ticket.getTipoTicket(), pasajero.ticket.getDuracion());

                while (true) {
                    System.out.println("¿Es correcto? SI | NO");
                    String opcion = sn.nextLine().toLowerCase();
                    if (opcion.equals("s") || opcion.equals("si") || opcion.equals("y")){
                        break;
                    } else if (opcion.equals("n") || opcion.equals("no")){
                        System.out.println("Vuelva a introducir los datos");
                        continue loop;
                    } else {
                        System.out.println("Opcion invalida");
                    }
                }

                return pasajero.ticket.getTipoTicket().equalsIgnoreCase(tipo) &&
                        pasajero.ticket.getDuracion().equalsIgnoreCase(duracion) &&
                        cantidad == pasajero.getCantidad();
            }
        }
        return true;
    }
}
