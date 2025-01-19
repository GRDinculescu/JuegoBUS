import java.util.Scanner;

// Aqui se inicia el juego

public class Main {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        Puntos puntos;
        String menuDificultad = """
                
                BIENVENIDO
                Elija dificultad
                [1] Facil
                [2] Normal
                [3] Dificil
                [9] Salir""";

        // INICIA EL PROGRAMA
        while (true){
            int dificultad = Info.dificultad = // Establece la dificualtad a la elejida por el jugador
                    Funciones.getInt(sn, menuDificultad, "Valor invalido");

            if (dificultad == 9){ // Si elije 9 salir del juego
                System.out.println("Saliendo...");
                break;
            }

            if (dificultad < 1 || dificultad > 3){ // Si a elejido una dificultad que no esta volver a preguntar
                System.out.println("Dificultan no disponible");
                continue;
            }
//             Mundo m =  Mundo.getMundo();
//             System.out.println(m);

            do {
                Juego.inicio(sn);
            } while (Info.pasajerosRestantes > 0); // Mientras no haya llevado a todos los pasajeros repetir
            System.out.printf("%nHas completado la ruta con un total de [%d] puntos de [%d]%n%n",
                    Info.puntos, Info.puntosObtenibles);
            Info.resetAll();
        }
    }
}