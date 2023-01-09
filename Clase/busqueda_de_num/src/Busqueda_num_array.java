import java.util.Scanner;

public class Busqueda_num_array {
    public static void main(String[] args) throws Exception {
        
        int a[] = new int [5], searchnum=0;

        System.out.println();

        Scanner numtoarray = new Scanner(System.in);

            for(int i = 4; i >= 0 ; i--)
            {
                System.out.print("[+] Introduce el elemento " + (i+1) + " de la lista: ");
                a[i] = numtoarray.nextInt();
            }

            System.out.print("\n[+] Introduce el numero a buscar: ");
            searchnum  =numtoarray.nextInt();

        numtoarray.close();

        for(int i = 0; i < 5; i++)
        {
            if(searchnum == a[i])
            {

                System.out.println("\n[+] El numero " + searchnum + " está en la " + (i+1) + "º posicion del array.");
                break;

            }
            else if(i==4 && searchnum != a[4])
            {

                System.out.println("\n[+] Numero no encontrado.");

            }
        }

    }
}
