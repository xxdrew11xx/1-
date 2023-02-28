package persona;

public class persona {

    private String nombre = "";
    private String sexo = "";
    
    persona(){}

    persona(String name, String sex){

        this.nombre = name;
        this.sexo = sex;
    }

    /*---------------------------------------------------------------SET---------------------------------------------------------------*/

    public void setNombre(String name) {
        this.nombre = name;
    }
    
    public void setSexo(String sex) {
        this.sexo = sex;
    }


    /*---------------------------------------------------------------GET---------------------------------------------------------------*/

    public String getNombre() {
        return nombre;
    }

    public String getSexo() {
        return sexo;
    }


}
