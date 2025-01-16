public class Puntos {
    private int puntos;
    private final boolean isEasy;

    public Puntos(boolean isEasy){
        this.puntos = 0;
        this.isEasy = isEasy;
    }

    public int getPuntos() { return puntos; }

    public void subePasajero(){
        if(!isEasy){
            this.puntos += 1;
        } else {
            this.puntos += 2;
        }
    }

    public void ticketsVendidos(){
        this.puntos += 1;
    }

    public void pasajerosDejadosAtras(int pasajeros){
        if (puntos != 0){
            if (!isEasy){
                this.puntos -= pasajeros * 2;
            } else {
                this.puntos -= pasajeros;
            }
        }
    }

    public void ticketsNoVendidos(){
        this.puntos -= 2;
    }

    public void ticketMalo(){
        this.puntos -= 1;
    }
}
