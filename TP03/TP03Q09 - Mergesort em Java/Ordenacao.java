class Ordenacao {
    
    void troca (int i, int j, Lista lista) {
        Filme temp = lista.array[i];
        lista.array[i] = lista.array[j];
        lista.array[j] = temp;
    }

    void selecao (Lista lista) {

        for (int i = 0; i < lista.num_filmes - 1; i++) {
            int desordenado = i;
            for (int j = i + 1; j < lista.num_filmes; j++) {
                
                if (lista.array[desordenado].getTitulo_original().charAt(0) > lista.array[j].getTitulo_original().charAt(0)) {
                    desordenado = j;
                }

                else if (lista.array[desordenado].getTitulo_original().charAt(0) == lista.array[j].getTitulo_original().charAt(0)) {
                    
                    int index = 1;
                    
                    while (lista.array[desordenado].getTitulo_original().charAt(index) == lista.array[j].getTitulo_original().charAt(index)) {
                        index++;
                    }

                    if (lista.array[desordenado].getTitulo_original().charAt(index) > lista.array[j].getTitulo_original().charAt(index)) {
                        desordenado = j;
                    }

                }
            }
            troca (desordenado, i, lista);
        }
    }
    
    public void insercao (Lista lista) {

        
        for (int i = 1; i < lista.num_filmes; i++) {

            Filme filme = lista.array[i];
            int j = i - 1;

            while (j >= 0 && lista.array[j].getData_lancamento().after(filme.getData_lancamento())) {

                lista.array[j+1] = lista.array[j];
                j--;
            }
            lista.array[j+1] = filme;
        }
    }

    /*
        void shellsort () {

            int h = 1;
            do {
                h = 3*h + 1
            } while (h < n);

            do {
                h /= 3;
                for (int cor = 0; cor < h; cor++) {
                    insercao_por_cor(cor, h);
                }
            } while (h != 1);
        }

        void insercao_por_cor(int cor, int h) {
            for (int i = (h + cor); i < n; i+=h) {
                int temp = array[i];
                int j = i - h;
                while (j >= 0 && array[j] > temp) {
                    array[j+h] = array[j];
                    j -= h;
                }
                array[j+h] = temp;
            }
        }
    */
    
    public void quicksort (Lista lista) {
        quicksort(0, lista.num_filmes-1, lista);
    }

    public void quicksort (int esq, int dir, Lista lista) {
        int i = esq, j = dir;
        Filme pivo = lista.array[(esq+dir)/2];

        while (i <= j) {
            while (lista.array[i].getSituacao().compareTo(pivo.getSituacao()) < 0) {
                i++;
            }
            while (lista.array[j].getSituacao().compareTo(pivo.getSituacao()) > 0) {
                j--;
            }

            if (lista.array[j].getSituacao().compareTo(pivo.getSituacao()) == 0) {
                
                while (lista.array[i].getNome().compareTo(pivo.getNome()) < 0) {
                    i++;
                }
                while (lista.array[j].getNome().compareTo(pivo.getNome()) > 0) {
                    j--;
                }
            }
            if (i <= j) {
                troca(i, j, lista);
                i++;
                j--;
            }
        }

        if (esq < j) {
            quicksort(esq, j, lista);
        }

        if (i < dir) {
            quicksort(i, dir, lista);
        }
    }

    public void bubblesort (Lista lista) {

        for (int i = lista.num_filmes - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (lista.array[j].getDuracao() > lista.array[j+1].getDuracao()) {
                    troca (j, j + 1, lista);
                }
            }
        }
    }
    
    public void mergesort (Lista lista) {
        mergesort(lista, lista.num_filmes);
    }

    public void mergesort (Lista lista, int tam) {
        
        if (tam < 2) return;

        int meio = tam/2;

        Lista esq_lista = new Lista();
        esq_lista.construtor(meio);
        Lista dir_lista = new Lista();
        dir_lista.construtor(tam - meio);

        for (int i = 0, k = 0; i < tam; ++i) {
            if (i < meio) {
                esq_lista.array[i] = lista.array[i];
                esq_lista.num_filmes++;
            }
            else {
                dir_lista.array[k] = lista.array[i];
                dir_lista.num_filmes++;
                k++;
            }
        }

        mergesort (esq_lista, meio);
        mergesort (dir_lista, tam - meio);

        merge (lista, esq_lista, dir_lista, meio, tam - meio);
    }

    public void merge (Lista lista, Lista esq_lista, Lista dir_lista, int esq_tam, int dir_tam) {

        int i = 0, e = 0, d = 0;

        while (e < esq_tam && d < dir_tam) {
            if ((Float.compare(esq_lista.array[e].getOrcamento(), dir_lista.array[d].getOrcamento()) < 0) || (Float.compare(esq_lista.array[e].getOrcamento(), dir_lista.array[d].getOrcamento()) == 0 && esq_lista.array[e].getNome().compareTo(dir_lista.array[d].getNome()) < 0)) {
                lista.array[i++] = esq_lista.array[e++];
            }
            else {
                lista.array[i++] = dir_lista.array[d++];
            }
        }

        while (e < esq_tam) {
            lista.array[i++] = esq_lista.array[e++];
        }

        while (d < dir_tam) {
            lista.array[i++] = dir_lista.array[d++];
        }
    }
}