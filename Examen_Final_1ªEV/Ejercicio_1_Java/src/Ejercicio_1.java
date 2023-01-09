import java.io.IOException;
import java.util.Scanner;

public class Ejercicio_1 {
    public static void main(String[] args) throws Exception {
        
        int multiplicador = 0;
        char opcion = 's';

        System.out.println();

        Scanner sc = new Scanner(System.in);

           do 
           {
            
                System.out.print("[?] Que tabla quieres saber: ");
                multiplicador = sc.nextInt();

                for(int i = 1; i < 11 ; i++)
                {

                    System.out.print(multiplicador + " * " + i + " = " + (multiplicador*i) + "\n");

                }

                do 
                {

                    try 
                    {
                    
                        System.out.print("\n[?] Otra tabla (s/n): ");
                        opcion = (char) System.in.read();
                        while(System.in.read() != '\n');

                    } 
                    catch (IOException ioe) {}
                    
                } 
                while (opcion != 's' && opcion != 'n');
                
           } 
           while (opcion == 's');
        
        sc.close();

    }
}
