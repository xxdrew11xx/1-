import java.util.Scanner;

public class Comercio {
    public static void main(String[] args) throws Exception {

       int cliente=0, numclientes=0, caja=0;
        
    Scanner readpc = new Scanner(System.in);

        do 
        {
            int price = 0, tprice= 0, dinero = 0;
            System.out.println();
        
            do 
            {
 
                System.out.print("[+] Introduce el precio del producto, 0 para finalizar: ");
                price=readpc.nextInt();

                tprice=price+tprice;
 
            } while (price != 0); 
        
            System.out.print("\n[+] El total de la compra es: " + tprice + "€.");
    
            caja=caja+tprice;

            do 
            {
 
                System.out.print("\n[+] Dinero a introducir: ");
                dinero=readpc.nextInt();
            
                tprice=tprice-dinero;
            
                if(tprice > 0)
                {

                System.out.print("\n[+] Faltan:" + (tprice) + "€");  

                }
        
            } 
            while (tprice > 0);
        
            if(tprice < 0)
            {

                System.out.println("\n[+]La vuelta es: " + Math.abs(tprice) + "€. Gracias por su visita.");

            }
            else
            {

            System.out.println("\n[+] Gracias por su visita.");

            }
        
            do
            {
                System.out.print("\n[+] ¿Hay otro cliente? [0-no/1-si]: ");
                cliente=readpc.nextInt();

            }
            while(cliente != 0 && cliente != 1);

            numclientes=numclientes+1;

        } 
        while (cliente == 1);
   
    readpc.close();

    System.out.println("\n[+] El numero de clientes es: " + numclientes + "\n[+] La caja tiene un total de: " + caja + "\n[+] La media es: " + (caja/(float)numclientes));

    }
}

