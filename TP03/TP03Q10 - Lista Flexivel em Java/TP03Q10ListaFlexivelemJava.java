import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

class Filme {
    private String nome = new String();
    private String titulo_original = new String();
    private Date data_lancamento = new Date();
    private int duracao = 0;
    private String genero = new String();
    private String idioma = new String();
    private String situacao = new String();
    private float orcamento = 0;
    private String[] chaves;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setTitulo_original(String titulo_original) {
        this.titulo_original = titulo_original;
    }
    public String getTitulo_original() {
        return titulo_original;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setData_lancamento(Date data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public Date getData_lancamento() {
        return data_lancamento;
    }

    public void setOrcamento(Float orcamento) {
        this.orcamento = orcamento;
    }

    public Float getOrcamento() {
        return orcamento;
    }

    public void constr_chaves (int num_chaves) {
        chaves = new String[num_chaves];
    }

    public void setChave(String chave, int index) {
        this.chaves[index] = chave;
    }

    public String getChaves() {
        return Arrays.toString(this.chaves);
    }

    public void le_html(String html) throws IOException {

        // !!!!!!!!!!!!LEMBRETE: MUDA ESSE PATH ANTES DO VERDE!!!!!!!!!!!!!!!!!!
        File arquivo = new File("/tmp/filmes/" + html); // concatena o path da pasta filmes com o html lido na entrada
        BufferedReader buffer = new BufferedReader(new FileReader(arquivo)); 
        //RandomAccessFile buffer = new RandomAccessFile(arquivo, "r");
        String line = new String(buffer.readLine()); // line é instaciada com o valor da primeira linha do html

        while (!line.contains("</html>")) // para o loop quando line tiver o valor da ultima linha do html
        {
            line = buffer.readLine(); // le o html linha por linha
            
            /**** NOME ****/
            if (line.contains("<h2 class=")) // "<h2 class=" esta uma linha antes de onde esta o nome
            {
                line = buffer.readLine(); // guarda a linha de onde esta o nome do filme
                
                // replaceAll remove as tags do html e trim remove espacos execivos, assim setNome recebe so o nome do filme
                setNome(line.replaceAll("\\<.*?\\>", "").trim());
                setTitulo_original(getNome());
            }

            /**** TITULO ORIGINAL ****/
            else if (line.contains("Título original")) // o titulo original esta nessa linha
            {
                // replaceAll e trim removem as tags e os espacos, mas o termo "Título original" continua na string;
                // assim, substring(16) indica que somente os char depois do indice 16 devem ser setados, pulando a expressao "Título original"  
                setTitulo_original(line.replaceAll("\\<.*?\\>", "").trim().substring(16));
            }
            
            /**** SITUACAO ****/
            
            else if (line.contains("<bdi>Situação</bdi>")) // a situacao esta nessa linha
            {
                setSituacao(line.replaceAll("\\<.*?\\>", "").trim().substring(9));                 
            }

            /**** IDIOMA ****/
            
            else if (line.contains("Idioma original"))
            { 
                setIdioma(line.replaceAll("\\<.*?\\>", "").trim().substring(16));
            }

            /**** GENERO ****/

            else if (line.contains("/genre/"))
            {
                //remove as tags e os espaços, em seguida substitui os "&nbsp;", que ficam entre os generos
                setGenero(line.replaceAll("\\<.*?\\>", "").trim().replaceAll("\\&.*?\\;", "")); 
            }
            
            /**** DURACAO ****/

            else if (line.contains("<span class=\"runtime\">"))
            {
                buffer.readLine(); // le uma linha vazia
                line = (buffer.readLine().trim()); // guarda a linha com os atributos da duração 
                int minutos = 0;

                // transforma os valores da substring que contem somente os minutos em inteiros 
                if (line.indexOf('m') != -1) {
                    try {
                        minutos = Integer.parseInt(line.substring(line.indexOf('m') - 2, line.indexOf('m')));
                    } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                        minutos = Integer.parseInt(line.substring(line.indexOf('m') - 1, line.indexOf('m')));
                    }
                }
 
                

                
                // procura-se o char 'h' para verificar se o filme tem mais de 60 minutos de duracao 
                if (line.indexOf('h') != -1) // indexOf() retorna -1 quando o char indicado nao é encontrado na string 
                {
                    // transforma o numero de horas em inteiros
                    int horas = Integer.parseInt(line.substring(0, line.indexOf('h')));
                    // calcula a minutagem total do filme
                    minutos += 60 * horas;
                }

                setDuracao(minutos);
            }

            /**** DATA DE LANCAMENTO ****/

            else if (line.contains("<span class=\"release\">"))
            {
                line = buffer.readLine().replaceAll("\\(.*?\\)", "").trim(); // guarda somente a string que contem a data de lancamento
                SimpleDateFormat formata_data = new SimpleDateFormat("dd/MM/yyyy"); // chama a classe que vai transformar a string para date
                
                try 
                {
                    setData_lancamento(formata_data.parse(line)); // a funcao recebe date
                } catch (ParseException p) {} // caso line contenha valores que não sao compativeis com Date
            }

            /**** ORCAMENTO ****/

            else if (line.contains("Orçamento"))
            {
                // formata a string para que ela possa ser convertida em float
                try 
                {
                    setOrcamento(Float.parseFloat(line.replaceAll("\\<.*?\\>", "").replaceAll(",", "").trim().substring(11)));
                }
                catch (NumberFormatException e)
                {

                }
            }

            /**** PALAVRAS CHAVE ****/

            else if (line.contains("Palavras-chave"))
            {
                int num_chaves = 0;
                boolean nenhuma_chave = false;

                // le o html uma vez para descobrir o tamanho do array a ser criado
                while (!line.contains("</ul>"))
                {
                    line = buffer.readLine();
                    if (line.contains("<p><bdi>Nenhuma palavra-chave foi adicionada.</bdi></p>")) 
                    {
                        nenhuma_chave = true;
                        continue;
                    }
                    else if (line.contains("<ul>")) 
                    {
                        buffer.mark(1000); // mark marca essa posicao do buffer para usar o reset; 1000 quer dizer que esse mark sera preservado ate serem lidos 1000 chars  
                    }
                    else if (line.contains("<li>"))
                    {
                        num_chaves++; // conta o maximo de chaves
                    }
                }

                

                if (!nenhuma_chave) 
                {
                    buffer.reset(); // reseta a posicao do buffer para o mark mais proximo
                    line = buffer.readLine(); // le a primeira linha após o reset
                    
                    int index = 0;
                    constr_chaves(num_chaves); // cria um array do tamanho do numero de chaves encontrados


                    // rele o mesmo trecho do html e seta o valor de cada chave no array                    
                    while (!line.contains("</ul>"))
                    {
                        line = buffer.readLine();
                        if (line.contains("<li>"))
                        {
                            setChave(line.replaceAll("\\<.*?\\>", "").trim(), index++);
                        }
                    }
                }

                else
                {
                    constr_chaves(1);
                    setChave("", 0);
                } 
            }
        }
        buffer.close();
    }


    public void imprimir () {
           
        SimpleDateFormat formata_data = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println (getNome() + " " + getTitulo_original() + " " + formata_data.format(getData_lancamento()) + " " + getDuracao() + " " + getGenero() + " " + getIdioma() + " " + getSituacao() + " " + getOrcamento()  + " " + getChaves());
    }
}

class Celula {
    public Filme filme;
    public Celula prox;

