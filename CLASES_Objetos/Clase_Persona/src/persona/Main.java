package persona;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    static int menugeneral(Scanner sc){

        int opcion = 4;

        do 
        {
                
            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Altas Personas");
            System.out.print("\n[2]---------> Altas Animales");
            System.out.print("\n[3]---------> Visualizar personas");
            System.out.print("\n[4]---------> Visualizar mascotas");
            System.out.print("\n[5]---------> Crear dueños");
            System.out.print("\n[6]---------> Visualizar dueños");
            System.out.print("\n[7]---------> Ordenar por nombre (menú de ordenación)");
            System.out.println("\n[8]---------> Exit");
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

            if(opcion < 1 || opcion > 8)
            {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } 
        while (opcion < 1 || opcion > 8);

        return opcion;

    }


    public static int menuOrdenar(Scanner sc){

        int opcion = 3;

        do 
        {
                
            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[1]---------> Por nombre de la persona");
            System.out.print("\n[2]---------> Por nombre de la mascota");
            System.out.println("\n[3]---------> Vovler al menú principal");
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

            if(opcion < 1 || opcion > 3)
            {

                System.out.print("\n[!!] OPCION NO VALIDA");

            }

        } 
        while (opcion < 1 || opcion > 3);

        return opcion;

    }

    static Persona[] altaPersonas(Scanner sc, Persona arr[]){

        String name = "", dni = "";

        for(int i = 0; i < arr.length; i ++)
        {

            do 
            {

                System.out.print("\n[+] Introduce el nombre de la persona: ");

                try 
                {
                
                    name = sc.nextLine();

                } catch (InputMismatchException ime) 
                {
                    
                    System.out.print("\n[!!] Error: " + ime.getMessage() + "\n\n");
                    name = "";
                    break;

                }

                if(name.length() <= 2 && name != "")
                {

                    System.out.print("\n[!!] Nombre inválido, introducelo de nuevo\n\n");

                }

                
            } while (name == "" || name.length() <= 2);

            do 
            {

                System.out.print("\n[+] Introduce el dni de la persona (xxxxxxxxZ): ");

                try 
                {
                
                    dni = sc.nextLine();

                } catch (InputMismatchException ime) 
                {
                    
                    System.out.print("\n[!!] Error: " + ime.getMessage() + "\n\n");
                    dni = "";
                    break;

                }

                if(dni.length() < 9 && dni != "")
                {

                    System.out.print("\n[!!] DNI inválido, introducelo de nuevo\n");

                }
                
            } while (dni == "");

            arr[i] = new Persona(name, dni);

        }


        return arr;
    }

    static Mascotas[] altaMascotas(Scanner sc, Mascotas arr[]){


        String name = "", dni = "";
        int edad;

        for(int i = 0; i < arr.length; i ++)
        {

            do 
            {

                System.out.print("\n[+] Introduce el nombre del animal: ");

                try 
                {
                
                    name = sc.nextLine();

                } catch (InputMismatchException ime) 
                {
                    
                    System.out.print("\n[!!] Error: " + ime.getMessage() + "\n\n");
                    name = "";
                    break;

                }

                if(name.length() <= 2 && name != "")
                {

                    System.out.print("\n[!!] Nombre inválido, introducelo de nuevo\n\n");

                }

                
            } while (name == "" || name.length() <= 2);


            do 
            {

                System.out.print("\n[+] Introduce el dni de la persona a cargo del animal (xxxxxxxxZ): ");

                try 
                {
                
                    dni = sc.nextLine();

                } catch (InputMismatchException ime) 
                {
                    
                    System.out.print("\n[!!] Error: " + ime.getMessage() + "\n\n");
                    dni = "";
                    break;

                }

                if(dni.length() < 9 && dni != "")
                {

                    System.out.print("\n[!!] DNI inválido, introducelo de nuevo\n");

                }
                
            } while (dni == "");

            do 
            {

                System.out.print("\n[+] Introduce la edad del animal: ");

                try 
                {

                    edad = sc.nextInt();
                    
                } catch (InputMismatchException ime) 
                {

                    System.out.print("\n[!!] Error: " + ime.getMessage() + "\n\n");
                    edad = 0;
                    break;

                }
                
            } while (edad < 0);

            arr[i] = new Mascotas(name, dni, edad);
            sc.nextLine();

        }

        return arr;
    }

    static void visualizarPersonas(Persona[] arr){

        System.out.print("\n\n--------------------------------------------------------------Listado de personas--------------------------------------------------------------");

        for (int i = 0; i < arr.length; i++) 
        {
            
            System.out.print("\n Nombre: " + arr[i].getName() + "\tDNI: " + arr[i].getDni());

        }

    }

    static void visualizarmascotas(Mascotas[] arr){

        System.out.print("\n\n--------------------------------------------------------------Listado de los animales--------------------------------------------------------------");

        for (int i = 0; i < arr.length; i++) 
        {
            
            System.out.print("\n Nombre del animal: " + arr[i].getNameA() + "\tDNI del dueño: " + arr[i].getDniA() + "\tedad del animal: " + arr[i].getEdadA());

        }

    }

    static Duenos[] crearDuenos(Persona[] arrPers, Mascotas[] arrMasc, Duenos[] arrDuenos){

        //dni nombrepers nombreanimal edad

        int i = 0;

        System.out.println("\n[!] Dueños creados");

        for(int j = 0; j < arrPers.length; j ++)
        {

            for (int k = 0; k < arrMasc.length; k++) 
            {

                if(arrPers[j].getDni().compareToIgnoreCase(arrMasc[k].getDniA()) == 0)
                {

                    arrDuenos[i] = new Duenos(arrPers[j].getDni(),arrPers[j].getName(),arrMasc[k].getNameA(),arrMasc[k].getEdadA());
                    
                    i++;
                }
  
            }

        }

 

        return arrDuenos;
    }

    static void visualizarDuenos(Duenos[] arr){

        System.out.print("\n-----------------------------------------------------------------------------------------------------------------Listado DE DUEÑOS-----------------------------------------------------------------------------------------------------------------");;

        for (int i = 0; i < arr.length; i++)
        {
            
            System.out.print("\nDNI: " + arr[i].getDni() + " Nombre Dueño: " + arr[i].getNombreP() + " Nombre Animal: " + arr[i].getNombreA() + " Edad Animal: " + arr[i].getEdad());

        }

    }

    static void ordenarDueños(Duenos[] arr){

        Duenos aux;

        for(int i = 0; i < arr.length; i ++)
        {

            for(int j = 0; j < arr.length; j++)
            {

                if(arr[i].getNombreP().compareToIgnoreCase(arr[j].getNombreP()) < 0)
                {

                    aux = arr[i];
                    arr[i] = arr[j];
                    arr[j] = aux;

                }


            }

        }

    }

    static void ordenarAnimales(Duenos[] arr){

        Duenos aux;

        for(int i = 0; i < arr.length; i ++)
        {

            for(int j = 0; j < arr.length; j++)
            {

                if(arr[i].getNombreA().compareToIgnoreCase(arr[j].getNombreA()) < 0)
                {

                    aux = arr[i];
                    arr[i] = arr[j];
                    arr[j] = aux;

                }


            }

        }

    }

    public static void main(String[] args) throws Exception {

        //los dueños son generados a partir de de los animales y las personas (EL DNI EN MASCOTAS Y PERSONAS ES EL MISMO)
        // 10 personas y 15 animales
        //al ordenar, ordenar por nombre d epersona o mascota

        //si los array estan vacios, visualizar mensajes


        int opcion, option;

        Persona listaPersonas[] = new Persona[2];
        Mascotas listaMascotas[] = new Mascotas[3];
        Duenos listaDuenos[] = new Duenos[listaMascotas.length];

        Scanner sc = new Scanner(System.in);

            do 
            {
                opcion = menugeneral(sc);
                sc.nextLine();

                switch(opcion)
                {

                    case 1:

                        listaPersonas = altaPersonas(sc, listaPersonas);
                        break;

                    case 2:
                    
                        listaMascotas = altaMascotas(sc, listaMascotas);
                        break;

                    case 3:

                        visualizarPersonas(listaPersonas);
                        System.out.println("\n");
                        break;

                    case 4:

                        visualizarmascotas(listaMascotas);
                        System.out.println("\n");
                        break;

                    case 5:

                        listaDuenos = crearDuenos(listaPersonas, listaMascotas, listaDuenos);
                        break;

                    case 6:
                        visualizarDuenos(listaDuenos);
                        System.out.println("\n");
                        break;

                    case 7:

                        do
                        {

                            option = menuOrdenar(sc);
                            sc.nextLine();

                            switch (option) {
                                case 1:
                                    
                                    ordenarDueños(listaDuenos);
                                    System.out.println("[!] Ordenado alfabeticamente por los dueños\n\n");
                                    break;

                                case 2:

                                    ordenarAnimales(listaDuenos);
                                    System.out.println("[!] Ordenado alfabeticamente por los animales\n\n");
                                    break;
                            
                                default:
                                    break;
                            }


                        }while(option != 3);
                        break;

                    default:

                        break;


                }

                
            } while (opcion != 8);

        sc.close();
    }
}
