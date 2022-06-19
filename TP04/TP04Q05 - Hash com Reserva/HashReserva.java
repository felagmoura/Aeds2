//************************** HASH COM RESERVA ****************************//
public class HashReserva {
    Filme[] tabela;
    final Filme NULO;
    int tam_tab, tam_res, tamanho, reserva;
    
    //----------------------------------------------------//
    // FUNCOES
    //----------------------------------------------------//

    //----------------------------------------------------//
    // CONSTRUTORES
    public HashReserva () {
        this(21, 9);
    }

    public HashReserva (int tam_tab, int tam_res) {
        this.tam_tab = tam_tab;
        this.tam_res = tam_res;
        this.tamanho = tam_tab + tam_res;
        this.tabela = new Filme [this.tamanho];
        this.NULO = new Filme();
        for (int i = 0; i < tam_tab; i++)
            tabela[i] = NULO;
        this.reserva = 0;
    }

    //----------------------------------------------------//
    // HASH
    public int hash (String titulo) {
        return hash (titulo, 0);
    }
    
    public int hash (String titulo, int sum_ASCII) {
        for (int i = 0; i < titulo.length(); i++)
            sum_ASCII += (int)titulo.charAt(i);
        //System.out.println("[" + sum_ASCII % tam_tab + "] " + titulo);
        return sum_ASCII % tam_tab; 
    }

    //----------------------------------------------------//
    // INSERIR
    public void inserir (Filme filme) {
        if (filme != null) {
            int pos = hash(filme.getTitulo_original());
            if (tabela[pos] == NULO)
                tabela[pos] = filme;
            else if (reserva < tam_res)
                tabela[tam_tab + reserva++] = filme;
        }
    }

    //----------------------------------------------------//
    // PESQUISAR
    public void pesquisar (String titulo) {
        System.out.println("=> " + titulo);
        pesquisar(titulo, hash(titulo), false);
    }

    public void pesquisar (String titulo, int pos, boolean encontrado) {
        if (tabela[pos].getTitulo_original().compareTo(titulo) == 0)
            encontrado = true;
        else if (tabela[pos] != NULO) {
            for (int i = 0; i < reserva; i++) {
                pos = tam_tab + i;
                if (tabela[pos].getTitulo_original().compareTo(titulo) == 0) {
                    encontrado = true;
                    i = reserva;
                }
            }
        }
        if (encontrado) 
            System.out.println("Posicao: " + pos);
        else 
            System.out.println("NAO");
    }

    //----------------------------------------------------//
    // IMPRIMIR
    public void imprimir () {
        //System.out.println();
        for (int i = 0; i < tamanho; i++) {
            if (tabela[i] != NULO)
                System.out.println("[" + i + "] " + tabela[i].getTitulo_original());
        }
    }
}
//************************************************************************//