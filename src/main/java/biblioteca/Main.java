package biblioteca;

import java.util.Scanner;

/**
 *
 * @author luana
 */
public class Main {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("__MENU PRINCIPAL__\n");
            System.out.println("Escolha o número da operação deseja realizar?");
            System.out.println("1 - Cadastrar um livro");
            System.out.println("2 - Cadastrar um periódico");
            System.out.println("3 - Cadastrar um autor");
            System.out.println("4 - Cadastrar uma editora");
            System.out.println("5 - Cadastrar um aluno");
            System.out.println("6 - Realizar Empréstimo");
            System.out.println("7 - Cancelar empréstimo");
            System.out.println("8 - Listar todos os empréstimos ativos");
            System.out.println("9 - Listar as devoluções");
            System.out.println("0 - Sair");
            option = ler.nextInt();
            ler.nextLine();

            switch (option){
            case 1 -> {
                System.out.println("Você selecionou a opção 1 - Cadastrar um livro");
                // Livros novo_livro = new Livros();
                // novo_livro.adicionarLivro();

           }
            case 2 -> {
                
                System.out.println("Você selecionou a opção 2 - Cadastrar um periódico");
                // Periodicos novo_periodico = new Periodicos();
                // novo_periodico.cadastraPeriodicos();

            }
            case 3 -> { // FUNCIONANDO!
                System.out.println("Você selecionou a opção 3 - Cadastrar um autor");
                Autores novo_autor = new Autores();
                novo_autor.cadastrarAutores();
           
            }
            case 4 -> { // FUNCIONANDO!
                System.out.println("Você selecionou a opção 4 - Cadastrar uma editora");
                Editoras nova_editora = new Editoras();
                nova_editora.adicionaEditoras();

            }
            case 5 -> {
                System.out.println("Você selecionou a opção 5 - Cadastrar um aluno");
                // Alunos novo_aluno = new Alunos();
                // novo_aluno.cadastraAlunos();

            }
            
            case 6 -> {
                System.out.println("Você selecionou a opção 6 - Realizar Empréstimo");
                // Emprestimos novo_emprestimo = new Emprestimos();
                // novo_emprestimo.cadastraEmprestimos();

            }
            
            case 7 -> {
                System.out.println("Você selecionou a opção 7 - Cancelar empréstimo");
                // novo_emprestimo.cancelaEmprestimo();

            }
            
            case 8 -> {
                System.out.println("Você selecionou a opção 8 - Listar todos os empréstimos ativos");
                // exibe empréstimos

            }
            
            case 9 -> {
                System.out.println("Você selecionou a opção 9 - Listar as devoluções");
                // Periodicos novo_periodico = new Periodicos();
                // novo_periodico.cadastraPeriodicos();

            }
            
            case 0 -> System.out.println("Saindo.");
            default -> System.out.println("Opção inválida! Tente novamente");
    }
            if (option != 0) {
                    System.out.println("\nPressione Enter para voltar ao menu...");
                    ler.nextLine();
            }
     } while (option != 0);
             ler.close();
     }
}