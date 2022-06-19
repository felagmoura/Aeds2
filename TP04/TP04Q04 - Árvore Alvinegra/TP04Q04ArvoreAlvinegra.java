import java.io.IOException;
import java.util.Scanner;

public class TP04Q04ArvoreAlvinegra {
    
    static ArvoreAlvinegra alvinegra = new ArvoreAlvinegra();
    static Scanner scanner = new Scanner(System.in, "UTF8");
    static public void main (String[] args) throws IOException, Exception {
        
        preencher_arvore ();
        busca_filmes ();
    }

    // -------------------------------------------------------------- //
    // FUNCOES
    // -------------------------------------------------------------- //
    // PREENCHE ALVINEGRA COM OS FILMES LISTADOS
    static public void preencher_arvore () throws IOException, Exception {
        String input = new String();
        preencher_arvore(input);
    }

    static private void preencher_arvore (String input) throws IOException, Exception {
        do {
            input = scanner.nextLine();
            if (fim(input)) continue;
            Filme filme = new Filme();
            filme.le_html(input);
            alvinegra.inserir(filme);
        } while (!fim(input));
    }

    // -------------------------------------------------------------- //
    // PERCORRE A ALVINEGRA EM BUSCA DOS FILMES PESQUISADOS
    static public void busca_filmes () {
        String titulo = new String();
        busca_filmes(titulo);
    }

    static private void busca_filmes (String titulo) {
        do {
            titulo = scanner.nextLine();
            if (fim(titulo)) continue;
            if (alvinegra.pesquisar(titulo))
                System.out.println("SIM");
            else
                System.out.println("NAO");
        } while (!fim(titulo));
    }
    // -------------------------------------------------------------- //
    // VERIFICA SE A ENTRADA FOR "FIM"
    public static boolean fim (String input) {
        return (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M');
    }
}
