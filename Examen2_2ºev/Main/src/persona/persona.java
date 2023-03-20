package persona;

public class persona {

    private String nombre = "", imp = "";

    persona(){}

    persona(String name, String imp){

        this.nombre = name;
        this.imp = imp;

    }

    //---------------------------------------------------------------------------------------SET---------------------------------------------------------------------------------------

    public void setImp(String imp) {
        this.imp = imp;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //---------------------------------------------------------------------------------------GET---------------------------------------------------------------------------------------

    public String getImp() {
        return imp;
    }

    public String getNombre() {
        return nombre;
    }

}
