package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Livros extends Exemplares {
    private String isbn;
    private int editora;

    // Construtor
    public Livros(int id, String titulo, int anoPublicacao, String autor, int n_estante,
                  int estante, int n_dispo_exemplare, int n_total_exemplare, int loc_estante, String isbn, int editora) {
        super(id, titulo, anoPublicacao, autor, n_estante, estante, n_dispo_exemplare, n_total_exemplare, loc_estante);
        this.isbn = isbn;
        this.editora = editora;
    }

    // Getters e Setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getEditora() {
        return editora;
    }

    public void setEditora(int editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
               ", Título: " + getTitulo() +
               ", Autor: " + getAutor() + 
               ", Data de Publicação: " + getAnoPublicacao() +
               ", Quantidade: " + getNTotalExemplare();
    }

    // Método para cadastro do livro
    public void cadastroLivro() {
        Scanner sc = new Scanner(System.in);

        // Entrada de dados
        System.out.println("Digite o título do livro: ");
        String titulo = sc.nextLine();
        
        System.out.println("Digite o nome do autor");
        String autor = sc.nextLine();
        sc.nextLine();
        
        System.out.println("Digite o ID da editora");
        int editora = sc.nextInt();
        
        System.out.println("Digite a data da publicação: ");
        int anoPublicacao = sc.nextInt();

        System.out.println("Digite a quantidade total de exemplares: ");
        int nTotalExemplares = sc.nextInt();
        
        System.out.println("Digite a Estante: ");
        int estante = sc.nextInt();
        
        System.out.println("Digite a localização do estante: ");
        int locEstante = sc.nextInt();

        System.out.println("Digite o num de exemplares dipo: ");
        int n_dispo_exemplare = sc.nextInt();
    
        sc.nextLine();  
        int edicao_livro = 5;
        // Consumir a nova linha deixada pelo nextInt
      /*  
        System.out.println("Digite o ISBN do livro: ");
        String isbnString = sc.nextLine();

        // Validação do ISBN
        long isbn;
        try {
            isbn = Long.parseLong(isbnString);
        } catch (NumberFormatException e) {
            System.out.println("Erro: ISBN inválido. Digite um número válido.");
            return;  // Interrompe a execução do cadastro caso o ISBN seja inválido
        }
    */
        String sql = "INSERT INTO tb_livros (titulo_exemplar, ano_exemplar, n_da_estante_exemplar, n_total_exemplares, n_dispo_exemplares, loc_estante, id_editora,autor_livro,edicao_livro) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            
            // Definir os valores para os parâmetros da consulta
            stm.setString(1, titulo);
            stm.setInt(2, anoPublicacao);
            stm.setInt(3, estante);
            stm.setInt(4, nTotalExemplares);
            stm.setInt(5, locEstante);
            stm.setLong(6, editora);
            stm.setLong(7, n_dispo_exemplare);
            stm.setString(8, autor);
            stm.setInt(9,edicao_livro);

            // Executar a inserção
            int linhasInseridas = stm.executeUpdate();
            if (linhasInseridas > 0) {
                System.out.println("Inserção bem-sucedida!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }
}
