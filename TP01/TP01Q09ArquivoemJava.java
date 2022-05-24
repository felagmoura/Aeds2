/*
    Arquivo em Java: Faça um programa que leia um número inteiro n indicando o número de
    valores reais que devem ser lidos e salvos sequencialmente em um arquivo texto.
    
    Após a leitura dos valores, devemos fechar o arquivo.
    
    Em seguida, reabri-lo e fazer a leitura de trás para frente usando os métodos getFilePointer 
    e seek da classe RandomAccessFile e mostre todos os valores lidos na tela.
    
    Nessa questão, você não pode usar, arrays, strings ou qualquer estrutura de dados.

    A entrada padrão é composta por um número inteiro n e mais n números reais.
    
    A saída padrão corresponde a n números reais mostrados um por linha de saída.
*/

import java.io.*;
import java.text.DecimalFormat;

public class TP01Q09ArquivoemJava {
    public static void main (String[] args) throws Exception
    {

        // formata_reais remove os zeros descartavéis dos valores reais
        DecimalFormat formata_reais = new DecimalFormat("###.####");
        
        // le o teto de valores que serão gravados no arquivo txt
        int num_valores = MyIO.readInt();
        
        // cria o txt
        Arq.openWrite("arquivo.txt");

        // escreve uma linha de input 'n' vezes. Sendo que 'n' é 'num_valores'
        for (int i = 0; i < num_valores; i++)
        {
            Arq.println(formata_reais.format(MyIO.readFloat()));
        }

        // fecha o txt
        Arq.close();

        // cria a raf e acessa o txt para leitura
        RandomAccessFile raf = new RandomAccessFile("arquivo.txt", "r");
        
        // bytes_ultima_line conta o numero de bytes na ultima linha do arquivo;
        // cont_bytes é o total de bytes contados até o momento
        int bytes_ultima_line = 0, cont_bytes = 0;

        long primeiro_pointer = 0;
        
        /*
            Para os imprimir os valores do arquivo de trás pra frente,
            Primeiramente, o arquivo é lido em ordem para encontrar a quantidade de bytes na ultima linha do arquivo
            Em seguida, para printar os valores em ordem invertida, deve ser designado o valor da ultima linha 
            que ainda não lida à 'raf.seek()'. Assim, 'raf.seek(raf.length() - cont_bytes)', sendo cont_bytes a soma dos bytes da 
            ultima linha lida
        */
        for (int i = 0; i < num_valores; i++) // le todos os valores do txt
        {            
            if (raf.length() > cont_bytes) // nao le o txt em ordem quando o o total de bytes do txt for igual ao valor de length do txt 
            {
                // le o arquivo em ordem
                raf.seek(0);
                for (int j = 0; j < num_valores - i; j++)
                {
                    primeiro_pointer = raf.getFilePointer();   
                    raf.readLine();
                    
                    // o numero de bytes na ultima linha é a diferença entre o valor do pointer antes e depois de ler a linha
                    bytes_ultima_line = (int)(raf.getFilePointer() - primeiro_pointer);
                }

                // somando o valor de bytes da ultima linha é possivel descobrir a pociçao do proximo valor 
                cont_bytes += bytes_ultima_line;
            }
            
            // o valor de seek é o valor da proxima linha a ser lida
            raf.seek(raf.length() - cont_bytes); 
            System.out.println(raf.readLine());
        }
        raf.close();
    }
}
