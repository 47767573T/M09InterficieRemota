import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Created by dremo on 22/02/2016.
 */
public class Cliente_Calculadora {


    public static void main(String[] args) throws RemoteException, NotBoundException {

        Scanner input = new Scanner(System.in);
        System.out.println("Introduzca la operación:");

        gestionOperacion(input.nextLine());

        System.out.println("\n\t\tBYE BYE");
    }

    /**
     * Define el tipo de operacion y la distribuye al metodo adecuado
     * @param operacion la operacion a definir
     * @throws RemoteException
     * @throws NotBoundException
     */
    public static void gestionOperacion(String operacion) throws RemoteException, NotBoundException {
        CalculadoraInterficie calculadora = null;
        Registry registry;

        System.out.print("Conectando ...");
        registry = LocateRegistry.getRegistry("localhost", 5554);
        System.out.println("... conseguido registro remoto");
        calculadora = (CalculadoraInterficie)registry.lookup("generico");

        if (calculadora != null) {
            System.out.println("\nOperacion: ");

            //SUMA
            if (operacion.contains("+")) System.out.println("\tResultado de suma: " + calculadora.sumar(operacion));

            //RESTA
            else if (operacion.contains("-"))  System.out.println("\tResultado de resta: " + calculadora.restar(operacion));

            //MULTIPLICACION
            else if (operacion.contains("*"))  System.out.println("\tResultado de multiplicación: " + calculadora.multiplicar(operacion));

            //DIVISION
            else if (operacion.contains("/"))  System.out.println("\tResultado de división: " + calculadora.dividir(operacion));

            else System.out.println("Operación no reconocida");

        }else{ System.out.println(" Error en conexión "); }

    }
}
