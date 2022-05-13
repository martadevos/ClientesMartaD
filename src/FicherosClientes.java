import java.io.*;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FicherosClientes {
    private static final Calendar fecha = Calendar.getInstance();
    public static final String CLIENTESAMPLIADOS = "src/Documentos/ClientesAmp_%S_%S_%S.dat".formatted(fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR));
    public static final File CLIENTESAMP = new File(CLIENTESAMPLIADOS);
    public static final String CLIENTESNORMAL = "src/Documentos/Clientes_%S_%S_%S.dat".formatted(fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR));
    public static final File CLIENTES = new File(CLIENTESNORMAL);
    public static BufferedReader leer = null;
    public static ObjectOutputStream escribir = null;
    private static Scanner s;
    public static Clientes sacarClienteAmpliado(String entrada){
        StringBuilder direccion;
        s = new Scanner(entrada);
        ClienteAmpliado cliente = new ClienteAmpliado();
        sacarDatosPadre(cliente);
        direccion = new StringBuilder(s.next());
        while (s.hasNext(Pattern.compile("\\D+"))){
            direccion.append(" ").append(s.next());
        }
        cliente.setDireccion(direccion.toString());
        cliente.setCodigoPostal(s.nextInt());
        return cliente;
    }

    public static Clientes sacarCliente(String entrada){
        s = new Scanner(entrada);
        Clientes cliente = new Clientes();
        sacarDatosPadre(cliente);
        return cliente;
    }

    private static void sacarDatosPadre(Clientes cliente) {
        String nombre;
        cliente.setNumCliente(s.nextInt());
        nombre = s.next();
        if (s.hasNext("[a-z]+")){
            nombre += " " + s.next();
        }
        cliente.setNombre(nombre);
        cliente.setApellido1(s.next());
        cliente.setApellido2(s.next());
        cliente.setSaldo(s.nextInt());
        cliente.setIngresosMedios(s.nextInt());
        cliente.setGastosMedios(s.nextInt());
    }

    public static void leerClientesAmpliados(){
        String linea;
        if (!CLIENTESAMP.exists()) {
            try {
                abrirFlujosDeDatos(CLIENTESAMPLIADOS);
                linea = leer.readLine();
                while (linea!=null){
                    escribir.writeObject(sacarClienteAmpliado(linea));
                    linea = leer.readLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Archivo de lectura no encontrado.");
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");
            } finally {
                cerrarFlujosDeDatos();
            }
        }
    }

    public static void leerClientes(){
        String linea;
        if (!CLIENTES.exists()) {
            try {
                abrirFlujosDeDatos(CLIENTESNORMAL);
                linea = leer.readLine();
                while (linea!=null){
                    escribir.writeObject(sacarCliente(linea));
                    linea = leer.readLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Archivo de lectura no encontrado.");
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");
            } finally {
                cerrarFlujosDeDatos();
            }
        }
    }
    public static void abrirFlujosDeDatos(String salida) throws IOException {
        leer = new BufferedReader(new FileReader("src/Documentos/Clientes.txt"));
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
