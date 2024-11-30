package biblioteca;

import java.util.Scanner;

/**
 *
 * @author luana
 */
public class Main {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int opInicial;

        do {
            System.out.println("Bem vindo ao Sistema Biblioteca!");
            System.out.println("==============================");
            System.out.println("Que tipo de operação você deseja realizar?");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Empréstimo");
            System.out.println("3 - Listar");
            System.out.println("0 - Sair");
            
            opInicial = getValidInt(ler);
            ler.nextLine();

            switch (opInicial) {
                case 1 -> menuCadastrar(ler);
                case 2 -> menuEmprestimos(ler);
                case 3 -> menuListagem(ler);
                case 0 -> System.out.println("Saindo.");
                default -> System.out.println("Opção inválida! Tente novamente");
            }
        } while (opInicial != 0);

        ler.close();
    }

    public static void menuCadastrar(Scanner ler) {
        int option;

        do {
            System.out.println("Escolha o número da operação deseja realizar:");
            System.out.println("1 - Cadastrar um livro");
            System.out.println("2 - Cadastrar um periódico");
            System.out.println("3 - Cadastrar um autor");
            System.out.println("4 - Cadastrar uma editora");
            System.out.println("5 - Cadastrar um aluno");
            System.out.println("0 - Voltar");

            option = getValidInt(ler);
            ler.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.println("Você selecionou a opção 1 - Cadastrar um livro");
                    Livros novo_livro = new Livros();
                    novo_livro.adicionarLivro();
                }
                case 2 -> {
                    System.out.println("Você selecionou a opção 2 - Cadastrar um periódico");
                    Periodicos novo_periodico = new Periodicos();
                    novo_periodico.cadastraPeriodicos();
                }
                case 3 -> {
                    System.out.println("Você selecionou a opção 3 - Cadastrar um autor");
                    Autores novo_autor = new Autores();
                    novo_autor.cadastrarAutores();
                }
                case 4 -> {
                    System.out.println("Você selecionou a opção 4 - Cadastrar uma editora");
                    Editoras nova_editora = new Editoras();
                    nova_editora.adicionaEditoras();
                }
                case 5 -> {
                    System.out.println("Você selecionou a opção 5 - Cadastrar um aluno");
                    Alunos novo_aluno = new Alunos();
                    novo_aluno.cadastrarAlunos();
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida! Tente novamente");
            }

            if (option != 0) pause(ler);
        } while (option != 0);
    }

    public static void menuEmprestimos(Scanner ler) {
        int option;

        do {
            System.out.println("Escolha o número da operação deseja realizar:");
            System.out.println("1 - Realizar Empréstimo");
            System.out.println("2 - Devolução");
            System.out.println("3 - Listar todos os empréstimos ativos");
            System.out.println("0 - Voltar");
            
            option = getValidInt(ler);
            ler.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.println("Você selecionou a opção 1 - Realizar um empréstimo");
                    Emprestimos novo_emprestimo = new Emprestimos();
                    novo_emprestimo.realizarEmprestimo();
                }
                case 2 -> {
                    System.out.println("Você selecionou a opção 2 - Devolução");
                    Emprestimos devolucao = new Emprestimos();
                    devolucao.devolucaoExemplar();
                }
                case 3 -> {
                    System.out.println("Você selecionou a opção 3 - Listar todos os empréstimos ativos");
                    Emprestimos listaEmprestimos = new Emprestimos();
                    listaEmprestimos.listarEmprestimo();
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida! Tente novamente");
            }

            if (option != 0) pause(ler);
        } while (option != 0);
    }

    public static void menuListagem(Scanner ler) {
        int option;

        do {
            System.out.println("Escolha o número da operação deseja realizar:");
            System.out.println("1 - Listar todos os alunos");
            System.out.println("2 - Listar todos os autores");
            System.out.println("3 - Listar todos os livros");
            System.out.println("4 - Listar todos os periodicos");
            System.out.println("0 - Voltar");
            
            option = getValidInt(ler);
            ler.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.println("Você selecionou a opção 1 - Listar todos os alunos");
                    Alunos listaAlunos = new Alunos();
                    listaAlunos.listarAlunos();
                }
                case 2 -> {
                    System.out.println("Você selecionou a opção 2 - Listar todos os autores");
                    Autores listaAutores = new Autores();
                    listaAutores.listarAutores();
                }
                case 3 -> {
                    System.out.println("Você selecionou a opção 3 - Listar todos os livros");
                    Livros listarLivros = new Livros();
                    listarLivros.listarLivros();
                }
                case 4 -> {
                    System.out.println("Você selecionou a opção 3 - Listar todos os periodicos");
                    Periodicos listarPeriodicos = new Periodicos();
                    listarPeriodicos.listarPeriodicos();
                }     
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida! Tente novamente");
            }

            if (option != 0) pause(ler);
        } while (option != 0);
    }

    private static void pause(Scanner ler) {
        System.out.println("\nPressione Enter para continuar...");
        ler.nextLine();
    }

    public static int getValidInt(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Digite sua escolha: ");
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite apenas números.");
                scanner.nextLine();
            }
        }
    }
}