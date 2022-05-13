import java.io.Serializable;

public class ClienteAmpliado extends Clientes implements Serializable {
    public String direccion;
    public int codigoPostal;
    public ClienteAmpliado() {}

    public ClienteAmpliado(int numCliente, String nombre, String apellido1, String apellido2, int saldo, int ingresosMedios, int gastosMedios, String direccion, int codigoPostal) {
        super(numCliente, nombre, apellido1, apellido2, saldo, ingresosMedios, gastosMedios);
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
