package alumno;

public class Alumno {

    private String name = "";
    private int numero_clase, nota1, nota2, nota3;

    Alumno(){}

    Alumno(String name, int class_number, int mark1, int mark2, int mark3){

        this.name = name;
        this.numero_clase = class_number;
        this.nota1 = mark1;
        this.nota2 = mark2;
        this.nota3 = mark3;

    }

    void set_Name(String newName){

        this.name= newName;

    }

    void set_NumeroClase(int newNumber){

        this.numero_clase = newNumber;

    }

    void set_PrimEv(int newMark){

        this.nota1 = newMark;

    }

    void set_SegEv(int newMark){

        this.nota2 = newMark;

    }

    void set_TerEv(int newMark){

        this.nota3 = newMark;

    }

    String get_Name(){

        return this.name;

    }

    int get_NumeroClase(){

        return this.numero_clase;

    }

    int get_PrimEv(){

        return this.nota1;
    }

    int get_SegEv(){

        return this.nota2;

    }

    int get_TerEv(){

        return this.nota3;

    }

    Float get_AverageMark(){

        float averageMark = (get_PrimEv() + get_SegEv() + get_TerEv())/3;


        return averageMark;

    }

    Boolean get_FinalMark(){

        if(get_PrimEv() <= 4 || get_SegEv() <= 4 || get_TerEv() <= 4)
        {

            return false;

        }
        else
        {
 
            return true;

        }

    }
    
}
