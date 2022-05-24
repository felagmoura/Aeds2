/*
    Palindromo em Java - Crie um metodo iterativo que recebe uma string como parametro e
    retorna true se essa e um palındromo. 
    Na saıda padrao, para cada linha de entrada, escreva uma linha de saıda com SIM/NAO indicando se a linha e um palındromo. 
    Destaca-se que uma linha de entrada pode ter caracteres nao letras. A entrada termina com a leitura de FIM
*/

public class TP01Q01PalindromoemJava {
    public static void main (String[] args) {
        
        String[] entrada = new String[1000];
        int num_entrada = 0;
        
        do {
            entrada[num_entrada] = MyIO.readLine();
        } while (!verifica_fim(entrada[num_entrada++]));
        num_entrada--;
        for (int i = 0; i < num_entrada; i++){
            
            if (verifica_palindromo(entrada[i])){
                MyIO.println("SIM");
            }
            else {
                MyIO.println("NAO");
            }
        }
    }

    public static boolean verifica_fim (String str_entrada){
        boolean fim = false;
        if (str_entrada.length() == 3 && str_entrada.charAt(0) == 'F' && str_entrada.charAt(1) == 'I' && str_entrada.charAt(2) == 'M') {
            fim = true;
        }
        return fim;
    }
    
    public static boolean verifica_palindromo (String str_entrada){
        boolean palindromo = true;
        int length_entrada = str_entrada.length();
        int j = length_entrada - 1;
 
        for (int i = 0; j - i >= 0; i++, j--)
        {
            if (str_entrada.charAt(i) != str_entrada.charAt(j))
            {
                palindromo = false;
            }
        }
        return palindromo;
    }
}
