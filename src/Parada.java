import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Parada {
    final private int id;
    final private String nombre;
    final private int nPasajeros;
    public List<Pasajero> pasajeros = new ArrayList<>();

    public Parada(int maxPasajeros, int id) {
        this.id = id;
        Random r = new Random();
        this.nPasajeros = r.nextInt(0, maxPasajeros+1);
        this.nombre = randomStop(r);
        for (int i = 0; i < nPasajeros; i++) {
            Pasajero p = new Pasajero();
            pasajeros.add(p);
        }
    }

    public String getNombre() { return nombre; }
    public int getPasajeros() { return nPasajeros; }
    public int getId() { return id; }

    @Override
    public String toString() {
        StringBuilder mensaje = new StringBuilder("ID: " + id + "\nNombre: " + nombre + "\nPasajeros: " + nPasajeros + "\n");
        for (Pasajero p : pasajeros){
            mensaje.append(p).append("\n");
        }
        return mensaje.toString();
    }

    public int getBilletes(){
        int totalBilletes = 0;
        for (Pasajero p : pasajeros){
            totalBilletes += p.getCantidad();
        }
        return totalBilletes;
    }

    // Genera el nombre de las paradas
    private String randomStop(Random r){
        try {
            // Cargar el archivo XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("src/nombres_paradas_bus.xml");

            // Obtener todos los elementos <stop>
            NodeList stops = document.getElementsByTagName("stop");

            // Guardar las paradas en una lista
            List<String> busStops = new ArrayList<>();
            for (int i = 0; i < stops.getLength(); i++) {
                Element stop = (Element) stops.item(i);
                busStops.add(stop.getTextContent()); // Agregar el contenido a la lista
            }

            // Elegir una parada aleatoria
            return busStops.get(r.nextInt(busStops.size()));
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Parada NULL";
    }
}
