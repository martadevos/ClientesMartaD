import java.io.*;
import java.util.Calendar;

public abstract class Ficheros {

    private static final Calendar fecha = Calendar.getInstance();
    public static final String SALDO0 = "src/Documentos/Saldo0_%S_%S_%S.dat".formatted(fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR));

    public static final File CLIENTESSALDO0 = new File(SALDO0);
    public static final String DEBITO = "src/Documentos/Debito_%S_%S_%S.dat".formatted(fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR));

    public static final File CLIENTESDEBITO = new File(DEBITO);
    public static final String CREDITO = "src/Documentos/Credito_%S_%S_%S.dat".formatted(fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR));

    public static final File CLIENTESCREDITO = new File(CREDITO);
    public static final String ROBINSON = "src/Documentos/Robinson_%S_%S_%S.dat".formatted(fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR));

    public static final File CLIENTESROBINSON = new File(ROBINSON);
    public static final String VIP = "src/Documentos/VIP_%S_%S_%S.dat".formatted(fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR));

    public static final File CLIENTESVIP = new File(VIP);

    private static ObjectInputStream leer = null;
    private static ObjectOutputStream escribir = null;

    public static void saldoA0(){
        FicherosClientes.leerClientes();
        Clientes cliente;
        boolean fin = false;
        if (!CLIENTESSALDO0.exists()) {
            try {
                abrirFlujosDeDatos(FicherosClientes.CLIENTESNORMAL, SALDO0);
                do {
                    try {
                        cliente = (Clientes) leer.readObject();
                        if (cliente.getSaldo() == 0) {
                            escribir.writeObject(cliente);
                        }
                    }catch (Exception e){
                        fin=true;
                    }
                } while (!fin);
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");
            } finally {
                cerrarFlujosDeDatos();
            }
        }
    }

    public static void clientesCredito(){
        FicherosClientes.leerClientes();
        Clientes cliente;
        boolean fin = false;
        if (!CLIENTESCREDITO.exists()) {
            try {
                abrirFlujosDeDatos(FicherosClientes.CLIENTESNORMAL, CREDITO);
                do {
                    try {
                        cliente = (Clientes) leer.readObject();
                        if (cliente.getSaldo() > 0) {
                            escribir.writeObject(cliente);
                        }
                    }catch (Exception e){
                        fin=true;
                    }
                } while (!fin);
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");
            } finally {
                cerrarFlujosDeDatos();
            }
        }
    }

    public static void clientesDebito(){
        FicherosClientes.leerClientes();
        Clientes cliente;
        boolean fin = false;
        if (!CLIENTESDEBITO.exists()) {
            try {
                abrirFlujosDeDatos(FicherosClientes.CLIENTESNORMAL, DEBITO);
                do {
                    try {
                        cliente = (Clientes) leer.readObject();
                        if (cliente.getSaldo() < 0) {
                            escribir.writeObject(cliente);
                        }
                    }catch (Exception e){
                        fin=true;
                    }
                } while (!fin);
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");
            } finally {
                cerrarFlujosDeDatos();
            }
        }
    }

    public static void clientesRobinson(){
        FicherosClientes.leerClientesAmpliados();
        int contador=0;
        ClienteAmpliado cliente;
        boolean fin = false;
        if (!CLIENTESROBINSON.exists()) {
            try {
                abrirFlujosDeDatos(FicherosClientes.CLIENTESAMPLIADOS, ROBINSON);
                do {
                    try {
                        cliente = (ClienteAmpliado) leer.readObject();
                        if (cliente.getSaldo() > 0 && cliente.getGastosMedios()>3000) {
                            escribir.writeObject(cliente);
                            contador++;
                        }
                    }catch (Exception e){
                        fin=true;
                    }
                } while (!fin);
                System.out.println(contador);
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");
            } finally {
                cerrarFlujosDeDatos();
            }
        }
    }
    public static void clientesVip(){
        FicherosClientes.leerClientesAmpliados();
        int contador=0;
        ClienteAmpliado cliente;
        boolean fin = false;
        if (!CLIENTESVIP.exists()) {
            try {
                abrirFlujosDeDatos(FicherosClientes.CLIENTESAMPLIADOS, VIP);
                do {
                    try {
                        cliente = (ClienteAmpliado) leer.readObject();
                        if (cliente.getSaldo() < 0 && cliente.getIngresosMedios()>3000) {
                            escribir.writeObject(cliente);
                            contador++;
                        }
                    }catch (Exception e){
                        fin=true;
                    }
                } while (!fin);
                System.out.println(contador);
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");
            } finally {
                cerrarFlujosDeDatos();
            }
        }
    }
    public static void abrirFlujosDeDatos(String entrada, String salida) throws IOException {
        leer = new ObjectInputStream(new FileInputStream(entrada));
        escribir = new ObjectOutputStream(new FileOutputStream(salida));
    }

    public static void cerrarFlujosDeDatos(){
        try {
            if (leer != null) {
                leer.close();
            }
            if (escribir != null) {
                escribir.close();
            }
        } catch (IOException e) {
            System.out.println("Error al cerrar el archivo de lectura.");
        }
    }
}
