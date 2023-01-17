import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class contar_letras_igualdad {

    static int menu(Scanner e){

        int opcion = 4;

        do 
        {
                
            System.out.print("\n----------------------------MENU---------------------------- ");
            System.out.print("\n[1]---------> Teclear Cadena");
            System.out.print("\n[2]---------> Invertir cadena");
            System.out.print("\n[3]---------> Visualizar coincidencias");
            System.out.print("\n[4]---------> Introducir caracter y comprobar en la cadena");
            System.out.println("\n[5]---------> Exit");
            System.out.println();
            System.out.print("[OPCION]----> ");
            
            opcion = e.nextInt();  

            if(opcion < 1 || opcion > 5)
            {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } 
        while (opcion < 1 || opcion > 5);

        return opcion;
    }

    static String stringinput(String cadena, Scanner sc){

     
        do 
        {
            
            try 
            {

                System.out.print("\n[+] Introduce una cadena: ");                
                cadena=sc.nextLine();
                
            } 
            catch (InputMismatchException ime) {
                
                cadena = "\n";

            }

        } 
        while (cadena == "\n");
            
      return cadena;

    }

    static String inverrtirString(String cadena){

        String cadenaInvert ="";

        System.out.print("\n[!] La cadena se ha invertido correctamente");

        for(int i = cadena.length() - 1; i >= 0; i--)
        {

            cadenaInvert += cadena.charAt(i);

        }

        System.out.println();
        System.out.println();

        return cadenaInvert;
    }

    static void coincidencias(String cadena, String cadenaInvertida){

        System.out.println();

        int letras = 0, numeros = 0, otros = 0, total = 0;

        for(int i = 0; i < cadena.length() - 1; i++)
        {


            if(cadena.charAt(i) == cadenaInvertida.charAt(i))
            {

                total += 1;

                if(Character.isLetter(cadena.charAt(i)))
                {

                    letras += 1;

                }
                else
                {

                    if(Character.isDigit(cadena.charAt(i)))
                    {

                        numeros += 1;

                    }
                    else
                    {

                        otros += 1;

                    }

                }

            }

        }

        total = numeros + letras + otros;

        System.out.print("------------------------------------Coincidencias------------------------------------");
        System.out.print("\ntotal                     numeros                    letras                    otros");
        System.out.print("\n" + total + "                         " + numeros + "                          " + letras + "                         " + otros);

        System.out.println();
        System.out.println();


    }

    static void existeletra(Scanner e, String cadena) {

        Character ch1 = ' ';
        boolean charexist = false;

            System.out.print("\n[+] Introduce una letra para ver si está en el string: ");
            try 
            {
                ch1 = (char) System.in.read();
                
            } 
            catch (IOException ioe) {}

        for(int i = 0; i < cadena.length(); i++)
        {

            if(cadena.charAt(i) == ch1)
            {
                charexist = true;
                break;
            }   
            

        }

        if(charexist == true)
        {

            System.out.print("\n[!] El caracter " + ch1 + " está en la cadena\n");

        }
        else
        {

            System.out.print("\n[!!] El caracter " + ch1 + " no está en la cadena\n");

        }


    }

    public static void main(String[] args) throws Exception {

        int opcion = 0;
        String cadena = null, cadenainvertida = null;

        System.out.println();

        Scanner e = new Scanner(System.in);

        do 
        {

            opcion = menu(e);
            e.nextLine();

            switch (opcion) 
            {
                case 1:
                    
                    cadena=stringinput(cadena, e);
                    break;
                
                case 2:

                    cadenainvertida=inverrtirString(cadena);
                    break;

                case 3:

                    coincidencias(cadena,cadenainvertida);
                    break;

                case 4:

                    existeletra(e, cadena);
                    break;
                
                case 5:

                    break;
                
                default:
                    break;
            }
        } 
        while (opcion != 5);

        e.close();
        
    }
}

