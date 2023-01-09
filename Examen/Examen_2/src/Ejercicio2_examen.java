import java.util.Scanner;

public class Ejercicio2_examen {
    public static void main(String[] args) throws Exception {
        
        int asig, clase[][] = new int [6][4];
        float notamedia = 0;

        System.out.println();

        Scanner sc = new Scanner(System.in);

            for(int i = 0; i < 6; i++)
            {

                for(int j = 0; j < 4; j++)
                {

                    System.out.print("[+]Introduce la nota de la asiganutra " + (j+1) + " del " + (i+1) + "º alumno: " );
                    clase[i][j] = sc.nextInt();
                                        
                }

            }


            System.out.print("\n[?]De que asignatura quieres saber la nota media: ");
            for(int i = 1; i < 5; i++)
            {

                System.out.print("\n[*]" + i + " -----> Asignatura " + i);

            }

            System.out.print("\n[+]Eleccion: ");
            asig = sc.nextInt();

            for(int i = 0; i < 6; i ++)
            {

                notamedia += (float) clase[i][(asig-1)];

            }

        sc.close();

        

        System.out.print("\n[!] La nota media de la clase en la asignatura " + asig + " es de : " + (notamedia/6));

        

    }
}
