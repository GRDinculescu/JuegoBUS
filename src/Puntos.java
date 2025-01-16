public class Puntos {
    private int puntos;
    private final boolean isEasy;

    public Puntos(boolean isEasy){
        this.puntos = 0;
        this.isEasy = isEasy;
    }

    public int getPuntos() { return puntos; }
    public boolean getEasy() { return isEasy; }

    public void subePasajero(){
        this.puntos += isEasy ? 2 : 1;
    }

    public void ticketsVendidos(Pasajero p){
        this.puntos += p.ticket == null ? 0 : 1;
    }

    public void pasajerosDejadosAtras(int pasajeros){
        this.puntos -= pasajeros * 2;
    }

    public void ticketsNoVendidos(){
        this.puntos -= 1;
    }

    public void ticketMalo(){
        this.puntos -= 1;
    }
}
