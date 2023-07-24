class FilaFlexivel {
    private CelulaDupla primeiro;
    private CelulaDupla ultimo;

    public FilaFlexivel() {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    public void inserir (Filme filme) throws Exception {
        int tam = getTamanho();
        if (tam == 5) {
            remover();
        }
        ultimo.prox = new CelulaDupla(filme);
        ultimo = ultimo.prox;
        mostraMedia();
    }

    public Filme remover() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro em R");
        }

        CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox;

        Filme removido = primeiro.filme;
        tmp.prox = null;
        tmp = null;

        return removido;
    }

    public void imprimir() {
        int j = 0;
        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox, j++) {
            System.out.print("[" + j + "] ");
            i.filme.imprimir();
        } 
    }

    public void mostraMedia () {
        double media = 0.0;
        int num_filmes = 0;

        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox, num_filmes++) {
            media += i.filme.getDuracao();
        }

        media /= num_filmes;

        System.out.println((int)Math.round(media));
    }
    public int getTamanho() {
        int tam = 0;
        for (CelulaDupla i = primeiro; i != ultimo; i = i.prox, tam++);
        return tam;
    }
}