    public Celula() {
        this.filme = new Filme();
    }
    
    public Celula (Filme item) {
        this.filme = item;
        this.prox = null;
    }
}

class ListaFlexivel {
    
    private Celula primeiro;
    private Celula ultimo;

    public ListaFlexivel () {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserirInicio (Filme filme) {
        Celula tmp = new Celula(filme);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }

    public void inserirFim(Filme filme) {
        ultimo.prox = new Celula(filme);
        ultimo = ultimo.prox;
    }

    public void inserir (Filme filme, int pos) throws Exception {
        int tam = getTamanho();

        if (pos < 0 || pos > tam) {
            throw new Exception("Erro em I");
        }
        else if (pos == 0) {
            inserirInicio(filme);
        }
        else if (pos == tam) {
            inserirFim(filme);
        }
        else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);

            Celula tmp = new Celula(filme);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    public Filme removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception ("Erro em RI");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Filme removido = primeiro.filme;
        tmp.prox = null;
        tmp = null;
        return removido;
    }

    public Filme removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro em RF");
        }

        Celula i;
        for (i = primeiro; i.prox != ultimo; i = i.prox);

        Filme removido = ultimo.filme;
        ultimo = i;
        i = ultimo.prox = null;

        return removido;
    }

    public Filme remover (int pos) throws Exception {
        
        Filme removido;
        int tam =  getTamanho();

        if ((primeiro == ultimo) || (pos < 0 || pos >= tam)) {
            throw new Exception ("Erro em R");
        }
        else if (pos == 0) {
            removido = removerInicio();
        }
        else if (pos == tam - 1) {
            removido = removerFim();
        }

        else {
            
            Celula i = primeiro;

            for (int j = 0; j < pos; j++, i = i.prox);

            Celula tmp = i.prox;
            removido = tmp.filme;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }

        return removido;
    }

    public void imprimir () {

        int j = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox, j++) {
            System.out.print("[" + j + "] ");
            i.filme.imprimir();
        }
    }

    public int getTamanho() {
        int tam = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox, tam++);
        return tam;
    }
}

