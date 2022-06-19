//****************************** NO ******************************//
class No {
    Filme filme;
    No esq, dir;
    int nivel;
    
    // --------------------------------------------------- //
    // NO FUNCTIONS
    // --------------------------------------------------- //
    
    // --------------------------------------------------- //
    // CONSTRUTORES
    public No (Filme filme) {
        this.filme = filme;
        this.esq = null;
        this.dir = null;
        this.nivel = 1;
    }

    public No (Filme filme, No esq, No dir, int nivel) {
        this.filme = filme;
        this.esq = esq;
        this.dir = dir;
        this.nivel = nivel;
    }
    
    // -------------------------------------------------- //
    // NIVEL
    public void setNivel () {
        this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
    }

    public int getNivel (No no) {
        int nivel;
        if (no == null)
            nivel = 0;
        else
            nivel = no.nivel;
        return nivel;
    }
}
//******************************************************************//
