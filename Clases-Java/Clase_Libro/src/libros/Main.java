package libros;

import java.util.Arrays;
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
            
            opcion = e.nextInt();

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

        String isbn = "";
        float precio;

        for(int i = 0; i < book.length; i++)
        {

            System.out.print("\n[+] Introduce el ISBN del libro (XXX-XXX-XX-XXXX-X): ");
            
            while(isbn.charAt(3) != '-' && isbn.charAt(7) != '-' && isbn.charAt(10) != '-' && isbn.charAt(15) != '-')
            {


                isbn = sc.nextLine();

                if(isbn.charAt(3) != '-' && isbn.charAt(7) != '-' && isbn.charAt(10) != '-' && isbn.charAt(15) != '-')
                {

                    System.out.print("\n[+] Introduce el ISBN del libro (XXX-XXX-XX-XXXX-X): ");

                }

            }

            book[i].set_isbn(isbn);

            System.out.print("\n[+] Introduce el titulo del libro): ");
            book[i].set_titulo(sc.nextLine());

            System.out.print("\n[+] Introduce el autor del libro): ");
            book[i].set_autor(sc.nextLine());

            System.out.print("\n[+] Introduce el genero del libro): ");
            book[i].set_genero(sc.nextLine());
            
            do
            {
                System.out.print("\n[+] Introduce el precio del libro): ");

                try
                {

                    precio = sc.nextFloat();

                }
                catch(InputMismatchException ime)
                {

                    System.out.print("\n[!!] ERROR: " + ime.getMessage());
                    precio = 0;

                }

            }
            while (precio == 0);

            book[i].set_precio(precio);

        }

        return book;

    }

    static void visualizarElementos(Libros book[]){

        System.out.print("\n--------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("\nISBN:\t\t\tTitulo:\t\t\tAutor:\t\t\tGenero:\t\t\tPrecio:\n");

        for(int i = 0; i < book.length; i++)
        {
        
           System.out.print(book[i].getIsbn() + "\t\t\t" + book[i].getTitulo() + "\t\t\t" + book[i].getAutor() + "\t\t\t" + book[i].getGenero() + "\t\t\t" + book[i].getPrecio() + "\n");

        }


    }


    public static void ordenarTitulo(Libros book[]){

        Libros aux;

        for(int i = 0; i < book.length; i++)
        {

            for(int j = 0; j < book.length; j++)
            {

                if(book[i].getTitulo().compareToIgnoreCase(book[j].getTitulo()) < 0)
                {

                    aux = book[i];
                    book[i] = book[j];
                    book[j] = book[i];

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

                if(book[i].getAutor().compareToIgnoreCase(book[j].getAutor()) < 0)
                {

                    aux = book[i];
                    book[i] = book[j];
                    book[j] = book[i];

                }

            }

        }

    }

    public static void ordenarGenero(Libros book[]){

        Libros aux;

        for(int i = 0; i < book.length; i++)
        {

            for(int j = 0; j < book.length; j++)
            {

                if(book[i].getGenero().compareToIgnoreCase(book[j].getGenero()) < 0)
                {

                    aux = book[i];
                    book[i] = book[j];
                    book[j] = book[i];

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
    
                        visualizarElementos(book);
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

                                    ordenarGenero(book);
                                    System.out.print("\n[!] Libros ordenados alfabeticamente por el género\n\n");
                                    break;
                            
                                default:
                                    break;
                            }

                        } while (option == 4);

                        break;
                    
                    default:
                        break;
                }
            } 
            while (opcion != 4);

        sc.close();


    }
}
