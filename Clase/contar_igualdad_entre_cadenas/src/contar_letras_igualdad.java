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
            System.out.println("\n[4]---------> Exit");
            System.out.println();
            System.out.print("[OPCION]----> ");
            
            opcion = e.nextInt();

            if(opcion < 1 || opcion > 4)
            {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } 
        while (opcion < 1 || opcion > 4);

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
        Character ch1 = ' ', ch2=' ';

        for(int i = 0; i < cadena.length() - 1; i++)
        {
            ch1 = cadena.charAt(i);
            ch2 = cadenaInvertida.charAt(i);


            if(Character.isLetter(ch1) == Character.isLetter(ch2))
            {

                letras += 1;

            }
            else
            {

                if(Character.isDigit(ch1) == Character.isDigit(ch2))
                {

                    numeros += 1;

                }
                else
                {

                    otros += 1;

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

    static void existeletra(Scanner e){

        Character ch1 = ' ';

        System.out.print("\n[+] Introduce una letra para ver si está en el string");

        do 
        {
            System.out.print("\n[+] Introduce una letra para ver si está en el string");
            
            
        } 
        while (ch1 != '\n');


    }

    public static void main(String[] args) throws Exception {

        int opcion = 0;
        String cadena = "", cadenainvertida="";

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
                    System.out.println(cadenainvertida);
                    System.out.println(cadena);
                    break;

                case 3:

                    coincidencias(cadena,cadenainvertida);
                    break;

                case 4:

                    break;
                
                default:
                    break;
            }
        } 
        while (opcion != 4);

        e.close();
        
    }
}
