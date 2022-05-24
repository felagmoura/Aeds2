class Pesquisa {
    public boolean sequencial (Lista lista_filmes, String chave) {
        
        boolean encontrado = false;

        
        for (int i = 0; i < lista_filmes.num_filmes; i++) 
        {
            if (lista_filmes.array[i].getNome().contains(chave))
            {
                encontrado = true;
            }
        }
        
        return encontrado;
    }
}
