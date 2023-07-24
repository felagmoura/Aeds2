/*
    Is em Java - Crie um metodo iterativo que recebe uma string e retorna true se a mesma e composta somente por vogais. 

    Crie outro metodo iterativo que recebe uma string e retorna true se a mesma e composta somente por consoantes. 

    Crie um terceiro metodo iterativo que recebe uma string e retorna true se a mesma corresponde a um numero inteiro. 

    Crie um quarto metodo iterativo que recebe uma string e retorna true se a mesma corresponde a um numero real. 

    Na saıda padrao, para cada linha de entrada, escreva outra de saıda da seguinte forma X1 X2 X3 X4 
    onde cada Xi e um booleano indicando se a e entrada e: 
    composta somente por vogais (X1);
    composta somente somente por consoantes (X2);
    um numero inteiro (X3); 
    um numero real (X4). 

    Se Xi for verdadeiro, seu valor sera SIM, caso contrario, NAO.
*/

public class TP01Q06IsemJava {
    public static void main (String[] args)
    {
        // Guarda o valor do input
        String entrada = new String();
        
        // Condição que para o laço while quando o input for "FIM"
        boolean finaliza = false;

        // esse vetor guarda o resultado das funcoes
        boolean resp_funcoes[] = new boolean[4];

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

            // atribui os valores retornados de cada funcao a seu respectivo vetor
            resp_funcoes[0] = verifica_vogal (entrada);
            resp_funcoes[1] = verifica_consoante (entrada);
            resp_funcoes[2] = verifica_inteiro (entrada);
            resp_funcoes[3] = verifica_real (entrada);

            // loop que imprime os reultados
            for (int i = 0; i < 4; i++)
            {
                if (resp_funcoes[i])
                {
                    MyIO.print("SIM ");
                }
                else
                {
                    MyIO.print("NAO ");
                }
                
            }
            MyIO.println("");
        } 
    }

    static boolean verifica_fim (String entrada)  
    {
        boolean fim = false;
        if (entrada.length() == 3 && entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M')
        {
            fim = true;
        }
        return fim;
    }
    static boolean verifica_vogal (String entrada)
    {
        boolean vogal = true;
        char c;
        for (int i = 0; i < entrada.length(); i++)
        {
            c = entrada.charAt(i);
            // caso o digito for diferente de uma vogal a funcao retorna falso
            if(verifica_letra(c))           
            {
                if (!(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'))
                {
                    vogal = false;
                }
            }
            else
            {
                vogal = false;
            }
        }
        return vogal;
    }
    
    static boolean verifica_consoante (String entrada)
    {
        boolean consoante = true;
        char c;
        for (int i = 0; i < entrada.length(); i++)
        {
            c = entrada.charAt(i);
            // se qualquer char for uma vogal a expressao nao é uma consoante
            if(verifica_letra(c))           
            {
                if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                {
                    consoante = false;
                }
            }
            else
            {
                consoante = false;
            }
        }
        return consoante;
    }
    
    static boolean verifica_letra (char c)
    {
        boolean letra = true;
        // verifica se o digito é uma letra, independente se ela for maiuscula ou minuscula
        if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')))
        {
            letra = false;
        }
    
        return letra;
    }
    static boolean verifica_inteiro(String entrada)
    {
        boolean inteiro = true;
        char c;

        for (int i = 0; i < entrada.length(); i++)
        {
            c = entrada.charAt(i);
            // se qualque digito da entrada for diferente de um numero, a entrada nao é um inteiro
            if (!(c >= '0' && c <= '9'))
            {
                inteiro = false;
            }
        }

        return inteiro;
    }

    static boolean verifica_real(String entrada)
    {
        boolean real = true;
        char c;
        int num_ponto = 0;

        for (int i = 0; i < entrada.length(); i++)
        {
            c = entrada.charAt(i);

            
            // conta quantos pontos ou virgulas aparecem na estrada
            if (c == '.' || c == ',')
            {
                num_ponto++;
            }
            /*
                se qualquer digito da entrada for diferente de um numero 
                ou um ponto ou uma virgula, real sera falso
            */
            if (!((c >= '0' && c <= '9') || (c == '.' || c == ',')))
            {
                real = false;
            }
            // se houver mais de um ponto ou virgula na entrada o numero nao é um real
            else if (!(num_ponto <= 1))
            {
                real = false;
            }       
        }

        return real;
    }
}
