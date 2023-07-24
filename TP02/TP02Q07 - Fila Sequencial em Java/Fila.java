public class Fila {
    Filme[] array;
    int primeiro, ultimo, num_filmes;

    public void construtor (int tamanho) {
        array = new Filme[tamanho+1];
        primeiro = 0;
        ultimo = 0;
        num_filmes = 0;
    }

    public void inserir (Filme filme) throws Exception {

        if ((ultimo + 1) % array.length == primeiro) {
            
            remover();
        }

        array[ultimo] = filme;
        ultimo = (ultimo + 1) % array.length;
        num_filmes++;

        int soma = 0, media = 0;
        for (int i = 0; i < num_filmes; i++) {
            soma += array[i].getDuracao();
        }
        media = soma/num_filmes;
        System.out.println(media);
        
    }

    public Filme remover () throws Exception {

        if (primeiro == ultimo) {
            throw new Exception("Erro em R");
        }

        Filme filme_removido = array[primeiro];
        primeiro = (primeiro + 1) % array.length;
        num_filmes--;
        return filme_removido;
    }

    public void imprimir () {

        Filme filme = new Filme();

        System.out.print("[");

        for (int i = primeiro; i != ultimo; i = (i+1) % array.length) {
            
            filme = array[i];
            System.out.print(filme.getNome() + ", ");
        }

        System.out.println("]");
    } 

}