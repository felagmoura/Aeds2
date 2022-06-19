//****************************** NO ******************************//
class No {
    Filme filme;
    boolean cor;
    No esq, dir;
    
    // --------------------------------------------------- //
    // NO FUNCTIONS
    // --------------------------------------------------- //
    
    // --------------------------------------------------- //
    // CONSTRUTORES
    public No (Filme filme) {
        this(filme, false, null, null);
    }

    public No (Filme filme, boolean cor) {
        this(filme, cor, null, null);
    }

    public No (Filme filme, boolean cor, No esq, No dir) {
        this.filme = filme;
        this.cor = cor;
        this.esq = esq;
        this.dir = dir;
    }
    
    // -------------------------------------------------- //
}
//****************************************************************//
