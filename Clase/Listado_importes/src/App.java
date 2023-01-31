import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class App {

    static int menu(Scanner e){

        int opcion = 5;

        do 
        {
                
            System.out.print("\n----------------------------MENU---------------------------- ");
            System.out.print("\n[1]---------> Altas");
            System.out.print("\n[2]---------> Listado general");
            System.out.print("\n[3]---------> Listado importes por encima de la media");
            System.out.print("\n[4]---------> Personas con mayor y menor importe");
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

    static void altas(Scanner sc, File data){

        String nombre = "", cantidad = "";   
        
        boolean verif = true;


        try 
        {

            BufferedWriter bw = new BufferedWriter(new FileWriter(data,true));

            System.out.print("\n[+] Nombre (\"Fin\" para salir): ");
            nombre = sc.nextLine();

            while (!nombre.equalsIgnoreCase("Fin"))
            {

                do 
                {

                    try
                    {
                        
                        System.out.print("\n[+] Cantidad: ");
                        cantidad = sc.nextLine();

                        Double.parseDouble(cantidad);
                        verif = true;

                    }
                    catch(NumberFormatException nmf)
                    {

                        System.out.print("\n[!!] ERROR " + nmf.getLocalizedMessage() + "\n\n");
                        verif = false;

                    }
                    
                } 
                while (verif != true);

                bw.write(nombre);
                bw.newLine();
                bw.write(cantidad);
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

    static int listado_general(File data){

        String nombre = "", cantidad = "";

        int media = 0, personas = 0;

            try 
            {

                BufferedReader br = new BufferedReader(new FileReader(data));

                    System.out.print("\n\n\t\t LISTADO GENERAL");
                    System.out.print("\n\t=================================");

                    System.out.print("\n\tNombre\t\t\tCantidad\n");

                    nombre = br.readLine();

                    while (nombre != null)
                    {

                        cantidad =  br.readLine();
                        media += Integer.parseInt(cantidad);
                        personas ++;

                        System.out.print("\n\t" + nombre + "\t\t\t" + cantidad);

                        nombre = br.readLine();

                    }

                br.close();
                
            } 
            catch (IOException ioe) 
            {

                System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

            }

            System.out.print("\n\n[!] Total importes: " + media + "");
            media = (media/personas);

            System.out.print("\n[!] Media: " + media + "\n\n");

        return media;
    }

    static float Cant_med(File data){

        String nombre = "", cantidad = "";

        int media = 0, personas = 0;

            try 
            {

                BufferedReader br = new BufferedReader(new FileReader(data));

                    nombre = br.readLine();

                    while (nombre != null)
                    {

                        cantidad =  br.readLine();

                        media += Float.parseFloat(cantidad);
                        personas ++;

                        nombre = br.readLine();

                    }

                br.close();
                
            } 
            catch (IOException ioe) 
            {

                System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

            }

            media = (media/personas);

            System.out.print("\n\n[!] Media: " + media + "\n\n");

        return media;

    }

    static void listado_mayor_media(float media, File data){

        String nombre = "", cantidad = "";

            try 
            {

                BufferedReader br = new BufferedReader(new FileReader(data));

                    System.out.print("\n\n\t\t LISTADO CON CANTIDAD > MEDIA");
                    System.out.print("\n\t==============================================");

                    System.out.print("\n\tNombre\t\t\t\tCantidad\n");

                    nombre = br.readLine();

                    while (nombre != null)
                    {

                        cantidad =  br.readLine();                        

                        if(Integer.parseInt(cantidad) >= media)
                        {

                            System.out.print("\n\t" + nombre + "\t\t\t\t" + cantidad);

                        }

                        nombre = br.readLine();

                    }

                br.close();
                
            } 
            catch (IOException ioe) 
            {

                System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

            } 

    }

    static float may(File data){

        String nombre = "", cantidad = "";

        float mayor = 0;

        try
        {

            BufferedReader br = new BufferedReader(new FileReader(data));

                nombre = br.readLine();

                while(nombre != null)
                {

                    cantidad = br.readLine();

                    if(Float.parseFloat(cantidad) > mayor)
                    {

                        mayor = Float.parseFloat(cantidad);

                    }

                    nombre = br.readLine();

                }


            br.close();

        }
        catch(IOException ioe)
        {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

        }

        return mayor;

    }

    static float min(File data){

        String nombre = "", cantidad = "";

        float mayor = 50;

        try
        {

            BufferedReader br = new BufferedReader(new FileReader(data));

                nombre = br.readLine();

                while(nombre != null)
                {

                    cantidad = br.readLine();

                    if(Float.parseFloat(cantidad) < mayor)
                    {

                        mayor = Float.parseFloat(cantidad);

                    }

                    nombre = br.readLine();

                }


            br.close();

        }
        catch(IOException ioe)
        {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

        }

        return mayor;

    }
    
    static void pers_may_men(File data, float mayor, float menor){

        String nombre = "", cantidad = "";

        try
        {

            BufferedReader br = new BufferedReader(new FileReader(data));

                nombre = br.readLine();

                while (nombre != null)
                {

                    cantidad = br.readLine();

                    if(Integer.parseInt(cantidad) == mayor)
                    {

                        System.out.print("\n[!] " + nombre + " tiene la mayor cantidad con un total de: " + cantidad);

                    }
                    else if(Integer.parseInt(cantidad) == menor)
                    {

                        System.out.print("\n[!] " + nombre + " tiene la menor cantidad con un total de: " + cantidad);

                    }

                    nombre = br.readLine();

                }


            br.close();

        }
        catch(IOException ioe)
        {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

        }

        System.out.println("\n");


    }

    public static void main(String[] args) throws Exception {

        File data = new File("/home/drew/DAM/Programcion_clase/Clase/Listado_importes/data/data.txt");

        int opcion = 1;
        float  media = 0, may = 0, min = 0;
        
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
    
                        media = listado_general(data);
                        break;
    
                    case 3:

                        media = Cant_med(data);
                        listado_mayor_media(media, data);
                        System.out.print("\n\n");
                        break;

                    case 4:

                        may = may(data);
                        min = min (data);
                        pers_may_men(data, may, min);
                        break;   
                    
                    default:
                        break;
                }
            } 
            while (opcion != 5);

        sc.close();

        
    }
}
