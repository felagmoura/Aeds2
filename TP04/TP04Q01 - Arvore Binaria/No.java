//****************************** NO ******************************//
class No {
    Filme filme;
    No esq;
    No dir;

    // --------------------------------------------------- //
    // NO FUNCTIONS
    // --------------------------------------------------- //

    public No (Filme filme) {
        this.filme = filme;
        this.esq = null;
        this.dir = null;
    }

    public No (Filme filme, No esq, No dir) {
        this.filme = filme;
        this.esq = esq;
        this.dir = dir;
    }
    // -------------------------------------------------------- //
}
//******************************************************************//

