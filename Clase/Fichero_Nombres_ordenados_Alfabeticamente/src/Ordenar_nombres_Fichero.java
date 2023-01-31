import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Ordenar_nombres_Fichero {

    static int menu(Scanner e){

        int opcion = 4;

        do 
        {
                
            System.out.print("\n----------------------------MENU---------------------------- ");
            System.out.print("\n[1]---------> Crear Fichero");
            System.out.print("\n[2]---------> Ordenar Fichero");
            System.out.print("\n[3]---------> Listar Fichero");
            System.out.println("\n[4]---------> Exit");
            System.out.println();
            System.out.print("[OPCION]----> ");
            
            try 
            {

                opcion = e.nextInt();
            
            } 
            catch (InputMismatchException ime) 
            {
            
                System.out.print("\n[!!] ERROR: " + ime.getMessage());

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

        String nombre = "";


        try 
        {

            BufferedWriter bw = new BufferedWriter(new FileWriter(data,false));

            System.out.print("\n[+] Nombre (\"Fin\" para salir): ");
            nombre = sc.nextLine();

            while (!nombre.equalsIgnoreCase("Fin"))
            {

                bw.write(nombre);
                bw.newLine();

                System.out.print("\n[+] Nombre (\"Fin\" para salir): ");
                nombre = sc.nextLine();

            }


            bw.close(); 

        } 
        catch (IOException ioe) 
        {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");
                
        }     
        
    }

    static void listado_general(File data){

        String nombre = "";

            try 
            {

                BufferedReader br = new BufferedReader(new FileReader(data));

                    System.out.print("\n\n\t\t LISTADO GENERAL");
                    System.out.print("\n\t=================================");

                    System.out.print("\n\tNombre\n");

                    nombre = br.readLine();

                    while (nombre != null)
                    {

                        System.out.print("\n\t" + nombre);

                        nombre = br.readLine();

                    }

                br.close();
                
            } 
            catch (IOException ioe) 
            {

                System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

            }

    }

    static int contarLineas(File data){

        String nombre = "";
        int lineas = 0;

        try 
        {

            BufferedReader br = new BufferedReader(new FileReader(data));

                System.out.print("\n\n\t\t LISTADO GENERAL");
                System.out.print("\n\t=================================");

                System.out.print("\n\tNombre");

                nombre = br.readLine();

                while (nombre != null)
                {

                    lineas ++;

                    nombre = br.readLine();

                }

            br.close();
            
        } 
        catch (IOException ioe) 
        {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

        }

        return lineas;

    }

    static void ordenar(File data) throws IOException{

        String nombre = "";

        int lineas = contarLineas(data), i = 0;

        String array[] = new String[lineas];

        try 
        {

            BufferedReader br = new BufferedReader(new FileReader(data));

                nombre = br.readLine();

                while (nombre != null)
                {


                    array[i] = nombre;
                    i++;

                    nombre = br.readLine();

                }

            br.close();
                
        } 
        catch (IOException ioe) 
        {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

        }

        Arrays.sort(array);        
        data.delete();

            if(!data.exists())
            {

                try 
                {

                    data.createNewFile();
                    
                } 
                catch (IOException ioe) 
                {
                    
                    System.out.print("\n[!!] ERROR: " + ioe.getMessage() + "\n\n");

                }

            }


        try 
        {

            BufferedWriter bw = new BufferedWriter(new FileWriter(data,true));

                for(int j = 0; j < lineas; j++)
                {
                    
                    bw.write(array[j]);
                    bw.newLine();

                }


            bw.close(); 

        } 
        catch (IOException ioe) 
        {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");
                
        }     

        try 
        {

            BufferedReader br = new BufferedReader(new FileReader(data));

                nombre = br.readLine();

                while (nombre != null)
                {

                    System.out.print("\n\t" + nombre);
                    nombre = br.readLine();

                }

            br.close();
                
        } 
        catch (IOException ioe) 
        {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

        }


    }


    public static void main(String[] args) throws Exception {
        
        File data = new File("/home/drew/DAM/Programcion_clase/Clase/Fichero_Nombres_ordenados_Alfabeticamente/data/data.txt");

        int opcion = 0;

        System.out.println();

        Scanner sc = new Scanner(System.in);

            do 
            {

                opcion = menu(sc);
                sc.nextLine();

                switch (opcion) 
                {
                    case 1:
                        
                        altas(sc, data);
                        break;
                    
                    case 2:

                        ordenar(data);
                        System.out.println();
                        break;
    
                    case 3:

                        listado_general(data);
                        System.out.println();
                        break;
                    
                    default:
                        break;
                }
            } 
            while (opcion != 4);

        sc.close();

    }
}