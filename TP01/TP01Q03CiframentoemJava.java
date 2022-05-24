/*
    Ciframento de Cesar em Java - O Imperador Julio Cesar foi um dos principais nomes do Imperio Romano. 
    
    Entre suas contribuicoes, temos um algoritmo de criptografia chamado “Ciframento de Cesar”. 
    
    Segundo os historiadores, Cesar utilizava esse algoritmo para criptografar as mensagens que enviava 
    aos seus generais durante as batalhas. A ideia basica e um simples deslocamento de caracteres. 
    
    Assim, por exemplo, se a chave utilizada para criptografar as mensagens for 3, todas as ocorrencias do caractere ’a’ 
    sao substituıdas pelo caractere ’d’, as do ’b’ por ’e’, e assim sucessivamente. 
    
    Crie um metodo iterativo que recebe uma string como parametro e retorna outra contendo a entrada de forma cifrada. 
    
    Neste exercıcio, suponha a chave de ciframento tres. Na saıda padrao, para cada linha de entrada, escreva uma linha 
    com a mensagem criptografada
*/
public class TP01Q03CiframentoemJava {
    public static void main (String[] argc) {
        
        String mensagem = new String();
        boolean isFim = false;


        while (!isFim) // Quando a entrada for FIM o loop será interrompido
        {
            mensagem = MyIO.readLine();
            isFim = verifica_fim(mensagem);
            if (isFim)
            {
                continue; // Não permite que seja feito o ciframento com a entrada FIM, pois 'continue' sai do loop 
            }
            MyIO.println(criptografa_mensagem(mensagem));
        }

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
    static String criptografa_mensagem (String mensagem)
    {
        String criptografada = new String();
        int chave = 3, temp = 0; 
        for (int i = 0; i < mensagem.length();i++)
        {
            /*
                temp é usado para guardar o valor inteiro dos char's que compõe as entradas
                em seguida, é feita a converção desse int para seu respectivo char, 
                para que ele possa ser retornado como uma nova String
            */
            temp = (int)mensagem.charAt(i) + chave;
            criptografada += (char)temp;
        }
        return criptografada;
    }
    
}
