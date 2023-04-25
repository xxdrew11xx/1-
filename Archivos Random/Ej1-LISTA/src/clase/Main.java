package clase;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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

            }

        } while ("ABMCLE".indexOf(op) == -1);

        return op;
    }

    static void llenarLista() throws Exception {

        int posicion = 0, numero = 0, posseek = 0;

        try {
            RandomAccessFile raf = new RandomAccessFile(data, "r");

            while ((posseek < raf.length())) {

                raf.seek(posseek);

                numero = raf.readInt();

                if (numero == 0) {

                    raf.readUTF();
                    raf.readInt();
                    raf.readInt();
                    raf.readInt();
                    raf.readChar();

                } else {

                    alumnos.add(new alumno(numero, raf.readUTF(), raf.readInt(), raf.readInt(), raf.readInt(),
                            raf.readChar()));

                }

                posseek = ++posicion * alumno.tamaño();

            }

            raf.close();

        } catch (FileNotFoundException fnf) {

        } catch (EOFException e) {
            // TODO: handle exception
        } catch (IOException ioe) {

        }

    }

    static boolean compararNumero(int numero) {

        boolean exists = false;

        alumno a = new alumno();

        for (int i = 0; i < alumnos.size(); i++) {

            a = alumnos.get(i);

            if (a.getNumero() == numero) {

                System.out.print("[!!] Este número ya está registrado....\n");
                exists = true;
                break;

            }

        }

        return exists;

    }

    static void nuevasAltas() throws IOException {

        int numero = 0, nota1 = 0, nota2 = 0, nota3 = 0;
        String nombre = "";
        char apto = 'N';

        do {

            System.out.print("\n[+] Introduce el numero del alumno: ");
            numero = k.rInt();

        } while (compararNumero(numero));

        do {

            System.out.print("\n[+] Introduce el nombre del alumno (MAX 20 Caracteres): ");
            nombre = k.rString();

        } while (nombre.length() > 20);

        do {

            do {

                System.out.print("\n[+] Introduce la nota de la 1ª evaluación: ");
                nota1 = k.rInt();

            } while (nota1 == Integer.MIN_VALUE);

        } while (nota1 < 1 || nota1 > 10);

        do {

            System.out.print("\n[+] Introduce la nota de la 2ª evaluación: ");
            nota2 = k.rInt();

        } while (nota2 < 1 || nota2 > 10);

        do {

            System.out.print("\n[+] Introduce la nota de la 3ª evaluación: ");
            nota3 = k.rInt();

        } while (nota3 < 1 || nota3 > 10);

        if (nota1 >= 5 && nota2 >= 5 && nota3 >= 5) {

            apto = 'S';

        }

        alumnos.add(new alumno(numero, nombre, nota1, nota2, nota3, apto));

    }

    static void altas() throws Exception {
        boolean exit = false;
        char otro = 'S';

        RandomAccessFile raf = new RandomAccessFile(data, "rw");

        // limpiar lista
        alumnos.clear();
        // llenar lista

        if (raf.length() != 0) {

            llenarLista();

        }

        // rellenar lista con nuevas altas

        while (!exit) {

            nuevasAltas();

            do {
                System.out.print("\n[+] Introudce otro alumno (S->Si | N->No): ");
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

            raf.writeInt(alum.getNumero());
            raf.writeUTF(alum.getNombre());
            raf.writeInt(alum.getNota1());
            raf.writeInt(alum.getNota2());
            raf.writeInt(alum.getNota3());
            raf.writeChar(alum.getApto());

        }

        raf.close();

    }

    static void Listados() {

        int posicion = 0, numero = 0, posseek = 0, nota1 = 0, nota2 = 0, nota3 = 0;
        String name = "";
        char apto = ' ';

        System.out.print("\nNumero:\t\tNombre:\t\t\tNota1:\t\tNota2:\t\tNota3:\t\tApto:\n");
        for (int i = 0; i < 85; i++) {

            System.out.print("-");

        }

        try {
            RandomAccessFile raf = new RandomAccessFile(data, "r");

            while ((posseek < raf.length())) {

                raf.seek(posseek);

                numero = raf.readInt();

                if (numero == 0) {

                    raf.readUTF();
                    raf.readInt();
                    raf.readInt();
                    raf.readInt();
                    raf.readChar();

                } else {

                    name = raf.readUTF();
                    nota1 = raf.readInt();
                    nota2 = raf.readInt();
                    nota3 = raf.readInt();
                    apto = raf.readChar();

                    if (name.length() > 8) {

                        System.out.print("\n" + numero + "\t\t" + name.trim().toUpperCase().charAt(0)
                                + name.substring(1, name.length()).toLowerCase() + "\t"
                                + nota1 + "\t\t" + nota2 + "\t\t" + nota3 + "\t\t" + apto);

                    } else {

                        System.out.print("\n" + numero + "\t\t" + name.toUpperCase().charAt(0)
                                + name.substring(1, name.length()).toLowerCase().trim() + "\t\t"
                                + nota1 + "\t\t" + nota2 + "\t\t" + nota3 + "\t\t" + apto);

                    }

                }

                posseek = ++posicion * alumno.tamaño();

            }

            raf.close();

        } catch (FileNotFoundException fnf) {

        } catch (EOFException e) {
            // TODO: handle exception
        } catch (IOException ioe) {

        }

    }

    static void bajas() {

        int numero = 0, posicion = 0, nraf = 0, posseek = 0;
        boolean out = false;

        alumnos.clear();

        try {

            llenarLista();

        } catch (Exception e) {
            System.out.print("\n[!!] Error: " + e.getLocalizedMessage());
        }

        Listados();
        System.out.println();

        try {

            do {

                System.out.print("\n[+] Alumno a dar de baja (Número): ");
                numero = k.rInt();

            } while (numero == Integer.MIN_VALUE || numero == 0);

        } catch (IOException ioe) {

            System.out.print("\n[!!] Error: " + ioe.getLocalizedMessage());

        }

        try {

            RandomAccessFile raf = new RandomAccessFile(data, "rw");

            while ((posseek) < raf.length()) {

                raf.seek(posseek);

                nraf = raf.readInt();
                raf.readUTF();
                raf.readInt();
                raf.readInt();
                raf.readInt();
                raf.readChar();

                if (numero == nraf) {

                    out = true;

                    raf.seek(0);

                    for (alumno p : alumnos) {

                        nraf = p.getNumero();

                        if (nraf != numero) {

                            raf.writeInt(nraf);
                            raf.writeUTF(p.getNombre());
                            raf.writeInt(p.getNota1());
                            raf.writeInt(p.getNota2());
                            raf.writeInt(p.getNota3());
                            raf.writeChar(p.getApto());

                        } else {

                            alumno.grabarPersonaBaja(raf);

                        }

                    }

                    System.out.print("\n[!] El alumno ha sido dado de baja.....\n");

                }
                posseek = ++posicion * alumno.tamaño();

            }

            if (!out) {

                System.out.print("\n[!!] No se ha encontrado a ningun alumno con ese número.....\n");

            }

            raf.close();

        } catch (FileNotFoundException fnf) {

            System.out.print("\n[!!] Error: " + fnf.getLocalizedMessage());

        } catch (EOFException eof) {

            System.out.print("\n[!!] Error: " + eof.getLocalizedMessage());

        } catch (IOException ioe) {

            System.out.print("\n[!!] Error: " + ioe.getLocalizedMessage());

        }

    }

    static void Consultas() {

        int posicion = 0, numero = 0, posseek = 0, numbusc;

        do {

            System.out.print("\n[+] Introduce el número del alumno a buscar: ");
            try {
                numbusc = k.rInt();
            } catch (IOException e) {
                numbusc = Integer.MIN_VALUE;
                break;
            }

        } while (numbusc == Integer.MIN_VALUE || numbusc == 0);

        try {
            RandomAccessFile raf = new RandomAccessFile(data, "r");

            while ((posseek < raf.length())) {

                raf.seek(posseek);

                numero = raf.readInt();

                if (numero == numbusc) {

                    System.out.print("\n[!] El alumno con el número " + numbusc + " está en el archivo.... ");
                    break;

                }

                raf.readUTF();
                raf.readInt();
                raf.readInt();
                raf.readInt();
                raf.readChar();

                posseek = ++posicion * alumno.tamaño();

            }

            raf.close();

        } catch (FileNotFoundException fnf) {

        } catch (EOFException e) {
        } catch (IOException ioe) {

        }

    }

    static alumno pregDatos(int numant) throws Exception {

        alumno aux = new alumno();

        int numero = 0, nota1 = 0, nota2 = 0, nota3 = 0;
        String nombre = "";
        char apto = 'N';

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
                nota1 = k.rInt();

            } while (nota1 == Integer.MIN_VALUE);

        } while (nota1 < 1 || nota1 > 10);

        do {

            System.out.print("\n[+] Introduce la nota de la 2ª evaluación: ");
            nota2 = k.rInt();

        } while (nota2 < 1 || nota2 > 10);

        do {

            System.out.print("\n[+] Introduce la nota de la 3ª evaluación: ");
            nota3 = k.rInt();

        } while (nota3 < 1 || nota3 > 10);

        if (nota1 >= 5 && nota2 >= 5 && nota3 >= 5) {

            apto = 'S';

        }

        aux = new alumno(numero, nombre, nota1, nota2, nota3, apto);

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

        int numbusc = 0;

        alumnos.clear();

        llenarLista();

        Listados();

        do {

            System.out.print("\n\n[+] Número del alumno a modificar: ");
            numbusc = k.rInt();

        } while (numbusc == Integer.MIN_VALUE || numbusc == 0 || !notexist(numbusc));

        alumno aux = pregDatos(numbusc);

        RandomAccessFile raf = new RandomAccessFile(data, "rw");

        raf.seek((numbusc - 1) * alumno.tamaño());

        raf.writeInt(aux.getNumero());
        raf.writeUTF(aux.getNombre());
        raf.writeInt(aux.getNota1());
        raf.writeInt(aux.getNota2());
        raf.writeInt(aux.getNota3());
        raf.writeChar(aux.getApto());

        raf.close();

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

                    bajas();
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
