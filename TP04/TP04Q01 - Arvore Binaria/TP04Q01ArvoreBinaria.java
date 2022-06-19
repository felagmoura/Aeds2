import java.io.IOException;
import java.util.Scanner;
//****************************** TP04Q01 - ARVORE BINARIA ******************************//
public class TP04Q01ArvoreBinaria {
    public static void main (String args[]) throws IOException, Exception {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Scanner scanner = new Scanner(System.in, "UTF8");
        String input = new String();
        
        // -------------------------------------------------------------- //
        // PREENCHE A ARVORE
        do {
            input = scanner.nextLine();
            if (fim(input)) continue;
            
            Filme filme = new Filme();
            filme.le_html(input);
            arvore.inserir(filme);

        } while (!fim(input));
        
        // -------------------------------------------------------------- //
        // EXECUTA OS COMANDOS DE INSERIR E REMOVER
        int num_comandos = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < num_comandos; i++) {
            input = scanner.nextLine();
            executa_comandos(input.charAt(0), input.substring(2), arvore);
        }        
        
        // -------------------------------------------------------------- //
        // PROCURA PELOS FILMES DO INPUT NA ARVORE
        do {
            input = scanner.nextLine();
            if (fim(input)) continue;
            
            if (arvore.pesquisar(input))
                System.out.println("SIM");
            else
                System.out.println("NAO");
        } while (!fim(input));

        // --------------------------------------------------------------//

        scanner.close();
    }

    // -------------------------------------------------------------- //
    // FUNCOES
    public static void executa_comandos (char comando, String input, ArvoreBinaria arvore) throws IOException, Exception {
        if (comando == 'I') {
            Filme filme = new Filme();
            filme.le_html(input);
            arvore.inserir(filme);
        }
        else if (comando == 'R') {
            arvore.remover(input);
        }
    }

    public static boolean fim (String input) {
        return (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M');
    }
    
    // -------------------------------------------------------------- //

}
//**************************************************************************************//
