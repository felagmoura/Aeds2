/*
    Algebra Booleana em Java  - Crie um metodo iterativo que recebe uma string contendo uma
    expressao booleana e o valor de suas entradas e retorna um booleano indicando se a expressao
    e verdadeira ou falsa. 

    Cada string de entrada e composta por um numero inteiro n indicando
    o numero de entradas da expressao booleana corrente. 
    
    Em seguida, a string contem n valores binarios (um para cada entrada) e a expressao booleana. 
    
    Na saıda padrao, para cada linha de entrada, escreva uma linha de saıda com SIM / NAO indicando 
    se a expressao corrente e verdadeira ou falsa.
*/

public class TP01Q05AlgebraBooleanaemJava {
    public static void main (String[] args){

        String entrada = new String();
        boolean finaliza = false;
        
        while(!finaliza)
        {
            entrada = MyIO.readLine();
            
            if (finaliza = verifica_fim(entrada))
            {
                continue;
            }

            if (processa_entrada(entrada))
            {
                MyIO.println("vero");
            }
            else 
            {
                MyIO.println("fake bro");
            }
        }
    } 

    // verifica quando o input for 0 para encerrar o programa
    static boolean verifica_fim (String entrada)
    {
        boolean fim = false;
        if (entrada.length() == 1 && entrada.charAt(0) == '0')
        {
            fim = true;
        }
        return fim;
    }

    static boolean processa_entrada (String entrada)
    {
        boolean resp = false;
        int num_entradas = (int)entrada.charAt(0) - 48;
        boolean valor_entradas[] = new boolean[num_entradas];
        String expressao = new String();
        

        // esse laco guarda os valores booleanos entrados
        for (int i = 2, j = 0; i <  2 * (num_entradas + 1); i+=2, j++)
        {
            if (entrada.charAt(i) == '0')
            {
                valor_entradas[j] = false;
            }
            else if (entrada.charAt(i) == '1')
            {
                valor_entradas[j] = true;
            }
        }

        // formata a entrada, e atribui valor as icognitas
        for (int i = 2 * (num_entradas + 1); i < entrada.length(); i++)
        {
            expressao += entrada.charAt(i);
        }

        //MyIO.println();
        for (int i = expressao.length() - 1; i >= 0; i--)
        {
            if (expressao.charAt(i) == '(')
            {
                if (expressao.charAt(i-1) == 't')
                {
                    resolve_not(expressao.charAt(i), valor_entradas);
                }

                if (expressao.charAt(i-1) == 'd')
                {

                }

                if (expressao.charAt(i-1) == 'r')
                {
                    //resolve_or();
                }
            }
            
        }

        return resp;
    }
    
    

    static void resolve_not (char c, boolean valor_entradas[])
    {
        if (c == 'A')
        {
            valor_entradas[0] = !valor_entradas[0];
        }
        else if (c == 'B')
        {
            valor_entradas[1] = !valor_entradas[1];
        }
        else if (c == 'C')
        {
            valor_entradas[2] = !valor_entradas[2];
        }
    }
}
