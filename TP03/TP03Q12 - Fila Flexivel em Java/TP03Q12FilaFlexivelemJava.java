import java.io.IOException;
import java.util.Scanner;

public class TP03Q12FilaFlexivelemJava {
    static public void main (String[] args) throws Exception, IOException{
        
        FilaFlexivel fila = new FilaFlexivel();
        Scanner scanner = new Scanner(System.in, "UTF-8");
        String input = new String();

        do {
            
            input = scanner.nextLine();

            if (fim(input)) continue;

            Filme filme = new Filme();

            filme.le_html(input);

            fila.inserir(filme);

        } while (!fim(input));

        scanner.nextLine();

        while(scanner.hasNextLine()) {
            input = scanner.nextLine();
            executa_comando(input.charAt(0), input, fila);
        }
        scanner.close();

        fila.imprimir();
    }

    public static void executa_comando(char comando, String input, FilaFlexivel fila) throws Exception {

        
        if (comando == 'I') {
            
            Filme filme = new Filme();
            String html = input.substring(2);
            
            filme.le_html(html);
            fila.inserir(filme);
        }

        else if (comando == 'R') {
            
            Filme removido = fila.remover();            
            System.out.println("(R) " + removido.getNome());
        }
    }
    public static boolean fim (String input) {
        return (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M'); 
    }
}

