import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class archivo_buffered {
    public static void main(String[] args) throws Exception {
        
        File data = new File("data.txt");
        String texto = "", linea = "";
        int contador = 0;

        System.out.println();

        Scanner sc = new Scanner(System.in);


        //--------------------------------------------------------------------------------------------------ESCRIBIR--------------------------------------------------------------------------------------------------
            try 
            {

                FileWriter fw = new FileWriter(data.getAbsolutePath(), false);
                    
                    BufferedWriter bw = new BufferedWriter(fw);

                        System.out.print("\n[+] Introduce un string: ");
                        texto = sc.nextLine();
                        
                        while(!texto.equals("-"))
                        {

                            bw.write(texto);
                            bw.newLine();
                            texto = sc.nextLine();

                        }
                   
                        
                    bw.close();

                fw.close();        
                
            } 
            catch (IOException ioe) 
            {
                
                System.out.print("\n[!!] ERROR" + ioe.getMessage() + "\n\n");
                
            }


            //--------------------------------------------------------------------------------------------------LECTURA--------------------------------------------------------------------------------------------------

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

        sc.close();

    }
}
