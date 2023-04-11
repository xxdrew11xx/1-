import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println();

        String palabra = "";
        int posicion = 0;

        Scanner sc = new Scanner(System.in);
        
        List<String> lista = new ArrayList<>();

        System.out.print("[+] Teclea palabra (Fin para finalizar): ");
        palabra=sc.nextLine();

        while(!palabra.equalsIgnoreCase("Fin"))
        {

            lista.add(palabra);

            System.out.print("[+] Teclea palabra (Fin para finalizar): ");
            palabra=sc.nextLine();

        }

        // --------------------------------------------------------------------------Ver listas--------------------------------------------------------------------------
        System.out.print("\n[!] La lista es: " + lista);
        System.out.print("\n[!] El tamaño de la lista es: " + lista.size());

        System.out.println();


        // --------------------------------------------------------------------------Ver listas con Iteradores--------------------------------------------------------------------------

        for (int i = 0; i < lista.size(); i++) 
        {
        
            System.out.print(lista.get(i));

        }

        System.out.println();

        Iterator<String> it = lista.iterator();

        while(it.hasNext())
        {

            System.out.println(it.next() + " ");

        }

        // --------------------------------------------------------------------------Modificar lista--------------------------------------------------------------------------

        System.out.print("\n[+] Introduce una nueva palabra: ");
        palabra = sc.nextLine();

        System.out.print("\n[+] Posicion: ");
        posicion=sc.nextInt();

        lista.set(posicion, palabra);
        sc.nextLine();

        //--------------------------------------------------------------------------Borrar elemento--------------------------------------------------------------------------

        System.out.print("\n[+] Posicion del elemento a borrar: ");
        posicion = sc.nextInt();

        sc.nextLine();

        lista.remove(posicion);

        // or

        System.out.print("\n[+] Elemento a borrar: ");
        palabra = sc.nextLine();

        lista.remove(palabra);

        //--------------------------------------------------------------------------Sublistas--------------------------------------------------------------------------

            //introducir inicio de la lista

            // introducir final de la lista   (comprobar final con el size de la lista)

            //lista.sublist(inicio, fin)

        //--------------------------------------------------------------------------Buscar palabra en la lista--------------------------------------------------------------------------

            //teclear palabra  

            //lista.contains(palabra tecleada) boolean

        //--------------------------------------------------------------------------Comprobar si la lista está vacia--------------------------------------------------------------------------

            ///lista.isEmpty() boolean

        sc.close();

    }
}
