package herramienta;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

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

            if(opcion < 1 || opcion > 5)
            {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } 
        while (opcion < 1 || opcion > 5);

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
                precio = sc.nextLine();
                
                while (precio == "")
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

    static void visfactura(File data){

        String codigo = "", denominacion = "", tipo = "", unidades = "", precio = "";

        float iva = 0, total = 0, totalfac = 0;

        DecimalFormat formato = new DecimalFormat("#.##");


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

                        iva = ((float) 21/100 * Float.parseFloat(precio)) * Float.parseFloat(unidades); 
                        break;
                    
                    case "B":
                        
                        iva = ((float) 10/100 * Float.parseFloat(precio)) * Float.parseFloat(unidades);
                        break;
                    
                    case "C": 
                        
                        iva = ((float) 2/100 * Float.parseFloat(precio)) * Float.parseFloat(unidades);
                        break;  

                    default:

                        break;

                }

                String numcadena = Float.toString(iva);

                total = Float.parseFloat(unidades) * Float.parseFloat(precio) + iva;

                if(denominacion.length() < 8)
                {

                    System.out.print("\n" + codigo + "\t\t" + denominacion + "\t\t\t" + tipo + "\t\t" + unidades + "\t\t" + precio + "\t\t" + formato.format(iva) + "\t\t" + formato.format(total) );

                }
                else if(denominacion.length() >= 8 && numcadena.length() < 8)
                {

                    System.out.print("\n" + codigo + "\t\t" + denominacion + "\t\t" + tipo + "\t\t" + unidades + "\t\t" + precio + "\t\t" + formato.format(iva) + "\t\t" + formato.format(total) );

                }
                else
                {

                    System.out.print("\n" + codigo + "\t\t" + denominacion + "\t\t" + tipo + "\t\t" + unidades + "\t\t" + precio + "\t\t" + formato.format(iva) + "\t\t" + formato.format(total) );

                }   

                
                totalfac += total;
                total = 0;

                codigo = br.readLine();


            }

            br.close();

            System.out.println("\n\n--------------------------------------------------------------------------------------------------------------");
            System.out.print("Total de la factura:\t\t\t\t\t\t\t\t\t\t\t" + formato.format(totalfac));


        }

        catch (IOException ioe)
        {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

        }



    }

    static int cuenta(File file){

        int cuenta = 0;

        String denom;

        try 
        {

            BufferedReader br = new BufferedReader(new FileReader(file));

                denom = br.readLine();

                if(denom == null)
                {

                    System.out.print("\n[!!]Factura vacia.");

                }

                while(denom != null)
                {

                    br.readLine();
                    br.readLine();
                    br.readLine();
                    br.readLine();
                    cuenta ++;

                    denom = br.readLine();

                }

            br.close();
            
        } catch (IOException ioe) 
        {
            
            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

        }


        return cuenta;
    }

    static void visobjeto(herramienta factura[], int option){


        if(factura[option].getDenominacion().length() < 8)
        {

            System.out.print("\n" + factura[option].getCodProc() + "\t\t" + factura[option].getDenominacion() + "\t\t\t" + factura[option].getTipos() + "\t\t" + factura[option].getUnidades() + "\t\t" + factura[option].getPrecio());
        }
        else if(factura[option].getDenominacion().length() >= 8 )
        {

            System.out.print("\n" + factura[option].getCodProc() + "\t\t" + factura[option].getDenominacion() + "\t\t" + factura[option].getTipos() + "\t\t" + factura[option].getUnidades() + "\t\t\t" + factura[option].getPrecio()) ;

        }
        else
        {

            System.out.print("\n" + factura[option].getCodProc() + "\t\t" + factura[option].getDenominacion() + "\t\t" + factura[option].getTipos() + "\t\t" + factura[option].getUnidades() + "\t\t" + factura[option].getPrecio());

        }   

    }

    static void modificaciones(File data, Scanner sc){

        int cuenta = cuenta(data), option = 0, campo = 0, nuevoCampoInt = 0;

        String codigo = "", denominacion = "", tipo = "", unidades = "", precio = "", opcion = "s", nuevoCampo = "", confir = "";

        boolean conf = false;

        herramienta factura[] = new herramienta[cuenta];


        try 
        {

            BufferedReader br = new BufferedReader(new FileReader(data));

                for(int i = 0; i < cuenta; i ++)
                {

                    codigo = br.readLine();
                    denominacion = br.readLine();
                    tipo = br.readLine();
                    precio = br.readLine();
                    unidades = br.readLine();
                    
                    factura[i] = new herramienta(codigo, denominacion, tipo, precio, unidades);

                }    

            br.close();
            
        } catch (IOException ioe) 
        {
            
            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

        }

        do
        {

            System.out.print("\n[?] Quieres modificar la factura (S || N): ");
            opcion = sc.nextLine();

        }while(!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N"));


        while(opcion.equalsIgnoreCase("S"))
        {

            if(opcion.equalsIgnoreCase("S"))
            {

                while(opcion.equalsIgnoreCase("S")) 
                {
                    do
                    {

                        visfactura(data);

                        System.out.print("\n[+] Qué producto quieres modificar (1- " + cuenta +") (" + (cuenta + 1) + " ---> para salir): ");
                        option = sc.nextInt();

                        if(option == (cuenta + 1) )
                        {

                            break;

                        }

                        option = option - 1;
        
                        System.out.print("\nCodigo:\t\tDenominacion:\t\tTipo:\t\tUnidades\tPrecio:");
                        System.out.print("\n-------------------------------------------------------------------------------");
        
                        visobjeto(factura, option);

                        System.out.println("\n[!] Qué campos quieres modificar");
                        System.out.print("\n----------------------------Campos---------------------------- ");
                        System.out.print("\n[1]---------> Código del producto");
                        System.out.print("\n[2]---------> Denominación");
                        System.out.print("\n[3]---------> Tipo del producto");
                        System.out.print("\n[4]---------> Unidades del producto");
                        System.out.print("\n[5]---------> Precio del producto");
                        System.out.println("\n[6]---------> Exit");
                        System.out.println();
                        System.out.print("[Opcion]----> ");

                        try 
                        {
                        
                            campo = sc.nextInt();
                            sc.nextLine();
                        
                        } catch (InputMismatchException ime) 
                        {
                            System.out.print("\n[!!] ERROR " + ime.getLocalizedMessage() + "\n\n");
                        }

                        if(campo < 1 || campo > 6)
                        {

                            System.out.print("\n[!] Campo seleccionado erroneo\n\n");

                        }
                    
                    }while (campo < 1|| campo > 6);

                    if(option == (cuenta + 1) )
                    {


                        break;

                    }


                    switch (campo) {
                        
                        case 1:
                        
                            do 
                            {

                                do 
                                {   

                                    System.out.print("\n[+] Nuevo código del producto: ");
                                    nuevoCampo = sc.nextLine();

                                }       
                                while (nuevoCampo.length() > 6);

                                System.out.print("\n[?] Confirmar cambios (S || N): ");
                                confir = sc.nextLine().toUpperCase();

                                if(confir.charAt(0) == 'S' )
                                {

                                    conf = true;

                                }
                                else 
                                {

                                    conf = false;

                                }

                            
                            } while (conf == false);

                            factura[option] = new herramienta(nuevoCampo, denominacion, tipo, precio, unidades);
                        
                            break;

                        case 2:

                        do 
                        {
                            do 
                            {

                                System.out.print("\n[+] Nueva denominación del producto: ");
                                nuevoCampo = sc.nextLine();

                            } while (nuevoCampo.length() > 15);


                            System.out.print("\n[?] Confirmar cambios (S || N): ");
                            confir = sc.nextLine().toUpperCase();

                            if(confir.charAt(0) == 'S' )
                            {

                                conf = true;

                            }
                            else 
                            {

                                conf = false;

                            }
                            
                        } while (conf == false);

                        factura[option] = new herramienta(codigo, nuevoCampo, tipo, precio, unidades);

                            break;
                    
                        case 3:

                            do 
                            {
                                do 
                                {
                                    System.out.print("\n[+] Nuevo tipo del producto (A | B | C): ");
                                    nuevoCampo = sc.nextLine();
                                    nuevoCampo.toUpperCase();
                            
                                } while ( nuevoCampo == "A" || nuevoCampo == "B" || nuevoCampo ==  "C");

                                System.out.print("\n[?] Confirmar cambios (S || N): ");
                                confir = sc.nextLine().toUpperCase();

                                if(confir.charAt(0) == 'S' )
                                {

                                    conf = true;

                                }
                                else 
                                {

                                    conf = false;

                                }
                        
                            } while (conf == false);
                            factura[option] = new herramienta(codigo, denominacion, nuevoCampo, precio, unidades);
                        
                            break;

                        case 4:

                            do 
                            {
                                do
                                {
                                    System.out.print("\n[+] Nuevas unidades del producto: ");
                                    try 
                                    {

                                        nuevoCampoInt = sc.nextInt();
                                
                                    } catch (InputMismatchException ime) 
                                    {
                                
                                        System.out.print("\n[!!] ERROR " + ime.getLocalizedMessage() + "\n\n");
                                        nuevoCampoInt = 0;
                                        break;

                                    }
                                }while(nuevoCampoInt == 0);

                                System.out.print("\n[?] Confirmar cambios (S || N): ");
                                confir = sc.nextLine().toUpperCase();

                                if(confir.charAt(0) == 'S' )
                                {

                                    conf = true;
                           
                                }
                                else 
                                {

                                    conf = false;

                                }
                        
                            } while (conf == false);

                            factura[option] = new herramienta(codigo, denominacion, tipo, precio, nuevoCampo);
                         
                            break;

                        case 5:

                            do 
                            {
                                do
                                {
                                    System.out.print("\n[+] Nuevao precio del producto: ");
                                    try 
                                    {

                                        nuevoCampoInt = sc.nextInt();
                            
                                    } catch (InputMismatchException ime) 
                                    {
                            
                                        System.out.print("\n[!!] ERROR " + ime.getLocalizedMessage() + "\n\n");
                                        nuevoCampoInt = 0;
                                        break;

                                    }

                                }while(nuevoCampoInt == 0);

                                System.out.print("\n[?] Confirmar cambios (S || N): ");
                                confir = sc.nextLine().toUpperCase();

                                if(confir.charAt(0) == 'S' )
                                {
                                    conf = true;
                                }
                                else 
                                {

                                 conf = false;

                                }
                        
                            } while (conf == false);

                            factura[option] = new herramienta(codigo, denominacion, tipo, nuevoCampo, unidades);
                        
                            break;
                
                        default:

                            opcion = "N";
                            break;
                    }    

                    data.delete();

                    try 
                    {

                        BufferedWriter bw = new BufferedWriter(new FileWriter(data));

                        for(int i = 0; i < cuenta; i ++)
                        {

                            bw.write(factura[i].getCodProc());
                            bw.newLine();
                            bw.write(factura[i].getDenominacion());
                            bw.newLine();
                            bw.write(factura[i].getTipos());
                            bw.newLine();
                            bw.write(factura[i].getPrecio());
                            bw.newLine();
                            bw.write(factura[i].getUnidades());
                            bw.newLine();

                        }

                        bw.close();
                
                    } catch (IOException ioe) 
                    {

                        System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");
                
                    }

                }

                
                if(option == (cuenta + 1))
                {

                    break;

                }

                do 
                {

                    System.out.print("\n[?] Quieres modificar la factura (S || N): ");
                    opcion = sc.nextLine().toUpperCase();
            
                } while (opcion.charAt(0) != 'S' || opcion.charAt(0) != 'N' );

            }
            else
            {

                System.out.print("\n[!] Volviendo al menú general...\n\n");
                break;

            }

            
        
        }

        if(opcion.equalsIgnoreCase("S") && option != 5)
        {

            System.out.println("\n[!] Modificaciones realizadas con exito.\n");

        }
        else 
        {

            System.out.print("\n[!] Volviendo al menú general...\n\n");

        }
    }

    public static void main(String[] args) throws Exception {

        
        
        File data = new File("/home/drew/DAM/Programcion_clase/CLASES_Objetos/Clase_Ferreteria/data/data.txt");

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

                        visfactura(data);
                        System.out.println("\n");
                        break;

                    case 3:

                        modificaciones(data, sc);
                        break;
    
                    case 4:

                        filedel(data);
                        System.out.println();
                        break;

                    default:
                        break;
                }
            } 
            while (opcion !=5);

        sc.close();

    }
}