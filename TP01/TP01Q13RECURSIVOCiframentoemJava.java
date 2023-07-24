/*
    Ciframento de Cesar em Java - O Imperador Julio Cesar foi um dos principais nomes do Imperio Romano. 
    
    Entre suas contribuicoes, temos um algoritmo de criptografia chamado “Ciframento de Cesar”. 
    
    Segundo os historiadores, Cesar utilizava esse algoritmo para criptografar as mensagens que enviava 
    aos seus generais durante as batalhas. A ideia basica e um simples deslocamento de caracteres. 
    
    Assim, por exemplo, se a chave utilizada para criptografar as mensagens for 3, todas as ocorrencias do caractere ’a’ 
    sao substituıdas pelo caractere ’d’, as do ’b’ por ’e’, e assim sucessivamente. 
    
    Crie um metodo recursivo que recebe uma string como parametro e retorna outra contendo a entrada de forma cifrada. 
    
    Neste exercıcio, suponha a chave de ciframento tres. Na saıda padrao, para cada linha de entrada, escreva uma linha 
    com a mensagem criptografada
*/

public class TP01Q13RECURSIVOCiframentoemJava {
    public static void main (String[] args)
    {
        
        String mensagem = new String();

        recebe_mensagem_imprime_cifrada(mensagem); 

    }

    static String recebe_mensagem_imprime_cifrada (String mensagem)
    {
        mensagem = MyIO.readLine();

        if (!verifica_fim (mensagem))
        {
            MyIO.println(cifra_mensagem (mensagem));
            recebe_mensagem_imprime_cifrada(mensagem);
        }
        return mensagem;
    }

    static boolean verifica_fim(String mensagem)
    {
        boolean fim = false;
        if (mensagem.length() == 3 && mensagem.charAt(0) == 'F' && mensagem.charAt(1) == 'I' && mensagem.charAt(2) == 'M')
        {
            fim = true;
        }
        return fim;
    }

    static String cifra_mensagem (String mensagem) {
        return cifra_mensagem (mensagem, 0, 0);
    }

    static String cifra_mensagem (String mensagem, int i, int temp) 
    {
        String str_cifrada = new String();
        if (i < mensagem.length())
        {
            temp = (int)mensagem.charAt(i) + 3;
            str_cifrada += (char)temp;
            str_cifrada += cifra_mensagem (mensagem, ++i, 0);
        }
        return str_cifrada;
    }
}
