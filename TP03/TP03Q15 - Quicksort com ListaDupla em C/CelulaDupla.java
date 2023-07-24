class CelulaDupla {
    public Filme filme;
    public CelulaDupla prox, ant;

    public CelulaDupla() {
        this.filme = new Filme();
    }
    
    public CelulaDupla (Filme item) {
        this.filme = item;
        this.prox = null;
        this.ant = null;
    }
}