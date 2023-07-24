import java.util.Random;

/*
    Alteração Aleatória em Java - Crie um método iterativo que recebe uma string, sorteia
    duas letras minúsculas aleatórias (código ASCII ≥ ’a’ e ≤ ’z’), substitui todas as ocorrências da
    primeira letra na string pela segunda e retorna a string com as alterações efetuadas. 

    Na saída padrão, para cada linha de entrada, execute o método desenvolvido nesta questão e mostre a
    string retornada como uma linha de saída. 

    Abaixo, observamos um exemplo de entrada supondo que para a primeira linha as letras sorteados foram o ’a’ e o ’q’. 
    Para a segunda linha, foram o ’e’ e o ’k'
*/

public class TP01Q04AlteraçãoAleatóriaemJava {
    public static void main (String[] args) {
        // Guarda o valor do input
        String entrada = new String();
        
        // Condição que para o laço while quando o input for "FIM"
        boolean finaliza = false;

        // Coisas pro Random funcionar
        Random gerador = new Random();
        gerador.setSeed(4);

        // Declara as variáveis aleatórias
        char substituida = ' ', substituta = ' ';

        while (!finaliza)
        {
            // guarda o valor do input na variável
            entrada = MyIO.readLine();

            // verifica_fim retorna true quando o input é "FIM"
            finaliza = verifica_fim(entrada);
            
            //sai do loop quando finaliza for true, isso impede que "FIM" seja processado
            if (finaliza)
            {
                continue; 
            }

            // os valores devem ser atribuidos dentro do loop para que cada linha de entrada tenha letras diferentes
            substituida = (char)('a' + (Math.abs(gerador.nextInt()) % 26 ));
            substituta = (char)('a' + (Math.abs(gerador.nextInt()) % 26 ));
            
            // para cada entrada, a função receberá as suas respectivas letras sorteadas, a fim de retorna a entrada alterada
            MyIO.println(faz_alteracao(entrada, substituida, substituta));
        }      
    }

    // verifiva se a entrada é 'FIM'
    static boolean verifica_fim (String entrada)  
    {
        boolean fim = false;
        if (entrada.length() == 3 && entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M')
        {
            fim = true;
        }
        return fim;
    }

    static String faz_alteracao (String entrada, char substituida, char substituta)
    {
        // Uma nova variavel do tipo string é necessario pra concatenar a entrada e os char's alterados
        String str_alterada = new String ();
        
        // esse algoritmo de pesquisa verifica cada char da entrada, caso encontre a primeira letra a ser sorteada, o algoritmo a substitui pela segunda 
        for (int i = 0; i < entrada.length(); i++)
        {
            // na verdade, a substituição é feita a partir da adição dos char's da entrada à variável str_entrada
            if (entrada.charAt(i) == substituida) // quando a substituida é encontrada, será somado a substituta
            {
                str_alterada += substituta; 
            }
            else
            {
                str_alterada += entrada.charAt(i); 
            }
        }
        return str_alterada;
    }
}
