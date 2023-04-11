import java.util.*;

public class Main {

    static int menuEj1(Scanner sc){

        int opcion = 7;

        do 
        {
                
            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Altas");
            System.out.print("\n[2]---------> Bajas");
            System.out.print("\n[3]---------> Modificaciones");
            System.out.print("\n[4]---------> Consultas");
            System.out.print("\n[5]---------> Listado Razas");
            System.out.println("\n[6]---------> Volver al menu general");
            System.out.println();
            System.out.print("[OPCION]----> ");
            
            try
            {

                opcion = sc.nextInt();

            }
            catch(InputMismatchException ime)
            {

                System.out.print("\n[!!] Error: " + ime.getMessage());
                opcion = 0; 
                break;

            }
            if(opcion < 1 || opcion > 6)
            {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } 
        while (opcion < 1 || opcion > 6);

        return opcion;

    }


    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
