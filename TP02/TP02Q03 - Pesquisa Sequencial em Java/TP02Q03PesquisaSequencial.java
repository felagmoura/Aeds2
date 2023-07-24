import java.io.IOException;
import java.util.Scanner;

public class TP02Q03PesquisaSequencial {
    public static void main (String[] args) throws IOException, Exception {

        Scanner scanner = new Scanner(System.in, "UTF-8");
        
        String input = new String();

        Lista lista_filmes = new Lista();
        lista_filmes.lista_constr(50);

        do 
        {
            input = scanner.nextLine();
            
            if (fim(input)) continue;
            
            Filme filme = new Filme();
            
            filme.le_html(input);

            lista_filmes.inserirFim(filme);
            
        } while (!fim(input));

        Pesquisa pesquisa = new Pesquisa();

        do 
        {
            input = scanner.nextLine();

            if (fim(input)) continue;
            
            if (pesquisa.sequencial(lista_filmes, input))
            {
                System.out.println("SIM");
            }
            else  System.out.println("NAO");
        
        } while (!fim(input));
        scanner.close();
    }

    public static boolean fim (String input) {
        return (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M');
    }
}
