package persona;

public class Duenos {

    String dni, nombreP, nombreA;
    int edad;

    Duenos(){}

    Duenos(String DNI, String nombrePersona, String nombreAnimal, int Edad){

        this.dni = DNI;
        this.nombreP = nombrePersona;
        this.nombreA = nombreAnimal;
        this.edad = Edad;

    }

    //-----------------------------------------------------------------------------------SET--------------------------------------------------------------------------------------
    
    public void setDni(String Newdni) {

        this.dni = Newdni;
    }
    
    public void setNombreA(String NewnombreA) {

        this.nombreA = NewnombreA;
    }

    public void setNombreP(String NewnombreP) {
        this.nombreP = NewnombreP;
    }

    public void setEdad(int Newedad) {
        this.edad = Newedad;
    }


    //-----------------------------------------------------------------------------------SET--------------------------------------------------------------------------------------

    public String getDni() {
      
        return dni;
     
    }

    public int getEdad() {
        return edad;
    }

    public String getNombreA() {
        return nombreA;
    }

    public String getNombreP() {
        return nombreP;
    }
}
