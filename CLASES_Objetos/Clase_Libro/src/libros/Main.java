package libros;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    public static int menuGeneral(Scanner e){

        int opcion = 4;

        do 
        {
                
            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Altas");
            System.out.print("\n[2]---------> Visualizar libros");
            System.out.print("\n[3]---------> Ordenar");
            System.out.println("\n[4]---------> Exit");
            System.out.println();
            System.out.print("[OPCION]----> ");
            
            try
            {

                opcion = e.nextInt();

            }
            catch(InputMismatchException ime)
            {

                System.out.print("\n[!!] Error: " + ime.getMessage());
                opcion = 0; 
                break;

            }
            if(opcion < 1 || opcion > 4)
            {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } 
        while (opcion < 1 || opcion > 4);

        return opcion;
    }

    public static int menuOrdenar(Scanner e){

        int opcion = 4;

        do 
        {
                
            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Por titulo");
            System.out.print("\n[2]---------> Por autor");
            System.out.print("\n[3]---------> Por precio");
            System.out.println("\n[4]---------> Vovler al menú principal");
            System.out.println();
            System.out.print("[OPCION]----> ");
            
            opcion = e.nextInt();

            if(opcion < 1 || opcion > 4)
            {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } 
        while (opcion < 1 || opcion > 4);

        return opcion;
    }


    static Libros[] altas(Scanner sc, Libros book[]){

        String isbn = "", autor, genero, titulo;
        float precio;

        for(int i = 0; i < book.length; i++)
        {

            do
            {

                try 
                {
                    do
                    {

                        System.out.print("\n[+] Introduce el ISBN del libro (XXX-XXX-XX-XXXX-X): ");
                        isbn = sc.nextLine();

                    } while(isbn.charAt(3) != '-' && isbn.charAt(7) != '-' && isbn.charAt(10) != '-' && isbn.charAt(15) != '-');

                }
                catch (StringIndexOutOfBoundsException ste) 
                {
            
                    System.out.print("\n[!!] ERROR: " + ste.getMessage());
                    isbn = "XXX-XXX-XX-XXXX-X";
                    System.out.print("\n[!] ISBN introducido invalido.\n");

                }

            }
            while (isbn == "XXX-XXX-XX-XXXX-X");
            

            System.out.print("\n[+] Introduce el titulo del libro: ");
            titulo = sc.nextLine();

            System.out.print("\n[+] Introduce el autor del libro: ");
            autor = sc.nextLine();

            System.out.print("\n[+] Introduce el genero del libro: ");
            genero  = sc.nextLine();
            
            do
            {
                System.out.print("\n[+] Introduce el precio del libro: ");

                try
                {

                    precio = sc.nextFloat();

                }
                catch(InputMismatchException ime)
                {

                    System.out.print("\n[!!] ERROR: " + ime.getMessage());
                    precio = 0;
                    break;

                }

            }
            while (precio == 0);

            book[i] = new Libros(isbn, titulo, autor, genero, precio);
            sc.nextLine();

        }

        return book;

    }

    static void visualizarLibros(Libros book[]){

        for(int i = 0; i < book.length; i++)
        {
            System.out.print("\n[!] Libro " + (i+1));
            System.out.print("\n--------------------------------------------------------------------------------------------------------------------------------------------------");
        
            System.out.print("\nISBN: " + book[i].get_Isbn() + "\t\tTitulo: " + book[i].get_Titulo() + "\t\tAutor:" + book[i].get_Autor() + "\t\tGenero: " + book[i].get_Genero() + "\t\tPrecio: " + book[i].get_Precio() + "\n");

        }


    }


    public static void ordenarTitulo(Libros book[]){

        Libros aux;

        for(int i = 0; i < book.length; i++)
        {

            for(int j = 0; j < book.length; j++)
            {

                if(book[i].get_Titulo().compareToIgnoreCase(book[j].get_Titulo()) < 0)
                {

                    aux = book[i];
                    book[i] = book[j];
                    book[j] = aux;

                }

            }

        }

    }

    public static void ordenarAutor(Libros book[]){

        Libros aux;

        for(int i = 0; i < book.length; i++)
        {

            for(int j = 0; j < book.length; j++)
            {

                if(book[i].get_Autor().compareToIgnoreCase(book[j].get_Autor()) < 0)
                {

                    aux = book[i];
                    book[i] = book[j];
                    book[j] = aux;

                }

            }

        }

    }

    public static void ordenarPrecio(Libros book[]){

        Libros aux;

        for(int i = 0; i < book.length; i++)
        {

            for(int j = 0; j < book.length; j++)
            {

                if(book[i].get_Precio() < book[j].get_Precio())
                {

                    aux = book[i];
                    book[i] = book[j];
                    book[j] = aux;

                }

            }

        }

    }
    

    public static void main(String[] args) throws Exception {
        

        int opcion, option;

        Libros book[] = new Libros[2];
        
        System.out.println();

        Scanner sc = new Scanner(System.in);

            do 
            {

                opcion = menuGeneral(sc);
                sc.nextLine();

                switch (opcion) 
                {
                    case 1:
                        
                        book = altas(sc, book);
                        break;
                    
                    case 2:
    
                        visualizarLibros(book);
                        break;
    
                    case 3:
    
                        do 
                        {
                            option = menuOrdenar(sc);
                            
                            switch (option) 
                            {
                                case 1:

                                    ordenarTitulo(book);
                                    System.out.print("\n[!] Libros ordenados alfabeticamente por los titulos\n\n");
                                    break;

                                case 2:

                                    ordenarAutor(book);
                                    System.out.print("\n[!] Libros ordenados alfabeticamente por el autor\n\n");
                                    break;
                                
                                case 3:

                                    ordenarPrecio(book);
                                    System.out.print("\n[!] Libros ordenados alfabeticamente por el precio\n\n");
                                    break;
                            
                                default:
                                    break;
                            }

                        } while (option != 4);

                        break;
                    
                    default:
                        break;
                }
            } 
            while (opcion != 4);

        sc.close();


    }
}
