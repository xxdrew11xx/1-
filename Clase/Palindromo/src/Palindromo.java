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
            catch (InputMismatchException ime) {}
            
        }
        while(cadena == "\n");

        return cadena;
    }

    static boolean espalindromo(String cadena){
        
        int longitud = 0;
        boolean opcion = true;
        
        cadena = cadena.toLowerCase().replace(" ", "").replace(".","").replace(",", "").replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");

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
            
            if(espalindromo(cadena) == true)
            {

                System.out.println("\n[!] La cadena es palindromo");

            }
            else
            {

                System.out.println("\n[!] La cadena no es palindromo");

            }

        sc.close();

    }
}
