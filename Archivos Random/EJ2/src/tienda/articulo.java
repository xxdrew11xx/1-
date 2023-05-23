package tienda;

import java.io.IOException;
import java.io.RandomAccessFile;

public class articulo implements Comparable<articulo> {

    private int codigo;
    private String denominacion;
    private double stockAct, stockMin, stockMax;
    private float precio;
    char aviso; // b ->stock debajo minimo, A -> encima o igual del stock maximo, N-> Normal

    // ------------------------------------------------------------------Constructores------------------------------------------------------------------

    public articulo() {
    }

    public articulo(int code, String denom, double stAct, double stMax, double stMin, float PRECIO) {

        this.codigo = code;
        this.denominacion = DenomSpace(denom);
        this.stockAct = stAct;
        this.stockMax = stMax;
        this.stockMin = stMin;
        this.precio = PRECIO;

        if (stAct <= stMin)
            aviso = 'B';
        else if (stAct >= stMax)
            aviso = 'A';
        else
            aviso = 'N';

    }

    // ------------------------------------------------------------------Set----------------------------------------------------------------------------

    public void setAviso(char aviso) {
        this.aviso = aviso;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setStockAct(double stockAct) {
        this.stockAct = stockAct;
    }

    public void setStockMax(double stockMax) {
        this.stockMax = stockMax;
    }

    public void setStockMin(double stockMin) {
        this.stockMin = stockMin;
    }

    // ------------------------------------------------------------------Get----------------------------------------------------------------------------

    public char getAviso() {
        return aviso;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public float getPrecio() {
        return precio;
    }

    public double getStockAct() {
        return stockAct;
    }

    public double getStockMax() {
        return stockMax;
    }

    public double getStockMin() {
        return stockMin;
    }

    // ------------------------------------------------------------------Funciones----------------------------------------------------------------------

    public static int tamaño() {

        return (4 + 22 + 24 + 4 + 2);

    }

    public static String DenomSpace(String denominacion) {

        denominacion.trim();

        for (int i = denominacion.length(); i < 20; i++)
            denominacion += " ";

        return denominacion;
    }

    public static void DenomVacia(RandomAccessFile raf) throws IOException {

        raf.writeInt(0);
        raf.writeUTF(DenomSpace(""));
        raf.writeDouble(0);
        raf.writeDouble(0);
        raf.writeDouble(0);
        raf.writeFloat(0);
        raf.writeChar('P');

    }

    public void Grabar(RandomAccessFile raf, articulo a) throws Exception {

        raf.writeInt(a.getCodigo());
        raf.writeUTF(a.getDenominacion());
        raf.writeDouble(a.getStockAct());
        raf.writeDouble(a.getStockMax());
        raf.writeDouble(a.getStockMin());
        raf.writeFloat(a.getPrecio());
        raf.writeChar(a.getAviso());

    }

    public static void leerVacio(RandomAccessFile raf) throws Exception {

        raf.readUTF();
        raf.readDouble();
        raf.readDouble();
        raf.readDouble();
        raf.readFloat();
        raf.readChar();

    }

    // ------------------------------------------------------------------Ordenar
    // lista----------------------------------------------------------------------

    @Override
    public int compareTo(articulo o) {
        if (o.getCodigo() > codigo) {
            return -1;

        } else if (o.getCodigo() > codigo) {

            return 0;

        } else {

            return 1;

        }
    }

}
