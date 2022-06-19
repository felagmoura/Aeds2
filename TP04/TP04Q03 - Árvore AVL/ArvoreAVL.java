//****************************** ARVORE AVL ******************************//
class ArvoreAVL {
    No raiz;

    // --------------------------------------------------- //
    // ARVORE AVL FUNCTIONS
    // --------------------------------------------------- //

    public ArvoreAVL () {
        raiz = null;
    }

    // --------------------------------------------------------- //
    // INSERIR
    void inserir (Filme filme) throws Exception {
        raiz = inserir (filme, raiz);
    }

    No inserir (Filme filme, No ptr) throws Exception {
        if (ptr == null) 
            ptr = new No (filme);
        else if (ptr.filme.getTitulo_original().compareTo(filme.getTitulo_original()) > 0)
            ptr.esq = inserir (filme, ptr.esq);
        else if (ptr.filme.getTitulo_original().compareTo(filme.getTitulo_original()) < 0)
            ptr.dir = inserir (filme, ptr.dir);
        else 
            throw new Exception("inserir");
        return balancear (ptr);
    }
    
    // --------------------------------------------------------- //
    // REMOVER
    void remover (String titulo) throws Exception {
        raiz = remover (titulo, raiz);
    }

    No remover (String titulo, No ptr) throws Exception {
        if (ptr == null)
            throw new Exception("remover");
        else if (ptr.filme.getTitulo_original().compareTo(titulo) > 0)
            ptr.esq = remover (titulo, ptr.esq);
        else if (ptr.filme.getTitulo_original().compareTo(titulo) < 0)
            ptr.dir = remover (titulo, ptr.dir);
        else if (ptr.dir == null) 
            ptr = ptr.esq;
        else if (ptr.esq == null)
            ptr = ptr.dir;
        else 
            ptr.esq = getMaior (ptr, ptr.esq);
        return balancear (ptr);
    }

    No getMaior (No i, No j) {
        if (j.dir == null) {
            i.filme = j.filme;
            j = j.esq;
        }
        else 
            j.dir = getMaior (i, j.dir);
        return j;
    }

    // --------------------------------------------------------- //
    // PESQUISAR
    boolean pesquisar (String titulo) {
        System.out.println(titulo);
        System.out.print("raiz ");
        return pesquisar (titulo, raiz);
    }

    boolean pesquisar (String titulo, No ptr) {
        boolean encontrado;
        if (ptr == null) 
            encontrado = false;
        else if (ptr.filme.getTitulo_original().compareTo(titulo) == 0)
            encontrado = true;
        else if (ptr.filme.getTitulo_original().compareTo(titulo) > 0) {
            System.out.print("esq ");
            encontrado = pesquisar (titulo, ptr.esq);
        }
        else {
            System.out.print("dir ");
            encontrado = pesquisar (titulo, ptr.dir);
        }
        return encontrado;
    }

    // --------------------------------------------------------- //
    // BALANCEAR
    No balancear (No no) throws Exception {
        if (no != null) {
            int fator = no.getNivel(no.dir) - no.getNivel(no.esq);
            if (Math.abs(fator) <= 1) {
                no.setNivel();
            }

            else if (fator == 2) {
                int fatorFilhoDir = no.getNivel(no.dir.dir) - no.getNivel(no.dir.esq);
                if (fatorFilhoDir == -1) 
                    no.dir = rotacionarDir(no.dir);
                no = rotacionarEsq(no);
            }

            else if (fator == -2) {
                int fatorFilhoEsq = no.getNivel(no.esq.dir) - no.getNivel(no.esq.esq);
                if (fatorFilhoEsq == 1)
                    no.esq = rotacionarEsq(no.esq);
                no = rotacionarDir(no); 
            }

            else {
                throw new Exception("balancear");
            }
        }
        return no;
    }

    No rotacionarEsq (No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;

        no.setNivel();
        noDir.setNivel();

        return noDir;
    }

    No rotacionarDir (No no) {
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;
        
        no.setNivel();
        noEsq.setNivel();

        return noEsq;
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
//************************************************************************//
