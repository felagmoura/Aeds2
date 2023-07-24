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

class Lista {
    Filme[] array;
    int num_filmes;


    public void construtor (int tamanho) {
        array = new Filme[tamanho];
        num_filmes = 0;
    }

    public void inserirInicio (Filme filme) throws Exception {

        if (num_filmes >= array.length)
        {
            throw new Exception("erro em II");
        }

        for (int i = num_filmes; i > 0; i--)
        {
            array[i] = array[i-1];
        }

        array[0] = filme;
        num_filmes++;
    }

    public void inserirFim (Filme filme) throws Exception {

        if (num_filmes >= array.length)
        {
            throw new Exception("erro em IF");
        }

        array[num_filmes++] = filme;
    }

    public void inserir (Filme filme, int pos) throws Exception {

        if (num_filmes >= array.length || pos < 0 || num_filmes < pos)
        {
            throw new Exception("erro em I");
        }

        for (int i = num_filmes; i > pos; i--)
        {
            array[i] = array[i-1];
        }

        array[pos] = filme;
        num_filmes++;
    }

    public Filme removerInicio () throws Exception {
        
        Filme filme_removido;

        if (num_filmes == 0)
        {
            throw new Exception("erro em RI");
        }

        filme_removido = array[0];
        num_filmes--;

        for (int i = 0; i < num_filmes; i++)
        {
            array[i] = array[i+1];
        }
        return filme_removido;
    }

    public Filme removerFim () throws Exception {
        if (num_filmes == 0) {
            throw new Exception("erro em RF");
        }
        return array[--num_filmes];
    }

    public Filme remover (int pos) throws Exception {
        Filme filme_removido;
        if (num_filmes == 0 || pos < 0 || num_filmes < pos) {
            throw new Exception("erro em R");
        }
        filme_removido = array[pos];
        num_filmes--;
        for (int i = pos; i < num_filmes; i++) {
            array[i] = array[i+1];
        }
        return filme_removido;
    }

    public void imprimir () {
        for (int i = 0; i < num_filmes; i++) {
            Filme filme = array[i];
            filme.imprimir();
        }
    }
}

class Ordenacao {
    
    void troca (int i, int j, Lista lista) {
        Filme temp = lista.array[i];
        lista.array[i] = lista.array[j];
        lista.array[j] = temp;
    }

    void selecao (Lista lista) {

        for (int i = 0; i < lista.num_filmes - 1; i++) {
            int desordenado = i;
            for (int j = i + 1; j < lista.num_filmes; j++) {
                
                if (lista.array[desordenado].getTitulo_original().charAt(0) > lista.array[j].getTitulo_original().charAt(0)) {
                    desordenado = j;
                }

                else if (lista.array[desordenado].getTitulo_original().charAt(0) == lista.array[j].getTitulo_original().charAt(0)) {
                    
                    int index = 1;
                    
                    while (lista.array[desordenado].getTitulo_original().charAt(index) == lista.array[j].getTitulo_original().charAt(index)) {
                        index++;
                    }

                    if (lista.array[desordenado].getTitulo_original().charAt(index) > lista.array[j].getTitulo_original().charAt(index)) {
                        desordenado = j;
                    }

                }
            }
            troca (desordenado, i, lista);
        }
    }
    
    public void insercao (Lista lista) {

        
        for (int i = 1; i < lista.num_filmes; i++) {

            Filme filme = lista.array[i];
            int j = i - 1;

            while (j >= 0 && lista.array[j].getData_lancamento().after(filme.getData_lancamento())) {

                lista.array[j+1] = lista.array[j];
                j--;
            }
            lista.array[j+1] = filme;
        }
    }

    /*
        void shellsort () {

            int h = 1;
            do {
                h = 3*h + 1
            } while (h < n);

            do {
                h /= 3;
                for (int cor = 0; cor < h; cor++) {
                    insercao_por_cor(cor, h);
                }
            } while (h != 1);
        }

        void insercao_por_cor(int cor, int h) {
            for (int i = (h + cor); i < n; i+=h) {
                int temp = array[i];
                int j = i - h;
                while (j >= 0 && array[j] > temp) {
                    array[j+h] = array[j];
                    j -= h;
                }
                array[j+h] = temp;
            }
        }
    */
    
