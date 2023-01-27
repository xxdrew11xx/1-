import java.util.Scanner;
import java.io.*;

public class Persona_edad {
    public static void main(String[] args) throws Exception {
        

        String nombre = null, edad = null;

        File data = new File("/home/h4ck3d/dam/Programcion_clase/Clase/fichero_texto_persona_edad/data/data.txt");

        Scanner sc = new Scanner(System.in);

            try 
            {
                BufferedWriter bw = new BufferedWriter(new FileWriter(data.getCanonicalPath(), true));

                    System.out.print("\n[+] Introduce un string (\"Fin\" para salir): ");
                    nombre = sc.nextLine();

                    while (! nombre.equalsIgnoreCase("Fin"))
                    {

                        System.out.print("\n[+]Edad: ");
                        edad = sc.nextLine();

                        bw.write(nombre);
                        bw.newLine();
                        bw.write(edad);
                        bw.newLine();

                        System.out.print("\n[+] Introduce un string (\"Fin\" para salir:)");
                        nombre = sc.nextLine();

                    }


                bw.close();

                BufferedReader br = new BufferedReader(new FileReader(data));

                    nombre = br.readLine();

                    while (nombre != null)
                    {
                        edad = br.readLine();
                        System.out.print(nombre + " tiene ");
                        System.out.print(edad + " años.\n\n");
                        
                        nombre = br.readLine();
                    }

                br.close();    

            } 
            catch (IOException ioe) 
            {

                System.out.println(ioe.getMessage());

            }

        sc.close();

    }
}
