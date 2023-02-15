package persona;

public class Persona {

    private String name = "";
    private String DNI = "";

    Persona(){}

    Persona(String nombre, String dni){

        this.name = nombre;
        this.DNI = dni;

    }

    //-----------------------------------------------------------------------------------SET--------------------------------------------------------------------------------------

    public void setDni(String Newdni) {

        this.DNI = Newdni;

    }

    public void setName(String Newname) {

        this.name = Newname;

    }


    //-----------------------------------------------------------------------------------GET--------------------------------------------------------------------------------------

    public String getName() {

        return name;

    }

    public String getDni() {

        return DNI;

    }
    
}
