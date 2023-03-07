import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static int[][] llenarArray(int arr[][], Scanner sc) {

        int numero = 0;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                try {

                    do {

                        System.out.print("[!] Teclea número (0 o 1) [" + i + "][" + j + "]: ");
                        numero = sc.nextInt();

                    } while (numero != 1 && numero != 0);

                } catch (InputMismatchException ime) {

                    System.out.print("\n [!!]Error: " + ime.getLocalizedMessage() + "\n\n");

                }

                arr[i][j] = numero;

            }

        }

        return arr;
    }

    static void visualizar(int arr[][]) {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                System.out.print(arr[i][j] + "\t");

            }

            System.out.println();

        }

    }

    static void columnas(int arr[][]) {

        String uno = "111", cero = "000", resultado = "";

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                resultado = resultado + String.valueOf(arr[j][i]);

            }

            if (resultado.equals(uno) || resultado.equals(cero)) {

                System.out.print("\n[!] Hay almenos una columna con tres en raya");
                break;

            }

            resultado = "";

        }

    }

    static void fila(int arr[][]) {

        String uno = "111", cero = "000", resultado = "";

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                resultado = resultado + String.valueOf(arr[i][j]);

            }

            if (resultado.equals(uno) || resultado.equals(cero)) {

                System.out.print("\n[!] Hay almenos una fila con tres en raya");
                break;

            }

            resultado = "";

        }

    }

    static void diagonal(int arr[][]) {

        String uno = "111", cero = "000", resultado = "", resultado2 = "";

        resultado = String.valueOf(arr[0][0]) + String.valueOf(arr[1][1]) + String.valueOf(arr[2][2]);
        resultado2 = String.valueOf(arr[2][0]) + String.valueOf(arr[1][1]) + String.valueOf(arr[0][2]);


        if((resultado.equals(uno) || resultado.equals(cero)) || (resultado2.equals(uno) || resultado2.equals(cero)))
        {

            System.out.print("\n[!] Hay almenos una diagonal con tres en raya");

        }

    }

    public static void main(String[] args) throws Exception {

        System.out.println();

        int matriz[][] = new int[3][3];

        Scanner sc = new Scanner(System.in);

            matriz = llenarArray(matriz, sc);
            System.out.println();
            visualizar(matriz);

            columnas(matriz);
            fila(matriz);
            diagonal(matriz);

            System.out.println();

        sc.close();

    }
}
