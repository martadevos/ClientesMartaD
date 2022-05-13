import java.util.Scanner;

public class Menu {
    public static void menu() {
        Scanner s = new Scanner(System.in);
        int respuesta;
        boolean salir = false;
        while (!salir) {
            System.out.print("""
                    DATOS DE LOS CLIENTES
                    Escriba el número de la operación que desee realizar
                    1.- Clientes con saldo 0
                    2.- Clientes con crédito
                    3.- Clientes con débito
                    4.- Enviar cartas de aviso a clientes Robinson
                    5.- Enviar cartas de agradecimiento a clientes VIP
                    Pulse otro número para salir
                    """);
            respuesta = s.nextInt();
            switch (respuesta) {
                case 1 -> Ficheros.saldoA0();
                case 2 -> Ficheros.clientesCredito();
                case 3 -> Ficheros.clientesDebito();
                case 4 -> Ficheros.clientesRobinson();
                case 5 -> Ficheros.clientesVip();
                default -> salir = true;
            }
        }
    }
}
