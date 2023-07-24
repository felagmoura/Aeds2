
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

typedef struct data {
    int dia;
    int mes;
    int ano;
} Data;


typedef struct filme 
{
    char nome[255];
    char titulo_original[255];
    Data data_lancamento;
    int duracao;
    char genero[1000];
    char idioma[255];
    char situacao[255];
    float orcamento;
    char *palvras_chave[1000];
    int num_chaves;
} Filme;

Filme le_html (char html[]);
char *substring (char linha[], int index);
char *formata_string (char linha[]);
void imprime (Filme filme);
bool verifica_fim (char input[]);


int main(void)
{
    Filme filmes[100];
    int num_filme = 0;
    char html[100];

    while (!verifica_fim(html))
    {
        fgets (html, sizeof(html), stdin);

        html[strlen(html)-1] = '\0';
        
        if (verifica_fim(html)) 
        {
            continue;
        }
        
        filmes[num_filme] = le_html (html);
        imprime (filmes[num_filme]);
        num_filme++;
    } 
    memset(&html[0], 0, sizeof(html));
    return 0;
}


Filme le_html (char html[]) 
{
    Filme filme;
    char arquivo[] = "tmp/filmes/";
    char linha[255];

    strcat(arquivo, html);
    arquivo[strlen(arquivo)-1] = '\0';

    FILE *ptr_html = fopen(arquivo, "r");
    
    if (ptr_html == NULL){
        perror("erri");
        exit(EXIT_FAILURE);
    }

    
    while (fgets(linha, sizeof(linha), ptr_html))
    {
        /**** NOME ****/
        if (strstr(linha, "<h2 class=")) 
        {
            fgets(linha, sizeof(linha), ptr_html);

            strcpy(filme.nome, formata_string (linha));
            strcpy(filme.titulo_original, filme.nome);
        }

        /**** TITULO ORIGINAL ****/
        else if (strstr(linha, "Título original"))
        {
            strcpy(filme.titulo_original, substring(formata_string (linha), 17));
        }

        /**** DATA DE LANCAMENTO ****/
        else if (strstr(linha, "<span class=\"release\">"))
        {
            fgets(linha, sizeof(linha), ptr_html);
            char dia[3], mes[3], ano[5];
            for (int i = 0, j = 0, count_barras = 0; i < strlen(linha); i++)
            {
                if (linha[i] == '/') {
                    count_barras++;
                    j = 0;
                }
                if (linha[i] >= '0' && linha[i] <= '9')
                {
                    if (count_barras == 0) dia[j++] = linha[i];

                    else if (count_barras == 1) mes[j++] = linha[i];

                    else if (count_barras == 2) ano[j++] = linha[i];
                }
            }
            filme.data_lancamento.dia = atoi(dia);
            filme.data_lancamento.mes = atoi(mes);
            filme.data_lancamento.ano = atoi(ano);

        }

        /**** DURACAO ****/
        else if (strstr(linha, "<span class=\"runtime\">"))
        {
            char minutos_str[3], horas_str[2];
            int minutos = 0, horas = 0;
            bool tem_horas = false;

            fgets(linha, sizeof(linha), ptr_html);
            fgets(linha, sizeof(linha), ptr_html);
            
            for (int i = 0; i < strlen(linha)-1; i++)
            {
                if (linha[i] == 'h') tem_horas = true;

                else if (linha[i+2] == 'm' && linha[i] >= '0' && linha[i] <= '9')
                {
                    strcpy(minutos_str, &linha[i]);
                    minutos_str[strlen(minutos_str)-2] = '\0';
                }

                else if (linha[i+2] == 'm' && linha[i+1] >= '0' && linha[i+1] <= '9')
                {
                    strcpy(minutos_str, &linha[i+1]);
                    minutos_str[strlen(minutos_str)-2] = '\0';
                }
            }

            minutos = atoi(minutos_str);

            if (tem_horas)
            {
                for (int i = 0; linha[i] != 'h'; i++)
                {
                    if (linha[i] >= '0' && linha[i] <= '9')
                    {
                        horas_str[0] = linha[i];
                        horas_str[1] = '\0';
                    }
                }
                minutos += 60*atoi(horas_str);
            }

            filme.duracao = minutos;
        }
    
        /**** GENERO ****/
        else if (strstr(linha, "/genre/"))
        {
            strcpy(filme.genero, formata_string(linha));
        }

        /**** IDIOMA ORIGINAL ****/
        else if (strstr(linha, "Idioma original"))
        {
            strcpy(filme.idioma, substring(formata_string (linha), 16));
        }

        /**** SITUACAO ****/
        else if (strstr(linha, "<bdi>Situação</bdi>"))
        {
            strcpy(filme.situacao, substring(formata_string (linha), 11));
        }

        /**** ORCAMENTO ****/
        else if (strstr(linha, "Orçamento"))
        {
            filme.orcamento = atof(substring(formata_string (linha), 12));
        }
    
        /**** PALAVRAS CHAVE ****/        
        else if (strstr(linha, "Palavras-chave"))
        {
            bool tem_chave = true;
            filme.num_chaves = 0;
            while (!strstr(linha, "</ul>"))
            {
                fgets(linha, sizeof(linha), ptr_html);
                
                if (strstr(linha, "<p><bdi>Nenhuma palavra-chave foi adicionada.</bdi></p>"))
                {
                    tem_chave = false;
                    break;
                }

                else if (strstr(linha, "<li>"))
                {
                    formata_string(linha);
                    filme.palvras_chave[filme.num_chaves] = (char*)malloc((strlen(linha)+ 1)*sizeof(char));
                    strcpy (filme.palvras_chave[filme.num_chaves++], linha);
                }

            }

        }
    }

    fclose(ptr_html);
    memset(&ptr_html[0], 0, sizeof(ptr_html));
    memset(&arquivo[0], 0, sizeof(arquivo));
    return filme;
}

