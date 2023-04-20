import java.util.*;
import java.io.*;

public class Main {

    static File data = new File("/home/drew/DAM/Programcion_clase/Archivos binarios/HyM/data/data.dat");

    static int menu(Scanner sc) {

        int opcion = 0;

        do {

            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Altas");
            System.out.print("\n[2]---------> Consultas");
            System.out.print("\n[3]---------> Listados ");
            System.out.print("\n[4]---------> Borrar fichero ");
            System.out.println("\n[5]---------> Exit");
            System.out.println();
            System.out.print("[OPCION]----> ");

            try {

                opcion = sc.nextInt();
                sc.nextLine();

            } catch (InputMismatchException ime) {

                System.out.print("\n[!!] Error: " + ime.getMessage());
                opcion = 0;
                break;

            }
            if (opcion < 1 || opcion > 5) {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } while (opcion < 1 || opcion > 5);

        return opcion;

    }

    static boolean dnirepetido(String dni) {

        boolean fin = false, repetido = false;
        String DNI = "";

        try {

            DataInputStream dinp = new DataInputStream(new FileInputStream(data));

            while (!fin) {

                try {

                    dinp.readUTF();
                    dinp.readChar();
                    dinp.readInt();
                    DNI = dinp.readUTF();

                    if(dni.equalsIgnoreCase(DNI))
                    {

                        System.out.print("\n[!!] DNI REPETIDO\n");
                        repetido = true;
                        break;

                    }

                } catch (EOFException eofe) {

                    fin = true;

                }

            }

            dinp.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] Error: " + ioe.getLocalizedMessage() + "\n");

        }


        return repetido;
    }

