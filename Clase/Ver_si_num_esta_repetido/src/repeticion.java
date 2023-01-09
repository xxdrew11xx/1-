import java.util.Scanner;

public class repeticion {
    public static void main(String[] args) throws Exception {
        
        int a[] = new int [11], num;

        System.out.println();
        
        Scanner numtoarray = new Scanner(System.in);

            for(int i = 0; i < a.length; i++)
            {

                System.out.print("[+] Introduce el numero de la posicion " + (i+1) + ": ");
                num = numtoarray.nextInt();

                for( int j = 0; j <= i; j++)
                {

                   if ( num == a[j])
                   {
                    
                        System.out.println("\n[!] Numero introducido existente.");
                        i--;
                        j=11;

                   }
                   else if ( j == 10)
                   {

                        a[i] = num;

                   }


                }

            }

        numtoarray.close();

        System.out.print("\n\n[+] El array es: " + a[0]);

        for (int i = 1; i < a.length; i++)
        {
                System.out.print(", " + a[i] + "\n");

        }

    }
}
