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
}