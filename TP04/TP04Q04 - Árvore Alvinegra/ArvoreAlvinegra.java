//****************************** ARVORE ALVINEGRA ******************************//
public class ArvoreAlvinegra {
    private No raiz;

    // --------------------------------------------------- //
    // ARVORE ALVINEGRA FUNCTIONS
    // --------------------------------------------------- //

    public ArvoreAlvinegra () {
        raiz = null;
    }

    // --------------------------------------------------------- //
    // INSERIR
    public void inserir (Filme filme) throws Exception {
        // Arvore vazia
        if (raiz == null)
            raiz = new No(filme);
        // Somente a raiz
        else if (raiz.esq == null && raiz.dir == null) {
            if (raiz.filme.getTitulo_original().compareTo(filme.getTitulo_original()) > 0)
                raiz.esq = new No(filme);
            else if (raiz.filme.getTitulo_original().compareTo(filme.getTitulo_original()) < 0)
                raiz.dir = new No(filme);
        }
        // Raiz e um elemento a direita
        else if (raiz.esq == null) {
            if (raiz.filme.getTitulo_original().compareTo(filme.getTitulo_original()) > 0)
                raiz.esq = new No(filme);
            else if (raiz.dir.filme.getTitulo_original().compareTo(filme.getTitulo_original()) > 0) {
                raiz.esq = new No(raiz.filme);
                raiz.filme = filme;
            }
            else {
                raiz.esq = new No(raiz.filme);
                raiz.filme = raiz.dir.filme;
                raiz.dir.filme = filme;
            }
            raiz.esq.cor = raiz.dir.cor = false;
        }
        // Raiz e um elemento a esquerda
        else if (raiz.dir == null) {
            if (raiz.filme.getTitulo_original().compareTo(filme.getTitulo_original()) < 0)
                raiz.dir = new No(filme);
            else if (raiz.esq.filme.getTitulo_original().compareTo(filme.getTitulo_original()) < 0) {
                raiz.dir = new No(raiz.filme);
                raiz.filme = filme;
            }
            else {
                raiz.dir = new No(raiz.filme);
                raiz.filme = raiz.esq.filme;
                raiz.esq.filme = filme;
            } 
            raiz.esq.cor = raiz.dir.cor = false;
        }
        // Arvore com mais de três filmes
        else {
            inserir(filme, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    private void inserir (Filme filme, No bisavo, No avo, No pai, No ptr) throws Exception {
        if (ptr == null) {
            if (pai.filme.getTitulo_original().compareTo(filme.getTitulo_original()) > 0)
                ptr = pai.esq = new No(filme, true);
            else if (pai.filme.getTitulo_original().compareTo(filme.getTitulo_original()) < 0)
                ptr = pai.dir = new No(filme, true);
            if (pai.cor == true)
                balancear(bisavo, avo, pai, ptr);
        }
        else {
            if (ptr.esq != null && ptr.dir != null && ptr.esq.cor == true && ptr.dir.cor == true) {
                ptr.cor = true;
                ptr.esq.cor = ptr.dir.cor = false;
                if (ptr == raiz)
                    ptr.cor = false;
                else if (pai.cor == true)
                    balancear(bisavo, avo, pai, ptr);
            }
            if (ptr.filme.getTitulo_original().compareTo(filme.getTitulo_original()) > 0)
                inserir(filme, avo, pai, ptr, ptr.esq);
            else if (ptr.filme.getTitulo_original().compareTo(filme.getTitulo_original()) < 0)
                inserir(filme, avo, pai, ptr, ptr.dir);
            else
                throw new Exception("inserir");
        }
    }
    
    // --------------------------------------------------------- //
    // REMOVER

    // --------------------------------------------------------- //
    // PESQUISAR
    public boolean pesquisar (String titulo) {
        System.out.println(titulo);
        System.out.print("raiz ");
        return pesquisar (titulo, raiz);
    }

    private boolean pesquisar (String titulo, No ptr) {
        boolean encontrado;
        if (ptr == null)
            encontrado = false;
        else if (ptr.filme.getTitulo_original().compareTo(titulo) == 0)
            encontrado = true;
        else if (ptr.filme.getTitulo_original().compareTo(titulo) > 0) {
            System.out.print("esq ");
            encontrado = pesquisar(titulo, ptr.esq);
        }
        else {
            System.out.print("dir ");
            encontrado = pesquisar(titulo, ptr.dir);    
        }
        return encontrado;
    }
    // --------------------------------------------------------- //
    // BALANCEAR
    private void balancear (No bisavo, No avo, No pai, No ptr) {
        if (pai.cor == true) {
            if (pai.filme.getTitulo_original().compareTo(avo.filme.getTitulo_original()) > 0) {
                if (ptr.filme.getTitulo_original().compareTo(pai.filme.getTitulo_original()) > 0)
                    avo = rotacaoEsq(avo);
                else
                    avo = rotacaoDirEsq(avo);
            } 
            else {
                if (ptr.filme.getTitulo_original().compareTo(pai.filme.getTitulo_original()) < 0)
                    avo = rotacaoDir(avo);
                else 
                    avo = rotacaoEsqDir(avo);
            }
            if (bisavo == null)
                raiz = avo;
            else if (avo.filme.getTitulo_original().compareTo(bisavo.filme.getTitulo_original()) < 0)
                bisavo.esq = avo;
            else 
                bisavo.dir = avo;
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        }
    }

    private No rotacaoEsq (No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;

        return noDir;
    }

    private No rotacaoDir (No no) {
        No noEsq = no.esq;
        No noEsqDir = no.esq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private No rotacaoEsqDir (No no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }

    private No rotacaoDirEsq (No no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }
    // --------------------------------------------------------- //
    // CAMINHAR
    void central () {
        central (raiz);
    }

    void central (No ptr) {
        if (ptr != null) {
            central (ptr.esq);
            System.out.println(ptr.filme.getTitulo_original());
            //ptr.filme.imprimir();
            central (ptr.dir);
        }
    }

}
//******************************************************************************//
