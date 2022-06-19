import java.io.IOException;
import java.util.Scanner;

//************************ TP04Q07 - HASH INDIRETA  *************************//
public class TP04Q07HashIndireta {

    static Scanner scr = new Scanner(System.in, "UTF8");
    static HashIndireta hash = new HashIndireta();
    static public void main (String[] args) throws IOException, Exception {
        preenche_tabela();
        busca_filmes();
        scr.close();
    }
    // -------------------------------------------------------------- //
    // PREENCHE A TABELA
    public static void preenche_tabela () throws IOException, Exception {
        String html = new String();
        preenche_tabela(html);
    }

    private static void preenche_tabela (String html) throws IOException, Exception {
        do {
            html = scr.nextLine();
            if (fim(html)) continue;
            Filme filme = new Filme();
            filme.le_html(html);
            hash.inserir(filme);
        } while (!fim(html));
    }

    // -------------------------------------------------------------- //
    // PREENCHE A TABELA
    public static void busca_filmes() {
        String titulo = new String();
        busca_filmes(titulo);
    }

    private static void busca_filmes(String titulo) {
        do {
            titulo = scr.nextLine();
            if (fim(titulo)) continue;
            hash.pesquisar(titulo);
        } while (!fim(titulo));
    }
    
    // -------------------------------------------------------------- //
    // VERIFICA SE A ENTRADA FOR "FIM"
    public static boolean fim (String input) {
        return (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M');
    }
}
//***************************************************************************//
