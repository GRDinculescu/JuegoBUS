//JUEGO AUTOBUS
//
//Misión -> Llevar el bus a todas las paradas en el menor tiempo posible y acumular el mayor numero de puntos
//Paradas -> De 10 a 20
//
//Que puede hacer el jugador:
//- Dejar que suban los pasajeros (+1 por pasajero) [TERMINADO]
//- Dar los billetes que pida el pasajero (+1 por billete) [TERMINADO]
//- Moverse a la siguiente estación. El jugador deberá elegir. [TERMINADO]
//
//Dificultades:
//- Fácil: Menor requisito de puntos, los pasajeros no piden billetes, las paradas ya vistas desaparecen de la lista.
//- Medio: Requisito normal de puntos, los pasajeros piden billetes, las paradas no desaparecen
//- Difícil: Mayor requerimiento de puntos, los pasajeros piden billetes, las paradas no desaparecen, no subir un pasajero sin motivo o no dar el billete bien resta puntos (-1)
//
//Billetes:
//- Tipo: Normal, Tercera edad, Estudiante
//- Cantidad: 1 - N
//- Duración: Un día, una semana, 10 viajes

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        Random r = new Random();
        String menuDificultad = """
                BIENVENIDO
                Elija dificultad
                [1] Facil
                [2] Normal
                [3] Dificil
                [9] Salir""";

        while (true){
            int dificultad = Funciones.getInt(sn, menuDificultad, "Valor invalido");
            if (dificultad == 9){
                System.out.println("Saliendo...");
                break;
            }
            Mundo m = new Mundo(dificultad);
            System.out.println(m);

            String mensaje = "Elija parada a viajar:\n";
            for (Parada p : m.paradas){
                mensaje += "["+p.getId()+"] "+p.getNombre()+"\n";
            }
            int parada = Funciones.getInt(sn, mensaje, "Valor invalido");
            m.bus.moverse(parada);
        }
    }
}