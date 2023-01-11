import java.util.Scanner;

public class Menu {

    static int menu(Scanner e){

        int opcion = 4;



        do 
        {
                
            System.out.print("\n----------------------------MENU---------------------------- ");
            System.out.print("\n[1]---------> Teclear Cadena");
            System.out.print("\n[2]---------> Crear cadena de caracteres agrupados");
            System.out.print("\n[3]---------> Visualizar cadena de caracteres agrupados");
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

     
        System.out.print("\n[+] Introduce una cadena: ");                
        cadena=sc.nextLine();
            
      return cadena;

    }

    static String createstring(String cadena){

        System.out.print("\n[!] Cadena agrupada");

        System.out.println();
        System.out.println();

        return cadena.replaceAll(" ", "");

    }
    
    static void viewstring(String cadena){

        System.out.println();

        System.out.print("\n[!] La cadena es sin espacios es: ");
        System.out.print(cadena);

        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) throws Exception {

        String cadena = "";
        int opcion = 1;
        
        System.out.println();

        Scanner sc = new Scanner(System.in);

            do 
            {

                opcion = menu(sc);
                sc.nextLine();

                switch (opcion) 
                {
                    case 1:
                        
                        cadena=stringinput(cadena, sc);
                        break;
                    
                    case 2:
    
                        cadena = createstring(cadena);
                        break;
    
                    case 3:
    
                        viewstring(cadena);
                        break;

                    case 4:

                        break;

                    default:
                        break;
                }
            } 
            while (opcion != 4);

        sc.close();


    }
}
