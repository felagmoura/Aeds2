class ListaFlexivel {
    
    private CelulaDupla primeiro;
    private CelulaDupla ultimo;

    public ListaFlexivel () {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    public void inserirInicio (Filme filme) {
        CelulaDupla tmp = new CelulaDupla(filme);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        } else {
            tmp.prox.ant = tmp;
        }
        tmp = null;
    }

    public void inserirFim(Filme filme) {
        ultimo.prox = new CelulaDupla(filme);
        ultimo.prox.ant = ultimo;
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
            CelulaDupla i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);

            CelulaDupla tmp = new CelulaDupla(filme);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp.prox.ant = tmp;
            tmp = i = null;
        }
    }

    public Filme removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception ("Erro em RI");
        }

        CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox;
        Filme removido = primeiro.filme;
        tmp.prox = null;
        primeiro.ant = null;
        tmp = null;
        return removido;
    }

    public Filme removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro em RF");
        }

        Filme removido = ultimo.filme;
        
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;

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
            
            CelulaDupla i = primeiro;

            for (int j = 0; j < pos; j++, i = i.prox);

            i.ant.prox = i.prox;
            i.prox.ant = i.ant;
            removido = i.filme;
            i.prox = i.ant = null;
            i = null;
        }

        return removido;
    }

    public void imprimir () {

        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            i.filme.imprimir();
        }
    }

    public int getTamanho() {
        int tam = 0;
        for (CelulaDupla i = primeiro; i != ultimo; i = i.prox, tam++);
        return tam;
    }

    /************************** QUICKSORT **************************/

    //A primeira função quando o quicksort é chamado
    public void quicksort() {
        quicksort(primeiro.prox, ultimo);
    }

    // A função principal de todo quicksort, essa é a função recursiva
    public void quicksort(CelulaDupla esq, CelulaDupla dir) {

        // condições para que as chamadas recursivas parem antes de acessarem valores nulos ou caso o ponteiro esq passe o ponteiro dir
        if (dir != null && esq != dir && esq != dir.prox) {
            
            //retorna a celula que será a nova referencia das proximas chamadas
            CelulaDupla tmp = ordena(esq, dir);
            
            quicksort(esq, tmp.ant);
            quicksort(tmp.prox, dir);
        }
    }

    /*
        A função ordena considera o dir como o pivo, e faz a ordenação a partir dele;
        Assim os filmes que tem a situação ou o nome menor que esse pivo ficarao a esquerda dele
        e os filmes maiores a direita;
    */
    public CelulaDupla ordena (CelulaDupla esq, CelulaDupla dir) {

        // i é a celula anterior a primeira celula recebida como paramentro
        // não porque mas funciona
        CelulaDupla i = esq.ant;

        // percorre a lista e, caso encontre valores desordenados, troca o filme na celula j com i
        for (CelulaDupla j = esq; j != dir; j = j.prox) {
            if ((j.filme.getSituacao().compareTo(dir.filme.getSituacao()) < 0) || (j.filme.getSituacao().compareTo(dir.filme.getSituacao()) == 0 && j.filme.getNome().compareTo(dir.filme.getNome()) < 0)) {
                if (i == null) {
                    i = esq;
                } else {
                    i = i.prox;
                }

                troca(i, j);
            }
        }

        // i será a celula seguinte 
        if (i == null) {
            i = esq;
        } else {
            i = i.prox;
        }

        // troca i com o pivo
        troca(i, dir);

        return i;
    }

    // funcao que faz a troca entre os filmes desordenados
    void troca (CelulaDupla i, CelulaDupla j) {        
        // troca somente os filmes guardados nas celulas
        Filme tmp = i.filme;
        i.filme = j.filme;
        j.filme = tmp;
    }
}
