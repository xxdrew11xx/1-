package clase;

import java.io.IOException;
import java.io.RandomAccessFile;

public class alumno implements Comparable<alumno> {

    private int numero = 0, nota1 = 0, nota2 = 0, nota3 = 0;
    private String nombre = "";
    private char apto = 'N';

    // ---------------------------------------------------------------------Constructores---------------------------------------------------------------------

    alumno() {
    }

    alumno(int number, String name, int mark1, int mark2, int mark3, char pass) {

        this.numero = number;
        this.nombre = NameSpace(name);
        this.nota1 = mark1;
        this.nota2 = mark2;
        this.nota3 = mark3;
        this.apto = pass;

    }

    // ---------------------------------------------------------------------GET---------------------------------------------------------------------

    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNota1() {
        return nota1;
    }

    public int getNota2() {
        return nota2;
    }

    public int getNota3() {
        return nota3;
    }

    public char getApto() {
        return apto;
    }

    // ---------------------------------------------------------------------SET---------------------------------------------------------------------

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }

    public void setNota3(int nota3) {
        this.nota3 = nota3;
    }

    public void setApto(char apto) {
        this.apto = apto;
    }

    // ---------------------------------------------------------------------COMPARABLE---------------------------------------------------------------------

    @Override
    public int compareTo(alumno o) {
        if (o.getNumero() > numero) {
            return -1;

        } else if (o.getNumero() > numero) {

            return 0;

        } else {

            return 1;

        }
    }

    // ---------------------------------------------------------------------FUNCIONES---------------------------------------------------------------------

    public static int tamaño() {

        return (40);

    }

    private String NameSpace(String name) {

        for (int i = name.length(); i < 20; i++) {

            name += " ";

        }

        return name;

    }

    public static void grabarPersonaBaja(RandomAccessFile raf) throws IOException {

        alumno aux = new alumno(0, " ", 0, 0, 0, 'N');

        raf.writeInt(aux.getNumero());
        raf.writeUTF(aux.getNombre());
        raf.writeInt(aux.getNota1());
        raf.writeInt(aux.getNota2());
        raf.writeInt(aux.getNota3());
        raf.writeChar(aux.getApto());

    }

    public static void grabarPersona(RandomAccessFile raf, alumno aux) throws Exception {

        raf.writeInt(aux.getNumero());
        raf.writeUTF(aux.getNombre());
        raf.writeInt(aux.getNota1());
        raf.writeInt(aux.getNota2());
        raf.writeInt(aux.getNota3());
        raf.writeChar(aux.getApto());

    }

    public static void leerPers4(RandomAccessFile raf) throws Exception {

        raf.readUTF();
        raf.readInt();
        raf.readInt();
        raf.readInt();
        raf.readChar();

    }

}