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
    
    public void shellsort (Lista lista) {
        
        int h = 1;

        do {
            h = 3*h + 1;
        } while (h < lista.num_filmes);
        
        do {
            h /= 3;
            for (int cor = 0; cor < h; cor++) {
                insercao_por_cor(cor, h, lista);
            }

        } while (h != 1);
    }

    public void insercao_por_cor (int cor, int h, Lista lista) {
        
        for (int i = h + cor; i < lista.num_filmes; i+=h) {
            
            Filme temp = lista.array[i];
            int j = i - h;

            while (j >= 0 && lista.array[j].getIdioma().compareTo(temp.getIdioma()) > 0) {
                lista.array[j+h] = lista.array[j];
                j -= h;
            }

            if (j >= 0 && lista.array[j].getIdioma().compareTo(temp.getIdioma()) == 0) {
                while (j >= 0 && lista.array[j].getNome().compareTo(temp.getNome()) > 0) {
                    lista.array[j+h] = lista.array[j];
                    j -= h;
                }
            }

            lista.array[j+h] = temp;
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
        mergesort (0, lista.num_filmes - 1, lista);
    }

    public void mergesort (int esq, int dir, Lista lista) {
        if (esq < dir) {
            int meio = (esq + dir)/2;
            mergesort(esq, meio, lista);
            mergesort(meio + 1, dir, lista);
            intercalar(esq, meio, dir, lista);
        }
    }

    public void intercalar (int esq, int meio, int dir, Lista lista) {
        
        int tam_esq = meio + 1 - esq;
        int tam_dir = dir - meio;

        Lista arr_esq = new Lista();
        arr_esq.construtor(tam_esq+1);
        Lista arr_dir = new Lista();
        arr_dir.construtor(tam_dir+1);

        int i_esq, i_dir, i;

        
        Filme filme = new Filme();

        for (i_esq = 0; i_esq < tam_esq; i_esq++) {
            filme = lista.array[esq + i_esq];
            arr_esq.array[i_esq] = filme;
        }

        for (i_dir = 0; i_dir < tam_dir; i_dir++) {
            filme = lista.array[(meio+1)+i_dir];
            arr_dir.array[i_dir] = filme;
        }

        //arr_esq.array[i_esq].setOrcamento(Float.MAX_VALUE);
        //arr_dir.array[i_dir].setOrcamento(Float.MAX_VALUE);

        for (i_esq = 0, i_dir = 0, i = esq; i <= dir; i++) {
            
            if (arr_esq.array[i_esq].getOrcamento() <= arr_dir.array[i_dir].getOrcamento()) {
                lista.array[i] = arr_esq.array[i_esq++];
            }
            else {
                lista.array[i] = arr_dir.array[i_dir++];
            }
        }
    }

    public void countingsort (Lista lista) {

        int[] count = new int[maior_duracao(lista)+1];
        Lista ordenado = new Lista();
        ordenado.construtor(lista.num_filmes);
        ordenado.num_filmes = lista.num_filmes;
        

        for (int i = 0; i < count.length; i++) {            
            count[i] = 0;
        }
        
        
        for (int i = 0; i < lista.num_filmes; i++) {
            count[lista.array[i].getDuracao()]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i-1];
        }

        for (int i = lista.num_filmes - 1; i >= 0; i--) {
            ordenado.array[count[lista.array[i].getDuracao()]-1] = lista.array[i];
            count[lista.array[i].getDuracao()]--;
        }

        for (int i = 0; i < lista.num_filmes; i++) {
            lista.array[i] = ordenado.array[i];
        }
    }

    public int maior_duracao (Lista lista) {

        int maior = 0;
        for (int i = 0; i < lista.num_filmes; i++) {
            if (lista.array[i].getDuracao() > maior) {
                maior = lista.array[i].getDuracao();
            }
        }
        return maior;
    }
    /*
        void countingsort () {
            int[] count = new int[getMaior() + 1];
            int[] ordenado = new int[n];

            for (int i = 0; i < count.length; count[i] = 0, i++);

            for (int i = 0; i < n; count[array[i]]++, i++);

            for (int i = 1; i < count.length; count[i] += count[i-1], i++);

            for (int i = n-1; i >= 0; ordenado[count[array[i]]-1] = array[i], count[array[i]]--, i--);
        }
    */
}