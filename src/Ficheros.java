import java.io.*;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

public abstract class Ficheros {

    private static final Calendar fecha = Calendar.getInstance();
    public static final File SALDOA0 = new File(String.format("Documentos/Saldo0_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR)));
    public static final File DEBITO = new File(String.format("Documentos/debito_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR));
    public static final File CREDITO = new File(String.format("Documentos/Credito_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR));
    public static final File ROBINSON = new File(String.format("Documentos/Robinson_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR));
    public static final File VIP = new File(String.format("Documentos/VIP_%S/%S/%S.dat", fecha.get(Calendar.DATE), (fecha.get(Calendar.MONTH)+1),fecha.get(Calendar.YEAR));
    public static void saldoA0(){
        ObjectInputStream lectura = null;
        ObjectOutputStream salida = null;
        Clientes cliente;
        if (!SALDOA0.exists()) {
            try {
                lectura = new ObjectInputStream(new FileInputStream("Documentos/Clientes.dat"));
                salida = new ObjectOutputStream(new FileOutputStream(SALDOA0));
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
        ObjectInputStream lectura = null;
        ObjectOutputStream salida = null;
        Clientes cliente;
        if (!CREDITO.exists()) {
            try {
                lectura = new ObjectInputStream(new FileInputStream("Documentos/Clientes.dat"));
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
        ObjectInputStream lectura = null;
        ObjectOutputStream salida = null;
        Clientes cliente;
        if (!DEBITO.exists()) {
            try {
                lectura = new ObjectInputStream(new FileInputStream("Documentos/Clientes.dat"));
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
        int contador=0;
        ObjectInputStream lectura = null;
        ObjectOutputStream salida = null;
        Clientes cliente;
        if (!ROBINSON.exists()) {
            try {
                lectura = new ObjectInputStream(new FileInputStream("Documentos/Clientes.dat"));
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
        int contador=0;
        ObjectInputStream lectura = null;
        ObjectOutputStream salida = null;
        Clientes cliente;
        if (!VIP.exists()) {
            try {
                lectura = new ObjectInputStream(new FileInputStream("Documentos/Clientes.dat"));
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
        String nombre, direccion;
        Scanner scanner = new Scanner(entrada);
        ClienteAmpliado cliente = new ClienteAmpliado();
        cliente.setNumCliente(scanner.nextInt());
        nombre = scanner.next();
        if (scanner.hasNext("[a-z]+")){
            nombre += " " + scanner.next();
        }
        cliente.setNombre(nombre);
        cliente.setApellido1(scanner.next());
        cliente.setApellido2(scanner.next());
        cliente.setSaldo(scanner.nextInt());
        cliente.setIngresosMedios(scanner.nextInt());
        cliente.setGastosMedios(scanner.nextInt());
        direccion = scanner.next();
        while (scanner.hasNext(Pattern.compile("[^0-9]+"))){
            direccion += " " + scanner.next();
        }
        cliente.setDireccion(direccion);
        cliente.setCodigoPostal(scanner.nextInt());
        return cliente;
    }

    public static void leer(){
        BufferedReader lectura = null;
        ObjectOutputStream salida = null;
            String linea;
            try {
                lectura = new BufferedReader(new FileReader("Documentos/Clientes.txt"));
                salida = new ObjectOutputStream(new FileOutputStream("Documentos/Clientes.dat"));
                linea = lectura.readLine();
                while (linea!=null){
                    salida.writeObject(sacarClienteAmpliado(linea));
                    linea = lectura.readLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Archivo de lectura no encontrado.");
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");
            } finally {
                try {
                    if (lectura!=null){
                        lectura.close();
                    }
                    if (salida!=null){
                        salida.close();
                    }
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo de lectura.");
                }
            }

    }

}
