package libros;

public class Libros {

    private String isbn;
    private String titulo;
    private String autor; 
    private String genero;
    private float precio;

    Libros(){}

    Libros(String isbn, String titulo, String autor, String genero, float precio){

        this.isbn = isbn;
        this.autor = autor;
        this.titulo = titulo;
        this.genero = genero;
        this.precio = precio;

    }

    //------------------------------------SET------------------------------------

    public void set_isbn(String NewIsbn){

        this.isbn = NewIsbn;

    }

    public void set_autor(String NewAutor){

        this.autor = NewAutor;

    }

    public void set_titulo(String NewTitulo){

        this.titulo = NewTitulo;

    }

    public void set_genero(String NewGenero){

        this.genero = NewGenero;

    }

    public void set_precio(Float NewPrecio){

        this.precio = NewPrecio;

    }

    //------------------------------------GET------------------------------------

    public String getAutor() {

        return autor;
    }

    public String getIsbn() {

        return isbn;
    }

    public float getPrecio() {

        return precio;
    }

    public String getGenero() {

        return genero;
    }

    public String getTitulo() {

        return titulo;
    }

}
