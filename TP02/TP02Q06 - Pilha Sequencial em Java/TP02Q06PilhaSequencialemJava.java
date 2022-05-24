import java.io.IOException;
import java.util.Scanner;

public class TP02Q06PilhaSequencialemJava {
    public static void main (String args[]) throws IOException, Exception {

        Pilha pilha = new Pilha();
        int tamanho = 50;
        pilha.construtor(tamanho);

        Scanner scanner = new Scanner(System.in, "UTF-8");
        String input = new String();
        
        do {
            
            input = scanner.nextLine();

            if (fim(input)) continue;

            Filme filme = new Filme();

            filme.le_html(input);

            pilha.inserir(filme);

        } while (!fim(input));

        tamanho += Integer.parseInt(scanner.nextLine());

        while (scanner.hasNextLine()) {
            
            input = scanner.nextLine();

            executa_comandos(pilha, input, input.charAt(0));
        }

        scanner.close();
        
        pilha.imprimir();
    }
    
    public static boolean fim (String input) {
        return (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M');
    }

    public static void executa_comandos (Pilha pilha, String input, char comando) throws IOException, Exception {

        Filme filme = new Filme();

        if (comando == 'I') {

            filme.le_html(input.substring(2));
            
            pilha.inserir(filme);
        }

        else if (comando == 'R') {

            filme = pilha.remover();

            System.out.println("(R) " + filme.getNome());
        }
    }
}
