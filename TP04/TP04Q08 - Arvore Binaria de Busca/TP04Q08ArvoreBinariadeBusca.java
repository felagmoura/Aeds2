import java.util.Scanner;

//****************************** NO ******************************//
class No {
    int num;
    No esq;
    No dir;

    // --------------------------------------------------- //
    // NO FUNCTIONS
    // --------------------------------------------------- //

    public No (int num) {
        this.num = num;
        this.esq = null;
        this.dir = null;
    }

    public No (int num, No esq, No dir) {
        this.num = num;
        this.esq = esq;
        this.dir = dir;
    }
    // -------------------------------------------------------- //
}

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
    void inserir (int num) throws Exception {
        raiz = inserir(num, raiz);
    }

    No inserir (int num, No ptr) throws Exception {
        if (ptr == null)
            ptr = new No (num);
        else if (num < ptr.num)
            ptr.esq = inserir (num, ptr.esq);
        else if (num > ptr.num)
            ptr.dir = inserir (num, ptr.dir);
        else
            throw new Exception ("inserir");
        return ptr;
    }

    // --------------------------------------------------- //
    // CAMINHAR
    void pre () {
        System.out.print("Pre.: ");
        pre (raiz);
        System.out.println();
    }

    void pre (No ptr) {
        if (ptr != null) {
            System.out.print(ptr.num + " ");
            pre(ptr.esq);
            pre(ptr.dir);
        }
    }

    void in () {
        System.out.print("In..: ");
        in (raiz);
        System.out.println();
    }

    void in (No ptr) {
        if (ptr != null) {
            in(ptr.esq);
            System.out.print(ptr.num + " ");
            in(ptr.dir);
        }
    }

    void pos () {
        System.out.print("Post: ");
        pos (raiz);
        System.out.println("\n");
    }

    void pos (No ptr) {
        if (ptr != null) {
            pos(ptr.esq);
            pos(ptr.dir);
            System.out.print(ptr.num + " ");
        }
    }

}

//*********************** TP04Q08 - ARVORE BINARIA DE BUSCA ***********************//
public class TP04Q08ArvoreBinariadeBusca {
    public static void main (String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        int casos = scanner.nextInt();
        for (int i = 1; i <= casos; i++) {
            System.out.println("Case "+ i + ":");
            ArvoreBinaria arvore = new ArvoreBinaria();

            int max_itens = scanner.nextInt();
            for (int j = 0; j < max_itens; j++) {
                int num = scanner.nextInt();
                arvore.inserir(num);
            }

            arvore.pre();
            arvore.in();
            arvore.pos();    
        }
        scanner.close();

    }
}
