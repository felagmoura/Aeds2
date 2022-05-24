import java.io.IOException;
import java.util.Scanner;

public class TP02Q03PesquisaSequencial {
    public static void main (String[] args) throws IOException, Exception {

        // inicializa o scanner
        Scanner scanner = new Scanner(System.in, "UTF-8");
        
        // variavel string que guarda as entradas
        String input = new String();

        // instacia o objeto lista filmes
        Lista lista_filmes = new Lista();
        // reserva 50 espacos na memoria para lista_filmes
        lista_filmes.lista_constr(50);

        /** LE O HTML E PREENCHE A LISTA DE FILMES **/
        do 
        {
            // le uma linha da entrada
            input = scanner.nextLine();
            
            // para o loop quando o input for FIM
            if (fim(input)) continue;
            
            // cria um novo objeto filme a cada loop 
            Filme filme = new Filme();
            
            // encontra os atributos de cada filme 
            filme.le_html(input);

            // guarda o filme e seus atributos em uma lista de filmes
            lista_filmes.inserirFim(filme);
            

        } while (!fim(input));

        /** PESQUISA POR TODAS AS ENTRADAS NA LISTA DE FILMES **/
        Pesquisa pesquisa = new Pesquisa();
        do 
        {
            // continua lendo uma linha da entrada
            input = scanner.nextLine();

            // sai do loop quando o input for FIM
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
