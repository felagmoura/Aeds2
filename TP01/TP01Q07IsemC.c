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

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

bool verifica_fim (char entrada[]);
bool verifica_letra (char c); 
bool verifica_numero (char c);
bool verifica_vogal (char entrada[]);
bool verifica_consoante (char entrada[]);
bool verifica_inteiro (char entrada[]);
bool verifica_real (char entrada[]);


int main (void)
{
    char *endereco_entradas[1000];
    int num_entradas = 0;
    char entrada[1000];
    bool finaliza = false;
    bool respostas[4];

    while (!finaliza)
    {
        fgets (entrada, 1000,stdin);
        endereco_entradas[num_entradas] = (char*)malloc((strlen(entrada)+1)*sizeof(char));
        strcpy (endereco_entradas[num_entradas++], entrada);
        finaliza = verifica_fim(entrada);
        if (finaliza)
        {
            continue;
        }

        respostas[0] = verifica_vogal(entrada);
        respostas[1] = verifica_consoante(entrada);
        respostas[2] = verifica_inteiro(entrada);
        respostas[3] = verifica_real(entrada);

        for (int i = 0; i < 4; i++)
        {
            if (respostas[i])
            {
                printf("SIM ");
            }
            else
            {
                printf("NAO ");
            }
        }
        printf("\n");
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

bool verifica_letra (char c)
{
    bool letra = false;
    if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
    {
        letra = true;
    }
    return letra;
}

bool verifica_numero (char c)
{
    bool numero = false;
    if (c >= '0' && c <= '9')
    {
        numero = true;
    }
    return numero;
}

bool verifica_vogal (char entrada[])
{
    bool vogal = true;
    for (int i = 0; i < strlen(entrada) - 1; i++)
    {
        if(verifica_letra(entrada[i]))
        {
            if (!(entrada[i] == 'A' || entrada[i] == 'E' || entrada[i] == 'I' || entrada[i] == 'O' || entrada[i] == 'U' || entrada[i] == 'a' || entrada[i] == 'e' || entrada[i] == 'i' || entrada[i] == 'o' || entrada[i] == 'u'))
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

bool verifica_consoante (char entrada[])
{
    bool consoante = true;
    for (int i = 0; i < strlen(entrada) - 1; i++)
    {
        // se qualquer char for uma vogal a expressao nao é uma consoante
        if(verifica_letra(entrada[i]))           
        {
            if (entrada[i] == 'A' || entrada[i] == 'E' || entrada[i] == 'I' || entrada[i] == 'O' || entrada[i] == 'U' || entrada[i] == 'a' || entrada[i] == 'e' || entrada[i] == 'i' || entrada[i] == 'o' || entrada[i] == 'u')
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

bool verifica_inteiro (char entrada[])
{
    bool inteiro = true;
    for (int i = 0; i < strlen(entrada) - 1; i++)
    {
        if (!verifica_numero(entrada[i]))
        {
            inteiro = false;
        }
    }
    return inteiro;
}

bool verifica_real (char entrada[])
{
    bool real = true;
    int num_pontos = 0;
    for (int i = 0; i < strlen(entrada) - 1; i++)
    {
        if (entrada[i] == '.' || entrada[i] == ',')
        {
            num_pontos++;
        }
        if (!(verifica_numero(entrada[i]) || entrada[i] == '.' || entrada[i] == ','))
        {
            real = false;
        }
        else if (!(num_pontos <= 1))
        {
            real = false;
        }
    }
    return real;
}