public class TP03Q10ListaFlexivelemJava {
    static public void main (String[] args) throws Exception, IOException{
        
        ListaFlexivel lista = new ListaFlexivel();
        Scanner scanner = new Scanner(System.in, "UTF-8");
        String input = new String();

        do {
            
            input = scanner.nextLine();

            if (fim(input)) continue;

            Filme filme = new Filme();

            filme.le_html(input);

            lista.inserirFim(filme);

        } while (!fim(input));

        scanner.nextLine();

        while(scanner.hasNextLine()) {
            input = scanner.nextLine();
            executa_comando(input.substring(0, 2), input, lista);
        }
        scanner.close();

        lista.imprimir();
    }

    public static void executa_comando(String comando, String input, ListaFlexivel lista) throws Exception {

        
        if (comando.contains("II")) {
            
            Filme filme = new Filme();
            String html = input.substring(3);
            
            filme.le_html(html);
            lista.inserirInicio(filme);
        }

        else if (comando.contains("IF")) {
            
            Filme filme = new Filme();
            String html = input.substring(3);
            
            filme.le_html(html);
            lista.inserirFim(filme);
        }

        else if (comando.contains("I*")) {
            
            Filme filme = new Filme();
            int pos;
            String html = new String();
            try {
                pos = Integer.parseInt(input.substring(3, 5));
                html = input.substring(6);    
            } catch (Exception e) {
                pos = Integer.parseInt(input.substring(3, 4));
                html = input.substring(5);
            } 
            
            filme.le_html(html);
            
            lista.inserir(filme, pos);
        }

        else if (comando.contains("RI")) {
            
            Filme removido = lista.removerInicio();            
            System.out.println("(R) " + removido.getNome());
        }

        else if (comando.contains("RF")) {
            
            Filme removido = lista.removerFim();            
            System.out.println("(R) " + removido.getNome());
        }

        else if (comando.contains("R*")) {
            
            int pos = Integer.parseInt(input.substring(3));
            Filme removido = lista.remover(pos);            
            System.out.println("(R) " + removido.getNome());
        }
    }
    public static boolean fim (String input) {
        return (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M'); 
    }
}

