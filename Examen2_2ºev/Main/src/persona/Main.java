package persona;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main { 
    
    static int menGen(Scanner e){

        int opcion;

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

    static void fin(){

        System.out.print("\n\n\t\t[!] Saliendo....\n");

    }

    static int menuEj1(Scanner sc){

        int opcion = 7;

        do 
        {
                
            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Llenar Array");
            System.out.print("\n[2]---------> Listar Array");
            System.out.print("\n[3]---------> Listar cadena segun caracter");
            System.out.print("\n[4]---------> Frase con mayor numero de vocales");
            System.out.print("\n[5]---------> Sumar digitos de uno de los iterandos");
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

    static String[] llenado(String[] frase, Scanner sc ){


        for(int i = 0; i < frase.length; i++)
        {

            System.out.print("\n[+] Introduce una frase [" + (i+1) + "]: ");
            frase[i]=sc.nextLine();

        }

        return frase;

    }

    static String[] visualizado(String[] frase){


        System.out.print("\n\t\tArray");
        System.out.println("\n\t================");


        for(int i = 0; i < frase.length; i++)
        {

            System.out.println(frase[i]);

        }

        return frase;

    }

    static void segunchar(String[] frases, Scanner sc){

        String carac = "";
        int cuenta = 0;

        do 
        {
        
            System.out.print("\n[+] Introduce un caracter: ");
            carac = sc.nextLine();

            if(carac.length() != 1)
            {

                System.out.print("\n\n\t[!!] Longitud invalida");
                
            }

        } while (carac.length() != 1 || carac.equalsIgnoreCase("\n"));  
        
        System.out.print("\n\t\tArray");
        System.out.println("\n\t====================");
        
        for (int i = 0; i < frases.length; i++) 
        {
        
            if(frases[i].charAt(0) == carac.charAt(0))
            {

                System.out.println(frases[i]);
                cuenta ++;

            }

        }

        if(cuenta == 0)
        {

            System.out.print("\n[!!] En el string no hay ninguna frase que empiece por " + carac);

        }

    }

    static void frasemasvoc(String[] frase){

        int contvoc = 0, contvocAnt = 0, frasei = 0;

        for (int i = 0; i < frase.length; i++) {

            contvoc = 0;

            for (int j = 0; j < frase[i].length(); j++) {

                if(frase[i].charAt(j) == 'a' || frase[i].charAt(j) == 'e' || frase[i].charAt(j) == 'i' || frase[i].charAt(j) == 'o' || frase[i].charAt(j) == 'u' || frase[i].charAt(j) == 'á' || frase[i].charAt(j) == 'é' || frase[i].charAt(j) == 'í' || frase[i].charAt(j) == 'ó' || frase[i].charAt(j) == 'ú' || frase[i].charAt(j) == 'ü' )
                {

                    contvoc ++;

                }
                
            }

            if(contvoc > contvocAnt)
            {

                contvocAnt = contvoc;
                frasei = i;

            }
            


        }

        System.out.print("\n[!] La frase con más vocales es: " + frase[frasei] + " con un total de: " + contvocAnt);

    }

    static void sumdig(String frase[], Scanner sc){

        int opcion, suma = 0;

        do
        {

            System.out.print("\n[+] Frrase a sumar digitos: ");
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



        }while(opcion < 1 || opcion > frase.length);

        for (int i = 0; i < frase[opcion-1].length(); i++) {

            if(Character.isDigit(frase[opcion-1].charAt(i)))
            {

                suma += Character.getNumericValue(frase[opcion-1].charAt(i));

            }
            
        }

        System.out.print("\n[!] El valor de los numeros sumados en la cadena es: " + suma);

    }

    static int menuEj2(Scanner sc){

        int opcion = 7;

        do 
        {
                
            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Altas");
            System.out.print("\n[2]---------> Listado general");
            System.out.print("\n[3]---------> Ordenar ficehro(menú)");
            System.out.println("\n[4]---------> Volver al menu general");
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
            if(opcion < 1 || opcion > 4)
            {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } 
        while (opcion < 1 || opcion > 4);

        return opcion;

    }

    static void altas(Scanner sc, File data){

        String name = "", imps = "";
        float imp;
        
        try 
        {


            BufferedWriter bw = new BufferedWriter(new FileWriter(data));
        
          

                do 
                {

                    System.out.print("\n[+] Introduce un nombre (MAX 20 chars): ");
                    name = sc.nextLine();

                    if(name.length() > 20)
                    {

                        System.out.print("\n[!!] Longitud es mayor a 20 chars");

                    }

                } while (name.length() > 20);


                while(!(name.equalsIgnoreCase("fin")))
                {

                    do 
                    {
                        try 
                        {

                            System.out.print("\n[+] Introduce el importe formato(#,##): ");
                            imp = sc.nextInt();

                        } catch (InputMismatchException ime)
                        {
                        
                            System.out.print("\n[!!] Error: " + ime.getMessage());
                            imp = Integer.MAX_VALUE;
                            break;

                        }
                    
                    } while (imp == Integer.MAX_VALUE );

                    sc.nextLine();


                    imps = String.valueOf(imp);

                    bw.write(name);
                    bw.newLine();
                    bw.write(imps);
                    bw.newLine();

                    do 
                    {

                        System.out.print("\n[+] Introduce un nombre (MAX 20 chars): ");
                        name = sc.nextLine();

                        if(name.length() > 20)
                        {

                            System.out.print("\n[!!] Longitud es mayor a 20 chars");

                        }
                    
                    } while (name.length() > 20);
                }

            bw.close();


        } catch (IOException ime) 
        {
            System.out.print("\n[!!] Error: " + ime.getMessage());
        }

    }   

    static void vislist(File data){

        String name = "", imp = "";

        System.out.println("\n\t\tListado general");
        System.out.println("\t\t===============");

        try 
        {

            BufferedReader br = new BufferedReader(new FileReader(data));

                name = br.readLine();

                if(name == null)
                {

                    System.out.print("\n[!!] Fichero vacio\n");

                }

                while(name != null)
                {

                    imp = br.readLine();

                    System.out.println(name + "\t" + imp);

                    name = br.readLine();

                }

            br.close();

            
        } catch (IOException ioe) 
        {

            System.out.print("\n[!!] Error: " + ioe.getMessage());

        }

    }

    static int menuOrd(Scanner sc){

        int opcion;

        do 
        {
                
            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Ordenar por nombre");
            System.out.print("\n[2]---------> Ordenar por importe");
            System.out.println("\n[3]---------> Volver al menu general");
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
            if(opcion < 1 || opcion > 3)
            {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } 
        while (opcion < 1 || opcion > 3);


        return opcion;
    }

    static persona[] ordName(persona[] arr, File data){

        persona aux;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                
                if(arr[i].getNombre().compareToIgnoreCase(arr[j].getNombre()) < 0)
                {

                    aux = arr[i];
                    arr[i] = arr[j];
                    arr[j] = aux;

                }

            }
        }

        return arr;

    }

    static persona[] ordImp(persona[] arr, File data){

        persona aux;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                
                if(Float.parseFloat(arr[i].getImp()) < Float.parseFloat(arr[j].getImp()))
                {

                    aux = arr[i];
                    arr[i] = arr[j];
                    arr[j] = aux;

                }

            }
        }

        return arr;

    }

    static int cuenta(File data){
        
        int cuenta = 0;
        String name = "";

        try 
        {

            BufferedReader br = new BufferedReader(new FileReader(data));

                name = br.readLine();

                if(name == null)
                {

                    System.out.print("\n[!!] Fichero vacio\n");

                }

                while(name != null)
                {
                    br.readLine();

                    cuenta ++;

                    name = br.readLine();

                }

            br.close();

            
        } catch (IOException ioe) 
        {

            System.out.print("\n[!!] Error: " + ioe.getMessage());

        }

        return cuenta;

    }

    static persona[] llenarArr(File data, persona[] arr){

        String name = "", imp = "";

        try 
        {

            BufferedReader br = new BufferedReader(new FileReader(data));

                for (int i = 0; i < arr.length; i++) {

                    name = br.readLine();
                    imp = br.readLine();

                    arr[i] = new persona(name, imp);

                }

            br.close();

            
        } catch (IOException ioe) 
        {

            System.out.print("\n[!!] Error: " + ioe.getMessage());

        }

        return arr;

    }

    static void devData(File data, persona[] arr){

        data.delete();


        try 
        {

            BufferedWriter bw = new BufferedWriter(new FileWriter(data));

                for (int i = 0; i < arr.length; i++) {

                    bw.write(arr[i].getNombre());
                    bw.newLine();
                    bw.write(arr[i].getImp());
                    bw.newLine();
                    
                }

            bw.close();
            
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    static void opsw(int op, int opt, int opc, int opo, String[] frases, File data, persona arr[], Scanner sc){

        do 
        {

            op = menGen(sc);
            sc.nextLine();

            switch(op)
            {

                case 1:

                    do 
                    {

                        opt = menuEj1(sc);
                        sc.nextLine();

                        switch (opt) {
                            case 1:

                                frases = llenado(frases, sc);    
                                break;

                            case 2:

                                visualizado(frases);
                                break;

                            case 3:

                                segunchar(frases, sc);
                                break;

                            case 4:

                                frasemasvoc(frases);
                                break;

                            case 5: 

                                sumdig(frases, sc);
                                break;
                        
                            default:
                                fin();
                                break;
                        }


                    
                    } while (opt != 6);

                    break;

                case 2:
                    
                    do 
                    {

                        opc = menuEj2(sc);
                        sc.nextLine();

                        

                        switch (opc) {
                            case 1:
                                
                                altas(sc, data);
                                break;

                            case 2:

                                vislist(data);
                                break;

                            case 3:

                                arr = llenarArr(data, arr); 

                                do 
                                {

                                    opo = menuOrd(sc);
                                    sc.nextLine();

                                    switch (opo) {
                                        case 1:

                                            arr = ordName(arr, data);
                                            devData(data, arr);
                                            System.out.print("\n[!]Fichero ordenado.....");
                                            break;
                                        
                                        case 2:

                                            arr = ordImp(arr, data);
                                            devData(data, arr);
                                            System.out.print("\n[!]Fichero ordenado.....");
                                            break;
                                    
                                        default:
                                            break;
                                    }
                                    
                                } while (opo != 3);
                        
                            default:
                                
                                System.out.print("\n[!] Volviendo al menú general....\n\n");
                                break;
                        }
                        
                    } while (opc != 4);
                    break;

                default:
                    
                    fin();
                    break;

            }
        
        } while (op != 3);


    }
    
    public static void main(String[] args) throws Exception {

        int op = 0, opt = 0, opc = 0, opo = 0;

        String frases[] = new String[3];

        File data = new File("/home/drew/DAM/Programcion_clase/Examen2_2ºev/data/data.txt");

        persona arr[] = new persona[cuenta(data)];
        

        Scanner sc = new Scanner(System.in);

        opsw(op, opt, opc, opo, frases, data, arr, sc);

        sc.close();

    }
}
