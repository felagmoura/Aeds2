//****************************** NO ******************************//
public class No {
    public char elemento;
    public No esq, dir;

    // --------------------------------------------------- //
    // NO FUNCTIONS
    // --------------------------------------------------- //
    
    // --------------------------------------------------- //
    // CONSTRUTORES
    public No (char elemento) {
        this(elemento, null, null);
    }

    public No (char elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}
//****************************************************************//
