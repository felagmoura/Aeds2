class FilaFlexivel {
    private Celula primeiro;
    private Celula ultimo;

    public FilaFlexivel() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserir (Filme filme) throws Exception {
        int tam = getTamanho();
        if (tam == 5) {
            remover();
        }
        ultimo.prox = new Celula(filme);
        ultimo = ultimo.prox;
        mostraMedia();
    }

    public Filme remover() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro em R");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;

        Filme removido = primeiro.filme;
        tmp.prox = null;
        tmp = null;

        return removido;
    }

    public void imprimir() {
        int j = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox, j++) {
            System.out.print("[" + j + "] ");
            i.filme.imprimir();
        } 
    }

    public void mostraMedia () {
        double media = 0.0;
        int num_filmes = 0;

        for (Celula i = primeiro.prox; i != null; i = i.prox, num_filmes++) {
            media += i.filme.getDuracao();
        }

        media /= num_filmes;

        System.out.println((int)Math.round(media));
    }
    public int getTamanho() {
        int tam = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox, tam++);
        return tam;
    }
}
