import java.util.InputMismatchException;
import java.util.Scanner;

public class Palindromo {

    static String inputstring(String cadena, Scanner e){

        do
        {

            try 
            {

                System.out.print("\n[+] Introduce una cadena: ");
                cadena = e.nextLine();
                
            } 
            catch (InputMismatchException ime) 
            {

                cadena = "\n";

            }

            if(cadena == "\n" || cadena.length() <= 1)
            {

                System.out.println("\n[!!] Cadena invalida\n\n");

            }
            
        }
        while(cadena == "\n" || cadena.length() <= 1);

        return cadena;
    }

    static boolean espalindromo(String cadena){
        
        int longitud = 0;
        boolean opcion = true;
        
        cadena = cadena.toLowerCase().replace(" ", "").replace(".","").replace(",", "").replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u").replace("ü", "u");

        if(cadena.length() % 2 == 0)
        {  

            longitud =  cadena.length()/2;

        }    
        else
        {

            longitud = (cadena.length()/2) + (1/2); 

        }

        for(int i = 0; i < longitud; i++)
        {

            if(cadena.charAt(i) != cadena.charAt(cadena.length() - 1 - i))
            {

                opcion = false;
                break;

            }
            
        }

        return opcion;
    }

    public static void main(String[] args) throws Exception {

        String cadena=null;
        
        Scanner sc = new Scanner(System.in);

            cadena=inputstring(cadena, sc);
            
            if(espalindromo(cadena))
            {

                System.out.println("\n[!] La cadena ===> " + cadena +  " es palindromo");

            }
            else
            {

                System.out.println("\n[!] La cadena ===> " + cadena +  " NO es palindromo");

            }

        sc.close();

    }
}
