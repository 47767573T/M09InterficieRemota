import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Dionis on 22/02/2016.
 */
public class Servidor_Calculadora implements CalculadoraInterficie {

    private static CalculadoraInterficie calculadoraRMI = new Servidor_Calculadora();

    public String operacion;
    public double [] numOp;

    public static void main(String[] args) {

        Registry registro = null;

        try {
            registro = LocateRegistry.createRegistry(5554);
            registro.rebind("generico", (CalculadoraInterficie)UnicastRemoteObject.exportObject(calculadoraRMI, 0));
            System.out.println("Los metodos ya estan disponibles de manera remota.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Servidor activo..");
    }

    @Override
    public double sumar(String operacion) throws RemoteException {
        this.operacion = operacion.trim();

        desmontarOperacion("+");

        return numOp[0] + numOp[1];
    }

    @Override
    public double restar(String operacion) throws RemoteException {
        this.operacion = operacion.trim();

        desmontarOperacion("-");

        return numOp[0] - numOp[1];
    }

    @Override
    public double multiplicar(String operacion) throws RemoteException {
        this.operacion = operacion.trim();

        desmontarOperacion("*");

        return numOp[0] * numOp[1];
    }

    @Override
    public double dividir(String operacion) throws RemoteException {
        this.operacion = operacion.trim();

        desmontarOperacion("/");

        return numOp[0] / numOp[1];
    }

    public void desmontarOperacion (String operante){

        numOp[0] = Double.parseDouble(operacion.split(operante)[0]);
        numOp[1] = Double.parseDouble(operacion.split(operante)[1]);
    }
}
