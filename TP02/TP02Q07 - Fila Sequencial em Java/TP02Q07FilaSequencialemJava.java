import java.util.Date;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TP02Q07FilaSequencialemJava {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        filaDeFilmes fila = new filaDeFilmes();
        String file = sc.nextLine();
        while (isFim(file) == false) {
            Filme tmp = new Filme();
            tmp.lerArquivo(file);
            fila.inserir(tmp);
            fila.imprimeMedia();
            file = sc.nextLine();
        }
        file = sc.nextLine();
        int op = Integer.parseInt(file);
        String filme = "";
        char operacao = '\0';
        int pos = 0;
        while (op > 0) {
            file = sc.nextLine();
            Filme tmp = new Filme();
            operacao = file.charAt(0);
            if ((operacao == 'I')) {
                    filme = file.substring(2);
                tmp.lerArquivo(filme);
                fila.inserir(tmp);
                fila.imprimeMedia();
            }
            else if (operacao == ('R')) {
                tmp = fila.remover();
                System.out.println("(R) " + tmp.getNome());
            }
            op--;
        }
        fila.imprimir();
        sc.close();
    }

    public static boolean isFim(String str) {
        return ((str.length() == 3) && (str.charAt(0) == 'F') && (str.charAt(1) == 'I')
                && (str.charAt(2) == 'M'));
    }
}

class Filme {

    private String nome;
    private String titulo;
    private Date data;
    private int duracao;
    private String genero;
    private String idioma;
    private String situacao;
    private float orcamento;
    private String[] palavrasChave;
    public Filme(String nome, String titulo, Date data, int duracao, String genero, String idioma,
            String situacao, float orcamento, String[] palavrasChave) {
        this.nome = nome;
        this.titulo = titulo;
        this.data = data;
        this.duracao = duracao;
        this.genero = genero;
        this.idioma = idioma;
        this.situacao = situacao;
        this.orcamento = orcamento;
        this.palavrasChave = palavrasChave;
    }

    public Filme() {
    }
    public String getNome() {
        return nome;
    }
    public String getTitulo() {
        return titulo;
    }
    public Date getData() {
        return data;
    }
    public int getDuracao() {
        return duracao;
    }
    public String getGenero() {
        return genero;
    }
    public String getIdioma() {
        return idioma;
    }
    public String getSituacao() {
        return situacao;
    }
    public float getOrcamento() {
        return orcamento;
    }
    public String[] getPalavrasChave() {
        return palavrasChave;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public void setOrcamento(float orcamento) {
        this.orcamento = orcamento;
    }

    public void setPalavrasChave(String[] palavrasChave) {
        this.palavrasChave = palavrasChave;
    }
    public Filme clone() {
        Filme clone = new Filme();
        clone.setNome(this.getNome());
        clone.setTitulo(this.getTitulo());
        clone.setData(this.getData());
        clone.setDuracao(this.getDuracao());
        clone.setGenero(this.getGenero());
        clone.setIdioma(this.getIdioma());
        clone.setSituacao(this.getSituacao());
        clone.setOrcamento(this.getOrcamento());
        clone.setPalavrasChave(this.getPalavrasChave());
        return clone;
    }
    public void Imprimir() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print(this.getNome() + " " + this.getTitulo() + " " + sdf.format(this.getData()) + " "
                + this.getDuracao() + " " + this.getGenero() + " " + this.getIdioma() + " " + this.getSituacao() + " "
                + this.getOrcamento() + " ");
        System.out.print("[");
        if (this.getPalavrasChave() != null) {
            String[] pChave = this.getPalavrasChave();

            for (int i = 0; i < pChave.length - 1; i++) {
                System.out.print(pChave[i] + ", ");
            }
            System.out.print(pChave[pChave.length - 1]);
        }
        System.out.print("]\n");
    }

