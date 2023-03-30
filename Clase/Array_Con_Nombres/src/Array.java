import java.util.Scanner;

public class Array {

    public static void llenar(String vn[], Scanner sc){

        //falta try catch

        for(int i = 0; i < vn.length; i++)
        {

            System.out.print("[+] Introduce un nombre: ");
            vn[i]=sc.nextLine();

        }

    }

    public static void ordenar(String vn[])
    {

        System.out.print("\n[!] El array desordenado es: " + vn[0]);

        for(int i = 1; i < vn.length; i++)
        {

            System.out.print(", " + vn[i]);

        }

        //Arrays.sort(vn);
        
        for (int i = 0; i < vn.length; i++) 
        {
            for (int j = 0; j < vn.length- 1; j++) 
            {                                                              
                if (vn[i].compareToIgnoreCase(vn[j]) < 0) // si se quiere ordenar de z -> a, cambiar el < por >
                {
                    String aux = vn[i];
                    vn[i] = vn[j];
                    vn[j] = aux;
                }
            }           
        }

        System.out.print("\n[!] El array ordenado es: " + vn[0]);

        for(int i = 1; i < vn.length; i++)
        {

            System.out.print(", " + vn[i]);

        }

    }
    
    public static void main(String[] args) throws Exception {

        System.out.println();
        
        String nombress[] = new String [10];

        Scanner sc = new Scanner(System.in);

            llenar(nombress,sc);
            ordenar(nombress);


        sc.close();

        System.out.println();

    }
}