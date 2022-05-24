/*
    Classe Filme em Java: Crie uma classe Filme seguindo todas as regras apresentadas no slide
    unidade01g conceitosBasicos introducaoOO.pdf. 
    
    Sua classe tera os atributos privados Nome(String), Tıtulo Original (String), Data de Lancamento (Date), 
    Duracao (int), Genero (String), Idioma original (String), Situacao (String), Orcamento (Float),
    Palavras-Chave (Vetor de Strings).
    
    Ela tera tambem pelo menos dois construtores, e os metodos gets, sets, clone e imprimir e ler.
    
    O metodo imprimir mostra a String ‘‘nome tituloOriginal dataLancamento duracao genero idiomaOriginal
    situacao orcamento [palavrasChave]’’, contendo todos os atributos da classe. 
    
    O metodo ler deve efetuar a leitura dos atributos de um registro. Veja que os dados estao divididos em varios arquivos.
    
    A entrada padrao e composta por varias linhas e cada uma contem o nome de um arquivo .html.

    A ultima linha da entrada contem FIM. 
    
    A saıda padrao tambem contem varias linhas, uma para cada registro contido em uma linha da entrada padrao
*/

import java.io.IOException;
import java.util.Scanner;

public class ClasseFilmeemJava {
    public static void main (String[] argc) throws IOException, Exception {
        
        // instancia o scanner para ler UTF-8 na entrada padrao
        Scanner scanner = new Scanner (System.in, "UTF-8");
        
        // instacia um objeto de Lista
        Lista lista_filmes = new Lista();
        // indica ao construtor que o objeto deve recervar 50 espacos na memoria
        lista_filmes.lista_constr(50); 

        // html guarda os documentos html's digitados no terminal
        String html = new String();

        do 
        {
            // instancia novo objeto filme em todo loop
            Filme filme = new Filme();
            // le o input
            html = scanner.nextLine();

            // para o loop qnd a entrada for FIM
            if (html.length() == 3 && html.charAt(0) == 'F' && html.charAt(1) == 'I' && html.charAt(2) == 'M') 
            {
                // direciona o compilador para o fim do loop, o que impede os proximos comandos de serem executados 
                continue;
            }
            
            // envia o html para a funcao le_html que esta em Filme.class
            filme.le_html(html);
            
            // insere o objeto filme com todos os seus atributos preenchidos para a lista_filmes
            lista_filmes.inserirFim(filme);
            
        } while (!(html.length() == 3 && html.charAt(0) == 'F' && html.charAt(1) == 'I' && html.charAt(2) == 'M'));
        scanner.close();

        // imprime todos filmes da lista e seus atributos
        lista_filmes.imprimir();
    }
}