    public void lerArquivo(String arquivo) throws Exception {
        File file = new File("/tmp/filmes/" + arquivo);
        Scanner scanner = new Scanner(file, "UTF-8");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean temTitulo = false;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("h2 class")) {
                line = scanner.nextLine();
                this.setNome(removeTag(line).trim().replace("&amp;", ""));
            }
            else if (line.contains("span class=\"release\"")) {
                line = scanner.nextLine();
                line = line.trim();
                this.setData(sdf.parse(line));
            }
            else if (line.contains("runtime")) {
                scanner.nextLine();
                line = scanner.nextLine().trim();
                int duracao = 0;
                int i = 0;
                int pos = 0;
                String tmp = "";
                if (line.contains("h")) {
                    i = line.indexOf("h");
                    for (int j = 0; j < i; j++) {
                        if ((line.charAt(j) >= 48) && (line.charAt(j) <= 57)) {
                            tmp = tmp + line.charAt(j);
                            pos++;
                        }
                    }
                    duracao = Integer.parseInt(tmp) * 60;
                    pos++;
                }

                if (line.contains("m")) {
                    i = line.indexOf("m");
                    tmp = "";
                    for (int j = pos; j < i; j++) {
                        if ((line.charAt(j) >= 48) && (line.charAt(j) <= 57)) {
                            tmp = tmp + line.charAt(j);
                        }
                    }
                    duracao = duracao + Integer.parseInt(tmp);
                }
                this.setDuracao(duracao);
            }
            else if (line.contains("genres")) {
                scanner.nextLine();
                line = scanner.nextLine();
                line = removeTag(line).replace("&nbsp;", "").trim();
                this.setGenero(line);
            }
            else if (line.contains("Idioma")) {
                line = removeTag(line.trim());
                line = line.replace("Idioma original ", "");
                this.setIdioma(line);
            }
            else if (line.contains("Orçamento")) {
                line = removeTag(line);
                line = line.replace("Orçamento", "").trim().replaceAll("[,$-]", "");
                if (line.isEmpty()) {
                    this.setOrcamento(0);
                } else {
                    this.setOrcamento(Float.parseFloat(line));
                }
            }
            else if (line.contains("Palavras-chave")) {
                String tmp = "";
                while ((line.contains("<li><a") == false) && (line.contains("Nenhuma palavra") == false)) {
                    line = scanner.nextLine();
                }
                if (line.contains("<li><a")) {
                    while ((line.contains("</ul>") == false)) {
                        line = removeTag(line).trim();
                        if (((line.isEmpty()) == false)) {
                            tmp = tmp + line + ',';
                        }
                        line = scanner.nextLine();
                    }
                    this.setPalavrasChave(tmp.split(","));
                }
            }
            else if (line.contains("strong><bdi>Situação</bdi></strong")) {
                line = removeTag(line);
                line = line.replace("Situação", "").trim();
                this.setSituacao(line);
            }
            else if (line.contains("<strong>Título original</strong>")) {
                temTitulo = true;
                line = removeTag(line);
                line = line.replace("Título original", "").trim();
                this.setTitulo(line);
            }
        }
        if (temTitulo == false) {
            this.setTitulo(this.getNome());
        }
        scanner.close();
    }
    public static String removeTag(String linha) {
        String linhaMod = "";
        for (int i = 0; i < linha.length(); i++) {
            if (linha.charAt(i) == '<') {
                i++;
                while (linha.charAt(i) != '>') {
                    i++;
                }
            } else {
                linhaMod = linhaMod + linha.charAt(i);
            }
        }
        return linhaMod;
    }
}

class filaDeFilmes {
    Filme filmes[] = new Filme[6];
    private int primeiro = 0;
    private int ultimo = 0;
    
    void inserir(Filme filme) throws Exception {
        if (((ultimo + 1) % filmes.length) == primeiro) {
            remover();
        }
        
        filmes[ultimo] = filme;
        ultimo = (ultimo + 1) % filmes.length;
    }

    Filme remover() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro!");
        }
        Filme resp = filmes[primeiro];
        primeiro = (primeiro + 1) % filmes.length;
        return resp;
    }

    public void imprimir() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int count = 0;
        for (int i = primeiro; i != ultimo; i =  (i + 1) % filmes.length){
            System.out.print(
                    "[" + count + "] "
                            + filmes[i].getNome() + " " + filmes[i].getTitulo() + " " + sdf.format(filmes[i].getData())
                            + " "
                            + filmes[i].getDuracao() + " " + filmes[i].getGenero() + " " + filmes[i].getIdioma() + " "
                            + filmes[i].getSituacao()
                            + " "
                            + filmes[i].getOrcamento() );

                            System.out.print("[");
                            if (filmes[i].getPalavrasChave() != null) {
                                String[] pChave = filmes[i].getPalavrasChave();
                
                                for (int j = 0; j < pChave.length - 1; j++) {
                                    System.out.print(pChave[j] + ", ");
                                }
                                System.out.print(pChave[pChave.length - 1]);
                            }
                            System.out.print("]\n");
                        }

        }
    void imprimeMedia () {
        double media = 0.0; 
        int count = 0;
        for (int i = primeiro; i != ultimo; i =  (i + 1) % filmes.length) {
            media = media + filmes[i].getDuracao();
            count++;
        }
        media = media/count;
        System.out.println(Math.round(media));
    }
}