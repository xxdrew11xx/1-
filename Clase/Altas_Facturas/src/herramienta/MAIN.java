package herramienta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MAIN {

    static int menu(Scanner e){

        int opcion = 4;

        do 
        {
                
            System.out.print("\n----------------------------MENU---------------------------- ");
            System.out.print("\n[1]---------> Altas");
            System.out.print("\n[2]---------> Factura");
            System.out.print("\n[3]---------> Modificaciones");
            System.out.print("\n[4]---------> Eliminar Fichero");
            System.out.println("\n[5]---------> Exit");
            System.out.println();
            System.out.print("[OPCION]----> ");
            
            try 
            {

                opcion = e.nextInt();
            
            } 
            catch (InputMismatchException ime) 
            {
            
                System.out.print("\n[!!] ERROR: " + ime.getMessage() + "\n\n");
                opcion = 5;
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

        String codigoProc = "", denominacion = "", precio = "", unidades = "", tipos = "";
 
        try 
        {

            BufferedWriter bw = new BufferedWriter(new FileWriter(data,true));

            do 
            {

                System.out.print("\n[+] Coidgo del Procucto [ MAX 6 caracteres] (\"Fin\" para salir): ");
                codigoProc = sc.nextLine();
                
            } 
            while (codigoProc.length() > 6);

            while (!codigoProc.equalsIgnoreCase("Fin"))
            {

                bw.write(codigoProc);
                bw.newLine();

                do 
                {
                    System.out.print("\n[+] Denominacion [ MAX 15 Caracteres]: ");
                    denominacion = sc.nextLine();

                } while (denominacion.length() > 15);

                bw.write(denominacion);
                bw.newLine();

                do 
                {

                    System.out.print("\n[+] Tipos (A | B | C): ");
                    tipos = sc.nextLine();
                    tipos.toUpperCase();
                    

                } while ( tipos == "A" || tipos == "B" || tipos ==  "C");

                bw.write(tipos);
                bw.newLine();

                System.out.print("\n[+] Precio (EJ: 10.10): ");
                
                while (precio == "");
                {
                
                    try
                    {

                        precio = sc.nextLine();

                    }
                    catch(NumberFormatException nme)
                    {

                        System.out.print("\n[!!] Error: " + nme.getMessage() + "\n\n");
                        precio = "";
                        break;

                    }

                    System.out.print("\n[+] Precio (EJ: 10.10): ");

                } 

                bw.write(precio);
                bw.newLine();

                System.out.print("\n[+] Unidades: ");
                unidades = sc.nextLine();
                bw.write(unidades);
                bw.newLine();


                do 
                {

                    System.out.print("\n[+] Coidgo del Procucto [6 caracteres] (\"Fin\" para salir): ");
                    codigoProc = sc.nextLine();

                    if(codigoProc.equalsIgnoreCase("Fin"))
                    {

                        break;

                    }
                
                } 
                while (codigoProc.length() < 6);
            }


            bw.close(); 

        } 
        catch (IOException ioe) 
        {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");
                
        }     
        
    }

    static void filedel(File data){

        data.delete();

        System.out.print("\n[!] File deleted successfully\n\n");

    }

    static void factura(File data){

        String codigo = "", denominacion = "", tipo = "", unidades = "", precio = "";

        float iva = 0, total = 0, totalfac = 0;


        System.out.print("\nCodigo:\t\tDenominacion:\t\tTipo:\t\tUnidades\tPrecio:\t\tIva:\t\tTotal:");
        System.out.print("\n--------------------------------------------------------------------------------------------------------------");
        
        try
        {

            BufferedReader br = new BufferedReader(new FileReader(data));

            codigo = br.readLine();

            while(codigo != null)
            {
                denominacion = br.readLine();
                tipo = br.readLine();
                precio = br.readLine();
                unidades = br.readLine();

                switch(tipo)
                {

                    case "A":

                        iva = (float) 21/100 * Float.parseFloat(precio); 
                        break;
                    
                    case "B":
                        
                        iva = (float) 10/100 * Float.parseFloat(precio);
                        break;
                    
                    case "C": 
                        
                        iva = (float) 2/100 * Float.parseFloat(precio);
                        break;  

                    default:

                        break;

                }

                String numcadena = Float.toString(iva);

                total = Float.parseFloat(unidades) * Float.parseFloat(precio) + iva;

                if(denominacion.length() < 8)
                {

                    System.out.print("\n" + codigo + "\t\t" + denominacion + "\t\t\t" + tipo + "\t\t" + unidades + "\t\t" + precio + "\t\t" + iva + "\t\t" + total );

                }
                else if(denominacion.length() >= 8 && numcadena.length() < 8)
                {

                    System.out.print("\n" + codigo + "\t\t" + denominacion + "\t\t" + tipo + "\t\t" + unidades + "\t\t" + precio + "\t\t" + iva + "\t\t" + total );

                }
                else
                {

                    System.out.print("\n" + codigo + "\t\t" + denominacion + "\t\t" + tipo + "\t\t" + unidades + "\t\t" + precio + "\t\t" + iva + "\t" + total );

                }   

                
                totalfac += total;
                total = 0;

                codigo = br.readLine();


            }

            br.close();

            System.out.println("\n\n--------------------------------------------------------------------------------------------------------------");
            System.out.print("Total de la factura:\t\t\t\t\t\t\t\t\t\t\t" + totalfac);


        }
        catch (IOException ioe)
        {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

        }



    }

    public static void main(String[] args) throws Exception {

        
        
        File data = new File("/home/drew/DAM/Programcion_clase/Clase/Altas_Facturas/data/data.txt");

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

                        factura(data);
                        System.out.println("\n");
                        break;
    
                    case 3:

                        filedel(data);
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
