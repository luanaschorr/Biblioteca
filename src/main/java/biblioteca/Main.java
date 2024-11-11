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
                     System.out.println("2 - Cadastrar uma editora");
                     System.out.println("3 - Cadastrar um autor");
                     System.out.println("4 - Cadastrar um periódico");
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
                         System.out.println("Informe os dados do livro a ser cadastrado:");
                         System.out.println("Titulo: ");
                         String titulo = ler.nextLine();
                         System.out.println("Autor: ");
                         String autor = ler.nextLine();
                         System.out.println("Editora: ");
                         String editora = ler.nextLine();
                        // chama função add livros
             }
                     case 2 -> { // FUNCIONANDO!
                         System.out.println("Você selecionou a opção 2 - Cadastrar uma editora");
                         Editoras nova_editora = new Editoras();
                         nova_editora.adicionaEditoras();
                         
                 
             }
                     case 3 -> {
                         System.out.println("Você selecionou a opção 3 - Devolver um livro");
                         System.out.println("Informe o ID do livro que deseja devolver:");
                         System.out.println("ID: ");
                         int id = ler.nextInt();

                 // chama função de devolução de livro
             }
                     case 4 -> {
                         System.out.println("Você selecionou a opção 4 - Mostrar todos os livros");
                         System.out.println("_Listagem de livros_");

                 // Select * from tb_livros
             }
                     case 5 -> System.out.println("Saindo.");
                     default -> System.out.println("Opção inválida! Tente novamente");
             }
                     if (option != 5) {
                             System.out.println("\nPressione Enter para voltar ao menu...");
                             ler.nextLine();
                     }
     } while (option != 5);
             ler.close();
     }
}