//************************** HASH COM REHASH ****************************//
public class HashRehash {
    Filme[] tabela;
    int tam;
    final Filme NULO;

    //----------------------------------------------------//
    // FUNCOES
    //----------------------------------------------------//

    //----------------------------------------------------//
    // CONSTRUTORES
    public HashRehash () {
        this(21);
    }

    public HashRehash (int tam) {
        this.tam = tam;
        this.tabela = new Filme[tam];
        this.NULO = new Filme();
        for (int i = 0; i < tam; i++)
            tabela[i] = NULO;
    }

    //----------------------------------------------------//
    // INSERIR
    public void inserir (Filme filme) throws Exception {
        inserir(filme, 0);
    }

    private void inserir (Filme filme, int pos) throws Exception {
        if (filme != null) {
            pos = hash(filme.getTitulo_original());
            if (tabela[pos] == NULO)
                tabela[pos] = filme;
            else {
                pos = rehash(filme.getTitulo_original());                    
                if (tabela[pos] == NULO)
                    tabela[pos] = filme;
            }            
        }
    }

    //----------------------------------------------------//
    // HASH E REHASH
    public int hash (String titulo) {
        return hash (titulo, 0);
    }

    private int hash (String titulo, int sum_ASCII) {
        for (int i = 0; i < titulo.length(); i++)
            sum_ASCII += (int) titulo.charAt(i);
        return sum_ASCII % tam;
    }

    public int rehash (String titulo) {
        return hash (titulo, 1);
    }

    //----------------------------------------------------//
    // PESQUISAR
    public void pesquisar (String titulo) {
        pesquisar(titulo, hash(titulo), false);
    }

    private void pesquisar (String titulo, int pos, boolean encontrado) {
        if (tabela[pos].getTitulo_original().compareTo(titulo) == 0)
            encontrado = true;
        else if (tabela[pos] != NULO) {
            pos = rehash(titulo);
            if (tabela[pos].getTitulo_original().compareTo(titulo) == 0)
                encontrado = true;
        }
        imprime_pesquisado (titulo, pos, encontrado);
    }

    private void imprime_pesquisado (String titulo, int pos, boolean encontrado) {
        System.out.println("=> " + titulo);
        if (encontrado)
            System.out.println("Posicao: " + pos);
        else
            System.out.println("NAO");
    }
}
//***********************************************************************//
