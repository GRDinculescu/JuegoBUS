import java.util.Random;

// Aqui se crea un ticket aleatorio

public class Ticket {
    private final int  tipoTicket;
    private final int duracion;

    public Ticket(){
        Random r = new Random();
        this.tipoTicket = r.nextInt(1, 4); // 1 - Normal | 2 - Tercera edad | 3 - Estudiante
        this.duracion = r.nextInt(1 ,4); // 1 - Un dia | 2 - Semana | 3 - Diez viajes
    }

    @Override
    public String toString() { return "Tipo: "+tipoTicket+" | Duracion: "+duracion; }

    public int getDuracion() { return duracion; }
    public int getTipoTicket() { return tipoTicket; }
}
