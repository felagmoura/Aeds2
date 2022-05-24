/*
    Palindromo em C Recursivo - Crie um metodo recursivo que recebe uma string como parametro e retorna true se essa e um palındromo.

    Na saıda padrao, para cada linha de entrada, escreva uma linha de saıda com SIM/NAO indicando se a linha e um palındromo. 

    Destaca-se que uma linha de entrada pode ter caracteres nao letras. A entrada termina com a leitura de FIM
*/

#include <stdio.h>
#include <stdbool.h>
#include <string.h>

void recebe_e_imprime_palindromo (char entrada[]);
bool verifica_palindromo (char entrada[], int i, int j);
bool verifica_fim (char entrada[]);

int main (void)
{
    char entrada[1000];
    recebe_e_imprime_palindromo (entrada);

    return 0;
}

void recebe_e_imprime_palindromo (char entrada[])
{
    fgets (entrada, 1000, stdin);
    if (!verifica_fim (entrada))
    {
        if (verifica_palindromo (entrada, 0, strlen(entrada) - 2))
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
        recebe_e_imprime_palindromo (entrada);
    }
}

bool verifica_fim (char entrada[])
{
    bool fim = false;
    if (strlen(entrada) == 4 && entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M')
    {
        fim = true;
    }
    return fim;
}

bool verifica_palindromo (char entrada[], int i, int j)
{
    bool palindromo = true;
    if (j - i >= 0)
    {
        if (entrada[i] != entrada[j])
        {
            palindromo = false;
        }
        if (palindromo)
        {
            palindromo = verifica_palindromo(entrada, ++i, --j);
        }
        
    }
    return palindromo;
}