char *formata_string (char linha[]) {

    /**** REMOVE CHARACTERES ****/
    int pos = 0;
    
    for (int i = 0; i < strlen(linha); i++)
    {
        /**** REMOVE TAGS ****/
        if (linha[i] == '<')
        {
            while (linha[i++] != '>') {}
            i--;
        }

        /**** REMOVE &nbsp; DE GENERO ****/
        else if (strstr(linha, "&nbsp;")) {
            if (linha[i] == '&')
            {
                while (linha[i++] != ';') {}
                i--;
            }
            else linha[pos++] = linha[i];
        }

        /**** REMOVE , DE ORCAMENTO ****/
        else if (strstr(linha, "Orçamento")) 
        {
            if (linha[i] == ',') {}
            else linha[pos++] = linha[i];
        }

        else linha[pos++] = linha[i];
    }

    linha[pos-1] = '\0';

    /**** TRIM ****/
    for (int i = 0; isspace(linha[i]); i++)
    {
        if (!isspace(linha[i+1]))
        {
            strcpy(linha, &linha[i+1]);
        }
    }

    return linha;
}

char *substring (char linha[], int index) {
    strcpy(linha,&linha[index]);
    return linha;
}

void imprime (Filme filme) {
    
    /**** NOME ****/
    printf ("%s ", filme.nome);
    
    /**** TITULO ORIGINAL ****/
    printf ("%s ", filme.titulo_original);
   
    /**** DATA DE LANCAMENTO ****/
    printf("%02d/%02d/%04d ", filme.data_lancamento.dia, filme.data_lancamento.mes, filme.data_lancamento.ano);
    
    /**** DURACAO ****/
    printf ("%d ", filme.duracao);
    
    /**** GENERO ****/
    printf ("%s ", filme.genero);
    
    /**** IDIOMA ****/
    printf ("%s ", filme.idioma);
    
    /**** SITUACAO ****/
    printf ("%s ", filme.situacao);
    
    /**** ORCAMENTO ****/
    printf ("%g ", filme.orcamento);
    
    /**** PALAVRAS CHAVE ****/
    int total_chaves;
    printf ("[");
    for (int i = 0; i < filme.num_chaves; i++)
    {
        printf ("%s", filme.palvras_chave[i]);
        if (i+1 < filme.num_chaves) 
        {
            printf (", ");
        }
    }
    printf("]\n");
}

bool verifica_fim (char input[])
{
    bool fim = false;

    if (strlen(input) == 4 && input[0] == 'F' && input[1] == 'I' && input[2] == 'M')
    {
        fim = true;
    }
    return fim;
}