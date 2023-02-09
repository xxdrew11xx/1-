import java.util.InputMismatchException;
import java.util.Scanner;

public class Intercalar_cadena {

    static int menu(Scanner e){

        int opcion = 4;

        do 
        {
                
            System.out.print("\n----------------------------MENU---------------------------- ");
            System.out.print("\n[1]---------> Teclear cadena");
            System.out.print("\n[2]---------> Iintercalar cadena");
            System.out.print("\n[3]---------> Visualizar cadena intercalada");
            System.out.println("\n[4]---------> Exit");
            System.out.println();
            System.out.print("[OPCION]----> ");
            
            try 
            {

                opcion = e.nextInt();  
            
            } 
            catch (InputMismatchException ime) 
            {

                opcion = 0;
                System.out.print("\n[!!] Solo variables numéricas.\n\n");
                break;

            }

            if(opcion < 1 || opcion > 4)
            {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }
          
        } 
        while (opcion < 1 || opcion > 4);

        return opcion;
    }

    static String stringinput(String cadena,String cadena2, Scanner sc){

     
        if(cadena == null)
        {

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
        else 
        {

            do 
            {
            
                try 
                {  

                    System.out.print("\n[+] Introduce otra cadena: ");                
                    cadena2=sc.nextLine();
                
                } 
                catch (InputMismatchException ime) {
                
                    cadena2 = "\n";

                }

            } 
            while (cadena2 == "\n");

            return cadena2;

        }

                    
    }

    static String intercalar(String cadena, String cadena2){

        String cadenaInter = "", longitud = "", longitud2 = "";


        if(cadena.length() > cadena2.length())
        {

            longitud = cadena;
            longitud2 = cadena2;

        }
        else
        {

            longitud = cadena2;
            longitud2 = cadena;

        }



        for(int i = 0; i < longitud.length(); i++ )
        {

            if( i >= longitud2.length())
            {

                cadenaInter += longitud.charAt(i);

            }
            else
            {

                cadenaInter = cadenaInter + longitud.charAt(i) + longitud2.charAt((longitud2.length() - 1) - i);

            }

        }

        return cadenaInter;
    }


    public static void main(String[] args) throws Exception {
        
        int opcion = 0;
        String cadena = null, cadena2 = null, cadenainter = null;

        System.out.println();

        Scanner e = new Scanner(System.in);

        do 
        {

            opcion = menu(e);
            e.nextLine();

            switch (opcion) 
            {
                case 1:
                    
                    cadena = stringinput(cadena, cadena2, e);
                    cadena2 = stringinput(cadena, cadena2, e);
                    break;
                
                case 2:

                    cadenainter = intercalar(cadena, cadena2);
                    System.out.print("\n[!] Cadena intercalada...\n\n");
                    break;

                case 3:

                    System.out.print("\n[!] La cadea intercalada es: " + cadenainter + "\n\n\n");
                    break;
                
                default:
                    break;
            }
        } 
        while (opcion != 4);

        e.close();
        
    }
    
}
