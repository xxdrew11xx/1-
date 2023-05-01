package clase;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    // variables globales

    static Keyboard k = new Keyboard();

    static File data = new File("/home/drew/DAM/Programcion_clase/Archivos Random/Ej1-LISTA/data/data.dat");
    static ArrayList<alumno> alumnos = new ArrayList<>();

    static char menu() {

        char op = 'J';

        do {

            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[A]---------> Altas");
            System.out.print("\n[B]---------> Bajas");
            System.out.print("\n[M]---------> Modificaciones");
            System.out.print("\n[C]---------> Consultas ");
            System.out.print("\n[L]---------> Listado ");
            System.out.println("\n[E]---------> Exit");
            System.out.println();
            System.out.print("[OPCION]----> ");

            try {

                op = Character.toUpperCase(k.rChar());

            } catch (IOException ioe) {

                op = 'J';
                break;

            } catch (StringIndexOutOfBoundsException siob) {

                op = 'J';

            }

        } while ("ABMCLE".indexOf(op) == -1 || op == '\n');

        return op;
    }

    static void llenarLista() throws Exception {

        int posicion = 0, numero = 0, posseek = 0;

        RandomAccessFile raf = new RandomAccessFile(data, "r");

        while ((posseek < raf.length())) {

            raf.seek(posseek);

            numero = raf.readInt();

            if (numero != 0)
                alumnos.add(new alumno(numero, raf.readUTF(), raf.readFloat(), raf.readFloat(), raf.readFloat()));

            posseek = ++posicion * alumno.tamaño();

        }

        raf.close();
    }

    static boolean compararNumero(int numero) {

        boolean exists = false;
        alumno a = new alumno();

        for (int i = 0; i < alumnos.size(); i++) {

            a = alumnos.get(i);

            if (a.getNumero() == numero) {

                exists = true;
                break;

            }

        }

        return exists;

    }

    static void nuevoAlumno(int numero) throws IOException {

        float nota1 = 0, nota2 = 0, nota3 = 0;
        String nombre = "";

        do {

            System.out.print("\n[+] Introduce el nombre del alumno (MAX 20 Caracteres): ");
            nombre = k.rString();

        } while (nombre.length() > 20);

        do {

            do {

                System.out.print("\n[+] Introduce la nota de la 1ª evaluación: ");
                nota1 = k.rFloat();

            } while (nota1 == Integer.MIN_VALUE);

        } while (nota1 < 1 || nota1 > 10);

        do {

            System.out.print("\n[+] Introduce la nota de la 2ª evaluación: ");
            nota2 = k.rFloat();

        } while (nota2 < 1 || nota2 > 10);

        do {

            System.out.print("\n[+] Introduce la nota de la 3ª evaluación: ");
            nota3 = k.rFloat();

        } while (nota3 < 1 || nota3 > 10);

        alumnos.add(new alumno(numero, nombre, nota1, nota2, nota3));

    }

    static void altas() throws Exception {
        boolean exit = false, exists = false;
        char otro = 'S', numeroc = ' ';
        int numero = 0;

        RandomAccessFile raf = new RandomAccessFile(data, "rw");

        // limpiar lista
        alumnos.clear();
        // llenar lista

        if (raf.length() != 0) {

            llenarLista();

        }

        // rellenar lista con nuevas altas

        while (!exit) {

            do {

                System.out.print("\n[+] Introduce el numero del alumno (E-> Salir): ");
                numeroc = Character.toUpperCase(k.rChar());

                if (numeroc == 'E')
                    break;

                try {

                    numero = Integer.parseInt(Character.toString(numeroc));
                    exists = compararNumero(numero);

                } catch (NumberFormatException e) {
                    numero = Integer.MIN_VALUE;
                }

                if (numero == Integer.MIN_VALUE)
                    System.out.print("[!!] Número invállido....\n");

                else if (exists)
                    System.out.print("[!!] Este número ya está registrado....\n");

            } while (exists || numero == Integer.MIN_VALUE);

            if (numeroc == 'E')
                break;

            nuevoAlumno(numero);

            do {
                System.out.print("\n[+] Introduce otro alumno (S->Si | N->No): ");
                otro = Character.toUpperCase(k.rChar());

            } while ("SN".indexOf(otro) == -1);

            if (otro == 'N') {

                exit = true;

            }

        }

        // ordenar lista
        Collections.sort(alumnos);

        // lenar fichero

        for (alumno alum : alumnos) {

            alumno.grabarPersona(raf, alum);

        }

        raf.close();

    }

    static void Listados() throws Exception {

        DecimalFormat formato = new DecimalFormat("#.##");

        int posicion = 0, numero = 0, posseek = 0, pagina = 1, linea = 0, totalAprobados = 0;
        float nota1 = 0, nota2 = 0, nota3 = 0;
        String name = "";
        char apto = ' ';

        System.out.print("\nNumero:\t\tNombre:\t\t\tNota1:\t\tNota2:\t\tNota3:\t\tApto:\t\tMedia:\t\tPágina: 1\n");
        for (int i = 0; i < 129; i++) {

            System.out.print("-");

        }

        RandomAccessFile raf = new RandomAccessFile(data, "r");

        while ((posseek < raf.length())) {

            raf.seek(posseek);
            linea++;

            numero = raf.readInt();

            if (linea % 6 == 0 && numero != 0) {
                pagina++;
                System.out.println();
                System.out.println(
                        "\nNumero:\t\tNombre:\t\t\tNota1:\t\tNota2:\t\tNota3:\t\tApto:\t\tMedia:\t\tPágina:" + pagina);
                for (int i = 0; i < 129; i++) {

                    System.out.print("-");

                }

            }

            if (numero == 0) {

                alumno.leerPers4(raf);

            } else {

                name = raf.readUTF();
                nota1 = raf.readFloat();
                nota2 = raf.readFloat();
                nota3 = raf.readFloat();
                apto = raf.readChar();

                if (name.length() > 8) {

                    if (nota1 >= 4.5 && nota2 >= 4.5 && nota3 >= 4.5) {
                        System.out.print("\n" + numero + "\t\t" + name.trim().toUpperCase().charAt(0)
                                + name.substring(1, name.length()).toLowerCase() + "\t" + nota1 + "\t\t" + nota2
                                + "\t\t" + nota3 + "\t\t" + apto + "\t\t"
                                + formato.format(((nota1 + nota2 + nota3) / (float) 3)));

                        totalAprobados++;
                    }

                    else
                        System.out.print("\n" + numero + "\t\t" + name.trim().toUpperCase().charAt(0)
                                + name.substring(1, name.length()).toLowerCase() + "\t" + nota1 + "\t\t" + nota2
                                + "\t\t" + nota3 + "\t\t" + apto + "\t\t" + "-");

                } else {

                    if (nota1 >= 4.5 && nota2 >= 4.5 && nota3 >= 4.5) {
                        System.out.print("\n" + numero + "\t\t" + name.trim().toUpperCase().charAt(0)
                                + name.substring(1, name.length()).toLowerCase() + "\t\t" + nota1 + "\t\t" + nota2
                                + "\t\t" + nota3 + "\t\t" + apto + "\t\t"
                                + formato.format(((nota1 + nota2 + nota3) / (float) 3)));

                        totalAprobados++;
                    } else
                        System.out.print("\n" + numero + "\t\t" + name.trim().toUpperCase().charAt(0)
                                + name.substring(1, name.length()).toLowerCase() + "\t" + nota1 + "\t\t" + nota2
                                + "\t\t" + nota3 + "\t\t" + apto + "\t\t" + "-");

                }

            }

            posseek = ++posicion * alumno.tamaño();

        }

        raf.close();

        System.out.print("\n\n[+] Total de aprobados listados: " + totalAprobados);

    }

    static void Bajas() throws Exception {

        boolean exit = false;
        int numero = 0;
        char otro = 'N', numeroc = ' ', confirm = ' ';

        while (!exit) {

            alumnos.clear();
            llenarLista();

            Listados();

            do {

                System.out.print("\n\n[+] Introduce el numero del alumno (E-> Salir): ");
                numeroc = Character.toUpperCase(k.rChar());

                if (numeroc == 'E')
                    break;

                numero = Integer.parseInt(Character.toString(numeroc));
                exit = compararNumero(numero);

                if (!exit)
                    System.out.print("[!!] Alumno no encontrado....\n");

            } while (!exit);

            if (numeroc == 'E')
                break;

            System.out.print("\n[?] Confirmar baja del alumno (S->Si | N->No): ");
            confirm = Character.toUpperCase(k.rChar());

            if (confirm == 'N')
                break;

            System.out.print("\n[!] Alumno borrado correctamente....\n");

            RandomAccessFile raf = new RandomAccessFile(data, "rws");

            raf.seek(Pos(numero) * alumno.tamaño());

            alumno.grabarPersonaBaja(raf);

            raf.close();

            do {

                System.out.print("\n[+] Dar de baja a otro alumno (S->Si | N->No): ");
                otro = Character.toUpperCase(k.rChar());

            } while ("SN".indexOf(otro) == -1);

            if (otro == 'N')
                exit = true;
        }

    }

    static int Pos(int numero) throws Exception {

        int posicion = 0, posseek = 0, nraf = 0;

        RandomAccessFile raf = new RandomAccessFile(data, "r");

        while (posseek < raf.length()) {

            raf.seek(posseek);

            nraf = raf.readInt();
            alumno.leerPers4(raf);

            if (numero == nraf)
                break;

            posseek = ++posicion * alumno.tamaño();

        }

        raf.close();

        return posicion;

    }

    static void Consultas() throws Exception {

        int posicion = 0, numero = 0, posseek = 0, numbusc = 0;
        char numc = ' ';
        boolean exists = true;

        while (true) {

            do {

                System.out.print("\n[+] Número del alumno a consultar (E-> Salir): ");
                numc = Character.toUpperCase(k.rChar());

                if (numc == 'E')
                    break;

                numbusc = Integer.parseInt(Character.toString(numc));

                exists = notexist(numbusc);

                if (!exists)
                    System.out.print("[!!] Alumno no encontrado....\n");

            } while (numbusc == 0 || !exists);

            if (numc == 'E')
                break;

            try {
                RandomAccessFile raf = new RandomAccessFile(data, "r");

                while ((posseek < raf.length())) {

                    raf.seek(posseek);

                    numero = raf.readInt();

                    if (numero == numbusc) {

                        System.out.print(
                                "[!] El alumno con nombre: " + raf.readUTF().trim() + " y número: " + numbusc
                                        + " está en el archivo....\n");
                        break;

                    }

                    raf.readFloat();
                    raf.readFloat();
                    raf.readFloat();
                    raf.readChar();

                    posseek = ++posicion * alumno.tamaño();

                }

                raf.close();

            } catch (FileNotFoundException fnf) {

            } catch (EOFException e) {

            } catch (IOException ioe) {

            }
        }

    }

    static alumno pregDatos(int numant, alumno alum) throws Exception {

        alumno aux = new alumno();

        boolean exit = false;
        int numero = 0;
        float nota1 = 0, nota2 = 0, nota3 = 0;
        String nombre = "";
        char op = ' ';

        while (!exit) {

            System.out.println(
                    "\nNumero:\t\tNombre:\t\t\tNota1:\t\tNota2:\t\tNota3:\t\tApto:\t\tPágina:");
            for (int i = 0; i < 113; i++) {

                System.out.print("-");

            }
            if (alum.getNombre().length() > 8) {

                System.out.print("\n" + alum.getNumero() + "\t\t" + alum.getNombre().trim().toUpperCase().charAt(0)
                        + alum.getNombre().substring(1, alum.getNombre().length()).toLowerCase() + "\t"
                        + alum.getNota1() + "\t\t" + alum.getNota2() + "\t\t" + alum.getNota3() + "\t\t"
                        + alum.getApto());

            } else {

                System.out.print("\n" + alum.getNumero() + "\t\t" + alum.getNombre().toUpperCase().charAt(0)
                        + alum.getNombre().substring(1, alum.getNombre().length()).toLowerCase().trim() + "\t\t"
                        + alum.getNota1() + "\t\t" + alum.getNota2() + "\t\t" + alum.getNota3() + "\t\t"
                        + alum.getApto());

            }

            do {
                System.out.print("\n\n----------------------------MENÚ---------------------------- ");
                System.out.print("\n[N]---------> Número");
                System.out.print("\n[U]---------> Nombre alumno");
                System.out.print("\n[1]---------> Nota 1ª Evaluación");
                System.out.print("\n[2]---------> Nota 2ª Evaluación");
                System.out.print("\n[3]---------> Nota 3ª Evaluación");
                System.out.print("\n[T]---------> Cambiar todo");
                System.out.println("\n[E]---------> Exit");
                System.out.println();
                System.out.print("[OPCION]----> ");
                op = Character.toUpperCase(k.rChar());

            } while ("NU123TE".indexOf(op) == -1);

            switch (op) {

                case 'N':

                    do {

                        System.out.print("\n[+] Introduce el nuevo numero del alumno: ");
                        numero = k.rInt();

                        if (numant == numero)
                            break;

                    } while ((compararNumero(numero)) || numero == Integer.MIN_VALUE);

                    aux = new alumno(numero, alum.getNombre(), alum.getNota1(), alum.getNota2(), alum.getNota3());

                    break;

                case 'U':

                    do {

                        System.out.print("\n[+] Introduce el nuevo nombre del alumno (MAX 20 Caracteres): ");
                        nombre = k.rString();

                    } while (nombre.length() > 20);

                    aux = new alumno(alum.getNumero(), nombre, alum.getNota1(), alum.getNota2(), alum.getNota3());
                    break;

                case '1':

                    do {

                        do {

                            System.out.print("\n[+] Introduce la nueva nota de la 1ª evaluación: ");
                            nota1 = k.rFloat();

                        } while (nota1 == Float.MIN_VALUE);

                    } while (nota1 < 1 || nota1 > 10);

                    aux = new alumno(alum.getNumero(), alum.getNombre(), nota1, alum.getNota2(), alum.getNota3());
                    break;

                case '2':

                    do {

                        System.out.print("\n[+] Introduce la nueva nota de la 2ª evaluación: ");
                        nota2 = k.rFloat();

                    } while (nota2 < 1 || nota2 > 10 || nota2 == Float.MIN_VALUE);

                    aux = new alumno(alum.getNumero(), alum.getNombre(), alum.getNota1(), nota2, alum.getNota3());
                    break;

                case '3':

                    do {

                        System.out.print("\n[+] Introduce la nueva nota de la 3ª evaluación: ");
                        nota3 = k.rFloat();

                    } while (nota3 < 1 || nota3 > 10 || nota3 == Float.MIN_VALUE);

                    aux = new alumno(alum.getNumero(), alum.getNombre(), alum.getNota1(), alum.getNota2(), nota3);
                    break;

                case 'T':

                    aux = todo(numant);
                    break;

                default:

                    exit = true;
                    break;
            }

            alum = aux;

        }

        return alum;

    }

    static alumno todo(int numant) throws Exception {

        alumno aux = new alumno();

        int numero = 0;
        float nota1 = 0, nota2 = 0, nota3 = 0;
        String nombre = "";

        do {

            System.out.print("\n[+] Introduce el numero del alumno: ");
            numero = k.rInt();

            if (numant == numero)
                break;

        } while ((compararNumero(numero)));

        do {

            System.out.print("\n[+] Introduce el nombre del alumno (MAX 20 Caracteres): ");
            nombre = k.rString();

        } while (nombre.length() > 20);

        do {

            do {

                System.out.print("\n[+] Introduce la nota de la 1ª evaluación: ");
                nota1 = k.rFloat();

            } while (nota1 == Float.MIN_VALUE);

        } while (nota1 < 1 || nota1 > 10);

        do {

            do {

                System.out.print("\n[+] Introduce la nota de la 2ª evaluación: ");
                nota1 = k.rFloat();

            } while (nota1 == Float.MIN_VALUE);

        } while (nota2 < 1 || nota2 > 10);

        do {

            do {

                System.out.print("\n[+] Introduce la nota de la 3ª evaluación: ");
                nota1 = k.rFloat();

            } while (nota1 == Float.MIN_VALUE);

        } while (nota3 < 1 || nota3 > 10);

        aux = new alumno(numero, nombre, nota1, nota2, nota3);

        return aux;

    }

    static boolean notexist(int numbusc) {

        boolean exists = false;

        for (alumno a : alumnos) {

            if (a.getNumero() == numbusc) {

                exists = true;
                break;

            }

        }

        return exists;

    }

    static void Modificaciones() throws Exception {

        ArrayList<alumno> actualizar = new ArrayList<>();

        int numbusc = 0, poscion = 0, posseek = 0;
        boolean exit = false;
        char numc = ' ';

        while (!exit) {

            alumnos.clear();
            actualizar.clear();

            llenarLista();

            Listados();

            do {

                System.out.print("\n\n[+] Número del alumno a modificar (E-> Salir): ");
                numc = Character.toUpperCase(k.rChar());

                if (numc == 'E')
                    break;
                numbusc = Integer.parseInt(Character.toString(numc));

            } while (numbusc == Integer.MIN_VALUE || numbusc == 0 || !notexist(numbusc));

            if (numc == 'E')
                break;

            alumno aux = new alumno(0, " ", 0, 0, 0);

            for (alumno p : alumnos) {

                if (p.getNumero() != numbusc)
                    actualizar.add(p);
                else {
                    aux = pregDatos(numbusc, p);

                    if (aux.getNumero() == 0) {

                        aux = p;

                    }

                }

            }

            actualizar.add(aux);

            Collections.sort(actualizar);

            RandomAccessFile raf = new RandomAccessFile(data, "rw");

            for (alumno alum : actualizar) {

                raf.seek(posseek);

                alumno.grabarPersona(raf, alum);

                posseek = ++poscion * alumno.tamaño();
            }

            raf.close();

            posseek = 0;
            poscion = 0;

        }

    }

    public static void main(String[] args) throws Exception {

        if (!data.exists()) {

            data.createNewFile();

        }

        char op = ' ';
        boolean exit = false;

        while (!exit) {

            op = menu();

            switch (op) {
                case 'A':

                    altas();
                    break;

                case 'B':

                    Bajas();
                    System.out.println();
                    break;

                case 'M':

                    Modificaciones();
                    System.out.println();
                    break;

                case 'C':

                    Consultas();
                    System.out.println();
                    break;

                case 'L':

                    Listados();
                    System.out.println();
                    break;

                default:

                    exit = true;
                    break;
            }

        }

    }

}
