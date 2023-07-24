class ListaFlexivel {
    
    private Celula primeiro;
    private Celula ultimo;

    public ListaFlexivel () {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserirInicio (Filme filme) {
        Celula tmp = new Celula(filme);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }

    public void inserirFim(Filme filme) {
        ultimo.prox = new Celula(filme);
        ultimo = ultimo.prox;
    }

    public void inserir (Filme filme, int pos) throws Exception {
        int tam = getTamanho();

        if (pos < 0 || pos > tam) {
            throw new Exception("Erro em I");
        }
        else if (pos == 0) {
            inserirInicio(filme);
        }
        else if (pos == tam) {
            inserirFim(filme);
        }
        else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);

            Celula tmp = new Celula(filme);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    public Filme removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception ("Erro em RI");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Filme removido = primeiro.filme;
        tmp.prox = null;
        tmp = null;
        return removido;
    }

    public Filme removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro em RF");
        }

        Celula i;
        for (i = primeiro; i.prox != ultimo; i = i.prox);

        Filme removido = ultimo.filme;
        ultimo = i;
        i = ultimo.prox = null;

        return removido;
    }

    public Filme remover (int pos) throws Exception {
        
        Filme removido;
        int tam =  getTamanho();

        if ((primeiro == ultimo) || (pos < 0 || pos >= tam)) {
            throw new Exception ("Erro em R");
        }
        else if (pos == 0) {
            removido = removerInicio();
        }
        else if (pos == tam - 1) {
            removido = removerFim();
        }

        else {
            
            Celula i = primeiro;

            for (int j = 0; j < pos; j++, i = i.prox);

            Celula tmp = i.prox;
            removido = tmp.filme;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }

        return removido;
    }

    public void imprimir () {

        int j = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox, j++) {
            System.out.print("[" + j + "] ");
            i.filme.imprimir();
        }
    }

    public int getTamanho() {
        int tam = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox, tam++);
        return tam;
    }
}
