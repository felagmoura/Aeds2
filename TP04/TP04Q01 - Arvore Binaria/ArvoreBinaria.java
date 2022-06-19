//****************************** ARVORE BINARIA ******************************//
class ArvoreBinaria {
    No raiz;

    // --------------------------------------------------- //
    // ARVORE BINARIA FUNCTIONS
    // --------------------------------------------------- //

    public ArvoreBinaria () {
        raiz = null;
    }

    // --------------------------------------------------- //
    // INSERIR
    void inserir (Filme filme) throws Exception {
        raiz = inserir (filme, raiz);
    }
    
    No inserir (Filme filme, No ptr) throws Exception {
        if (ptr == null)
            ptr = new No(filme);
        else if (ptr.filme.getTitulo_original().compareTo(filme.getTitulo_original()) > 0)
            ptr.esq = inserir(filme, ptr.esq);
        else if (ptr.filme.getTitulo_original().compareTo(filme.getTitulo_original()) < 0)
            ptr.dir = inserir(filme, ptr.dir);
        else 
            throw new Exception("inserir");
        return ptr;
    }

    // --------------------------------------------------- //
    // PESQUISAR
    boolean pesquisar (String titulo_original) {
        System.out.println(titulo_original);
        System.out.print("=>raiz ");
        return pesquisar (titulo_original, raiz);
    }

    boolean pesquisar (String titulo_original, No ptr) {
        boolean encontrado;
        if (ptr == null)
            encontrado = false;
        else if (ptr.filme.getTitulo_original().compareTo(titulo_original) == 0)
            encontrado = true;
        else if (ptr.filme.getTitulo_original().compareTo(titulo_original) > 0) {
            System.out.print("esq ");
            encontrado = pesquisar(titulo_original, ptr.esq);
        }            
        else {
            System.out.print("dir ");
            encontrado = pesquisar(titulo_original, ptr.dir);
        }
        return encontrado;
    }

    // --------------------------------------------------- //
    // CAMINHAR
    void Central () {
        Central(raiz);
    }

    void Central (No ptr) {
        if (ptr != null) {
            Central (ptr.esq);
            ptr.filme.imprimir();
            Central (ptr.dir);
        }
    }

    // --------------------------------------------------- //
    // REMOVER
    void remover (String titulo_original) throws Exception {
        raiz = remover (titulo_original, raiz);
    }

    No remover (String titulo_original, No ptr) throws Exception {
        if (ptr == null) 
            throw new Exception("remover");
        else if (ptr.filme.getTitulo_original().compareTo(titulo_original) > 0)
            ptr.esq = remover (titulo_original, ptr.esq);
        else if (ptr.filme.getTitulo_original().compareTo(titulo_original) < 0)
            ptr.dir = remover (titulo_original, ptr.dir);
        else if (ptr.dir == null)
            ptr = ptr.esq;
        else if (ptr.esq == null)
            ptr = ptr.dir;
        else
            ptr.esq = getMaiorEsq (ptr.esq, ptr);
        return ptr;
    }

    No getMaiorEsq (No j, No i) {
        if (j.dir == null) {
            i.filme = j.filme;
            j = j.esq;
        }
        else 
            j.dir = getMaiorEsq (j.dir, i);
        return j;
    }

}
//****************************************************************************//
