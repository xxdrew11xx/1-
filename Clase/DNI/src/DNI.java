import java.util.Scanner;

public class DNI {
//  public static int tecleaNumero (Scanner r) {
//      int dni = 0;
//      do {
//          System.out.println("Escribe el número (sin letra) del DNI");
//          try {
//              dni=r.nextInt();
//          }catch(java.util.InputMismatchException ime) {System.out.println("Se pedía un número"); dni=-1; r.nextLine();}
//      }while(dni==-1);
//      return dni;
//  }
//  public static char tecleaLetra (Scanner r) {
//      char dni = 0;
//      System.out.println("Escribe la letra");
//      try {
//          dni=(char) System.in.read();
//          while (System.in.read()!='\n');
//      }catch (IOException ioe) {System.out.println("Se pedía una letra, saliendo");}
//      return dni;
//  }
//  public static boolean comprobarNif(int dni, char l, String w) {
//      if (w.charAt(dni%23)==l) {
//          return true;
//      }else {
//          return false;
//      }
//  }
//  public static void main(String[] args) {
//      Scanner r = new Scanner(System.in);
//      String letras = "TRWAGMYFPDXBNJZSOVHLCKE";
//      int number = tecleaNumero(r);
//      char letter = Character.toUpperCase(tecleaLetra(r));
//      if (comprobarNif(number,letter,letras)) {
//          System.out.println("El DNI es correcto");
//      }else {
//          System.out.println("El número o la letra del DNI es incorrecto");
//      }
//      r.close();
//  }
//}
    public static String tecleaDNI (Scanner r) 
    {

        String dni = "";

        do 
        {
            do 
            {

                System.out.print("[+] Introduce el DNI: ");
                dni=r.next();

            }
            while(dni.length()!=9);

            if (!Character.isLetter(dni.charAt(8))) 
            {
                try 
                {
                   
                    Integer.parseInt(dni.substring(0,8));

                }
                catch (NumberFormatException nfe) 
                {

                    System.out.print("[!!] Los 8 primeros digitos deben de ser numeros");
                
                }

                dni="-1";
                System.out.print("[!!] El ultimo caracter debe de ser una letra.");

            }
            else 
            {
                try 
                {
                    Integer.parseInt(dni.substring(0,8));
                }
                catch (NumberFormatException nfe) {System.out.println("Error: Los 8 primeros carácteres no son números"); dni="-1";}
            }
        }
        while(dni=="-1");

        return dni;
    }

    public static boolean comprobarNif(String dni, String w)
    {
        if (w.charAt(Integer.parseInt(dni.substring(0,8))%23)==Character.toUpperCase(dni.charAt(8))) 
        {

            return true;

        }
        else 
        {
            
            return false;

        }
    }

    public static void main(String[] args) {

        System.out.println();

        String letras, nif;

        Scanner r = new Scanner(System.in);

        letras = "TRWAGMYFPDXBNJZSOVHLCKE";
        nif = tecleaDNI(r);

        if (comprobarNif(nif,letras)) 
        {
            System.out.println("\n[!] DNI CORRTECTO");
        }
        else 
        {
            System.out.println("\n[!!] DNI INCORRECTO");
        }

        r.close();
    }
}