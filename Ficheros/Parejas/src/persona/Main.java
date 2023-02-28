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

    static int menuGeneral(Scanner sc) {

        int opcion = 0;

        do {

            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Crear ficheros hombre y/o mujeres");
            System.out.print("\n[2]---------> Crear fichero de hombre y mujeres");
            System.out.print("\n[3]---------> Crear fichero de parejas");
            System.out.print("\n[4]---------> Visualizar fichero");
            System.out.print("\n[5]---------> Ordenar");
            System.out.println("\n[6]---------> Exit");
            System.out.println();
            System.out.print("[OPCION]----> ");

            try {

                opcion = sc.nextInt();

            } catch (InputMismatchException ime) {

                System.out.print("\n[!!] Error: " + ime.getMessage());
                opcion = 0;
                break;

            }

            if (opcion < 1 || opcion > 6) {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } while (opcion < 1 || opcion > 6);

        return opcion;
    }

    public static int menuOrdenar(Scanner sc) {

        int opcion = 3;

        do {

            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Por nombre de la persona");
            System.out.print("\n[2]---------> Por sexo");
            System.out.println("\n[3]---------> Vovler al menú principal");
            System.out.println();
            System.out.print("[OPCION]----> ");

            try {

                opcion = sc.nextInt();

            } catch (InputMismatchException ime) {

                System.out.print("\n[!!] Error: " + ime.getMessage());
                opcion = 0;
                break;

            }

            if (opcion < 1 || opcion > 3) {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } while (opcion < 1 || opcion > 3);

        return opcion;

    }

    static void ficheroGenereal(File general, Scanner sc) {

        String nombre = "", sexo = "";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(general, true));

            do {

                System.out.print("\n[+] Introduce el nombre de la persona [ Fin para salir]: ");
                nombre = sc.nextLine();

                if (nombre.length() <= 2) {

                    System.out.print("\n[!!] Nombre invalido, introducelo de nuevo\n");

                }

            } while (nombre.length() <= 2);

            while (!nombre.equalsIgnoreCase("Fin")) {

                System.out.print("\n[+] Sexo [V -> varón || M -> Mujer]: ");
                sexo = sc.nextLine();

                bw.write(nombre);
                bw.newLine();
                bw.write(sexo.toUpperCase());
                bw.newLine();

                System.out.print("\n[+] Introduce el nombre de la persona [ Fin para salir]: ");
                nombre = sc.nextLine();

            }

            bw.close();

        } catch (InputMismatchException ime) {

            System.out.print("\n[!!] ERROR" + ime.getMessage() + "\n\n");

        } catch (IOException ioe) {

            System.out.print("\n[!!] ERROR" + ioe.getMessage() + "\n\n");

        }

    }

    static void escribirH(String nombre, String sexo, File file) {

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

            bw.write(nombre);
            bw.newLine();
            bw.write(sexo);
            bw.newLine();

            bw.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] ERROR" + ioe.getMessage() + "\n\n");

        }

    }

    static void escribirM(String nombre, String sexo, File file) {

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

            bw.write(nombre);
            bw.newLine();
            bw.write(sexo);
            bw.newLine();

            bw.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] ERROR" + ioe.getMessage() + "\n\n");

        }

    }

    static void ficheroHM(File general, File hombre, File mujer) {

        String nombre = "", sexo = "";

        hombre.delete();
        mujer.delete();

        try {

            BufferedReader br = new BufferedReader(new FileReader(general));

            nombre = br.readLine();

            while (nombre != null) {

                sexo = br.readLine();

                if (sexo.charAt(0) == 'V') {

                    escribirH(nombre, sexo, hombre);

                } else if (sexo.charAt(0) == 'M') {

                    escribirM(nombre, sexo, mujer);

                }

                nombre = br.readLine();

            }

            br.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] ERROR" + ioe.getMessage() + "\n\n");

        }

    }

    static void hombre(File hombre, File mujer, File pareja) {

        String nombreH = "", nombreM = "", sexoH = "", sexoM = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(mujer));

            BufferedReader br2 = new BufferedReader(new FileReader(hombre));

            BufferedWriter bw = new BufferedWriter(new FileWriter(pareja, true));

            nombreM = br.readLine();

            while (nombreM != null) {

                sexoM = br.readLine();
                nombreH = br2.readLine();
                sexoH = br2.readLine();

                bw.write(nombreH);
                bw.newLine();
                bw.write(sexoH);
                bw.newLine();
                bw.write(nombreM);
                bw.newLine();
                bw.write(sexoM);
                bw.newLine();

                nombreM = br.readLine();
            }

            nombreH = br2.readLine();

            while (nombreH != null) {

                sexoH = br2.readLine();

                bw.write(nombreH);
                bw.newLine();
                bw.write(sexoH);
                bw.newLine();

                nombreH = br2.readLine();

            }

            bw.close();

            br2.close();

            br.close();
        } catch (IOException ioe) {

            System.out.print("\n[!!] ERROR" + ioe.getMessage() + "\n\n");

        }

    }

    static void mujer(File hombre, File mujer, File pareja) {

        String nombreH = "", nombreM = "", sexoH = "", sexoM = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(hombre));

            BufferedReader br2 = new BufferedReader(new FileReader(mujer));

            BufferedWriter bw = new BufferedWriter(new FileWriter(pareja, true));

            nombreH = br.readLine();

            while (nombreH != null) {

                sexoH = br.readLine();
                nombreM = br2.readLine();
                sexoM = br2.readLine();

                bw.write(nombreH);
                bw.newLine();
                bw.write(sexoH);
                bw.newLine();
                bw.write(nombreM);
                bw.newLine();
                bw.write(sexoM);
                bw.newLine();

                nombreH = br.readLine();

            }

            nombreM = br2.readLine();

            while (nombreM != null) {

                sexoM = br2.readLine();

                bw.write(nombreM);
                bw.newLine();
                bw.write(sexoM);
                bw.newLine();

                nombreM = br2.readLine();

            }

            bw.close();

            br2.close();

            br.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] ERROR" + ioe.getMessage() + "\n\n");

        }

    }

    static void igual(File hombre, File mujer, File pareja) {

        String nombreH = "", nombreM = "", sexoH = "", sexoM = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(hombre));

            BufferedReader br2 = new BufferedReader(new FileReader(mujer));

            BufferedWriter bw = new BufferedWriter(new FileWriter(pareja, true));

            nombreM = br.readLine();

            while (nombreM != null) {

                sexoM = br.readLine();
                nombreM = br2.readLine();
                sexoH = br2.readLine();

                bw.write(nombreM);
                bw.newLine();
                bw.write(sexoM);
                bw.newLine();
                bw.write(nombreH);
                bw.newLine();
                bw.write(sexoH);
                bw.newLine();

                nombreH = br.readLine();

            }

            bw.close();

            br2.close();

            br.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] ERROR" + ioe.getMessage() + "\n\n");

        }

    }

    static int contar(File hombre, File mujer) {

        int cuenta = 0;

        String nombre = "";

        try {

            BufferedReader brH = new BufferedReader(new FileReader(hombre));

            nombre = brH.readLine();

            while (nombre != null) {

                brH.readLine();
                cuenta++;

                nombre = brH.readLine();

            }

            brH.close();

            BufferedReader brM = new BufferedReader(new FileReader(mujer));

            nombre = brM.readLine();

            while (nombre != null) {

                brM.readLine();
                cuenta--;

                nombre = brM.readLine();

            }

            brM.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] ERROR" + ioe.getMessage() + "\n\n");

        }

        return cuenta;

    }

    static void crearparejas(File hombre, File mujer, File pareja) {

        pareja.delete();

        int cuenta = contar(hombre, mujer);

        if (cuenta >= 1) {

            hombre(hombre, mujer, pareja);

        } else if (cuenta <= -1) {

            mujer(hombre, mujer, pareja);

        } else if (cuenta == 0) {

            igual(hombre, mujer, pareja);

        }

    }

    static int visualizar(Scanner sc) {

        int option;

        do {

            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Visualizar fichero general");
            System.out.print("\n[2]---------> Visualizar fichero hombres");
            System.out.print("\n[3]---------> Visualizar fichero mujeres");
            System.out.print("\n[4]---------> Visualizar fichero parejas");
            System.out.println("\n[5]---------> Exit");
            System.out.println();
            System.out.print("[OPCION]----> ");

            try {

                option = sc.nextInt();

            } catch (InputMismatchException ime) {

                System.out.print("\n[!!] Error: " + ime.getMessage());
                option = 0;
                break;

            }

            if (option < 1 || option > 5) {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } while (option < 1 || option > 5);

        return option;

    }

    static void visualizarGeneral(File file) {

        String nombre = "", sexo = "";

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            System.out.print(
                    "\n----------------------------Fichero General||Hombres||Mujeres----------------------------");

            nombre = br.readLine();

            while (nombre != null) {

                sexo = br.readLine();

                System.out.print("\n" + nombre + "\t" + sexo);

                nombre = br.readLine();

            }

            br.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] Error: " + ioe.getMessage());

        }

    }

    static void visualizarParejas(File file, File hombres, File mujeres) {

        String nombreH = "", sexoH = "", nombreM = "", sexoM = "";

        int cuenta = contar(hombres, mujeres);

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            System.out.print("\n----------------------------Fichero Pareajas----------------------------");

            nombreH = br.readLine();

            while (cuenta != 0) {

                sexoH = br.readLine();
                nombreM = br.readLine();
                sexoM = br.readLine();

                System.out.print("\n" + nombreH + "\t" + sexoH + "\t" + nombreM + "\t" + sexoM);

                if (cuenta <= -1) {

                    cuenta++;

                } else if (cuenta >= 1) {

                    cuenta--;

                }

                nombreH = br.readLine();

            }

            System.out.println();

            while (nombreH != null) {

                sexoH = br.readLine();

                System.out.print("\n" + nombreH + "\t" + sexoH);

                nombreH = br.readLine();

            }

            br.close();

        } catch (IOException ioe) 
        {

            System.out.print("\n[!!] Error: " + ioe.getMessage());

        }

    }

    static int total(File general){

        int cuenta = 0;
        String nombre = "";

        try 
        {
        
            BufferedReader br = new BufferedReader(new FileReader(general));

                nombre = br.readLine();

                while(nombre != null)
                {

                    br.readLine();

                    cuenta ++;

                    nombre = br.readLine();

                }

            br.close();


        } 
        catch (IOException ioe) 
        {

            System.out.print("\n[!!] Error: " + ioe.getMessage());

        }


        return cuenta;

    }

    static void ordenarNombre(File genereal){

        int cuenta = total(genereal);

        String nombre = "", sexo = "";

        persona aux;

        persona personas[] = new persona[cuenta];

        try 
        {

            BufferedReader br = new BufferedReader(new FileReader(genereal));

                for(int i = 0; i < cuenta; i++)
                {   

                    nombre = br.readLine();
                    sexo = br.readLine();

                    personas[i] = new persona(nombre, sexo);


                }

            br.close();
            
        } catch (IOException ioe)   
        {

            System.out.print("\n[!!] Error: " + ioe.getMessage());

        }

        for(int i = 0; i < cuenta; i++)
        {

            for( int j = 0; j < cuenta; j ++)
            {

                if(personas[i].getNombre().compareToIgnoreCase(personas[j].getNombre()) < 0)
                {

                    aux = personas[i];
                    personas[i] = personas[j];
                    personas[j] = aux;


                }

            }
        }

        genereal.delete();

        try
        {

            BufferedWriter bw = new BufferedWriter(new FileWriter(genereal));

                for(int i = 0; i < cuenta; i++)
                {

                    bw.write(personas[i].getNombre());
                    bw.newLine();
                    bw.write(personas[i].getSexo());
                    bw.newLine();

                }

            bw.close();

        }
        catch(IOException ioe)
        {

            System.out.print("\n[!!] Error: " + ioe.getMessage());

        }

        System.out.print("\n[!] Fichero general ordenado por nombre");


    }    

    static void ordenarSexo(File genereal){

        int cuenta = total(genereal);

        String nombre = "", sexo = "";

        persona aux;

        persona personas[] = new persona[cuenta];

        try 
        {

            BufferedReader br = new BufferedReader(new FileReader(genereal));

                for(int i = 0; i < cuenta; i++)
                {   

                    nombre = br.readLine();
                    sexo = br.readLine();

                    personas[i] = new persona(nombre, sexo);

                }

            br.close();
            
        } catch (IOException ioe)   
        {

            System.out.print("\n[!!] Error: " + ioe.getMessage());

        }

        for(int i = 0; i < cuenta; i++)
        {

            for( int j = 0; j < cuenta; j ++)
            {

                if(personas[i].getSexo().compareToIgnoreCase(personas[j].getSexo()) < 0)
                {

                    aux = personas[i];
                    personas[i] = personas[j];
                    personas[j] = aux;


                }

            }
        }

        genereal.delete();

        try
        {

            BufferedWriter bw = new BufferedWriter(new FileWriter(genereal));

                for(int i = 0; i < cuenta; i++)
                {

                    bw.write(personas[i].getNombre());
                    bw.newLine();
                    bw.write(personas[i].getSexo());
                    bw.newLine();

                }

            bw.close();

        }
        catch(IOException ioe)
        {

            System.out.print("\n[!!] Error: " + ioe.getMessage());

        }

        System.out.print("\n[!] Fichero general ordenado por sexo");

    } 


    public static void main(String[] args) throws Exception {

        int opcion, option;

        File general = new File("/home/drew/DAM/Programcion_clase/Ficheros/Parejas/file/general.txt");
        File hombres = new File("/home/drew/DAM/Programcion_clase/Ficheros/Parejas/file/hombres.txt");
        File mujeres = new File("/home/drew/DAM/Programcion_clase/Ficheros/Parejas/file/mujeres.txt");
        File parejas = new File("/home/drew/DAM/Programcion_clase/Ficheros/Parejas/file/parejas.txt");

        Scanner sc = new Scanner(System.in);

        do {
            opcion = menuGeneral(sc);
            sc.nextLine();

            switch (opcion) {

                case 1:

                    ficheroGenereal(general, sc);
                    break;

                case 2:

                    ficheroHM(general, hombres, mujeres);
                    System.out.println("[!] Ficheros creados correctamente");
                    break;

                case 3:

                    crearparejas(hombres, mujeres, parejas);
                    System.out.println("[!] Fichero creado correctamente");
                    break;

                case 4:

                    do {
                        option = visualizar(sc);
                        sc.nextLine();

                        switch (option) {

                            case 1:

                                visualizarGeneral(general);
                                System.out.println("\n");
                                break;

                            case 2:

                                visualizarGeneral(hombres);
                                System.out.println("\n");
                                break;

                            case 3:

                                visualizarGeneral(mujeres);
                                System.out.println("\n");
                                break;

                            case 4:

                                visualizarParejas(parejas, hombres, mujeres);
                                System.out.println("\n");
                                break;

                            default:

                                break;

                        }

                    } while (option != 5);

                    break;

                case 5:

                    do {

                        option = menuOrdenar(sc);
                        sc.nextLine();

                        switch (option) {
                            case 1:

                                ordenarNombre(general);
                                break;

                            case 2:

                                ordenarSexo(general);
                                break;

                            default:
                                break;
                        }

                    } while (option != 3);
                    break;

                default:

                    break;

            }

        } while (opcion != 6);

        sc.close();
    }
}
