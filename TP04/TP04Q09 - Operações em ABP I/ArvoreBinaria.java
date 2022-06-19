//************************* ARVORE BINARIA *************************//
public class ArvoreBinaria {
    private No raiz;

    // --------------------------------------------------- //
    // ARVORE ALVINEGRA FUNCTIONS
    // --------------------------------------------------- //

    public ArvoreBinaria () {
        raiz = null;
    }

    // --------------------------------------------------------- //
    // INSERIR
    public void inserir (char elemento) throws Exception {
        raiz = inserir(elemento, raiz);
    }

    private No inserir (char elemento, No ptr) throws Exception {
        if (ptr == null) 
            ptr = new No (elemento);
        else if (elemento < ptr.elemento) 
            ptr.esq = inserir (elemento, ptr.esq);
        else if (elemento > ptr.elemento) 
            ptr.dir = inserir (elemento, ptr.dir);
        else 
            throw new Exception ("inserir");
        return ptr;
    }
    
    // --------------------------------------------------------- //
    // REMOVER
    public void remover (char elemento) throws Exception {
        raiz = remover (elemento, raiz);
    }

    private No remover (char elemento, No ptr) throws Exception {
        if (ptr == null)
            throw new Exception("remover");
        else if (elemento < ptr.elemento)
            ptr.esq = remover (elemento, ptr.esq);
        else if (elemento > ptr.elemento)
            ptr.dir = remover (elemento, ptr.dir);
        else if (ptr.dir == null)
            ptr = ptr.esq;
        else if (ptr.esq == null)
            ptr = ptr.dir;
        else 
            ptr.esq = getMaior(ptr, ptr.esq);
        return ptr;
    }

    public No getMaior (No i, No j) {
        if (j.dir == null) {
            i.elemento = j.elemento;
            j = j.esq;
        } else 
            j.dir = getMaior(i, j.dir);
        return j;
    }

    // --------------------------------------------------------- //
    // PESQUISAR
    public boolean pesquisar (char elemento) {
        return pesquisar(elemento, raiz);
    }

    private boolean pesquisar (char elemento, No ptr) {
        boolean encontrado;
        if (ptr == null) 
            encontrado = false;
        else if (elemento == ptr.elemento)
            encontrado = true;
        else if (elemento < ptr.elemento)
            encontrado = pesquisar(elemento, ptr.esq);
        else 
            encontrado = pesquisar(elemento, ptr.dir);
        return encontrado;
    }
    
    // --------------------------------------------------------- //
    // CAMINHAR
    public void prefixa () {
        prefixa (raiz);
        System.out.println();
    }

    private void prefixa (No ptr) {
        if (ptr != null) {
            System.out.print(ptr.elemento + " ");
            prefixa (ptr.esq);
            prefixa (ptr.dir);
        }
    }

    public void infixa () {
        infixa (raiz);
        System.out.println();
    }

    private void infixa (No ptr) {
        if (ptr != null) {
            infixa (ptr.esq);
            System.out.print(ptr.elemento + " ");
            infixa (ptr.dir);
        }
    }

    public void posfixa () {
        posfixa (raiz);
        System.out.println();
    }

    private void posfixa (No ptr) {
        if (ptr != null) {
            posfixa (ptr.esq);
            posfixa (ptr.dir);
            System.out.print(ptr.elemento + " ");
        }
    }


}
//*****************************************************************//
