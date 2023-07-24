class PilhaFlexivel {
    
    private CelulaDupla primeiro;
    private CelulaDupla ultimo;

    public PilhaFlexivel () {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    public void inserir (Filme filme) {

        ultimo.prox = new CelulaDupla(filme);
        ultimo = ultimo.prox;
    }

    public Filme remover () throws Exception {

        if (primeiro == ultimo) {
            throw new Exception("Erro em R");
        }
        CelulaDupla i;
        for (i = primeiro; i.prox != ultimo; i = i.prox);

        Filme removido = ultimo.filme;
        ultimo = i;
        i = ultimo.prox = null;

        return removido;
    }

    public void imprimir () {

        int j = 0;
        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox, j++) {
            System.out.print("[" + j + "] ");
            i.filme.imprimir();
        }
    }
}
