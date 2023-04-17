import java.io.*;
import java.util.*;

public class Main {

    static int menu(Scanner sc){

        int opcion = 7;

        do 
        {
                
            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Altas");
            System.out.print("\n[2]---------> Listar");
            System.out.print("\n[3]---------> Crear fichero segun inicial");
            System.out.print("\n[4]---------> Listar fichero segun inicial");
            System.out.println("\n[5]---------> Fin del programa");
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
            if(opcion < 1 || opcion > 5)
            {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } 
        while (opcion < 1 || opcion > 5);

        return opcion;

    }

    static void altas(File data, Scanner sc){


        String name = "";
        int edad = 0;

        try 
        {

            DataOutputStream dout = new DataOutputStream(new FileOutputStream(data));


                System.out.print("\n[+] Introduce el nombre (fin para salir): ");
                name = sc.nextLine();
                name = name.toLowerCase();

                while(!name.equalsIgnoreCase("fin"))
                {

                    System.out.print("[+] Introduce la edad: ");
                    edad = sc.nextInt();
                    sc.nextLine();

                    dout.writeUTF(name);
                    dout.writeInt(edad);

                    System.out.print("\n[+] Introduce el nombre (fin para salir): ");
                    name = sc.nextLine();
                    name = name.toLowerCase();


                }


            dout.close();

            
        } catch (IOException ioe) 
        {
        
            System.out.print("\n[!!] Error: " + ioe.getLocalizedMessage() + "\n");
            
        }

    }

    static void espacios(String name, int edad){

        System.out.print(name);

        for(int i = name.length(); i <= 26; i++)
        {

            System.out.print(" ");

        }

        System.out.println(edad);

    }

    static void listar(File data){

        
        String name = "";
        int edad = 0;
        boolean fin = false;

        System.out.print("\nNombre:\t\t\t  Edad:\n\n");

        try 
        {
            
            DataInputStream dinp = new DataInputStream(new FileInputStream(data));
            
                name = dinp.readUTF();

                while(!fin)
                {

                 try 
                    {

                        edad = dinp.readInt();
                        
                        espacios(name, edad);

                        name = dinp.readUTF();
                    
                    } catch (EOFException eof)
                    {
                    
                        fin = true;
                    
                    }

                }

            dinp.close();             
        
        } catch (IOException ioe) 
        {
            

            System.out.print("\n[!!] Error: " + ioe.getLocalizedMessage() + "\n");
            
        }
        

    }

    static void fichINI(File data, File data2, Scanner sc){

        boolean fin = false;
        String name = "", letra = "";
        int edad;

        System.out.print("\n[+] Introduce la inicial: ");
        letra = sc.next();

        try 
        {

            DataInputStream dinp = new DataInputStream(new FileInputStream(data));

                DataOutputStream dout = new DataOutputStream(new FileOutputStream(data2));

                name = dinp.readUTF(); 

                while(!fin)
                {
                    try 
                    {
                        edad = dinp.readInt();

                        if(name.charAt(0) == letra.charAt(0))
                        {

                            dout.writeUTF(name);
                            dout.writeInt(edad);

                            name = dinp.readUTF(); 

                        }

                            
                        } catch (EOFException eof) 
                        {
                            
                            fin = true;
                            
                        }
                    }
                        
                dout.close();

            dinp.close();
            
        } catch (IOException ioe) 
        {
        
            System.out.print("\n[!!] Error: " + ioe.getLocalizedMessage() + "\n");
        
        }


    }

    public static void main(String[] args) throws Exception {

        System.out.println();

        File data = new File("/home/drew/DAM/Programcion_clase/Archivos binarios/Ej ejemplo/data/data.dat");
        File data2 = new File("/home/drew/DAM/Programcion_clase/Archivos binarios/Ej ejemplo/data/data2.dat");

        int op = 0;
        
        Scanner sc = new Scanner(System.in);

        do
        {
            op = menu(sc);
            sc.nextLine();

            switch (op) {
                case 1:

                    altas(data, sc);
                    break;

                case 2:

                   listar(data);
                    break;

                case 3:

                    if(!data2.exists())
                    {

                        data2.createNewFile();

                    }
                    fichINI(data, data2, sc);
                    break;
            
                case 4: 

                    listar(data2);
                    break;

                default:
                    break;
            }

        }
        while(op != 5);

    sc.close();

    }
}
