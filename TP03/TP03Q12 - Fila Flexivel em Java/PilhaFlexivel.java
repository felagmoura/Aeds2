class PilhaFlexivel {
    
    private Celula primeiro;
    private Celula ultimo;

    public PilhaFlexivel () {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserir (Filme filme) {

        ultimo.prox = new Celula(filme);
        ultimo = ultimo.prox;
    }

    public Filme remover () throws Exception {

        if (primeiro == ultimo) {
            throw new Exception("Erro em R");
        }
        Celula i;
        for (i = primeiro; i.prox != ultimo; i = i.prox);

        Filme removido = ultimo.filme;
        ultimo = i;
        i = ultimo.prox = null;

        return removido;
    }

    public void imprimir () {

        int j = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox, j++) {
            System.out.print("[" + j + "] ");
            i.filme.imprimir();
        }
    }
}
