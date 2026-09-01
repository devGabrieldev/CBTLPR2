package Book;		//GABRIEL RIBEIRO CB3021726
import java.util.Scanner;

public class TesteBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- CADASTRO DE LIVRO ---");

        System.out.print("Digite o título do livro: ");
        String nomeLivro = scanner.nextLine();

     
        int qtdAutores = 0;
        while (true) {
            System.out.print("Quantos autores escreveram este livro? ");
            String entrada = scanner.nextLine().trim();
            try {
                qtdAutores = Integer.parseInt(entrada); 
                if (qtdAutores > 0) {
                    break; 
                }
                System.out.println("❌ Erro: A quantidade de autores deve ser maior que zero.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Erro: Digite apenas um número inteiro válido.");
            }
        }

        Author[] autores = new Author[qtdAutores];
        for (int i = 0; i < qtdAutores; i++) {
            System.out.println("\n-> Dados do Autor #" + (i + 1) + ":");
            
            System.out.print("Nome: ");
            String nomeAutor = scanner.nextLine();
            
            System.out.print("E-mail: ");
            String emailAutor = scanner.nextLine();
            
            char generoAutor;
            while (true) {
                System.out.print("Gênero (m/f): ");
                String entradaG = scanner.nextLine().trim().toLowerCase();
                if (entradaG.length() == 1 && (entradaG.charAt(0) == 'm' || entradaG.charAt(0) == 'f')) {
                    generoAutor = entradaG.charAt(0);
                    break;
                }
                System.out.println("❌ Gênero inválido! Use apenas 'm' ou 'f'.");
            }

            autores[i] = new Author(nomeAutor, emailAutor, generoAutor);
        }

        System.out.println("\n-> Dados de Venda:");
        
        
        double preco = 0;
        while (true) {
            System.out.print("Preço do livro: ");
            String entradaP = scanner.nextLine().trim();
            try {
                preco = Double.parseDouble(entradaP);
                if (preco > 0) break;
                System.out.println("❌ Erro: O preço não pode ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Erro: Digite um preço válido!");
            }
        }
        
        int qtdEstoque = 0;
        while (true) {
            System.out.print("Quantidade em estoque (qty): ");
            String entradaQ = scanner.nextLine().trim();
            try {
                qtdEstoque = Integer.parseInt(entradaQ);
                if (qtdEstoque >= 0) {
                    break;
                }
                System.out.println("❌ Erro: A quantidade em estoque não pode ser negativa.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Erro: Digite apenas um número inteiro válido para o estoque.");
            }
        }

        Book livro = new Book(nomeLivro, autores, preco, qtdEstoque);
        System.out.println("\n✔ Livro criado com sucesso utilizando o array de autores!");        
        System.out.println("\n--- VERIFICANDO MÉTODO TOSTRING() ---");
        System.out.println(livro.toString());        
        System.out.println("\n--- TESTANDO GETAUTHORNAMES() ---");
        System.out.println("Nomes dos autores vinculados: " + livro.getAuthorNames());
        scanner.close();
        System.out.println("\n--- FIM DOS TESTES ---");
    }
}
