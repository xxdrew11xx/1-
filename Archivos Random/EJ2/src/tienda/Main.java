package tienda;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static final Keyboard k = new Keyboard();
    static final ArrayList<articulo> articulos = new ArrayList<>();
    static final File data = new File("/home/drew/DAM/Programcion_clase/Archivos Random/EJ2/data/data.dat");

    static char menu() {

        char op = 'J';

        do {

            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[A]---------> Altas");
            System.out.print("\n[B]---------> Bajas");
            System.out.print("\n[M]---------> Modificaciones");
            System.out.print("\n[C]---------> Consultas ");
            System.out.print("\n[L]---------> Listado ");
            System.out.println("\n[E]---------> Exit");
            System.out.println();
            System.out.print("[OPCION]----> ");

            try {

                op = Character.toUpperCase(k.rChar());

            } catch (IOException ioe) {

                op = 'J';
                break;

            } catch (StringIndexOutOfBoundsException siob) {

                op = 'J';

            }

        } while ("ABMCLE".indexOf(op) == -1 || op == '\n');

        return op;
    }

    static char menuListados() {

        char op = 'J';

        do {

            System.out.print("\n----------------------------MENÚ---------------------------- ");
            System.out.print("\n[G]---------> General");
            System.out.print("\n[E]---------> Entre limites (Codigo)");
            System.out.print("\n[P]---------> Pedidos");
            System.out.println("\n[R]---------> Volver al menú general");
            System.out.println();
            System.out.print("[OPCION]----> ");

            try {

                op = Character.toUpperCase(k.rChar());

            } catch (IOException ioe) {

                op = 'J';
                break;

            } catch (StringIndexOutOfBoundsException siob) {

                op = 'J';

            }

        } while ("GEPR".indexOf(op) == -1 || op == '\n');

        return op;
    }

    static void llenarLista() throws Exception {

        RandomAccessFile raf = new RandomAccessFile(data, "r");

        int poscion = 0, posseek = 0, codigo = 0;

        while (posseek < raf.length()) {

            raf.seek(posseek);

            codigo = raf.readInt();

            articulos.add(new articulo(codigo, raf.readUTF(), raf.readDouble(), raf.readDouble(), raf.readDouble(),
                    raf.readFloat()));

            posseek = ++poscion * articulo.tamaño();

        }

        raf.close();

    }

    static boolean compararCodigo(int codigo) {

        articulo a = new articulo();

        for (int i = 0; i < articulos.size(); i++) {

            a = articulos.get(i);

            if (a.getCodigo() == codigo)
                return true;

        }

        return false;

    }

    static void nuevasAltas(int codigo) throws IOException {

        String denominacion;
        double stockAct, stockMin, stockMax;
        float precio;

        do {

            System.out.print("\n[+] Denominación del articulo (MAX 20 caracteres): ");
            denominacion = k.rString();

        } while (denominacion.length() > 20);

        do {

            System.out.print("\n[+] Stock actual del articulo: ");
            stockAct = k.rDouble();

        } while (stockAct == Double.MIN_VALUE);

        do {

            System.out.print("\n[+] Stock mínimo del articulo: ");
            stockMin = k.rDouble();

        } while (stockMin == Double.MIN_VALUE);

        do {

            System.out.print("\n[+] Stock máximo del articulo: ");
            stockMax = k.rDouble();

        } while (stockMax == Double.MIN_VALUE);

        do {

            System.out.print("\n[+] Precio del articulo: ");
            precio = k.rFloat();

        } while (precio == Float.MIN_VALUE);

        articulos.add(new articulo(codigo, denominacion, stockAct, stockMax, stockMin, precio));

    }

    static void altas() throws Exception {

        boolean exit = false;
        int codigo = 0;
        String code, otro = "";

        articulos.clear();

        llenarLista();

        while (!exit) {

            do {

                System.out.print("\n[+] Introduce el codigo del articulo (0000 | E -> Salir): ");
                code = k.rString();

                if (code.equalsIgnoreCase("e"))
                    break;

                try {

                    codigo = Integer.parseInt(code);

                } catch (NumberFormatException e) {
                    codigo = Integer.MIN_VALUE;
                }

            } while (codigo == Integer.MIN_VALUE || compararCodigo(codigo) || Integer.toString(codigo).length() != 4);

            if (code.equalsIgnoreCase("e"))
                break;

            nuevasAltas(codigo);

            do {

                System.out.print("\n[?] Realizar otro alta (S->SI | N-> NO): ");
                otro = k.rString().toUpperCase();

            } while ("SN".indexOf(otro) == -1);

            if (otro.equalsIgnoreCase("N"))
                exit = true;

        }

        Collections.sort(articulos);

        RandomAccessFile raf = new RandomAccessFile(data, "rw");

        for (articulo aux : articulos) {

            aux.Grabar(raf, aux);

        }

        raf.close();

    }

    static void listados(char tipo) throws Exception {

        int pagina = 1, linea = 0, codigo = 0;

        articulos.clear();
        llenarLista();

        System.out.print(
                "-------------------Avisos-------------------\n[N]------------------->Stock normal\n[B]------------------->Hacer pedido\n[A]------------------->Por encima del stock máximo\n\n\n");

        System.out.print(
                "Codigo:\t\tDenominación:\t\tStock Actual:\t\tStock Máximo:\t\tStock Mínimo:\t\tPrecio:\t\tAviso:\t\tPagina:"
                        + pagina + "\n");
        for (int i = 0; i < 152; i++) {
            System.out.print("-");
        }

        System.out.println();

        for (articulo aux : articulos) {

            if (linea % 5 == 0 && linea != 0) {
                pagina++;
                linea++;

                if (tipo == 'P') {

                    System.out.print("\n Pulsa Enter para continuar....");
                    k.newLine();
                    System.out.println();

                }

                System.out.print(
                        "Codigo:\t\tDenominación:\t\tStock Actual:\t\tStock Máximo:\t\tStock Mínimo:\t\tPrecio:\t\tAviso:\t\tPagina:"
                                + pagina + "\n");

                for (int i = 0; i < 152; i++) {
                    System.out.print("-");
                }

                System.out.println();

            }

            codigo = aux.getCodigo();

            if (codigo != 0) {

                linea++;
                System.out.println(codigo + "\t\t" + aux.getDenominacion() + "\t" + aux.getStockAct() + " \t\t\t"
                        + aux.getStockMax() + " \t\t\t" + aux.getStockMin() + " \t\t\t" + aux.getPrecio() + "\t\t"
                        + aux.getAviso());
            }

        }

    }

    static int NumeroBaja(int codigo) throws Exception {

        int posicion = 0, posseek = 0, posArt = 0, code = 0;

        RandomAccessFile raf = new RandomAccessFile(data, "r");

        while (posseek < raf.length()) {

            raf.seek(posseek);

            code = raf.readInt();

            if (code == codigo)
                break;

            articulo.leerVacio(raf);

            posseek = ++posicion * articulo.tamaño();
            posArt++;

        }

        raf.close();

        return posArt;

    }

    static void bajas() throws Exception {

        int codigo = 0;
        String code = "", op = "";
        boolean exists = true, exit = false;

        articulos.clear();
        llenarLista();

        while (!exit) {

            do {

                System.out.print("[+] Codigo del articulo a dar de baja (E -> Salir): ");
                code = k.rString();

                if (Character.isLetter(code.charAt(0)) && Character.toLowerCase(code.charAt(0)) == 'e')
                    break;

                try {

                    codigo = Integer.parseInt(code);

                } catch (NumberFormatException nfe) {
                    codigo = Integer.MIN_VALUE;
                }
                exists = compararCodigo(codigo);

                if (!exists)
                    System.out.print("[!!] Articulo no encontrado....\n");

            } while (!exists || codigo == Integer.MIN_VALUE);

            if (Character.toLowerCase(code.charAt(0)) == 'e')
                break;

            System.out.print(
                    "\nCodigo:\t\tDenominación:\t\tStock Actual:\t\tStock Mínimo:\t\tStock Maximo:\t\tPrecio:\t\tAviso:\n");
            articulo aux = articulos.get(NumeroBaja(codigo));

            for (int i = 0; i < 134; i++) {
                System.out.print("-");
            }

            System.out.println();

            System.out.println(aux.getCodigo() + "\t\t" + aux.getDenominacion() + "\t" + aux.getStockAct() + " \t\t\t"
                    + aux.getStockMax() + " \t\t\t" + aux.getStockMin() + " \t\t\t" + aux.getPrecio() + "\t\t"
                    + aux.getAviso());

            do {

                System.out.print("\n[?] Confirmar la baja del articulo (S->SI | N->NO):");
                op = k.rString().toUpperCase();

            } while ("SN".indexOf(op) == -1);

            if (op.equalsIgnoreCase("S")) {

                RandomAccessFile raf = new RandomAccessFile(data, "rw");

                raf.seek(NumeroBaja(codigo) * articulo.tamaño());

                articulo.DenomVacia(raf);

                raf.close();

                System.out.print("\n[!] El articulo ha sido dado de baja");

                do {

                    System.out.print("\n[?] Dar otro articulo de baja (S->SI | N->NO):");
                    op = k.rString().toUpperCase();

                } while ("SN".indexOf(op) == -1);

                if (op.equalsIgnoreCase("n"))
                    exit = true;

            } else {

                exit = false;

            }

        }

    }

    static int NumConsulta(int code) throws Exception {

        int codigo = 0;

        for (articulo aux : articulos) {

            if (aux.getCodigo() == code)
                break;

            codigo++;

        }

        return codigo;

    }

    static void consultas() throws Exception {

        int codigo = 0;
        String code = "";
        boolean exists;

        articulos.clear();

        llenarLista();

        do {

            System.out.print("[+] Codigo del articulo a buscar (E -> Salir): ");
            code = k.rString();

            if (Character.isLetter(code.charAt(0)) && Character.toLowerCase(code.charAt(0)) == 'e')
                break;

            try {

                codigo = Integer.parseInt(code);

            } catch (NumberFormatException nfe) {
                codigo = Integer.MIN_VALUE;
            }
            exists = compararCodigo(codigo);

            if (!exists)
                System.out.print("[!!] Articulo no encontrado....\n");

        } while (!exists || codigo == Integer.MIN_VALUE);

        if (!code.equalsIgnoreCase("e")) {

            System.out.print(
                    "Codigo:\t\tDenominación:\t\tStock Actual:\t\tStock Máximo:\t\tStock Mínimo:\t\tPrecio:\t\tAviso:\n");
            articulo aux = articulos.get(NumConsulta(codigo));

            for (int i = 0; i < 134; i++) {
                System.out.print("-");
            }

            System.out.println();

            System.out.println(aux.getCodigo() + "\t\t" + aux.getDenominacion() + "\t" + aux.getStockAct() + " \t\t\t"
                    + aux.getStockMax() + " \t\t\t" + aux.getStockMin() + " \t\t\t" + aux.getPrecio() + "\t\t"
                    + aux.getAviso());

        }

    }

    static int newCode(int codigoAnt) throws Exception {

        int newcode = 0;
        String code = "";

        do {

            System.out.print("\n[+] Introduce el nuevo codigo (E-> Salir): ");
            code = k.rString();

            try {

                newcode = Integer.parseInt(code);

            } catch (Exception e) {
                newcode = Integer.MIN_VALUE;
            }

            if (compararCodigo(newcode))
                System.out.print("[!!] Codigo ya existente...\n");

        } while (newcode == Integer.MIN_VALUE || compararCodigo(newcode));

        if (code.equalsIgnoreCase("e"))
            return codigoAnt;

        else
            return newcode;

    }

    static double newStockAct(double stockAnt) throws Exception {

        double newStock = 0;
        String code = "";

        do {

            System.out.print("\n[+] Introduce el nuevo stock actual (E-> Salir): ");
            code = k.rString();

            try {

                newStock = Integer.parseInt(code);

            } catch (Exception e) {
                newStock = Integer.MIN_VALUE;
            }

        } while (newStock == Double.MIN_VALUE);

        if (code.equalsIgnoreCase("e"))
            return stockAnt;

        else
            return newStock;

    }

    static double newStockMin(double stockAnt) throws Exception {

        double newStock = 0;
        String code = "";

        do {

            System.out.print("\n[+] Introduce el nuevo stock minimo (E-> Salir): ");
            code = k.rString();

            try {

                newStock = Integer.parseInt(code);

            } catch (Exception e) {
                newStock = Integer.MIN_VALUE;
            }

        } while (newStock == Double.MIN_VALUE);

        if (code.equalsIgnoreCase("e"))
            return stockAnt;

        else
            return newStock;

    }

    static double newStockMax(double stockAnt) throws Exception {

        double newStock = 0;
        String code = "";

        do {

            System.out.print("\n[+] Introduce el nuevo stock maximo (E-> Salir): ");
            code = k.rString();

            try {

                newStock = Integer.parseInt(code);

            } catch (Exception e) {
                newStock = Integer.MIN_VALUE;
            }

        } while (newStock == Double.MIN_VALUE);

        if (code.equalsIgnoreCase("e"))
            return stockAnt;

        else
            return newStock;

    }

    static String nuevaDenominacion(String denom) throws Exception {

        String newdenom = "";

        do {
            System.out.print("\n[+] Introduce la nueva denominacion (E -> salir): ");
            newdenom = k.rString();

            if (newdenom.equalsIgnoreCase("e"))
                break;

        } while (newdenom.length() > 20);

        if (newdenom.equalsIgnoreCase("e"))
            return denom;

        else
            return newdenom;

    }

    static float newPrecio(float precioAnt) throws Exception {

        float newprecio = 0;
        String code = "";

        do {

            System.out.print("\n[+] Introduce el nuevo precio (E-> Salir): ");
            code = k.rString();

            if (code.equalsIgnoreCase("e"))
                break;

            try {

                newprecio = Float.parseFloat(code);

            } catch (Exception e) {
                newprecio = Float.MIN_VALUE;
            }

        } while (newprecio == Float.MIN_VALUE);

        if (code.equalsIgnoreCase("e"))
            return precioAnt;

        else
            return newprecio;

    }

    static articulo pregDatos(articulo aux) throws Exception {

        boolean exit = false;
        char op = ' ';

        while (!exit) {

            System.out.print(
                    "\nCodigo:\t\tDenominación:\t\tStock Actual:\t\tStock Mínimo:\t\tStock Máximo:\t\tPrecio:\t\tAviso:\n");

            for (int i = 0; i < 134; i++) {
                System.out.print("-");
            }

            System.out.println();

            System.out.println(aux.getCodigo() + "\t\t" + aux.getDenominacion() + "\t" + aux.getStockAct() + " \t\t\t"
                    + aux.getStockMax() + " \t\t\t" + aux.getStockMin() + " \t\t\t" + aux.getPrecio() + "\t\t"
                    + aux.getAviso());

            do {
                System.out.print("\n\n----------------------------MENÚ---------------------------- ");
                System.out.print("\n[C]---------> Codigo");
                System.out.print("\n[D]---------> Denominacion");
                System.out.print("\n[A]---------> Stock Actual");
                System.out.print("\n[M]---------> Stock Minimo");
                System.out.print("\n[O]---------> Stock Maximo");
                System.out.print("\n[P]---------> Precio");
                System.out.print("\n[3]---------> Cambiar todos los stocks");
                System.out.print("\n[T]---------> Cambiar todo");
                System.out.println("\n[E]---------> Exit");
                System.out.println();
                System.out.print("[OPCION]----> ");
                op = Character.toUpperCase(k.rChar());

            } while ("CDAMO3TEP".indexOf(op) == -1);

            switch (op) {
                case 'C':

                    aux = new articulo(newCode(aux.getCodigo()), aux.getDenominacion(), aux.getStockAct(),
                            aux.getStockMin(), aux.getStockMax(), aux.getPrecio());
                    break;

                case 'D':

                    aux = new articulo(aux.getCodigo(), nuevaDenominacion(aux.getDenominacion()), aux.getStockAct(),
                            aux.getStockMin(), aux.getStockMax(), aux.getPrecio());
                    break;

                case 'A':

                    aux = new articulo(aux.getCodigo(), aux.getDenominacion(), newStockAct(aux.getStockAct()),
                            aux.getStockMin(), aux.getStockMax(), aux.getPrecio());
                    break;

                case 'M':

                    aux = new articulo(aux.getCodigo(), aux.getDenominacion(), aux.getStockAct(), aux.getStockMax(),
                            newStockMin(aux.getStockMin()), aux.getPrecio());
                    break;

                case 'O':

                    aux = new articulo(aux.getCodigo(), aux.getDenominacion(), aux.getStockAct(),
                            newStockMax(aux.getStockMax()), aux.getStockMin(), aux.getPrecio());
                    break;

                case 'P':

                    aux = new articulo(aux.getCodigo(), aux.getDenominacion(), aux.getStockAct(), aux.getStockMin(),
                            aux.getStockMax(), newPrecio(aux.getPrecio()));
                    break;

                case '3':

                    aux = new articulo(aux.getCodigo(), aux.getDenominacion(), newStockAct(aux.getStockAct()),
                            newStockMin(aux.getStockMin()), newStockMax(aux.getStockMax()), aux.getPrecio());
                    break;

                case 'T':

                    aux = new articulo(newCode(aux.getCodigo()), nuevaDenominacion(aux.getDenominacion()),
                            newStockAct(aux.getStockAct()), newStockMax(aux.getStockMax()),
                            newStockMin(aux.getStockMin()), newPrecio(aux.getPrecio()));
                    break;

                default:

                    exit = true;
                    break;
            }

        }

        return aux;

    }

    static void modificaciones() throws Exception {

        boolean exit = false, confirm = false;
        String code = "", op = "";
        int codigo = 0;

        while (!exit) {

            articulos.clear();
            llenarLista();

            do {
                System.out.print("\n[+] Introduce el código del articulo a modificar (E-> Salir): ");
                code = k.rString();

                if (code.equalsIgnoreCase("e"))
                    break;

                try {

                    codigo = Integer.parseInt(code);

                } catch (NumberFormatException e) {
                    codigo = Integer.MIN_VALUE;
                }

            } while (!compararCodigo(codigo) || codigo == Integer.MIN_VALUE);

            if (!code.equalsIgnoreCase("e"))
                while (!confirm) {

                    for (articulo art : articulos) {

                        if (art.getCodigo() == codigo) {

                            articulos.remove(art);
                            articulos.add(pregDatos(art));

                            break;

                        }

                    }

                    do {

                        System.out.print("\n[?] Confirmar modificaciones (S-> Si | N-> NO): ");
                        op = k.rString().toUpperCase();

                    } while ("SN".indexOf(op) == -1);

                    if (op.equalsIgnoreCase("s"))
                        confirm = true;

                }

            do {

                System.out.print("\n[?] Hacer otra modificación (S-> Si | N-> NO): ");
                op = k.rString().toUpperCase();

            } while ("SN".indexOf(op) == -1);

            if (op.equalsIgnoreCase("n"))
                exit = true;
            else
                confirm = false;

            RandomAccessFile raf = new RandomAccessFile(data, "rw");

            Collections.sort(articulos);

            for (articulo art : articulos) {

                art.Grabar(raf, art);

            }

            raf.close();

        }

    }

    static void listadoEntreLimites() throws Exception {

        int liminf = Integer.MAX_VALUE, limsup = Integer.MIN_VALUE, pagina = 1, linea = 0, codigo = 0;
        char tipo = 'P';

        articulos.clear();
        llenarLista();

        do {

            do {

                System.out.print("\n[+] Primer codigo a listar: ");
                liminf = k.rInt();

                if (liminf == Integer.MIN_VALUE || Integer.toString(liminf).length() != 4)
                    System.out.print("\n[!!]Limite NO válido....\n\n");

            } while (liminf == Integer.MIN_VALUE || Integer.toString(liminf).length() != 4);

            do {

                System.out.print("\n[+] Último codigo a listar: ");
                limsup = k.rInt();

                if (limsup == Integer.MIN_VALUE || Integer.toString(limsup).length() != 4)
                    System.out.print("\n[!!]Limite NO válido....\n\n");

            } while (limsup == Integer.MIN_VALUE || Integer.toString(limsup).length() != 4);

        } while (limsup < liminf);

        System.out.print(
                "\nCodigo:\t\tDenominación:\t\tStock Actual:\t\tStock Máximo:\t\tStock Mínimo:\t\tPrecio:\t\tAviso:\t\tPagina:"
                        + pagina + "\n");
        for (int i = 0; i < 152; i++) {
            System.out.print("-");
        }

        System.out.println();

        for (articulo aux : articulos) {

            if (linea % 5 == 0 && linea != 0) {
                pagina++;

                if (tipo == 'P') {

                    System.out.print("\n Pulsa Enter para continuar....");
                    k.newLine();
                    System.out.println();

                }

                System.out.print(
                        "Codigo:\t\tDenominación:\t\tStock Actual:\t\tStock Máximo:\t\tStock Mínimo:\t\tPrecio:\t\tAviso:\t\tPagina:"
                                + pagina + "\n");

                for (int i = 0; i < 152; i++) {
                    System.out.print("-");
                }

                System.out.println();

            }

            codigo = aux.getCodigo();

            if (codigo != 0 && (codigo >= liminf && codigo <= limsup)) {

                linea++;
                System.out.println(codigo + "\t\t" + aux.getDenominacion() + "\t" + aux.getStockAct() + " \t\t\t"
                        + aux.getStockMax() + " \t\t\t" + aux.getStockMin() + " \t\t\t" + aux.getPrecio() + "\t\t"
                        + aux.getAviso());
            }

            if (codigo == limsup)
                break;

        }

    }

    static void listadoPedidos() throws Exception {

        int pagina = 1, linea = 0, codigo = 0;
        char tipo = 'P', aviso = ' ';
        double precioTotal = 0, precioPagina = 0;

        articulos.clear();
        llenarLista();

        System.out.print(
                "\nCodigo:\t\tDenominación:\t\tStock Actual:\t\tStock Máximo:\t\tStock Mínimo:\t\tPrecio:\t\tAviso:\t\tPagina:"
                        + pagina + "\n");
        for (int i = 0; i < 152; i++) {
            System.out.print("-");
        }

        System.out.println();

        for (articulo aux : articulos) {

            if (linea % 5 == 0 && linea != 0) {
                pagina++;

                System.out.print("\n[!] Precio total de la página: " + precioPagina);
                precioPagina = 0;

                if (tipo == 'P') {

                    System.out.print("\n Pulsa Enter para continuar....");
                    k.newLine();
                    System.out.println();

                }

                System.out.print(
                        "Codigo:\t\tDenominación:\t\tStock Actual:\t\tStock Máximo:\t\tStock Mínimo:\t\tPrecio:\t\tAviso:\t\tPagina:"
                                + pagina + "\n");

                for (int i = 0; i < 152; i++) {
                    System.out.print("-");
                }

                System.out.println();

            }

            codigo = aux.getCodigo();
            aviso = aux.getAviso();

            if (codigo != 0 && aviso == 'B') {

                linea++;
                System.out.println(codigo + "\t\t" + aux.getDenominacion() + "\t" + aux.getStockAct() + " \t\t\t"
                        + aux.getStockMax() + " \t\t\t" + aux.getStockMin() + " \t\t\t" + aux.getPrecio() + "\t\t"
                        + aviso);

                precioPagina += aux.getPrecio() * (aux.getStockMin() - aux.getStockAct());
                precioTotal += aux.getPrecio() * (aux.getStockMin() - aux.getStockAct());
            }

        }

        if (pagina == 1)
            System.out.print("\n[!] Precio total de la página: " + precioPagina);

        System.out.print("\n[!] Precio total del pedido: " + precioTotal + "\n\n");

    }

    public static void main(String[] args) throws Exception {

        if (!data.exists()) {

            data.createNewFile();

        }

        char op = ' ';
        boolean exit = false, exitList = false;
        ;

        while (!exit) {

            op = menu();

            switch (op) {
                case 'A':

                    altas();
                    break;

                case 'B':

                    bajas();
                    System.out.println();
                    break;

                case 'M':

                    modificaciones();
                    System.out.println();
                    break;

                case 'C':

                    System.out.println();
                    consultas();
                    break;

                case 'L':

                    System.out.println();
                    exitList = false;
                    while (!exitList) {

                        op = menuListados();

                        switch (op) {
                            case 'G':

                                listados('P');
                                break;

                            case 'E':

                                listadoEntreLimites();
                                break;

                            case 'P':

                                listadoPedidos();
                                break;

                            default:

                                exitList = true;
                                break;
                        }

                    }
                    System.out.println();
                    break;

                default:

                    exit = true;
                    break;
            }

        }

    }
}