    public void quicksort (Lista lista) {
        quicksort(0, lista.num_filmes-1, lista);
    }

    public void quicksort (int esq, int dir, Lista lista) {
        int i = esq, j = dir;
        Filme pivo = lista.array[(esq+dir)/2];

        while (i <= j) {
            while (lista.array[i].getSituacao().compareTo(pivo.getSituacao()) < 0) {
                i++;
            }
            while (lista.array[j].getSituacao().compareTo(pivo.getSituacao()) > 0) {
                j--;
            }

            if (lista.array[j].getSituacao().compareTo(pivo.getSituacao()) == 0) {
                
                while (lista.array[i].getNome().compareTo(pivo.getNome()) < 0) {
                    i++;
                }
                while (lista.array[j].getNome().compareTo(pivo.getNome()) > 0) {
                    j--;
                }
            }
            if (i <= j) {
                troca(i, j, lista);
                i++;
                j--;
            }
        }

        if (esq < j) {
            quicksort(esq, j, lista);
        }

        if (i < dir) {
            quicksort(i, dir, lista);
        }
    }

    public void bubblesort (Lista lista) {

        for (int i = lista.num_filmes - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (lista.array[j].getDuracao() > lista.array[j+1].getDuracao()) {
                    troca (j, j + 1, lista);
                }
            }
        }
    }
    
    public void mergesort (Lista lista) {
        mergesort(lista, lista.num_filmes);
    }

    public void mergesort (Lista lista, int tam) {
        
        if (tam < 2) return;

        int meio = tam/2;

        Lista esq_lista = new Lista();
        esq_lista.construtor(meio);
        Lista dir_lista = new Lista();
        dir_lista.construtor(tam - meio);

        for (int i = 0, k = 0; i < tam; ++i) {
            if (i < meio) {
                esq_lista.array[i] = lista.array[i];
                esq_lista.num_filmes++;
            }
            else {
                dir_lista.array[k] = lista.array[i];
                dir_lista.num_filmes++;
                k++;
            }
        }

        mergesort (esq_lista, meio);
        mergesort (dir_lista, tam - meio);

        merge (lista, esq_lista, dir_lista, meio, tam - meio);
    }

    public void merge (Lista lista, Lista esq_lista, Lista dir_lista, int esq_tam, int dir_tam) {

        int i = 0, e = 0, d = 0;

        while (e < esq_tam && d < dir_tam) {
            if ((Float.compare(esq_lista.array[e].getOrcamento(), dir_lista.array[d].getOrcamento()) < 0) || (Float.compare(esq_lista.array[e].getOrcamento(), dir_lista.array[d].getOrcamento()) == 0 && esq_lista.array[e].getNome().compareTo(dir_lista.array[d].getNome()) < 0)) {
                lista.array[i++] = esq_lista.array[e++];
            }
            else {
                lista.array[i++] = dir_lista.array[d++];
            }
        }

        while (e < esq_tam) {
            lista.array[i++] = esq_lista.array[e++];
        }

        while (d < dir_tam) {
            lista.array[i++] = dir_lista.array[d++];
        }
    }
}

public class TP03Q09MergesortemJava {
    public static void main (String args[]) throws IOException, Exception {

        Lista lista = new Lista();
        lista.construtor(50);

        Ordenacao ordenacao = new Ordenacao();

        Scanner scanner = new Scanner(System.in, "UTF-8");
        String input = new String();

        do {
            
            input = scanner.nextLine();

            if (fim(input)) continue;

            Filme filme = new Filme();

            filme.le_html(input);
            
            lista.inserirFim(filme);

        } while (!fim(input));

        scanner.close();

        ordenacao.mergesort(lista);

        lista.imprimir();
    }

    public static boolean fim (String input) {
        return (input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M'); 
    }
}