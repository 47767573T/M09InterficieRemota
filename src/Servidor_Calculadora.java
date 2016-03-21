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
    public double op1;
    public double op2;

    public static void main(String[] args) {

        try {
            System.out.print("Creando registro...");
            Registry registro = LocateRegistry.createRegistry(5554);
            registro.rebind("generico", UnicastRemoteObject.exportObject(calculadoraRMI, 0));
            System.out.println("...registro Creado");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public double sumar(String operacion) throws RemoteException {
        this.operacion = operacion.trim();

        int operantePos = operacion.indexOf("+");
        op1 = Double.parseDouble(operacion.substring(0,operantePos));
        op2 = Double.parseDouble(operacion.substring(operantePos+1));

        return op1+op2;
    }

    @Override
    public double restar(String operacion) throws RemoteException {
        this.operacion = operacion.trim();

        int operantePos = operacion.indexOf("-");
        op1 = Double.parseDouble(operacion.substring(0,operantePos));
        op2 = Double.parseDouble(operacion.substring(operantePos+1));

        return op1-op2;
    }

    @Override
    public double multiplicar(String operacion) throws RemoteException {
        this.operacion = operacion.trim();

        int operantePos = operacion.indexOf("*");
        op1 = Double.parseDouble(operacion.substring(0,operantePos));
        op2 = Double.parseDouble(operacion.substring(operantePos+1));

        return op1*op2;
    }

    @Override
    public double dividir(String operacion) throws RemoteException {
        this.operacion = operacion.trim();

        int operantePos = operacion.indexOf("/");
        op1 = Double.parseDouble(operacion.substring(0,operantePos));
        op2 = Double.parseDouble(operacion.substring(operantePos+1));

        return op1/op2;
    }
}
