import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        int array[] = new int [10], arrayinverso[] = new int [10], num;

        System.out.println();

        Scanner sc = new Scanner(System.in);

            for(int i = 0; i < 10; i++)
            {

                do 
                {
                
                    System.out.print("\n[+]Introduce un numero entre 25-75 para la posicion " + (i+1) +": ");
                    num = sc.nextInt();

                    if(num <= 75 && num >= 25)
                    {

                        array[i] = num;
                        arrayinverso [(arrayinverso.length-1)-i] = num;

                    }
                    else
                    {

                        System.out.print("[!!]Numero introducido invalido, vuelve a intentarlo.\n");

                    }

                } 
                while (num < 25 || num > 75);

            }

        sc.close();

        System.out.print("\n[!]El array 1 es: " + array[0]);

        for(int i = 1; i < 10; i ++)
        {

            System.out.print(", " + array[i]);

        }

        System.out.print("\n[!]El array 2 es: " + array[9]);

        for(int i = 8; i >= 0; i --)
        {

            System.out.print(", " + array[i]);

        }
        
    }
}

