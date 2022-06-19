import java.util.Scanner;

public class TP04Q09OperacoesemABPI {

    static ArvoreBinaria arvore = new ArvoreBinaria();
    static public void main (String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        while (scanner.hasNextLine()) 
            executa_comandos (scanner.nextLine());
    
        scanner.close();
    }

    static public void executa_comandos (String input) throws Exception {
        if (input.contains("INFIXA"))
            arvore.infixa();
        else if (input.contains("PREFIXA"))
            arvore.prefixa();
        else if (input.contains("POSFIXA"))
            arvore.posfixa();
        else if (input.charAt(0) == 'I')
            arvore.inserir(input.charAt(2));
        else if (input.charAt(0) == 'P')
            if (arvore.pesquisar(input.charAt(2))) 
                System.out.println(input.charAt(2) + " existe");
            else
                System.out.println("nao existe");
    }
}
