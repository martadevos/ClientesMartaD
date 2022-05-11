public class ClienteAmpliado extends Clientes{
    public String direccion;
    public int codigoPostal;
    public ClienteAmpliado() {}

    public ClienteAmpliado(int numCliente, String nombre, String apellido1, String apellido2, int saldo, int ingresosMedios, int gastosMedios, String direccion, int codigoPostal, String direccion1, int codigoPostal1) {
        super(numCliente, nombre, apellido1, apellido2, saldo, ingresosMedios, gastosMedios, direccion, codigoPostal);
        this.direccion = direccion1;
        this.codigoPostal = codigoPostal1;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