    static void altas(Scanner sc) {

        String name = "", dni = "";
        char genero = ' ';
        int edad = 0;

        DNIValidator DNI = new DNIValidator();

        try {

            DataOutputStream dout = new DataOutputStream(new FileOutputStream(data, true));

                do
                {
                    do 
                    {

                        System.out.print("\n[+] Introduce DNI (Fin para salir): ");
                        dni = sc.nextLine();
                        if(dni.equalsIgnoreCase("fin"))
                            break;

                        dni = dni.substring(0, 8) + dni.toUpperCase().substring(8, 9);
                        DNI.setDni(dni);

                    } while (!DNI.validar());

                    if(dni.equalsIgnoreCase("fin"))
                        break;

                }while((dnirepetido(dni)));
           

                while (!dni.equalsIgnoreCase("fin")) 
                {

                    System.out.print("\n[+] Introduce el nombre: ");
                    name = sc.nextLine();
                    name = name.toUpperCase().substring(0, 1) + name.substring(1, name.length());

                    do 
                    {
                        try {

                            System.out.print("\n[+] Introduce la edad: ");
                            edad = sc.nextInt();

                        } catch (InputMismatchException ime) {

                            System.out.print("\n[!!] Error: " + ime.getLocalizedMessage() + "\n");
                            edad = -5;
                            break;

                        }

                    } while (edad < 0);

                    do 
                    {

                        System.out.print("\n[+] Introduce el género (H-> Hombre | M -> Mujer): ");
                        genero = sc.next().toUpperCase().charAt(0);

                        if (genero != 'H' && genero != 'M') {

                            System.out.print("\n[!!] Género mal especificado...");

                        }

                    } while (genero != 'H' && genero != 'M');

                    sc.nextLine();

                    dout.writeUTF(name);
                    dout.writeChar(genero);
                    dout.writeInt(edad);
                    dout.writeUTF(DNI.getDni());

                    do
                    {
                        do 
                        {
    
                            System.out.print("\n[+] Introduce DNI (Fin para salir): ");
                            dni = sc.nextLine();
                            if(dni.equalsIgnoreCase("fin"))
                                break;

                            dni = dni.substring(0, 8) + dni.toUpperCase().substring(8, 9);
                            DNI.setDni(dni);
    
                        } while (!DNI.validar());
    
                        if(dni.equalsIgnoreCase("fin"))
                            break;
 
                    }while((dnirepetido(dni)));

                }

            dout.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] Error: " + ioe.getLocalizedMessage() + "\n");

        }

    }

    static void consultas(Scanner sc) {

        DNIValidator dni = new DNIValidator();

        boolean fin = false;

        String DNI = "";

        do {

            System.out.print("\n[+] Introduce un DNI: ");
            dni.setDni(sc.nextLine());

        } while (!dni.validar());

        try {

            DataInputStream dinp = new DataInputStream(new FileInputStream(data));

            while (!fin) {

                try {

                    dinp.readUTF();
                    dinp.readChar();
                    dinp.readInt();
                    DNI = dinp.readUTF();

                    if (DNI.equalsIgnoreCase(dni.getDni())) {

                        System.out.print("\n[!] El DNI: " + dni.getDni() + " está en el archivo.\n");
                        break;

                    }

                } catch (EOFException eofe) {

                    fin = true;

                }

            }

            dinp.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] Error: " + ioe.getLocalizedMessage() + "\n");

        }

    }



    static void general() {

        String name = "", DNI = "";
        int edad = 0;
        char genero = ' ';

        boolean fin = false;

        System.out.print("\nNombre: \t\t\tGenero: \tEdad:  \tDNI:\n");
        for (int i = 0; i < 65; i++) {
            System.out.print("-");
        }


        try {

            DataInputStream dinp = new DataInputStream(new FileInputStream(data));

            while (!fin) {

                try {

                    name = dinp.readUTF();
                    genero = dinp.readChar();
                    edad = dinp.readInt();
                    DNI = dinp.readUTF();

                    System.out.print("\n"+name);
                    for(int i = 0; i < 32-name.length(); i++)
                    {

                        System.out.print(" ");

                    }
                    System.out.print(genero + "\t\t" + edad + "\t" + DNI);


                   
                } catch (EOFException eofe) {

                    fin = true;

                }

            }

            dinp.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] Error: " + ioe.getLocalizedMessage() + "\n");

        }

        System.out.println();

    }

    static void Hombres() {

        String name = "", DNI = "";
        int edad = 0;
        char genero = ' ';

        boolean fin = false;

        System.out.print("\nNombre: \t\t\tGenero: \tEdad:  \tDNI:\n");
        for (int i = 0; i < 65; i++) {
            System.out.print("-");
        }


        try {

            DataInputStream dinp = new DataInputStream(new FileInputStream(data));

            while (!fin) {

                try {

                    name = dinp.readUTF();
                    genero = dinp.readChar();
                    edad = dinp.readInt();
                    DNI = dinp.readUTF();

                    if(genero == 'H')
                    {

                        System.out.print("\n"+name);
                        for(int i = 0; i < 32-name.length(); i++)
                        {

                            System.out.print(" ");

                        }
                        System.out.print(genero + "\t\t" + edad + "\t" + DNI);

                    }


                   
                } catch (EOFException eofe) {

                    fin = true;

                }

            }

            dinp.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] Error: " + ioe.getLocalizedMessage() + "\n");

        }

        System.out.println();

    }

    static void edad(Scanner sc) {

        String name = "", DNI = "";
        int edad = 0, minima = 0, maxima = 0;
        char genero = ' ';

        boolean fin = false;

        do 
        {

            System.out.print("\n[+] Introduce la edad mínima: ");
            minima = sc.nextInt();

            System.out.print("\n[+] Introduce la edad máxima: ");
            maxima = sc.nextInt();

            
        } while (maxima < minima);

        System.out.print("\nNombre: \t\t\tGenero: \tEdad:  \tDNI:\n");
        for (int i = 0; i < 65; i++) {
            System.out.print("-");
        }


        try {

            DataInputStream dinp = new DataInputStream(new FileInputStream(data));

            while (!fin) {

                try {

                    name = dinp.readUTF();
                    genero = dinp.readChar();
                    edad = dinp.readInt();
                    DNI = dinp.readUTF();

                    if(edad < maxima && edad > minima)
                    {

                        System.out.print("\n"+name);
                        for(int i = 0; i < 32-name.length(); i++)
                        {

                            System.out.print(" ");

                        }
                        System.out.print(genero + "\t\t" + edad + "\t" + DNI);

                    }


                   
                } catch (EOFException eofe) {

                    fin = true;

                }

            }

            dinp.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] Error: " + ioe.getLocalizedMessage() + "\n");

        }

        System.out.println();

    }

    static void Mujeres() {

        String name = "", DNI = "";
        int edad = 0;
        char genero = ' ';

        boolean fin = false;

        System.out.print("\nNombre: \t\t\tGenero: \tEdad:  \tDNI:\n");
        for (int i = 0; i < 65; i++) {
            System.out.print("-");
        }


        try {

            DataInputStream dinp = new DataInputStream(new FileInputStream(data));

            while (!fin) {

                try {

                    name = dinp.readUTF();
                    genero = dinp.readChar();
                    edad = dinp.readInt();
                    DNI = dinp.readUTF();

                    if(genero == 'M')
                    {

                        System.out.print("\n"+name);
                        for(int i = 0; i < 32-name.length(); i++)
                        {

                            System.out.print(" ");

                        }
                        System.out.print(genero + "\t\t" + edad + "\t" + DNI);

                    }


                   
                } catch (EOFException eofe) {

                    fin = true;

                }

            }

            dinp.close();

        } catch (IOException ioe) {

            System.out.print("\n[!!] Error: " + ioe.getLocalizedMessage() + "\n");

        }

        System.out.println();

    }

    static void listado(Scanner sc) {

        char op = ' ';
        boolean exit = false;

        while (!exit)
        {

            do 
            {

                System.out.print("\n----------------------------MENÚ---------------------------- ");
                System.out.print("\n[G]---------> General");
                System.out.print("\n[H]---------> Hombres");
                System.out.print("\n[M]---------> Mujeres ");
                System.out.print("\n[E]---------> Entre edades ");
                System.out.println("\n[B]---------> Exit");
                System.out.println();
                System.out.print("[OPCION]----> ");

                op = sc.next().toUpperCase().charAt(0);
                sc.nextLine();

            } while ("GHMEB".indexOf(op) == -1);

            switch (op) 
            {
                case 'G':

                    general();
                    break;

                case 'H':

                    Hombres();
                    break;

                case 'M':

                    Mujeres();
                    break;

                case 'E':

                    edad(sc);
                    break;

                default:

                    exit = true;
                    break;
            }

        } 

    }

    static void borrarFich(){

        data.delete();

        try 
        {

            data.createNewFile();
            
        } catch (IOException ioe) {

            System.out.print("\n[!!] Error: " + ioe.getLocalizedMessage() + "\n");

        }
    }

    public static void main(String[] args) throws Exception {

        boolean exit = false;

        Scanner sc = new Scanner(System.in);

        while(!exit)
        {

            switch (menu(sc)) 
            {
                case 1:

                    altas(sc);
                    break;

                case 2:

                    consultas(sc);
                    break;

                case 3:

                    listado(sc);
                    break;

                case 4:

                    borrarFich();
                    System.out.print("\n[!] Fichero borrado correctamente....\n");
                    break;

                default:

                    exit = true;
                    break;
            }

        } 

        sc.close();

    }
}
