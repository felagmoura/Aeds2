class Pesquisa {
    public boolean sequencial (Lista lista, String chave) {
        
        boolean encontrado = false;

        for (int i = 0; i < lista.num_filmes; i++) 
        {
            if (lista.array[i].getNome().contains(chave))
            {
                encontrado = true;
                continue;
            }
        }
        
        return encontrado;
    }

    public boolean binaria (Lista lista, String chave) throws InterruptedException {
        
        boolean encontrado = false;
        int menor = 0, maior = lista.num_filmes - 1, meio;

        while (menor <= maior)
        {
            meio = (maior + menor)/2;

            if (lista.array[meio].getNome().contains(chave)) 
            {
                encontrado = true;
                menor = maior + 1;
            }
            else if (chave.charAt(0) > lista.array[meio].getNome().charAt(0))
            {
                menor = meio + 1;
            }
            else if (chave.charAt(0) < lista.array[meio].getNome().charAt(0))
            {
                maior = meio - 1;
            }

            else if (chave.charAt(0) == lista.array[meio].getNome().charAt(0))
            {
                int index = 1;

                while (chave.charAt(index) == lista.array[meio].getNome().charAt(index)) 
                {
                    index++;
                }

                if (chave.charAt(index) > lista.array[meio].getNome().charAt(index))
                {
                    menor = meio + 1;
                }
                else if (chave.charAt(index) < lista.array[meio].getNome().charAt(index))
                {
                    maior = meio - 1;
                }
            }
        }

        return encontrado;
    }
}
