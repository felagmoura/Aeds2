import java.io.IOException;
import java.util.Scanner;

public class TP02Q05ListaSequencialemJava {
    
    public static void main (String args[]) throws IOException, Exception {

        Lista lista = new Lista();
        int tamanho = 50;
        lista.lista_constr(tamanho);

        Scanner scanner = new Scanner(System.in, "UTF-8");
        String input = new String();


        do {
            
            input = scanner.nextLine();
            
            if (fim(input)) continue;

            Filme filme = new Filme();

            filme.le_html(input);

            lista.inserirFim(filme);
        
        } while (!fim(input));

        tamanho += Integer.parseInt(scanner.nextLine());

        while (scanner.hasNextLine()) {
            
            input = scanner.nextLine();
            executa_comandos(lista, input);

        }
        lista.imprimir();

        scanner.close();
    }

    public static boolean fim (String input) {
        return (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M');
    }

    public static void executa_comandos (Lista lista, String input) throws Exception {

        Filme filme = new Filme();
        String comando = input.substring(0, 2);
        int pos;
        String html = new String();

        switch (comando) {
            
            case "II": 
            html = input.substring(3);
            filme.le_html(html);
            lista.inserirInicio(filme);
            break;

            case "IF":
            html = input.substring(3);
            filme.le_html(html);
            lista.inserirFim(filme);
            break;

            case "I*":
            pos = Integer.parseInt(input.substring(3, 5));
            html = input.substring(6);
            filme.le_html(html);
            lista.inserir(filme, pos);
            break;

            case "RI":
            filme = lista.removerInicio();
            System.out.println("(R) " + filme.getNome());
            break;

            case "RF":
            filme = lista.removerFim();
            System.out.println("(R) " + filme.getNome());
            break;

            case "R*":
            pos = Integer.parseInt(input.substring(3, 5));
            filme = lista.remover(pos);
            System.out.println("(R) " + filme.getNome());
            break;

        }

    }
}
