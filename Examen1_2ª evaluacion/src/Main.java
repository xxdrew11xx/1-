import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static int menuGeneral(Scanner e){

        int opcion = 4;

        do 
        {
                
            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Ejercicio 1");
            System.out.print("\n[2]---------> Ejercicio 2");
            System.out.println("\n[3]---------> Exit");
            System.out.println();
            System.out.print("[OPCION]----> ");
            
            try
            {

                opcion = e.nextInt();

            }
            catch(InputMismatchException ime)
            {

                System.out.print("\n[!!] Error: " + ime.getMessage());
                opcion = 0; 
                break;

            }
            if(opcion < 1 || opcion > 3)
            {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } 
        while (opcion < 1 || opcion > 3);

        return opcion;
    }

    public static int menuEj2(Scanner e){

        int opcion = 4;

        do 
        {
                
            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Llenar array");
            System.out.print("\n[2]---------> visualizar array");
            System.out.print("\n[3]---------> Contar palabras con X letra");
            System.out.println("\n[4]---------> Menú principal");
            System.out.println();
            System.out.print("[OPCION]----> ");
            
            try
            {

                opcion = e.nextInt();

            }
            catch(InputMismatchException ime)
            {

                System.out.print("\n[!!] Error: " + ime.getMessage());
                opcion = 0; 
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

    public static int menuEj1(Scanner e){

        int opcion = 4;

        do 
        {
                
            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Teclear cadena");
            System.out.print("\n[2]---------> Convertir cadena");
            System.out.print("\n[3]---------> Visualizar cadena");
            System.out.println("\n[4]---------> Menú principal");
            System.out.println();
            System.out.print("[OPCION]----> ");
            
            try
            {

                opcion = e.nextInt();

            }
            catch(InputMismatchException ime)
            {

                System.out.print("\n[!!] Error: " + ime.getMessage());
                opcion = 0; 
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

    static void visualizarcad(String cadena){

        System.out.print("\n[!] La cadena es: " + cadena + "\n\n");

    }

    static String stringiput(Scanner sc){

        String cadena = "";

        do
        {

            System.out.print("\n[+] Introduce una cadena (MAX 50 Caracteres): ");
            cadena = sc.nextLine();

            if(cadena.length() > 50)
            {
                
                System.out.print("\n[!!] Cadena demasiado larga, máximo 50 caracteres.");

            }

        }
        while(cadena.length() > 50);


        return cadena;
    }

    static String convertircadena(String cadenamain){

        String cadena = "";
        

        for(int i =0; i < cadenamain.length(); i++)
        {

            
            if(Character.isLetter(cadenamain.charAt(i)))
            {

                if(cadenamain.charAt(i) == 'a' || cadenamain.charAt(i) == 'e' || cadenamain.charAt(i) == 'i' || cadenamain.charAt(i) == 'o' || cadenamain.charAt(i) == 'u' || cadenamain.charAt(i) == 'á' || cadenamain.charAt(i) == 'é' || cadenamain.charAt(i) == 'í' || cadenamain.charAt(i) == 'ó' || cadenamain.charAt(i) == 'ú' || cadenamain.charAt(i) == 'ü')
                {

                    cadena += Character.toUpperCase(cadenamain.charAt(i));

                }
                else
                {

                    cadena += Character.toLowerCase(cadenamain.charAt(i));

                }

            }
            else if(Character.isDigit(cadenamain.charAt(i)) && Character.getNumericValue(cadenamain.charAt(i)) < 5)
            {

                cadena += "¿";

            }
            else if(Character.isDigit(cadenamain.charAt(i)) && Character.getNumericValue(cadenamain.charAt(i)) >= 5)
            {

                cadena += "?";

            }
            else if(cadenamain.charAt(i) == ' ')
            {

                cadena += "$";

            }
            else 
            {

                cadena += '*';

            }
            

        }

        System.out.print("\n[!!] Cadena convertida\n\n");

        return cadena;
    }

    static String[] llenararray(Scanner sc, String[] array){

        String arr[] = new String[array.length];

        for(int i = 0; i < array.length; i++)
        {

            System.out.print("\n[+] Introduce un string ["+ (i+1) +"]: ");
            arr[i] = sc.nextLine();

        }

        return arr;
    }

    static void visualizarArr(String arr[]){

        System.out.print("\n[+] El array es: " + arr[0]);

        for(int i = 1; i < arr.length; i++)
        {

            System.out.print(", " + arr[i]);

        }
        System.out.println();
    }

    static void contarPalabras(String arr[], Scanner sc){

        String letra;
        int contador = 0;

        System.out.print("\n[+] Introduce la letra a filtrar: ");
        letra = sc.nextLine();

        for(int i = 0; i < arr.length; i++)
        {

            for(int j = 0; j < arr[i].length() - 1; j++)
            {

                if(arr[i].charAt(j) == letra.charAt(0))
                {

                    contador ++;
                    break;

                }

            }

        }

        if(contador != 1)
        {

            System.out.print("\n[!] En el array hay " + contador + " palabra con la letra " + letra + "\n");

        }
        else
        {

            System.out.print("\n[!] En el array hay " + contador + " palabras con la letra " + letra + "\n");

        }

    }
    
    public static void main(String[] args) throws Exception {
        
        int opcion, opcionG;

        String cadena = "";
        String array[] = new String[4];
        
        System.out.println();

        Scanner sc = new Scanner(System.in);

            do 
            {
                opcionG = menuGeneral(sc);
                sc.nextLine();

                switch (opcionG) {
                    case 1:

                        do 
                        {
    
                            opcion = menuEj1(sc);
                            sc.nextLine();
    
                            switch (opcion) 
                            {
                                case 1:
                            
                                    cadena = stringiput(sc);
                                    break;
                        
                                case 2:
        
                                    cadena = convertircadena(cadena);
                                    break;

                                case 3:
    
                                    visualizarcad(cadena);
                                    break;
                        
                                default:
                                    System.out.print("\n\n\t[!!] Volviendo al menú principal....\n");
                                    break;
                            }
                        } 
                        while (opcion != 4);
                        break;

                    case 2:
                        
                        do
                        {

                            opcion = menuEj2(sc);
                            sc.nextLine();

                            switch (opcion) {
                                case 1:
                                    
                                    array = llenararray(sc, array);
                                    break;

                                case 2:
                                    
                                    visualizarArr(array);
                                    break;

                                case 3:

                                    contarPalabras(array, sc);
                                    break;
                            
                                default:
                                    System.out.print("\n\n\t[!!] Volviendo al menú principal....\n");
                                    break;
                            }

                        }
                        while(opcion != 4);
                        break;
                
                    default:
                        System.out.print("\n\n\t[!!]Saliendo....\n");
                        break;
                }
                
            } while (opcionG != 3);

        sc.close();


    }
}