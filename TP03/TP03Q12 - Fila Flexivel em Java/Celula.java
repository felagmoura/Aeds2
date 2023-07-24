class Celula {
    public Filme filme;
    public Celula prox;

    public Celula() {
        this.filme = new Filme();
    }
    
    public Celula (Filme item) {
        this.filme = item;
        this.prox = null;
    }
}