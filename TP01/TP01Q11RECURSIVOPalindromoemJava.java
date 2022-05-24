/*
    Palindromo em Java Recursivo - Crie um metodo recursivo que recebe uma string como parametro e retorna true se essa e um palındromo.

    Na saıda padrao, para cada linha de entrada, escreva uma linha de saıda com SIM/NAO indicando se a linha e um palındromo. 

    Destaca-se que uma linha de entrada pode ter caracteres nao letras. A entrada termina com a leitura de FIM
*/

public class TP01Q11RECURSIVOPalindromoemJava {
    public static void main (String[] args)
    {
        String entrada = new String();
        recebe_e_imprime_palindromo(entrada);

    }

    public static boolean recebe_e_imprime_palindromo (String entrada) {
        return recebe_e_imprime_palindromo (entrada, 0);
    }

    public static boolean recebe_e_imprime_palindromo (String entrada, int i)
    {
        boolean palindromo = false;
        
        entrada = MyIO.readLine();
        
        if (!verifica_fim(entrada))
        {
            palindromo = verifica_palindromo(entrada);
            
            if (palindromo)
            {
                MyIO.println("SIM"); 
            }
            else
            {
                MyIO.println("NAO");
            }
            
            recebe_e_imprime_palindromo (entrada, i++);
        }
        return palindromo;
    }


    public static boolean verifica_fim (String str_entrada){
        boolean fim = false;
        if (str_entrada.length() == 3 && str_entrada.charAt(0) == 'F' && str_entrada.charAt(1) == 'I' && str_entrada.charAt(2) == 'M') {
            fim = true;
        }
        return fim;
    }

    public static boolean verifica_palindromo (String entrada) {
        return verifica_palindromo (entrada, 0, entrada.length() - 1);
    }

    public static boolean verifica_palindromo (String entrada, int i, int j) {
        boolean palindromo = true;
        
        if (j - i >= 0)
        {
            if ((int)entrada.charAt(i) != (int)entrada.charAt(j))
            {
                palindromo = false;
            }
            if (palindromo)
            {
                palindromo = verifica_palindromo (entrada, ++i, --j);
            }
        }
        return palindromo;
    }
}