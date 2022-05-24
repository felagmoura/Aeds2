class Pilha {
    Filme[] array;
    int num_filmes = 0;


    public void construtor (int tamanho) {
        array = new Filme[tamanho];
    }

    public void inserir (Filme filme) throws Exception {

        if (num_filmes >= array.length) {
            throw new Exception("Erro em I");
        }

        array[num_filmes] = filme;
        num_filmes++;
    }

    public Filme remover() throws Exception {
        
        if (num_filmes == 0) {
            throw new Exception("Erro em R");
        }

        return array[--num_filmes];
    }

    public void imprimir ()
    {
        for (int i = 0; i < num_filmes; i++) {
            System.out.println ("[" + i + "] " + array[i].getNome() + " " + array[i].getTitulo_original() + " " + array[i].getData_lancamento() + " " + array[i].getDuracao() + " " + array[i].getGenero() + " " + array[i].getIdioma() + " " + array[i].getSituacao() + " " + array[i].getOrcamento()  + " " + array[i].getChaves());
        }
    }
}
