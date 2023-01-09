import java.util.Scanner;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {

        int numin = 0, numfin = 0, razon;
        char opcion = ' ';
        boolean repetir = true;
        
        System.out.println();

        Scanner sc = new Scanner(System.in);

            do 
            {
                
                do 
                {

                    System.out.print("\n[+]Introduce el numero inicial de la sucesion: ");
                    numin = sc.nextInt();

                    System.out.print("\n[+]Introduce el numero final de la sucesion: ");
                    numfin = sc.nextInt();

                    if(numin >= numfin)
                    {

                        System.out.print("[!!] Numeros introducidos invalidos, introducelos de nuevo. \n");

                    }
            
                } 
                while (numin >= numfin);

                System.out.print("\n[+]Introduce la diferencia de la sucesion: ");
                razon = sc.nextInt();
                
                System.out.print("\n[!]La sucesion seria: " + numin);
                

                for(int i = 2; numin * (int) Math.pow(razon, (i-1)) <= numfin; i++)
                {
                    
                    System.out.print(", " + numin * (int) Math.pow(razon, (i-1)));
        
                }

                try 
                {
                    
                    System.out.print("\n[?] Quiere hacer otra progresión (s->SI / n->NO): ");
                    opcion = (char) System.in.read();
                    while(System.in.read() != '\n');

                    switch (Character.toLowerCase(opcion)) 
                    {
                        case 's':
                            
                            repetir = true;

                        break;
                        
                        case 'n':
                            
                            repetir = false;
                        
                        break;
                    
                        default:
                            //
                        break;
                    }
                    
                } 
                catch (IOException ioe) {}



            } 
            while (repetir != false);

        sc.close();


    }
}
