import java.util.Scanner;

public class Funciones {
    public static int getInt(Scanner sn, String mensaje, String error){
        while (true){
            try {
                System.out.println(mensaje);
                return Integer.parseInt(sn.nextLine());
            } catch (Exception e){
                System.out.println(error);
            }
        }
    }
}
