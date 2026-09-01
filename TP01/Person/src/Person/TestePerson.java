package Person;			//GABRIEL RIBEIRO CB3021726
import java.util.Scanner;

public class TestePerson {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== CADASTRO DE ESTUDANTE (STUDENT) ======");
        System.out.print("Nome: ");
        String nomeEstudante = scanner.nextLine();
        System.out.print("Endereço: ");
        String enderecoEstudante = scanner.nextLine();
        System.out.print("Programa/Curso: ");
        String programa = scanner.nextLine();

        int ano = 0;
        while (true) {
            System.out.print("Ano letivo (Número inteiro): ");
            String entradaAno = scanner.nextLine().trim();
            try {
                ano = Integer.parseInt(entradaAno);
                if (ano > 0) break;
                System.out.println("❌ Erro: O ano deve ser maior que zero.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Erro: Digite apenas um número inteiro válido.");
            }
        }

        double mensalidade = 0;
        while (true) {
            System.out.print("Valor da mensalidade (fee): ");
            String entradaFee = scanner.nextLine().trim().replace(',', '.');
            try {
                mensalidade = Double.parseDouble(entradaFee);
                if (mensalidade >= 0) break;
                System.out.println("❌ Erro: A mensalidade não pode ser negativa.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Erro: Digite um valor numérico válido (Ex: 450,50).");
            }
        }

        Student estudante = new Student(nomeEstudante, enderecoEstudante, programa, ano, mensalidade);

        System.out.println("\n====== CADASTRO DE FUNCIONÁRIO (STAFF) ======");
        System.out.print("Nome: ");
        String nomeStaff = scanner.nextLine();
        System.out.print("Endereço: ");
        String enderecoStaff = scanner.nextLine();
        System.out.print("Escola: ");
        String escola = scanner.nextLine();

        double salario = 0;
        while (true) {
            System.out.print("Salário (pay): ");
            String entradaPay = scanner.nextLine().trim().replace(',', '.');
            try {
                salario = Double.parseDouble(entradaPay);
                if (salario >= 0) break;
                System.out.println("❌ Erro: O salário não pode ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Erro: Digite um valor numérico válido (Ex: 3200,00).");
            }
        }

      
        Staff funcionario = new Staff(nomeStaff, enderecoStaff, escola, salario);


        System.out.println("\n=============================================");
        System.out.println("     EXECUTANDO TESTES DOS MÉTODOS");
        System.out.println("=============================================");

        System.out.println("\n[Teste toString() - Student]:");
        System.out.println(estudante.toString());
        
        System.out.println("\n[Teste toString() - Staff]:");
        System.out.println(funcionario.toString());

        System.out.println("\n[Modificando dados de Student via Setters...]");
        estudante.setAddress("Novo Endereço do Aluno, 123");
        estudante.setProgram("Novo Curso de TI");
        estudante.setYear(2);
        estudante.setFee(520.80);
        System.out.println("Dados atualizados de Student via Getters:");
        System.out.println("-> getName(): " + estudante.getName()); // herdado de Person
        System.out.println("-> getAddress(): " + estudante.getAddress()); // herdado de Person
        System.out.println("-> getProgram(): " + estudante.getProgram());
        System.out.println("-> getYear(): " + estudante.getYear());
        System.out.println("-> getFee(): " + estudante.getFee());

        System.out.println("\n[Modificando dados de Staff via Setters...]");
        funcionario.setAddress("Novo Endereço do Staff, 456");
        funcionario.setSchool("Nova Escola Politécnica");
        funcionario.setPay(4100.25);
        System.out.println("Dados atualizados de Staff via Getters:");
        System.out.println("-> getName(): " + funcionario.getName()); // herdado de Person
        System.out.println("-> getAddress(): " + funcionario.getAddress()); // herdado de Person
        System.out.println("-> getSchool(): " + funcionario.getSchool());
        System.out.println("-> getPay(): " + funcionario.getPay());
        scanner.close();
        System.out.println("\n--- FIM DE TODOS OS TESTES ---");
    }
}
