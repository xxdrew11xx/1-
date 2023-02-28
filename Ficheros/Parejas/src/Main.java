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
            System.out.print("\n[5]---------> Ordenar por nombre");
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
            System.out.print("\n[2]---------> Por nombre de la mascota");
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

    static int ficheroHM(File general, File hombre, File mujer, int cuenta) {

        String nombre = "", sexo = "";

        hombre.delete();
        mujer.delete();

        try {

            BufferedReader br = new BufferedReader(new FileReader(general));

            nombre = br.readLine();

            while (nombre != null) {

                sexo = br.readLine();

                if (sexo.charAt(0) == 'V') {

                    cuenta++;
                    escribirH(nombre, sexo, hombre);

                } else if (sexo.charAt(0) == 'M') {

                    cuenta--;
                    escribirM(nombre, sexo, mujer);

                }

                nombre = br.readLine();

            }

            br.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] ERROR" + ioe.getMessage() + "\n\n");

        }

        return cuenta;
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

                System.out.println(nombreM + "\t" + sexoM);

                // bw.write(nombreH);
                // bw.newLine();
                // bw.write(sexoH);
                // bw.newLine();
                // bw.write(nombreM);
                // bw.newLine();
                // bw.write(sexoM);
                // bw.newLine();

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
                sexoH = br.readLine();

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

    static void crearparejas(File hombre, File mujer, File pareja, int cuenta) {

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

            System.out.print("\n----------------------------Fichero General----------------------------");

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

    public static void main(String[] args) throws Exception {

        int opcion, option, cuenta = 0;

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

                    cuenta = ficheroHM(general, hombres, mujeres, cuenta);
                    System.out.println("[!] Ficheros creados correctamente");
                    break;

                case 3:

                    crearparejas(hombres, mujeres, parejas, cuenta);
                    System.out.println("[!] Fichero creado correctamente");
                    break;

                case 4:

                    do {
                        option = visualizar(sc);
                        sc.nextLine();

                        switch (option) {

                            case 1:

                                visualizarGeneral(general);
                                break;

                            case 2:

                                visualizarGeneral(hombres);
                                break;

                            case 3:

                                visualizarGeneral(mujeres);
                                break;

                            case 4:

                                visualizarGeneral(parejas);
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

                                break;

                            case 2:

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
