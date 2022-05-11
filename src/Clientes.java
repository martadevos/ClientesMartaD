import java.time.MonthDay;
import java.util.Calendar;
import java.util.Date;

public class Clientes {

    public int numCliente;
    public String nombre;
    public String apellido1;
    public String apellido2;
    public int saldo;
    public int ingresosMedios;
    public int gastosMedios;

    public Clientes() {}
    public Clientes(int numCliente, String nombre, String apellido1, String apellido2, int saldo, int ingresosMedios, int gastosMedios, String direccion, int codigoPostal) {
        this.numCliente = numCliente;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.saldo = saldo;
        this.ingresosMedios = ingresosMedios;
        this.gastosMedios = gastosMedios;
    }

    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getIngresosMedios() {
        return ingresosMedios;
    }

    public void setIngresosMedios(int ingresosMedios) {
        this.ingresosMedios = ingresosMedios;
    }

    public int getGastosMedios() {
        return gastosMedios;
    }

    public void setGastosMedios(int gastosMedios) {
        this.gastosMedios = gastosMedios;
    }

    public static void main(String[] args) {
        Calendar fecha = Calendar.getInstance();
        System.out.println();
    }
}
