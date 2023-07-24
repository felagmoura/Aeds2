import java.io.IOException;
import java.util.Scanner;

public class TP03Q08BolhaemJava {
    public static void main (String args[]) throws IOException, Exception {

        Lista lista = new Lista();
        lista.construtor(50);

        Ordenacao ordenacao = new Ordenacao();

        Scanner scanner = new Scanner(System.in, "UTF-8");
        String input = new String();

        do {
            
            input = scanner.nextLine();

            if (fim(input)) continue;

            Filme filme = new Filme();

            filme.le_html(input);
            
            lista.inserirFim(filme);

        } while (!fim(input));

        scanner.close();

        ordenacao.bubblesort(lista);

        lista.imprimir();
    }

    public static boolean fim (String input) {
        return (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M'); 
    }
}