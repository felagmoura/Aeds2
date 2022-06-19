import java.io.IOException;
import java.util.Scanner;

public class TP04Q03ArvoreAVL {

    static Scanner scanner = new Scanner(System.in, "UTF8");
    static ArvoreAVL arvore = new ArvoreAVL();
    public static void main (String[] args) throws Exception, IOException {

        preenche_arvore ();
        executa_comandos ();
        pesquisa_filmes ();
        scanner.close();
    }

    // -------------------------------------------------------------- //
    // FUNCOES
    // -------------------------------------------------------------- //
    // PREENCHE AVL COM OS FILMES LISTADOS
    public static void preenche_arvore () throws Exception, IOException {
        String input = new String();
        preenche_arvore(input);
    }

    private static void preenche_arvore (String input) throws Exception, IOException {
        do {
            input = scanner.nextLine();
            if (fim(input)) continue;

            Filme filme = new Filme();
            filme.le_html(input);
            arvore.inserir(filme);
        } while (!fim(input));
    }

    // -------------------------------------------------------------- //
    // INSERE E REMOVE FILME A PARTIR DOS COMANDOS NA ENTRADA
    public static void executa_comandos () throws Exception, IOException {
        int num_comandos = Integer.parseInt(scanner.nextLine());
        String input = new String();

        for (int i = 0; i < num_comandos; i++) {
            input = scanner.nextLine();
            executa_comandos(input.charAt(0), input.substring(2));
        }
    }

    private static void executa_comandos (char comando, String input) throws Exception, IOException {
        if (comando == 'I') {
            Filme filme = new Filme();
            filme.le_html(input);
            arvore.inserir(filme);
        }
        else if (comando == 'R') {
            arvore.remover(input);
        }
    }

    // -------------------------------------------------------------- //
    // PERCORRE A AVL EM BUSCA DOS FILMES PESQUISADOS
    public static void pesquisa_filmes () {
        String input = new String ();
        pesquisa_filmes(input);
    }

    private static void pesquisa_filmes (String input) {
        do {
            input = scanner.nextLine();
            if (fim(input)) continue;
            
            if (arvore.pesquisar(input))
                System.out.println("SIM");
            else
                System.out.println("NAO");
        } while (!fim(input));        
    } 

    // -------------------------------------------------------------- //
    // VERIFICA SE A ENTRADA FOR "FIM"
    public static boolean fim (String input) {
        return (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M');
    }
}
