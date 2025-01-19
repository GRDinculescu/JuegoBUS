import java.util.Scanner;

// Aqui se gestiona la logica de la maquina de tickets

public class Taquillera {
    private Taquillera(){}
    private static final Taquillera taquillera = new Taquillera();
    public static Taquillera getTaquilla(){ return taquillera; }

    public boolean darTicket(Pasajero pasajero){
        Scanner sn = new Scanner(System.in);
        int tipo, duracion, cantidad;
        String[] tipos = {"Normal", "Tercera edad", "Estudiante"};
        String[] duraciones = {"Un dia", "Una semana", "Diez viajes"};

        if (pasajero.ticket != null){
            while (true){
                while (true) {
                    System.out.printf("%nEl pasajero pide un ticket %s%n", tipos[pasajero.ticket.getTipoTicket()-1]);
                    tipo = Funciones.getInt(sn, "Elija:\n[1] Normal\n[2] Tercera edad\n[3] Estudiante", "Opcion invalida");
                    if (tipo >= 1 && tipo <= 3) { break; }
                    System.out.println("Ese tipo no esta");
                }

                while (true) {
                    System.out.printf("El ticket debe tener una duracion de %s%n", duraciones[pasajero.ticket.getDuracion()-1]);
                    duracion = Funciones.getInt(sn, "Elija:\n[1] Dia\n[2] Semana\n[3] Diez viajes", "Opcion invalida");
                    if (duracion >= 1 && duracion <= 3){ break; }
                    System.out.println("Esa duracion no esta.");
                }

                System.out.printf("El pasajero pide %d copias%n", pasajero.getCantidad());
                cantidad = Funciones.getInt(sn, "Inserte cantidad", "Cantidad invalida");

                System.out.printf("El pasajero pide %d tickets, tipo [%s] de duracion [%s]%n", pasajero.getCantidad(),
                        pasajero.ticket.getTipoTicket(), pasajero.ticket.getDuracion());
                System.out.printf("Tu le vas a entregar %d tickets, tipo [%s] de duracion [%s]%n", pasajero.getCantidad(),
                        pasajero.ticket.getTipoTicket(), pasajero.ticket.getDuracion());

                boolean canContinue = false;
                while (true) {
                    System.out.println("¿Es correcto? SI | NO");
                    String opcion = sn.nextLine().toLowerCase();
                    if (opcion.equals("s") || opcion.equals("si") || opcion.equals("y")){
                        canContinue = true;
                    } else if (opcion.equals("n") || opcion.equals("no")){
                        System.out.println("Vuelva a introducir los datos");
                    } else {
                        System.out.println("Opcion invalida");
                        continue;
                    }
                    break;
                }

                if (!canContinue){
                    continue;
                }

                return pasajero.ticket.getTipoTicket() == tipo &&
                        pasajero.ticket.getDuracion() == duracion &&
                        cantidad == pasajero.getCantidad();
            }
        }
        return true;
    }
}
