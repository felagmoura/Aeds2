import java.io.IOException;
import java.util.Scanner;

public class TP03Q13QuicksortcomListaDuplaemJava {
    static public void main (String[] args) throws Exception, IOException{
        
        ListaFlexivel lista = new ListaFlexivel();
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

        lista.quicksort();
        
        lista.imprimir();
    }

    public static boolean fim (String input) {
        return (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M'); 
    }
}

