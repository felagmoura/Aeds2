import java.io.IOException;
import java.util.Scanner;

public class TP02Q04PesquisaBinaria {
    public static void main(String[] args) throws IOException, Exception {

        // cria objeto lista com 50 espacos reservados de memoria
        Lista lista_filmes = new Lista();
        lista_filmes.lista_constr(50);

        Scanner scanner = new Scanner(System.in, "UTF-8");
        String input = new String();

        /** LE O HTML E GUARDA OS FILMES NA LISTA DE FILMES **/
        do 
        {
            input = scanner.nextLine();
            if (fim(input)) continue;

            Filme filme = new Filme();

            filme.le_html(input);

            lista_filmes.inserirFim(filme);

        } while (!fim(input));

        /** PESQUISA OS FILMES  **/
        Pesquisa pesquisa = new Pesquisa();
        
        
        do 
        {
            input = scanner.nextLine();
            if (fim(input)) continue;

            if (pesquisa.binaria(lista_filmes, input)) 
            {
                System.out.println("SIM");
            } 
            else System.out.println("NAO");

        } while (!fim(input));
        scanner.close();
    }

    public static boolean fim (String input) {
        return (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M');
    }
}