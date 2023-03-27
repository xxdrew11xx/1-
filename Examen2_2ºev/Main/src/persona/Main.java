package persona;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
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
            System.out.print("\n[4]---------> Personas con mayor o menor importe");
            System.out.print("\n[5]---------> Listado Importes por encima de la media");        
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

    static void altas(Scanner sc, File data){

        String name = "", imps = "";
        Float imp = (float) 0.00;
        
        try 
        {


            BufferedWriter bw = new BufferedWriter(new FileWriter(data,true));
        
          

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
                            imp = sc.nextFloat();


                        } catch (InputMismatchException ime)
                        {
                        
                            System.out.print("\n[!!] Error: " + ime.getMessage());
                            imp = Float.MAX_VALUE;
                            break;

                        }
                    
                    }while (imp == Float.MAX_VALUE);

                    imps = String.valueOf(imp);

                    bw.write(name);
                    bw.newLine();
                    bw.write(imps);
                    bw.newLine();

                    sc.nextLine();

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

        DecimalFormat formato = new DecimalFormat("##.##");

        float cuenta = (float) 0;

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
                    cuenta += Float.parseFloat(imp);

                    System.out.println(name + "\t" + formato.format(Float.parseFloat(imp)));

                    name = br.readLine();

                }

            br.close();

            
        } catch (IOException ioe) 
        {

            System.out.print("\n[!!] Error: " + ioe.getMessage());

        }

        System.out.print("\n\n[!]El total de importes es: " + cuenta + "\n");

    }

    static String menuOrd(Scanner sc){

        String opcion = "";

        do 
        {
                
            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[N]---------> Ordenar por nombre");
            System.out.print("\n[I]---------> Ordenar por importe");
            System.out.println("\n[V]---------> Volver al menu general");
            System.out.println();
            System.out.print("[OPCION]----> ");
            
            try
            {

                opcion = sc.next().toUpperCase();

            }
            catch(InputMismatchException ime)
            {

                System.out.print("\n[!!] Error: " + ime.getMessage());
                opcion = "J"; 
                break;

            }
            if(!opcion.equals("N") && !opcion.equals("I") && !opcion.equals("V"))
            {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } 
        while (!opcion.equals("N") && !opcion.equals("I") && !opcion.equals("V"));


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

    static float media(File data){

        float media = 0;
        int cuenta = 0;
        String name = "", imp = "";

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

                    media += Float.parseFloat(imp);
                    cuenta ++;

                    name = br.readLine();

                }

            br.close();

            
        } catch (IOException ioe) 
        {

            System.out.print("\n[!!] Error: " + ioe.getMessage());

        }

        return (media/(float)cuenta);

    }
    

    static void listImpMayorMed(File data){

        DecimalFormat formato = new DecimalFormat("##.##");

        float media = media(data), totalimp = (float) 0;
        String name = "", imp = "";
        int personas = 0;

        System.out.print("\n\tListado superior a la media");
        System.out.print("\n\t===========================");

        System.out.println("\n Media: " + formato.format(media));

        System.out.println("\nNombre:\t\t\tImporte:");
        System.out.println("----------------------------------------");

        try 
        {

            BufferedReader br = new BufferedReader(new FileReader(data));

                name = br.readLine();

                if(name == null)
                {

                    System.out.print("\n\t[!!] Fichero vacio....");

                }

                while(name != null)
                {

                    imp = br.readLine();
                    

                    if(Float.parseFloat(imp) >= media)
                    {

                        personas ++;
                        totalimp += Float.parseFloat(imp);
                        System.out.print(name + "\t\t\t" + imp);

                    }

                    name = br.readLine();

                }

            br.close();
            
        } catch (IOException ioe) 
        {

            System.out.print("\n[!!] Error: " + ioe.getMessage());
        
        }

        System.out.println("\n\n[!] Total de importes listados: " + totalimp);
        System.out.println("\n[!] Total de personas listadas: " + personas);


    }

    static float mayor(File data){

        float mayor = (float) 0;
        String name = "", imp = "";

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

                    if( Float.parseFloat(imp) > mayor)
                    {

                        mayor = Float.parseFloat(imp);

                    }

                    name = br.readLine();

                }

            br.close();

            
        } catch (IOException ioe) 
        {

            System.out.print("\n[!!] Error: " + ioe.getMessage());

        }

        return mayor;

    }

    static float menor(File data){

        float menor = (float) 1000;
        String name = "", imp = "";

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

                    if( Float.parseFloat(imp) < menor)
                    {

                        menor = Float.parseFloat(imp);

                    }

                    name = br.readLine();

                }

            br.close();

            
        } catch (IOException ioe) 
        {

            System.out.print("\n[!!] Error: " + ioe.getMessage());

        }

        return menor;

    }

    static void listMayMen(File data){

        float mayor = mayor(data), menor = menor(data);

        String name = "", imp = "";

        System.out.print("\nListado Mayor | Menor");
        System.out.print("\n---------------------\n");

        try 
        {

            BufferedReader br = new BufferedReader(new FileReader(data));

                name = br.readLine();

                if(name == null)
                {

                    System.out.print("\n\t[!!] Fichero vacio....");

                }

                while(name != null)
                {

                    imp = br.readLine();

                    if(Float.parseFloat(imp) == menor)
                    {

                        System.out.println(name + " tiene el importe menor cpn un total de: " + imp + "\n");

                    }
                    else if(Float.parseFloat(imp) == mayor)
                    {

                        System.out.println(name + " tiene el importe mayor con un total de: " + imp + "\n");

                    }

                    name = br.readLine();

                }



            br.close();
            
        } catch (IOException ioe) 
        {

            System.out.print("\n[!!] Error: " + ioe.getMessage());

        }



    }

    static void opsw(int op, int opt, int opc, String opo, String[] frases, File data, persona arr[], Scanner sc){

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
                                Arrays.fill(arr, null);
                                arr = llenarArr(data, arr);
                                break;

                            case 2:

                                vislist(data);
                                break;

                            case 3:

                                do 
                                {

                                    opo = menuOrd(sc);
                                    sc.nextLine();

                                    switch (opo) 
                                    {
                                        case "N":

                                            if(arr[0] == null)
                                            {

                                                arr = llenarArr(data, arr);

                                            }

                                            arr = ordName(arr, data);
                                            devData(data, arr);
                                            System.out.print("\n[!]Fichero ordenado.....\n");
                                            break;
                                        
                                        case "I":

                                            if(arr[0] == null)
                                            {

                                                arr = llenarArr(data, arr);

                                            }

                                            arr = ordImp(arr, data);
                                            devData(data, arr);
                                            System.out.print("\n[!]Fichero ordenado.....\n");
                                            break;
                                    
                                        default:
                                            break;
                                    }
                                    
                                } while (!opo.equals("V"));
                                break;
                            
                            case 4:

                                listMayMen(data);   
                                break;

                            case 5:

                                listImpMayorMed(data);
                                break;
                        
                            default:
                                
                                System.out.print("\n[!] Volviendo al menú general....\n\n");
                                break;
                        }
                        
                    } while (opc != 6);
                    break;

                default:
                    
                    fin();
                    break;

            }
        
        } while (op != 3);


    }
    
    public static void main(String[] args) throws Exception {

        int op = 0, opt = 0, opc = 0;
        String opo = "";

        String frases[] = new String[3];

        File data = new File("/home/drew/DAM/Programcion_clase/Examen2_2ºev/data/data.txt");

        persona arr[] = new persona[cuenta(data)];
        

        Scanner sc = new Scanner(System.in);

            opsw(op, opt, opc, opo, frases, data, arr, sc);

        sc.close();

    }
}
