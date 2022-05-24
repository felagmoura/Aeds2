// IMPORTS
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
//-------------------------------------------------------------------------------//

// CLASSE FILME
public class Filme {
    // ATRIBUTOS
    private String nome = new String();
    private String titulo_original = new String();
    private Date data_lancamento = new Date();
    private int duracao = 0;
    private String genero = new String();
    private String idioma = new String();
    private String situacao = new String();
    private float orcamento = 0;
    private String[] chaves;

    //-------------------------------------------------------------------------------//
    // FUNCOES
    //-------------------------------------------------------------------------------//

    // GETs E SETs  
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

    public String getData_lancamento() {
        SimpleDateFormat formata_data = new SimpleDateFormat("dd/MM/yyyy");
        return formata_data.format(data_lancamento);
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
    
    //-------------------------------------------------------------------------------//
    // ENCONTRA OS ATRIBUTOS NO HTML 
    public void le_html(String html) throws IOException {

        File arquivo = new File("tmp/filmes/" + html);
        BufferedReader buffer = new BufferedReader(new FileReader(arquivo)); 
        String line = new String(buffer.readLine());

        // LE O HTML
        while (!line.contains("</html>")) {
            line = buffer.readLine();
            
            //-----------------------------------------------------------//
            // NOME
            if (line.contains("<h2 class=")) {
                line = buffer.readLine(); // valor do atributo esta na linha seguinte
                setNome(line.replaceAll("\\<.*?\\>", "").trim());
                setTitulo_original(getNome());
            }
            
            //-----------------------------------------------------------//
            // TITULO ORIGINAL
            else if (line.contains("Título original")) {
                // o valor de titulo original e setado em nome, com o mesmo valor que nome,
                // caso o filme tenha um titulo original diferente de nome, esse atributo sera atualizado;
                setTitulo_original(line.replaceAll("\\<.*?\\>", "").trim().substring(16));
            }
            
            //-----------------------------------------------------------//
            // SITUACAO
            else if (line.contains("<bdi>Situação</bdi>")) {
                setSituacao(line.replaceAll("\\<.*?\\>", "").trim().substring(9));                 
            }

            //-----------------------------------------------------------//
            // IDIOMA ORIGINAL
            else if (line.contains("Idioma original")) { 
                setIdioma(line.replaceAll("\\<.*?\\>", "").trim().substring(16));
            }

            //-----------------------------------------------------------//
            // GENERO
            else if (line.contains("/genre/")) {
                setGenero(line.replaceAll("\\<.*?\\>", "").trim().replaceAll("\\&.*?\\;", "")); 
            }

            //-----------------------------------------------------------//
            // TEMPO DE DURACAO
            else if (line.contains("<span class=\"runtime\">")) {
                buffer.readLine(); 
                line = (buffer.readLine().trim()); 
                int minutos = 0;
                
                try {
                    minutos = Integer.parseInt(line.substring(line.indexOf('m') - 2, line.indexOf('m')));
                } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    minutos = Integer.parseInt(line.substring(line.indexOf('m') - 1, line.indexOf('m')));
                }

                if (line.indexOf('h') != -1) {
                    int horas = Integer.parseInt(line.substring(0, line.indexOf('h')));
                    minutos += 60 * horas;
                }

                setDuracao(minutos);
            }

           //-----------------------------------------------------------//
           // DATA DE LANCAMENTO
            else if (line.contains("<span class=\"release\">")) {
                line = buffer.readLine().replaceAll("\\(.*?\\)", "").trim();
                SimpleDateFormat formata_data = new SimpleDateFormat("dd/MM/yyyy"); 
                try {
                    setData_lancamento(formata_data.parse(line)); 
                } catch (ParseException p) {} 
            }

            //-----------------------------------------------------------//
            // ORCAMENTO
            else if (line.contains("Orçamento")) {
                try {
                    setOrcamento(Float.parseFloat(line.replaceAll("\\<.*?\\>", "").replaceAll(",", "").trim().substring(11)));
                } catch (NumberFormatException e);  
            }

            //-----------------------------------------------------------//
            // PALAVRAS CHAVE
            else if (line.contains("Palavras-chave")) {
                // le o html duas vezes,
                // primeiro, para verificar se existem palavras chaves, 
                // e se tiverem, marcar o ponto de releitura do html e contar o num de chaves do filme
                // a segunda, para setar os valores do atributo
                int num_chaves = 0;
                boolean nenhuma_chave = false;
                
                while (!line.contains("</ul>"))
                {
                    line = buffer.readLine();
                    if (line.contains("<p><bdi>Nenhuma palavra-chave foi adicionada.</bdi></p>")) {
                        nenhuma_chave = true;
                        continue;
                    }
                    else if (line.contains("<ul>")) {
                        buffer.mark(1000); 
                    }
                    else if (line.contains("<li>")) {
                        num_chaves++; 
                    }
                }

                

                if (!nenhuma_chave) {
                    buffer.reset(); 
                    line = buffer.readLine(); 
                    
                    int index = 0;
                    constr_chaves(num_chaves);
                    
                    while (!line.contains("</ul>")) {
                        line = buffer.readLine();
                        if (line.contains("<li>")) {
                            setChave(line.replaceAll("\\<.*?\\>", "").trim(), index++);
                        }
                    }
                }

                else {
                    constr_chaves(1);
                    setChave("", 0);
                } 
            }
        }
        buffer.close();
    }

    //-------------------------------------------------------------------------------//
    // IMPRIMIR
    public void imprimir ()
    {   
        System.out.println (getNome() + " " + getTitulo_original() + " " + getData_lancamento() + " " + getDuracao() + " " + getGenero() + " " + getIdioma() + " " + getSituacao() + " " + getOrcamento()  + " " + getChaves());
    }
}
