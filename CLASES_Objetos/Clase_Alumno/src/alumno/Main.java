package alumno;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        float finalmark;
        
        Alumno alumno1 = new Alumno();

        Scanner sc = new Scanner(System.in);

            System.out.print("\n[+] Introdduce el nombre del alumno: ");
            alumno1.set_Name(sc.nextLine());
            System.out.print("\n[[+] Introdduce el numero de clase: ");
            alumno1.set_NumeroClase(sc.nextInt());
            sc.nextLine();
            System.out.print("\n[[+] Introdduce la nota de la primera evaluacion: ");
            alumno1.set_PrimEv(sc.nextInt());
            System.out.print("\n[[+] Introdduce la nota de la segunda evaluacion: ");
            alumno1.set_SegEv(sc.nextInt());
            System.out.print("\n[[+] Introdduce la nota de la tercera evaluacion: ");
            alumno1.set_TerEv(sc.nextInt());

            System.out.print("\n\n--------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.print("\nAlumno\t\tNumero de clase\t\tPrimera Ev.\t\tSegunda Ev.\t\tTercera Ev.\t\tNota Media\t\tNota Final");
            
            if(alumno1.get_FinalMark() == false && (alumno1.get_AverageMark()) >= 4.5)
            {

                finalmark = 4;

            }
            else if (alumno1.get_FinalMark() == false && (alumno1.get_AverageMark()) >= 4.5)
            {

                finalmark = (alumno1.get_AverageMark());

            }
            else
            {

                finalmark = (alumno1.get_AverageMark());

            }

            if(alumno1.get_Name().length() <8)
            {

                System.out.print("\n" + alumno1.get_Name() + "\t\t" + alumno1.get_NumeroClase() + "\t\t\t" + alumno1.get_PrimEv() + "\t\t\t" + alumno1.get_SegEv() + "\t\t\t" + alumno1.get_TerEv() + "\t\t\t" + alumno1.get_AverageMark() + "\t\t\t" + Math.round(finalmark) + "\n");


            }
            else
            {

                System.out.print("\n" + alumno1.get_Name() + "\t" + alumno1.get_NumeroClase() + "\t\t\t" + alumno1.get_PrimEv() + "\t\t\t" + alumno1.get_SegEv() + "\t\t\t" + alumno1.get_TerEv() + "\t\t\t" + alumno1.get_AverageMark() + "\t\t\t" + Math.round(finalmark) + "\n");


            }


        sc.close();

    }
}
