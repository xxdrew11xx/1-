import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Ordenar_nombres_Fichero_Lista {

    static int menu(Scanner e) {

        int opcion = 4;

        do {

            System.out.print("\n----------------------------MENU---------------------------- ");
            System.out.print("\n[1]---------> Crear Fichero");
            System.out.print("\n[2]---------> Ordenar Fichero");
            System.out.print("\n[3]---------> Listar Fichero");
            System.out.print("\n[4]---------> Eliminar Fichero");
            System.out.println("\n[5]---------> Exit");
            System.out.println();
            System.out.print("[OPCION]----> ");

            try {

                opcion = e.nextInt();

            } catch (InputMismatchException ime) {

                System.out.print("\n[!!] ERROR: " + ime.getMessage());

            }

            if (opcion < 1 || opcion > 5) {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } while (opcion < 1 || opcion > 5);

        return opcion;
    }

    static void altas(Scanner sc, File data) {

        String nombre = "";

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(data, false));

            System.out.print("\n[+] Nombre (\"Fin\" para salir): ");
            nombre = sc.nextLine();

            while (!nombre.equalsIgnoreCase("Fin")) {

                bw.write(nombre);
                bw.newLine();

                System.out.print("\n[+] Nombre (\"Fin\" para salir): ");
                nombre = sc.nextLine();

            }

            bw.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

        }

    }

    static void listado_general(File data) {

        String nombre = "";

        try {

            BufferedReader br = new BufferedReader(new FileReader(data));

            System.out.print("\n\n\t\t LISTADO GENERAL");
            System.out.print("\n\t=================================");

            System.out.print("\n\tNombre\n");

            nombre = br.readLine();

            while (nombre != null) {

                System.out.print("\n\t" + nombre);

                nombre = br.readLine();

            }

            br.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

        }

    }

    static void ordenar(File data, Scanner sc) throws IOException {

        String nombre = "";

        List<String> lista = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(data));

            nombre = br.readLine();

            while (nombre != null) {

                lista.add(nombre);
                nombre = br.readLine();

            }

            br.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

        }

        System.out.print("\n[1] Ordenar de la A-Z ");
        System.out.println("\n[2] Ordenar de la Z-A");
        System.out.print("[+] Opcion: ");
        int op = sc.nextInt();
        sc.nextLine();

        System.out.print("\n\n\t\t LISTADO GENERAL");
        System.out.print("\n\t=================================");
        System.out.print("\n\tNombre\n");

        if (op == 1) {

            Collections.sort(lista);

        } else {

            Collections.sort(lista);
            Collections.reverse(lista);

        }
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(data, false));

            for (int j = 0; j < lista.size(); j++) {

                bw.write(lista.get(j));
                bw.newLine();

            }

            bw.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

        }

        try {

            BufferedReader br = new BufferedReader(new FileReader(data));

            nombre = br.readLine();

            while (nombre != null) {

                System.out.print("\n\t" + nombre);
                nombre = br.readLine();

            }

            br.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] ERROR " + ioe.getLocalizedMessage() + "\n\n");

        }

        lista.clear();

    }

    static void filedel(File data) {

        data.delete();

        System.out.print("\n[!] File deleted successfully\n\n");

    }

    public static void main(String[] args) throws Exception {

        File data = new File(
                "/home/drew/DAM/Programcion_clase/Clase/Fichero_Nombres_ordenados_Alfabeticamente/data/data.txt");

        int opcion = 0;

        System.out.println();

        Scanner sc = new Scanner(System.in);

        do {

            opcion = menu(sc);
            sc.nextLine();

            switch (opcion) {
                case 1:

                    altas(sc, data);
                    break;

                case 2:

                    ordenar(data, sc);
                    System.out.println();
                    break;

                case 3:

                    listado_general(data);
                    System.out.println();
                    break;

                case 4:

                    filedel(data);
                    break;

                default:
                    break;
            }
        } while (opcion != 5);

        sc.close();

    }
}
