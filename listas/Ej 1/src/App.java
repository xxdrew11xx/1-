import java.util.*;

public class App {

    static int menu(Scanner sc){

        int opcion = 7;

        do 
        {
                
            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Altas");
            System.out.print("\n[2]---------> Bajas");
            System.out.print("\n[3]---------> Modificaciones");
            System.out.print("\n[4]---------> Consultas");
            System.out.print("\n[5]---------> Listado Razas");
            System.out.println("\n[6]---------> Fin del programa");
            System.out.println();
            System.out.print("[OPCION]----> ");
            
            try
            {

                opcion = sc.nextInt();

            }
            catch(InputMismatchException ime)
            {

                System.out.print("\n[!!] Error: " + ime.getMessage());
                opcion = 0; 
                break;

            }
            if(opcion < 1 || opcion > 6)
            {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } 
        while (opcion < 1 || opcion > 6);

        return opcion;

    }

    static List<String> altas(List<String> lista, Scanner sc){

        String raza = "";

        System.out.print("\n[+]Introduce la raza: ");
        raza = sc.nextLine();

        while(!raza.equalsIgnoreCase("fin"))
        {

            lista.add(raza);

            System.out.print("\n[+]Introduce la raza: ");
            raza = sc.nextLine();

        }


        return lista;
    }

    
    static List<String> rm(List<String> lista, Scanner sc){
    

        String raza = "";

        System.out.print("\n[+]Introduce la raza a eliminar: ");
        raza = sc.nextLine();

        while(!raza.equalsIgnoreCase("fin"))
        {

           if(lista.contains(raza))
           {

                lista.remove(raza);

                System.out.print("\n[+]Introduce la raza a eliminar: ");
                raza = sc.nextLine();

           }
           else
           {

                System.out.print("\n[!!] Esta raza no está en la lista");

           }

        }


        return lista;
    
    }

    static List<String> modificaciones(List<String> lista, Scanner sc){

        String raza = "", Newraza = "";
        int posicion = 0;

        System.out.print("\n[+] Que raza quieres modificar: ");
        raza = sc.nextLine();

        if(lista.contains(raza))
        {

            System.out.print("\n[+] Posicion a modificar: ");
            posicion = sc.nextInt();
            sc.nextLine();

            System.out.print("\n[+] Nueva raza: ");
            Newraza = sc.nextLine();

            lista.set(posicion-1, Newraza);

        }
        else
        {

            System.out.print("\n[+] La raza introduciada no está en la lista\n");

        }

        return lista;

    }

    static void listar(List<String> lista){

        Iterator<String> it = lista.iterator();

        System.out.print("\n[!] La lista es: \n");

        while(it.hasNext())
        {

            System.out.print("\n" + it.next() + " ");

        }

    }

    static void consulta(List<String> lista, Scanner sc){

        String raza = "";

        System.out.print("\n[+] Introduce un raza para consultar si está en la lista: ");
        raza = sc.nextLine();

        if(lista.contains(raza))
        {

            System.out.print("\n[!] La raza: " + raza + " está en la lista\n");

        }
        else
        {

            System.out.print("\n[!] La raza: " + raza + " no está en la lista\n");

        }

    }

    

    public static void main(String[] args) throws Exception {
        
        System.out.println();

        int op = 0;

        List<String> lista = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

            do
            {
                op = menu(sc);
                sc.nextLine();

                switch (op) {
                    case 1:

                        lista = altas(lista, sc);    
                        break;

                    case 2:

                        lista = rm(lista, sc);
                        break;

                    case 3:

                        lista = modificaciones(lista, sc);
                        break;
                
                    case 4: 

                        consulta(lista, sc);
                        break;

                    case 5:

                        listar(lista);
                        break;

                    default:
                        break;
                }

            }
            while(op != 6);

        sc.close();

    }
}
