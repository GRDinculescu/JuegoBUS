import java.util.Random;

public class Ticket {
    private final String  tipoTicket;
    private final String duracion;

    public Ticket(){
        Random r = new Random();
        this.tipoTicket = setTipoTicket(r);
        this.duracion = setDuracion(r);
    }

    @Override
    public String toString() {
        return "Tipo: "+tipoTicket+" | Duracion: "+duracion;
    }

    private String setTipoTicket(Random r){
        return switch (r.nextInt(1, 4)) {
            case 1 -> "Normal";
            case 2 -> "Anciano";
            case 3 -> "Estudiante";
            default -> "";
        };
    }

    private String setDuracion(Random r){
        return switch (r.nextInt(1, 4)) {
            case 1 -> "Dia";
            case 2 -> "Semana";
            case 3 -> "Diez viajes";
            default -> "";
        };
    }

    public String getDuracion() { return duracion; }
    public String getTipoTicket() { return tipoTicket; }
}
