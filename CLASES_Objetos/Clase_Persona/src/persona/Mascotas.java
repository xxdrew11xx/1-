package persona;

public class Mascotas {

    String dniA, nameA;
    int edadA;

    Mascotas(){}

    Mascotas(String nombreAnimal, String dniAnimal, int edadAnimal){

        this.nameA = nombreAnimal;
        this.dniA = dniAnimal;
        this.edadA = edadAnimal;

    }

    //-----------------------------------------------------------------------------------SET--------------------------------------------------------------------------------------

    public void setDniA(String NewdniA) {

        this.dniA = NewdniA;

    }

    public void setEdadA(int NewedadA) {

        this.edadA = NewedadA;

    }

    public void setNameA(String NewnameA) {

        this.nameA = NewnameA;

    }


    //-----------------------------------------------------------------------------------GET--------------------------------------------------------------------------------------

    public String getDniA() {
        return dniA;
    }

    public int getEdadA() {
        return edadA;
    }

    public String getNameA() {
        return nameA;
    }
    
}
