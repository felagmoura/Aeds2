/*
    Is em Java - Crie um metodo recursivo que recebe uma string e retorna true se a mesma e composta somente por vogais. 

    Crie outro metodo recursivo que recebe uma string e retorna true se a mesma e composta somente por consoantes. 

    Crie um terceiro metodo recursivo que recebe uma string e retorna true se a mesma corresponde a um numero inteiro. 

    Crie um quarto metodo recursivo que recebe uma string e retorna true se a mesma corresponde a um numero real. 

    Na saıda padrao, para cada linha de entrada, escreva outra de saıda da seguinte forma X1 X2 X3 X4 
    onde cada Xi e um booleano indicando se a e entrada e: 
    composta somente por vogais (X1);
    composta somente somente por consoantes (X2);
    um numero inteiro (X3); 
    um numero real (X4). 

    Se Xi for verdadeiro, seu valor sera SIM, caso contrario, NAO.
*/

public class TP01Q15RECURSIVOIsemJava {
    public static void main (String[] args) {
        String entrada = new String ();
        entrada_e_saida (entrada);
    }

    // recebe os inputs do usuário e chama uma funcao para pocessar e imprimir a saida
    static void entrada_e_saida (String entrada)
    {
        entrada = MyIO.readLine();
        
        if (!verifica_fim(entrada))
        {
            imprime_saidas(entrada);
            MyIO.println("");
            entrada_e_saida (entrada);
        }
    }

    // verifica se o input eh FIM
    static boolean verifica_fim (String entrada)  
    {
        boolean fim = false;
        if (entrada.length() == 3 && entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M')
        {
            fim = true;
        }
        return fim;
    }

    static int imprime_saidas (String entrada){
        boolean resp[] = new boolean[4];
        resp[0] = verifica_vogal(entrada);
        resp[1] = verifica_consoante(entrada);
        resp[2] = verifica_inteiro(entrada);
        resp[3] = verifica_real(entrada);
        return imprime_saidas (resp, 0);
    }

    static int imprime_saidas (boolean resp[], int i)
    {
        if (i < resp.length)
        {
            if (resp[i])
            {
                MyIO.print("SIM ");
            }
            else 
            {
                MyIO.print("NAO ");
            }
            imprime_saidas (resp, ++i);
        } 
        return i;
    }

    // confirma se o char é uma letra
    static boolean verifica_letra (char c)
    {
        boolean letra = false;
        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
        {
            letra = true;
        }
        return letra;
    }

    // confirma se o char é um numero
    static boolean verifica_numero (char c)
    {
        boolean numero = false;
        if (c >= '0' && c <= '9')
        {
            numero = true;
        }
        return numero;
    }

    // confirma se a entrada é composta somente por vogais
    static boolean verifica_vogal (String entrada){
        return verifica_vogal (entrada, 0);
    }

    static boolean verifica_vogal (String entrada, int i)
    {
        boolean vogal = true;
        
        if (i < entrada.length())
        {
            char c = entrada.charAt(i);
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

            // a funcao so sera chamada enquanto as letras ate entao encontradas forem uma vogal
            if (vogal)
            {
                vogal = verifica_vogal (entrada, ++i);
            }
        }
        return vogal;
    }

    // confirma se a entrada é composta somente por consoantes
    static boolean verifica_consoante (String entrada){
        return verifica_consoante (entrada, 0);
    }

    static boolean verifica_consoante (String entrada, int i)
    {
        boolean consoante = true;
        
        if (i < entrada.length())
        {
            char c = entrada.charAt(i);
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
            if (consoante)
            {
                consoante = verifica_consoante (entrada, ++i);
            }
        }

        return consoante;
    }

    // confirma se a entrada é compostosta exclusivamente por numeros
    static boolean verifica_inteiro (String entrada){
        return verifica_inteiro (entrada, 0);
    }

    static boolean verifica_inteiro (String entrada, int i)
    {
        boolean inteiro = true;
        
        if (i < entrada.length())
        {
            char c = entrada.charAt(i);
            if(!(verifica_numero(c)))           
            {
                inteiro = false;
            }
            else
            {
                inteiro = verifica_inteiro (entrada, ++i);
            }
        }

        return inteiro;
    }

    // confirma se a entrada é compostosta por numeros, e, no máximo um ponto ou virgula
    static boolean verifica_real (String entrada){
        return verifica_real (entrada, 0, 0);
    }

    static boolean verifica_real (String entrada, int num_pontos, int i)
    {
        boolean real = true;
        
        if (i < entrada.length())
        {
            char c = entrada.charAt(i);
            if (c == '.' || c == ',')
            {
                num_pontos++;
            }
            if(!((verifica_numero(c)) || (c == '.' || c == ',')))         
            {
                real = false;
            }
            else if (!(num_pontos <= 1))
            {
                real = false;
            }

            if (real)
            {
                real = verifica_real (entrada, num_pontos, ++i);
            }
            
        }

        return real;
    }


}
