import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Palabra {

    static int menu(Scanner e){

        int opcion = 4;

        do 
        {
                
            System.out.print("\n----------------------------MENU---------------------------- ");
            System.out.print("\n[1]---------> Introducir string en fichero");
            System.out.print("\n[2]---------> Visualizar fichero");
            System.out.print("\n[3]---------> Palabra más larga del fichero");
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

    static void writedata(Scanner e, File data){

        String texto = "";

        try
        {

            BufferedWriter bw = new BufferedWriter(new FileWriter(data, false));


                while(true)
                {
                    
                    System.out.print("\n[+] Introduce el string (* para salir): ");
                    texto = e.nextLine();


                    if(texto.equals("*"))
                    {

                        break;

                    }

                    bw.write(texto);
                    bw.newLine();

                }

            bw.close();
        
        }
        catch(IOException ioe)
        {

            System.out.print("\n[!!] ERROR --->" + ioe.getMessage() + "\n\n");

        }
        catch(InputMismatchException im)
        {

            System.out.print("\n[!!] ERROR --->" + im.getMessage() + "\n\n");

        }
    

    }

    static void readdata(File data){

        String linea = "";
       
        int contador = 0;

        try 
        {

            BufferedReader br = new BufferedReader(new FileReader(data.getCanonicalPath()));

                linea = br.readLine();
                System.out.println();

                while (linea != null)
                {   
                    contador++;
                    System.out.println("Linea " + contador + ": " + linea);
                    linea = br.readLine();
                
                }

            br.close();
                
        } 
        catch (IOException ioe) 
        {

            System.out.print("\n[!!] ERROR" + ioe.getMessage() + "\n\n");
                
        }


    }

    static void longestword(File data){

        String frase = "", word = "", longestword = "";

        int longword = 0, palabras = 0;

       

        try 
        {            
            BufferedReader br = new BufferedReader(new FileReader(data.getCanonicalPath()));

                frase = br.readLine();
                System.out.println();

                while (frase != null)
                {   

                    StringTokenizer Stexto = new StringTokenizer(frase);

                        palabras = Stexto.countTokens();

                        for(int j = 0; j < palabras ; j++)
                        {

                            word = Stexto.nextToken();

                            if(word.length() > longword)
                            {

                                longword = word.length();
                                longestword = word;


                            }
                            else if (word.length() == longword)
                            {

                                longestword = longestword + ", " + word;

                            }

                        }

                    frase = br.readLine();

                }

            for(int i = 0; i < br.lines().count() - 1; i++)
            {

                frase = br.readLine();

                StringTokenizer Stexto = new StringTokenizer(frase);

                System.out.println(Stexto.countTokens());

                for(int j = 0; j <= Stexto.countTokens() ; j++)
                {

                    word = Stexto.nextToken();

                    if(word.length() > longword)
                    {

                        longword = word.length();
                        longestword = word;


                    }
                    else if (word.length() == longword)
                    {

                        longestword = longestword + ", " + word;

                    }

                }
            }

            for(int j = 0; j < longestword.length(); j++)
            {

                if(longestword.charAt(j) == ',')
                {

                    System.out.print("\n[!] Las palabras más largas del archivo son: " + longestword + "\n\n");
                    break;

                }
                else if (j == longestword.length() - 1)
                {

                    System.out.print("\n[!] La palabra más larga del archivo es: " + longestword  + "\n\n");

                }

            }

            

        br.close();    
            
                
        } 
        catch (IOException ioe) 
        {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");
                
        }
        catch(NullPointerException npe)
        {

            System.out.println(npe.getMessage());
        }        

    }

    public static void main(String[] args) throws Exception {

        //--------------------------------Change File Directory---------------------------------------------------
        
        File data = new File("/home/h4ck3d/dam/Programcion_clase/Clase/frase_más_larga_buffered/data/data.txt");

        int opcion = 0;        

        Scanner sc = new Scanner(System.in);

            do 
            {

                opcion = menu(sc);
                sc.nextLine();

                switch (opcion) 
                {
                    case 1:
                    
                        writedata(sc, data);
                        break;
                
                    case 2:

                        readdata(data);
                        break;

                    case 3:

                        longestword(data);
                        break;
                
                    default:
                        break;
            }
        } 
        while (opcion != 4);

        sc.close();

    }
}
