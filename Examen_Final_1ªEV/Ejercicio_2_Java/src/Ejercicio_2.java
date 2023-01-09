import java.util.Scanner;

public class Ejercicio_2 {
    public static void main(String[] args) throws Exception {

        //
        //
        //
        //  PARA PROBAR EL PROGRAMA COMENTAR EL APARTADO C ENTERO, SINO SALEN 72 LINEAS
        //
        //
        //


        System.out.println();

        int mat[][][] = new int [12][6][4], suma = 0, media = 0, delegacion = 0;

        Scanner sc = new Scanner(System.in);

            //APARTADO A

            for(int mes = 0; mes < 12 ; mes ++)
            {

                for(int del = 0; del < 4; del ++)
                {
                    
                    for(int art = 0 ; art < 6 ; art++ )
                    {
                        //Llenado automatico del vector con valores de 1 a 10.
                        mat[mes][art][del] = (int) (Math.random()*10+1);

                        /* //Llenado por teclado

                            System.out.print("[+]Introduce el precio del articulo " + (art + 1) + " de la " + (del+1) + "ª delegación en el mes " + (mes+1) + ": ");
                            mat[mes][art][del] = sc.nextInt();

                        */

                    }

                }

            }

            //APARTADO B

            for( int del = 0; del < 4; del ++)
            {
                suma = 0;

                for(int mes = 0; mes < 12 ; mes ++)
                {
                    
                    for(int art = 0 ; art < 6 ; art++ )
                    {
                        
                        suma += mat[mes][art][del];

                    }

                }

                if(suma > media)
                {

                    media = suma;

                }

            }

            System.out.println();

            for( int del = 0; del < 4; del ++)
            {
                suma = 0;

                for(int mes = 0; mes < 12 ; mes ++)
                {
                    
                    for(int art = 0 ; art < 6 ; art++ )
                    {
                        
                        suma += mat[mes][art][del];

                    }

                }

                if(suma == media)
                {

                    System.out.print("[+] La delegacion " + (del + 1) + " ha tenido un total de ventas de: " + media + "\n");

                }

            }

            //APARTADO C

            System.out.println();

            for( int art = 0; art < 6; art ++)
            {
                suma = 0;

                for(int mes = 0; mes < 12; mes ++)
                {

                    for(int del = 0; del < 4; del ++)
                    {

                        suma += mat[mes][art][del];

                    }
                    
                    System.out.print("\n[+] La media del articulo " + (art + 1) + " en el mes " + (mes + 1) + " es de: " + (suma/ (float) 4) );

                }

            }

            //APARTADO D

            System.out.println("\n");
            System.out.print("[?] De que delegacion queires saber las ventas y la media de los productos: ");

           

            do 
            { 
                for(int i = 1; i < 5; i++)
                {

                    System.out.print("\n[*] " + i + " -----> Delegacion " + i);

                }

                System.out.print("\n[*] Eleccion: ");
                delegacion = sc.nextInt();

                if(delegacion < 1 || delegacion > 4)
                {

                    System.out.println("[!!] Delegacion introducida invalida.");

                }



            } 
            while ( delegacion < 1 || delegacion > 4);

            for(int art = 0; art < 6; art++)
            {

                suma = 0;

                for(int mes = 0 ; mes < 12; mes++)
                {

                    suma += mat[mes][art][delegacion-1];

                }

                System.out.print("\n[!] La delegacion " + delegacion + " tuvo un total de ventas en el articulo " + (art + 1) + " de " + suma + " unidades y una media de anual de: " + (suma/(float) 12) );

            }


        sc.close();

        System.out.println();

    }
}
