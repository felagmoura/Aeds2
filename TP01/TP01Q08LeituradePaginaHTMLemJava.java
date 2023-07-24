/*
    Leitura de Página HTML em Java - Leia duas strings sendo que a primeira é o nome de
    uma página web e a segunda, seu endereço. 
    
    Por exemplo, “Pontifícia Universidade Católica de Minas Gerais” e “www.pucminas.br”. 
    
    Em seguida, mostre na tela o número de vogais (sem e com acento), consoantes e dos padrões 
    “< br >” e “< table >” que aparecem no código dessa página. 
    
    A entrada padrão é composta por várias linhas. 

    Cada uma contém várias strings sendo que a primeira é um endereço web e as demais o nome dessa página web. 
    
    A última linha da entrada padrão contém a palavra “FIM”. 
    
    A saída padrão contém várias linhas sendo que cada uma apresenta o número de ocorrência 
    (valor xi entre parênteses) de cada caractere ou string solicitado.

    Cada linha de saída será da seguinte forma: a(x1) e(x2) i(x3) o(x4) u(x5) á(x6) é(x7)
    í(x8) ó(x9) ú(x10) à(x11) è(x12) ì(x13) ò(x14) Ú(x15) ã(x16) õ(x17) â(x19) ê(x19) î(x20) ô(x21)
    û(x22) consoante(x23) < br >(x24) < table >(x25) nomepágina(x26).
*/

import java.io.*;
import java.net.*;

public class TP01Q08LeituradePaginaHTMLemJava 
{
    // trecho do ExemploURL.java
    public static String getHtml(String endereco){
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;
  
        try {
           url = new URL(endereco);
           is = url.openStream();  // throws an IOException
           br = new BufferedReader(new InputStreamReader(is));
  
           while ((line = br.readLine()) != null) {
              resp += line + "\n";
           }
        } catch (MalformedURLException mue) {
           mue.printStackTrace();
        } catch (IOException ioe) {
           ioe.printStackTrace();
        } 
  
        try {
           is.close();
        } catch (IOException ioe) {
           // nothing to see here
  
        }
  
        return resp;
     }
    public static void main (String[] args)
    {

        String nome_pag = new String();
        String url = new String();
        String html = new String();

        boolean finaliza = false;

        while (!finaliza)
        {
            nome_pag = MyIO.readLine();
            
            if (finaliza = verifica_fim (nome_pag))
            {
                continue;
            }

            url = MyIO.readLine();

            html = getHtml(url);

            conta_letras (html);

            System.out.println(nome_pag);
            
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

    static void conta_letras (String html)
    {
        int contador[] = new int[25];

        char c;
    
        for (int i = 0; i < html.length(); i++)
        {
            c = html.charAt(i);

            conta_vogais (c, contador);
            conta_acento (c, contador);
            conta_agudo (c, contador);
            conta_til (c, contador);
            conta_circunflexo (c, contador);
            conta_consoantes (c, contador);
            conta_br_table (html, i, contador);
        }
        
        // imprime número de vogais
        System.out.print("a(" + contador[0] + ") ");
        System.out.print("e(" + contador[1] + ") ");
        System.out.print("i(" + contador[2] + ") ");
        System.out.print("o(" + contador[3] + ") ");
        System.out.print("u(" + contador[4] + ") ");
        
        // imprime número de vogais com acento
        System.out.print("á(" + contador[5] + ") ");
        System.out.print("é(" + contador[6] + ") ");
        System.out.print("í(" + contador[7] + ") ");
        System.out.print("ó(" + contador[8] + ") ");
        System.out.print("ú(" + contador[9] + ") ");

        //imprime número de vogais com acento agudo
        System.out.print("à(" + contador[10] + ") ");
        System.out.print("è(" + contador[11] + ") ");
        System.out.print("ì(" + contador[12] + ") ");
        System.out.print("ò(" + contador[13] + ") ");
        System.out.print("ù(" + contador[14] + ") ");

        //imprime número de vogais com til
        System.out.print("ã(" + contador[15] + ") ");
        System.out.print("õ(" + contador[16] + ") ");

        //imprime número de vogais com acento circunflexo
        System.out.print("â(" + contador[17] + ") ");
        System.out.print("ê(" + contador[18] + ") ");
        System.out.print("î(" + contador[19] + ") ");
        System.out.print("ô(" + contador[20] + ") ");
        System.out.print("û(" + contador[21] + ") ");
        
        //imprime número de consoantes
        System.out.print("consoante(" + contador[22] + ") ");

        //imprime número de "<br>" e "<table>"
        System.out.print("<br>(" + contador[23] + ") ");
        System.out.print("<table>(" + contador[24] + ") ");

    }

    static void conta_vogais (char c, int[] contador)
    {
        if (c == 'a')
        {
            contador[0]++;
        }
        else if (c == 'e')
        {
            contador[1]++;
        }
        else if (c == 'i')
        {
            contador[2]++;
        }
        else if (c == 'o')
        {
            contador[3]++;
        }
        else if (c == 'u')
        {
            contador[4]++;
        }
    }

    static void conta_acento (char c, int[] contador)
    {
        if (c == 'á')
        {
            contador[5]++;
        }
        else if (c == 'é')
        {
            contador[6]++;
        }
        else if (c == 'í')
        {
            contador[7]++;
        }
        else if (c == 'ó')
        {
            contador[8]++;
        }
        else if (c == 'ú')
        {
            contador[9]++;
        }
    }

    static void conta_agudo (char c, int[] contador)
    {
        if (c == 'à')
        {
            contador[10]++;
        }
        else if (c == 'è')
        {
            contador[11]++;
        }
        else if (c == 'ì')
        {
            contador[12]++;
        }
        else if (c == 'ò')
        {
            contador[13]++;
        }
        else if (c == 'ù')
        {
            contador[14]++;
        }
    }

    static void conta_til (char c, int[] contador)
    {
        if (c == 'ã')
        {
            contador[15]++;
        }
        else if (c == 'õ')
        {
            contador[16]++;
        }
    }

    static void conta_circunflexo (char c, int[] contador)
    {
        if (c == 'â')
        {
            contador[17]++;
        }
        else if (c == 'ê')
        {
            contador[18]++;
        }
        else if (c == 'î')
        {
            contador[19]++;
        }
        else if (c == 'ô')
        {
            contador[20]++;
        }
        else if (c == 'û')
        {
            contador[21]++;
        }
    }

    static void conta_consoantes (char c, int[] contador)
    {
        if (c >= 'a' && c <= 'z')
        {
            if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'))
            {
                contador[22]++;
            }
        }
    }

    static void conta_br_table (String html, int i, int[] contador)
    {
        if (html.charAt(i) == '<' && html.charAt(i+1) == 'b' && html.charAt(i+2) == 'r' && html.charAt(i+3) == '>')
        {
            contador[23]++;

            // contabiliza br no contador de consoantes
            contador[22] -= 2;
        }

        if (html.charAt(i) == '<' && html.charAt(i+1) == 't' && html.charAt(i+2) == 'a' && html.charAt(i+3) == 'b' && html.charAt(i+4) == 'l' && html.charAt(i+5) == 'e' && html.charAt(i+6) == '>')
        {
            contador[24]++;
            /*
                definitivamente o código abaixo não é uma boa pratica, 
                mas os contadores de 'a', 'e' e 'consoante' nao deveriam contabilizar
                as letras de 'table' mas essa gabiarra resolve o problema
            */
            contador[0] -= 1;
            contador[1] -= 1;
            contador[22] -= 3;
        }
    }
}