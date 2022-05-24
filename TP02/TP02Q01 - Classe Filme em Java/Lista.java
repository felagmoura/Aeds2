public class Lista {
    Filme[] array;
    int num_filmes;


    public void lista_constr (int tamanho) {
        array = new Filme[tamanho];
    }

    public void inserirInicio (Filme filme) throws Exception {

        if (num_filmes >= array.length)
        {
            throw new Exception("erro em II");
        }

        for (int i = num_filmes; i > 0; i--)
        {
            array[i] = array[i-1];
        }

        array[0] = filme;
        num_filmes++;
    }

    public void inserirFim (Filme filme) throws Exception {

        if (num_filmes >= array.length)
        {
            throw new Exception("erro em IF");
        }

        array[num_filmes++] = filme;
    }

    public void inserir (Filme filme, int pos) throws Exception {

        if (num_filmes >= array.length || pos < 0 || num_filmes < pos)
        {
            throw new Exception("erro em I");
        }

        for (int i = num_filmes; i > pos; i--)
        {
            array[i] = array[i-1];
        }

        array[pos] = filme;
        num_filmes++;
    }

    public Filme removerInicio () throws Exception{
        
        Filme filme_removido;

        if (num_filmes == 0)
        {
            throw new Exception("erro em RI");
        }

        filme_removido = array[0];
        num_filmes--;

        for (int i = 0; i < num_filmes; i++)
        {
            array[i] = array[i+1];
        }
        return filme_removido;
    }

    public Filme removerFim () throws Exception {

        if (num_filmes == 0)
        {
            throw new Exception("erro em RF");
        }

        return array[num_filmes--];
    }

    public Filme remover (int pos) throws Exception {

        Filme filme_removido;

        if (num_filmes == 0 || pos < 0 || num_filmes < pos)
        {
            throw new Exception("erro em R");
        }

        filme_removido = array[pos];
        num_filmes--;

        for (int i = pos; i < num_filmes; i++)
        {
            array[i] = array[i+1];
        }

        return filme_removido;
    }

    public void imprimir ()
    {
        for (int i = 0; i < num_filmes; i++)
        {
            System.out.println (array[i].getNome() + " " + array[i].getTitulo_original() + " " + array[i].getData_lancamento() + " " + array[i].getDuracao() + " " + array[i].getGenero() + " " + array[i].getIdioma() + " " + array[i].getSituacao() + " " + array[i].getOrcamento()  + " " + array[i].getChaves());
        }
 
    }
}


