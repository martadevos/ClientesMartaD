import java.io.*;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Ficheros {

    private static final Calendar fecha = Calendar.getInstance();
    public static final File CLIENTES = new File(String.format("src/Documentos/Clientes_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR)));
    public static final File CLIENTESAMP = new File(String.format("src/Documentos/ClientesAmp_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR)));
    public static final File SALDOA0 = new File(String.format("src/Documentos/Saldo0_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR)));
    public static final File DEBITO = new File(String.format("src/Documentos/debito_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR)));
    public static final File CREDITO = new File(String.format("src/Documentos/Credito_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR)));
    public static final File ROBINSON = new File(String.format("src/Documentos/Robinson_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR)));
    public static final File VIP = new File(String.format("src/Documentos/VIP_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR)));
    private static Scanner s;

    public static void saldoA0(){
        Ficheros.leerClientes();
        ObjectInputStream lectura = null;
        ObjectOutputStream salida = null;
        Clientes cliente;
        if (!SALDOA0.exists()) {
            try {
                lectura = new ObjectInputStream(new FileInputStream(String.format("src/Documentos/ClientesAmp_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR))));
                salida = new ObjectOutputStream(new FileOutputStream(String.format("src/Documentos/Saldo0_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR))));
                do {
                    cliente = (Clientes) lectura.readObject();
                    if (cliente.getSaldo() == 0) {
                        salida.writeObject(cliente);
                    }
                } while (lectura.readObject() != null);
            } catch (ClassNotFoundException e) {
                System.out.println("Archivo de lectura no encontrado.");
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");
            } finally {
                try {
                    if (lectura != null) {
                        lectura.close();
                    }
                    if (salida != null) {
                        salida.close();
                    }
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo de lectura.");
                }
            }
        }
    }

    public static void clientesCredito(){
        Ficheros.leerClientes();
        ObjectInputStream lectura = null;
        ObjectOutputStream salida = null;
        Clientes cliente;
        if (!CREDITO.exists()) {
            try {
                lectura = new ObjectInputStream(new FileInputStream(CLIENTES));
                salida = new ObjectOutputStream(new FileOutputStream(CREDITO));
                do {
                    cliente = (Clientes) lectura.readObject();
                    if (cliente.getSaldo() > 0) {
                        salida.writeObject(cliente);
                    }
                } while (lectura.readObject() != null);
            } catch (ClassNotFoundException e) {
                System.out.println("Archivo de lectura no encontrado.");
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");
            } finally {
                try {
                    if (lectura != null) {
                        lectura.close();
                    }
                    if (salida != null) {
                        salida.close();
                    }
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo de lectura.");
                }
            }
        }
    }

    public static void clientesDebito(){
        Ficheros.leerClientes();
        ObjectInputStream lectura = null;
        ObjectOutputStream salida = null;
        Clientes cliente;
        if (!DEBITO.exists()) {
            try {
                lectura = new ObjectInputStream(new FileInputStream(CLIENTES));
                salida = new ObjectOutputStream(new FileOutputStream(DEBITO));
                do {
                    cliente = (Clientes) lectura.readObject();
                    if (cliente.getSaldo() < 0) {
                        salida.writeObject(cliente);
                    }
                } while (lectura.readObject() != null);
            } catch (ClassNotFoundException e) {
                System.out.println("Archivo de lectura no encontrado.");
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");
            } finally {
                try {
                    if (lectura != null) {
                        lectura.close();
                    }
                    if (salida != null) {
                        salida.close();
                    }
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo de lectura.");
                }
            }
        }
    }

    public static void clientesRobinson(){
        Ficheros.leerClientesAmpliados();
        int contador=0;
        ObjectInputStream lectura = null;
        ObjectOutputStream salida = null;
        Clientes cliente;
        if (!ROBINSON.exists()) {
            try {
                lectura = new ObjectInputStream(new FileInputStream(CLIENTESAMP));
                salida = new ObjectOutputStream(new FileOutputStream(ROBINSON));
                do {
                    cliente = (Clientes) lectura.readObject();
                    if (cliente.getSaldo() > 0 && cliente.getGastosMedios()>3000) {
                        salida.writeObject(cliente);
                        contador++;
                    }
                } while (lectura.readObject() != null);
                System.out.println(contador);
            } catch (ClassNotFoundException e) {
                System.out.println("Archivo de lectura no encontrado.");
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");
            } finally {
                try {
                    if (lectura != null) {
                        lectura.close();
                    }
                    if (salida != null) {
                        salida.close();
                    }
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo de lectura.");
                }
            }
        }
    }
    public static void clientesVip(){
        Ficheros.leerClientesAmpliados();
        int contador=0;
        ObjectInputStream lectura = null;
        ObjectOutputStream salida = null;
        Clientes cliente;
        if (!VIP.exists()) {
            try {
                lectura = new ObjectInputStream(new FileInputStream(CLIENTESAMP));
                salida = new ObjectOutputStream(new FileOutputStream(VIP));
                do {
                    cliente = (Clientes) lectura.readObject();
                    if (cliente.getSaldo() < 0 && cliente.getIngresosMedios()>3000) {
                        salida.writeObject(cliente);
                        contador++;
                    }
                } while (lectura.readObject() != null);
                System.out.println(contador);
            } catch (ClassNotFoundException e) {
                System.out.println("Archivo de lectura no encontrado.");
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");
            } finally {
                try {
                    if (lectura != null) {
                        lectura.close();
                    }
                    if (salida != null) {
                        salida.close();
                    }
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo de lectura.");
                }
            }
        }
    }

    public static Clientes sacarClienteAmpliado(String entrada){
        String nombre;
        StringBuilder direccion;
        s = new Scanner(entrada);
        ClienteAmpliado cliente = new ClienteAmpliado();
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
        direccion = new StringBuilder(s.next());
        while (s.hasNext(Pattern.compile("[^0-9]+"))){
            direccion.append(" ").append(s.next());
        }
        cliente.setDireccion(direccion.toString());
        cliente.setCodigoPostal(s.nextInt());
        return cliente;
    }

    public static Clientes sacarCliente(String entrada){
        String nombre;
        s = new Scanner(entrada);
        ClienteAmpliado cliente = new ClienteAmpliado();
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
        return cliente;
    }

    public static void leerClientesAmpliados(){
        BufferedReader lectura = null;
        ObjectOutputStream salida = null;
        String linea;
        if (!CLIENTESAMP.exists()) {
            try {
                lectura = new BufferedReader(new FileReader("src/Documentos/Clientes.txt"));
                salida = new ObjectOutputStream(new FileOutputStream(String.format("src/Documentos/ClientesAmp_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR))));
                linea = lectura.readLine();
                while (linea!=null){
                    salida.writeObject(sacarCliente(linea));
                    linea = lectura.readLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Archivo de lectura no encontrado.");
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");
            } finally {
                try {
                    if (lectura != null) {
                        lectura.close();
                    }
                    if (salida != null) {
                        salida.close();
                    }
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo de lectura.");
                }
            }
        }
    }

    public static void leerClientes(){
        BufferedReader lectura = null;
        ObjectOutputStream salida = null;
        String linea, clientes = "";
        clientes = clientes.formatted("src/Documentos/Clientes_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR));
        if (!CLIENTES.exists()) {
            try {
                lectura = new BufferedReader(new FileReader("src/Documentos/Clientes.txt"));
                salida = new ObjectOutputStream(new FileOutputStream(clientes));
                linea = lectura.readLine();
                while (linea!=null){
                    salida.writeObject(sacarCliente(linea));
                    linea = lectura.readLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Archivo de lectura no encontrado.");
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");
            } finally {
                try {
                    if (lectura != null) {
                        lectura.close();
                    }
                    if (salida != null) {
                        salida.close();
                    }
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo de lectura.");
                }
            }
        }
    }

}
