package Author;		//GABRIEL RIBEIRO CB3021726
import java.util.Scanner;

public class TesteAuthor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--- CADASTRO DE AUTOR ---");
        
        System.out.print("Digite o nome do autor: ");
        String nome = scanner.nextLine();
        
        System.out.print("Digite o e-mail do autor: ");
        String email = scanner.nextLine();
        
        char genero;
        while (true) {
            System.out.print("Digite o gênero do autor (m/f): ");
            String entrada = scanner.nextLine().trim().toLowerCase(); 
            
            if (entrada.length() == 1 && (entrada.charAt(0) == 'm' || entrada.charAt(0) == 'f')) {
                genero = entrada.charAt(0);
                break;
            }
            
            System.out.println("❌ Erro: Gênero inválido! Digite apenas 'm' para masculino ou 'f' para feminino.");
        }
        
        Author autor = new Author(nome, email, genero);
        System.out.println("\n✔ Autor cadastrado com sucesso usando o Construtor!");
        System.out.println("\n--- VERIFICANDO MÉTODO TOSTRING() ---");
        System.out.println(autor.toString());
        System.out.println("\n--- TESTANDO O SETTER (ALTERAÇÃO DE E-MAIL) ---");
        System.out.print("Digite um novo e-mail para atualizar o cadastro: ");
        String novoEmail = scanner.nextLine();
        autor.setEmail(novoEmail);
        System.out.println("✔ E-mail atualizado via Setter.");
        System.out.println("\n--- TESTANDO OS GETTERS (DADOS ATUAIS NO SISTEMA) ---");
        System.out.println("Nome retornado pelo método getName():     " + autor.getName());
        System.out.println("Gênero retornado pelo método getGender(): " + autor.getGender());
        System.out.println("E-mail retornado pelo método getEmail():   " + autor.getEmail());
        scanner.close();
        System.out.println("\n--- FIM DOS TESTES ---");
    }
}
