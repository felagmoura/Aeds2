//************************ CELULA *************************//
public class Celula {
    Filme filme;
    Celula prox;

    public Celula () {
        this.filme = new Filme();
    } 

    public Celula (Filme filme) {
        this.filme = filme;
        this.prox = null;
    }
}
//*********************************************************//
