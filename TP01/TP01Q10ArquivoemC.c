/*
    Arquivo em C: Faça um programa que leia um número inteiro n indicando o número de
    valores reais que devem ser lidos e salvos sequencialmente em um arquivo texto.
    
    Após a leitura dos valores, devemos fechar o arquivo.
    
    Em seguida, reabri-lo e fazer a leitura de trás para frente usando os métodos getFilePointer 
    e seek da classe RandomAccessFile e mostre todos os valores lidos na tela.
    
    Nessa questão, você não pode usar, arrays, strings ou qualquer estrutura de dados.

    A entrada padrão é composta por um número inteiro n e mais n números reais.
    
    A saída padrão corresponde a n números reais mostrados um por linha de saída.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main (void)
{
    int num_valores = 0;
    float valores = 0;
    
    // guarda o limite de entradas
    scanf("%d", &num_valores);

    FILE *ptr_arquivo;
    
    ptr_arquivo = fopen ("arquivo.txt", "w");
    
    // escreve os valores no arquivo txt
    for (int i = 0; i < num_valores; i++)
    {
        scanf("%f", &valores);
        fprintf (ptr_arquivo, "%.6g\n", valores);
    }

    fclose(ptr_arquivo);

    char valor_lido [10];
    long f_length = 0, posicao_ptr = 0, bytes_linha = 0, soma_bytes = 0;

    ptr_arquivo = fopen ("arquivo.txt", "r");

    // descobre o tamanho do arquivo
    fseek(ptr_arquivo, 0, SEEK_END);
    f_length = ftell(ptr_arquivo);
    rewind (ptr_arquivo);

    for (int i = 0; i < num_valores; i++)
    {
        // le o arquivo em ordem para descobrir o tamanho da linha
        if (f_length >= soma_bytes)
        {   
            rewind(ptr_arquivo);
            for (int j = 0; j < num_valores - i; j++)
            {
                posicao_ptr = ftell(ptr_arquivo);
                fscanf (ptr_arquivo, "%s", valor_lido);

                if (posicao_ptr == 0)
                {
                    posicao_ptr = -1;
                }
                bytes_linha = (ftell(ptr_arquivo) - posicao_ptr);
            }
            
            soma_bytes += bytes_linha;
        }
        
        // le e printa as entradas do txt na ordem contraria
        fseek (ptr_arquivo, -soma_bytes, SEEK_END);
        fgets (valor_lido, 10, ptr_arquivo);

        printf ("%s", valor_lido);
    }
    
    fclose(ptr_arquivo);

    return 0;
}
