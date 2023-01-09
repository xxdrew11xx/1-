import java.util.Arrays;
import java.util.Scanner;

public class ordenar_instantaneo {
    public static void main(String[] args) throws Exception {

        System.out.println("");
        
        int a[] = new int [11];

        Scanner numtoarray = new Scanner(System.in);

            for(int i = 0; i < a.length; i++)
            {

                System.out.print("[+] Introduce el numero de la posicion " + (i+1) + ": ");
                a[i] = numtoarray.nextInt();

             }

        numtoarray.close();

        Arrays.sort(a);

        System.out.print("\n\n[+] El array es: " + a[0]);

        for (int i = 1; i < a.length; i++)
        {
                System.out.print(", " + a[i]);

        }

        System.out.println("");

    }
}
