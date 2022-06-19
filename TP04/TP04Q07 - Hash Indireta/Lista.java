//************************ LISTA *************************//
public class Lista {
    private final Filme NULO;
    private Celula primeiro, ultimo;

    //--------------------------------------------------//
    // FUNCOES
    //--------------------------------------------------//

    //--------------------------------------------------//
    // CONSTRUTORES
    public Lista () {
        NULO = new Filme();
        primeiro = ultimo = new Celula(NULO);
    }

    
    //--------------------------------------------------//
    // INSERIR
    public void inserirInicio(Filme filme) {
        Celula tmp = new Celula(filme);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }


    public void inserirFim (Filme filme) throws Exception {
        ultimo.prox = new Celula(filme);
        ultimo = ultimo.prox;
    }

    //--------------------------------------------------//
    // PESQUISAR
    public boolean pesquisar (String titulo, boolean encontrado) {
        for (Celula ptr = primeiro.prox; ptr != null; ptr = ptr.prox) {
            if (ptr.filme.getTitulo_original().compareTo(titulo) == 0) {
                encontrado = true;
                ptr = ultimo;
            }
        }
        return encontrado;
    }
}
//********************************************************//
