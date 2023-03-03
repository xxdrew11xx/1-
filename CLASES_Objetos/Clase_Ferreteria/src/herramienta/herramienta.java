package herramienta;

public class herramienta {

    private String codProc = "", denominacion = "", precio = "", unidades = "", tipos = "";

    herramienta(){}

    herramienta(String codigo, String denom, String tipo, String prec, String und ){

        this.codProc = codigo;
        this.denominacion = denom;
        this.precio = prec;
        this.tipos = tipo; 
        this.unidades = und;

    }
    


    /*------------------------------------------------------------------SET------------------------------------------------------------------ */

    public void setCodProc(String codProc) {
        this.codProc = codProc;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setTipos(String tipos) {
        this.tipos = tipos;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    /*------------------------------------------------------------------GET------------------------------------------------------------------ */

    public String getCodProc() {
        return codProc;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public String getPrecio() {
        return precio;
    }

    public String getTipos() {
        return tipos;
    }

    public String getUnidades() {
        return unidades;
    }

}
