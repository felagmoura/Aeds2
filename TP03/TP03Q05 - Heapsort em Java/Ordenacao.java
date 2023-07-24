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

    public void heapsort (Lista lista) throws Exception {

        for (int tamanho = 2; tamanho < lista.num_filmes; tamanho++) {
            constroi_heap(lista, tamanho);
        }

        int tamanho = lista.num_filmes - 1;
/*
        while (tamanho > 1) {
            troca(1, tamanho--, lista);
            reconstroi_heap(lista, tamanho);
        } 
        */
    }

    public void constroi_heap (Lista lista, int tamanho) {
        /*
        int i = tamanho;

        String genero1 = lista.array[i].getGenero();
        String genero2 = lista.array[i/2].getGenero();
        
        while (i > 1 && genero1.charAt(0) > genero2.charAt(0)) {
            troca(i, i/2, lista);
            i /= 2;
        }
        
        if (genero1.charAt(0) == genero2.charAt(0)) {
            while (i > 1 && genero1.substring(1).compareTo(genero2.substring(1)) > 0) {
                troca(i, i/2, lista);
                i /= 2;
            }    
        }
        */

        int i = tamanho;
        String[] generos = lista.array[i].getGenero().split(",");
        String[] generos2 = lista.array[i/2].getGenero().split(",");

        while (i > 1 && generos[0].compareTo(generos2[0]) > 0 || (generos[0].compareTo(generos2[0]) == 0 && lista.array[i].getNome().compareTo(lista.array[i/2].getNome()) > 0 )) {
            troca(i, i/2, lista);
            i /= 2;
        }
    }

    public void reconstroi_heap (Lista lista, int tamanho) {
        
        int i = 1;

        String[] generos;
        String[] generos_filho;

        while (tem_filho(i, tamanho)) {
            
            int filho = get_maior_filho(lista, i, tamanho);
            
            generos = lista.array[i].getGenero().split(",");
            generos_filho = lista.array[filho].getGenero().split(",");

            if (generos[0].compareTo(generos_filho[0]) < 0) {
                troca(i, filho, lista);
                i = filho;
            }
            else if (generos[0].compareTo(generos_filho[0]) == 0) {
                
                int index = 1;
                while ((index < generos.length || index < generos_filho.length) && generos[index].compareTo(generos_filho[index]) == 0) {
                    index++;
                }
    
                if (generos[index].compareTo(generos_filho[index]) < 0) {
                    troca(i, filho, lista);
                    i = filho;
                }
                else {
                    i = tamanho;
                }
        
            }
            else {
                i = tamanho;
            }
        }
    }

    public boolean tem_filho (int i, int tamanho) {
        return i <= tamanho/2;
    }

    public int get_maior_filho (Lista lista, int i, int tamanho) {
        
        int filho;
        
        String[] generos1 = lista.array[2*i].getGenero().split(",");
        String[] generos2 = lista.array[2*i+1].getGenero().split(",");

        if (2 * i == tamanho || generos1[0].compareTo(generos2[0]) > 0) {
            filho = 2*i;
        }
        else if (generos1[0].compareTo(generos2[0]) == 0) {
            int index = 1;
            while ((index < generos1.length || index < generos2.length) && generos1[index].compareTo(generos2[index]) == 0) {
                index++;
            }
            if (generos1[index].compareTo(generos2[index]) > 0) {
                filho = 2*i;
            }
            else {
                filho = 2 * i + 1;
            }
        }
        else {
            filho = 2 * i + 1;
        }
        
        return filho;
    } 

}