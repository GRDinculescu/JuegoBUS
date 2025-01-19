import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Aqui se crea la parada de forma aleatoria con pasajeros

public class Parada {
    final private int id; // Solo se usa para cuando se le pregunta al ususario a que parada ir
    final private String nombre;
    public List<Pasajero> pasajerosParada = new ArrayList<>();

    public Parada(int maxPasajeros, int id) {
        this.id = id;
        Random r = new Random();
        this.nombre = randomStop(r);
        for (int i = 0; i < r.nextInt(0, maxPasajeros+1); i++) {
            Pasajero p = new Pasajero(this);
            pasajerosParada.add(p);
        }
    }

    public String getNombre() { return nombre; }
    public int getPasajeros() { return pasajerosParada.size(); }
    public int getId() { return id; }
    public int getBilletes(){
        int totalBilletes = 0;
        for (Pasajero p : pasajerosParada){
            totalBilletes += p.getCantidad();
        }
        return totalBilletes;
    }

    @Override
    public String toString() {
        StringBuilder mensaje = new StringBuilder("ID: " + id + "\nNombre: " +
                nombre + "\npasajerosParada: " + pasajerosParada.size() + "\n");
        for (Pasajero p : pasajerosParada){
            mensaje.append(p).append("\n");
        }
        return mensaje.toString();
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
            System.out.println("No se pudo leer el archivo :(");
        }
        return "Parada NULL";
    }
}
