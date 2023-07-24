import java.util.Scanner;

class Celula {
    public int num;
    public Celula dir, esq, sup, inf;

    public Celula() {
        this.num = -1;
    }
    
    public Celula (int num) {
        this.num = num;
        this.dir = null;
        this.esq = null;
        this.sup = null;
        this.inf = null;
    }
}

class Matriz {
    private Celula inicio;
    private int linhas, colunas;

    public Matriz() {}

    public Matriz(int linhas, int colunas) {
        
        this.linhas = linhas;
        this.colunas = colunas;
        
        inicio = new Celula();
        Celula tmp = new Celula();
        
        tmp = inicio;
        
        //primeira linha
        for (int i = 0; i < colunas; i++) {
            tmp.dir = new Celula();
            tmp.dir.esq = tmp;
            tmp = tmp.dir;
        }

        tmp = inicio;

        for (int l = 1; l < linhas; l++, tmp = tmp.inf) {
            Celula i = tmp;
            i.inf = new Celula();
            i.inf.sup = i;
            Celula j = i.inf;
            for (int c = 1; c < colunas; c++, j = j.dir) {
                i = i.dir;
                i.inf = new Celula();
                i.inf.sup = i;
                i.inf.esq = j;
                j.dir = i.inf;
            }
        }
    }

    public void inserir(int num) {

        Celula tmp = inicio;

        for (int l = 0; l < linhas; l++, tmp = tmp.inf) {
            Celula i = tmp;
            for (int c = 0; c < colunas; c++, i = i.dir) {
                if (i.num < 0) {
                    i.num = num;
                    c = colunas;
                    l = linhas;
                }
            }
        }
    }

    public void imprimir () {

        Celula tmp = inicio;

        for (int l = 0; l < linhas; l++, tmp = tmp.inf) {
            Celula i = tmp;
            for (int c = 0; c < colunas; c++, i = i.dir) {
                System.out.print(i.num + " ");
            }
            System.out.println();
        }
    }

    public void imprimirDiagonalPrincipal () {
        
        Celula tmp = inicio;

        for (int l = 0; l < linhas; l++, tmp = tmp.inf) {
            Celula i = tmp;
            for (int c = 0; c < colunas; c++, i = i.dir) {
                if (l == c) {
                    System.out.print(i.num + " ");
                }
                
            }
        }

        System.out.println();
    }

    public void imprimirDiagonalSecundaria () {
        
        Celula tmp = inicio;

        for (int l = 0; l < linhas; l++, tmp = tmp.inf) {
            Celula i = tmp;
            for (int c = 0; c < colunas; c++, i = i.dir) {
                if ((l + c) == (linhas - 1)) {
                    System.out.print(i.num + " ");
                }
                
            }
        }

        System.out.println();
    }

    public Matriz soma (Matriz matriz_2) {

        Matriz resultado = new Matriz(linhas, colunas);

        Celula pri = inicio;
        Celula seg = matriz_2.inicio;
        Celula resp = resultado.inicio;

        for (int l = 0; l < linhas; l++, pri = pri.inf, seg = seg.inf, resp = resp.inf) {
            
            Celula p = pri;
            Celula s = seg;
            Celula r = resp;
            
            for (int c = 0; c < colunas; c++, p = p.dir, s = s.dir, r = r.dir) {
                r.num = s.num + p.num;
            }
        }

        return resultado;
    }
 
 
    public Matriz multiplicacao (Matriz matriz_2) {

        Matriz resultado = new Matriz (linhas, matriz_2.colunas);

        Celula pri = inicio;
        Celula resp = resultado.inicio;

        for (int l = 0; l < linhas; l++, pri = pri.inf, resp = resp.inf) {
            
            Celula cp = pri;
            Celula seg = matriz_2.inicio;
            Celula r = resp;
            
            for (int c = 0; c < colunas; c++, cp = cp.dir, seg = seg.dir, r = r.dir) {
                
                Celula p = pri;
                Celula s = seg;

                for (int col = 0; col < colunas; col++, p = p.dir, s = s.inf) {

                    r.num += p.num * s.num;
                }
                r.num++;
            }
        }
        return resultado;
    }
}

public class TP03Q16MatrizDinamica {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in, "UTF8");
        
        int num_casos = scanner.nextInt();

        for (int casos = 0; casos < num_casos; casos++) {
            
            /************************ PRIMEIRA MATRIZ ************************/
            int linhas = scanner.nextInt();
            int colunas = scanner.nextInt();

            Matriz matriz = new Matriz(linhas, colunas);

            for (int cont = 0; cont < linhas * colunas; cont++) {

                int valor = scanner.nextInt();
                matriz.inserir(valor);
            }
            
            /************************ SEGUNDA MATRIZ ************************/
            linhas = scanner.nextInt();
            colunas = scanner.nextInt();

            Matriz matriz_2 = new Matriz(linhas, colunas);

            for (int cont = 0; cont < linhas * colunas; cont++) {
                int valor = scanner.nextInt();
                matriz_2.inserir(valor);
            }
            
            matriz.imprimirDiagonalPrincipal();
            matriz.imprimirDiagonalSecundaria();

            Matriz soma = matriz.soma(matriz_2);
            soma.imprimir();

            Matriz multi = matriz.multiplicacao(matriz_2);
            multi.imprimir();
        }
        scanner.close();
    }
}