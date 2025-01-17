import java.util.ArrayList;
import java.util.List;

// Aqui se guarda toda la informacion global del juego

public class Info {
    public static int dificultad;
    public static int pasajerosTotales;
    public static int pasajerosRestantes;
    public static int pasajerosSubidos;
    public static int pasajerosBajados;
    public static int paradasTotales;
    public static int ultimoID;
    public static int puntosObtenibles;
    public static int puntos;
    public static int intentosMulty;

    public static Parada paradaActual;
    public static Bus bus;
    public static Pasajero pasajero;

    public static List<Parada> paradas;
    public static List<Pasajero> pasajerosMundo;
    public static List<Pasajero> pasajerosBus;

    public static boolean puertasAutomaticas;
    public static boolean pidenTicket;

    private Info(){ resetAll(); } // Contructor vacio. Solo se establecen los valores predeterminados

    public static void resetAll(){
        dificultad = 1;
        puertasAutomaticas = true;
        pidenTicket = false;
        pasajerosTotales =
            pasajerosRestantes =
            paradasTotales =
            puntosObtenibles =
            puntos =
            intentosMulty =
            ultimoID = 0;
        bus = null;
        paradaActual = null;
        pasajero = null;
        paradas = new ArrayList<>();
        pasajerosMundo = new ArrayList<>();
        pasajerosBus = new ArrayList<>();
    }
}
