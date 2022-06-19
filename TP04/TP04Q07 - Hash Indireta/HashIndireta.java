//************************ HASH INDIRETA COM LISTA  *************************//
public class HashIndireta {
    Lista[] tabela;
    int tam;

    //--------------------------------------------------//
    // FUNCOES
    //--------------------------------------------------//

    //--------------------------------------------------//
    // CONSTRUTORES
    public HashIndireta () {
        this(21);
    }

    public HashIndireta (int tam) {
        this.tabela = new Lista[tam];
        this.tam = tam;
        for (int i = 0; i < tam; i++)
            tabela[i] = new Lista();
    }

    //--------------------------------------------------//
    // INSERIR
    public void inserir (Filme filme) {
        inserir (filme, hash(filme.getTitulo_original()));
    }

    private void inserir (Filme filme, int pos) {
        tabela[pos].inserirInicio(filme);        
    }

    //--------------------------------------------------//
    // HASH
    public int hash (String titulo) {
        return hash (titulo, 0);
    }

    private int hash (String titulo, int sum_ASCII) {
        for (int i = 0; i < titulo.length(); i++)
            sum_ASCII += (int) titulo.charAt(i);
        return sum_ASCII % tam;
    }

    //--------------------------------------------------//
    // PESQUISAR
    public void pesquisar (String titulo) {
        pesquisar (titulo, hash(titulo), false);
    }

    private void pesquisar (String titulo, int pos, boolean encontrado) {
        System.out.println("=> " + titulo);
        if (tabela[pos].pesquisar(titulo, encontrado))
            System.out.println("Posicao: " + pos);
        else 
            System.out.println("NAO");
    }
}
//***************************************************************************//
