/*
    Palindromo em C - Crie um metodo iterativo que recebe uma string como parametro e
    retorna true se essa e um palındromo. 
    Na saıda padrao, para cada linha de entrada, escreva uma linha de saıda com SIM/NAO indicando se a linha e um palındromo. 
    Destaca-se que uma linha de entrada pode ter caracteres nao letras. A entrada termina com a leitura de FIM
*/
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define MAX_ENTRADAS 1000
#define MAX_LENGTH 1000

bool verifica_fim (char entrada[]);
bool verifica_palindromo (char entrada[]);

int main (void)
{
    char entrada[MAX_ENTRADAS][MAX_LENGTH];
    int num_entrada = 0;
    
    do 
    {
        fgets(entrada[num_entrada], MAX_LENGTH, stdin);
        
    } while (!verifica_fim (entrada[num_entrada++]));
    num_entrada--;
    
    for (int i = 0; i < num_entrada; i++)
    {
        if (verifica_palindromo(entrada[i]) == true)
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
    }
    return 0;
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
bool verifica_palindromo (char entrada[])
{
    bool palindromo = true;
    for (int i = 0, j = strlen(entrada) - 2; j - i >= 0; i++, j--)
    {
        if (entrada[i] != entrada[j])
        {
            palindromo = false;
        }
    }
    return palindromo;
}