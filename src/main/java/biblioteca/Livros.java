package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Livros {


    public void adicionarLivro() {
        Editoras editoras = new Editoras();
        Autores autores = new Autores();
        Estante estante = new Estante(null);
        Scanner ler = new Scanner(System.in);


        System.out.println("Informe os dados do livro.");
        System.out.print("Título: ");
        String titulo = ler.nextLine();

        System.out.print("ID editora: ");
        int id_editora = ler.nextInt();
        long editoraV = editoras.verificarEditoraExistente(id_editora);
        if (editoraV == -1) {
            System.out.println("Editora não encontrada. Abortando operação.");
            return;
        }

        System.out.println("ID do autor:");
        int id_autor = ler.nextInt();
        Long autor = autores.verificarAutorExistente(id_autor);
        if (autor == null) {
            System.out.println("Autor não encontrado. Abortando operação.");
            return;
        }
        
        String codigoEstante = autores.retornaAutor(id_autor);
        System.out.println(codigoEstante);
        int idEstante  = estante.verificaEstante(codigoEstante);
        System.out.print("Edição: ");
        int edicao_livro = ler.nextInt();

        System.out.print("Ano de Publicação: ");
        int ano_exemplar = ler.nextInt();

        System.out.print("Número total de exemplares: ");
        int n_total_exemplares = ler.nextInt();

        System.out.print("Número de exemplares disponíveis: ");
        int n_dispo_exemplares = ler.nextInt();

        int loc_estante = idEstante + 1;

        int n_da_estante_exemplar = 0;

        String sqlInsert = "INSERT INTO tb_livros (titulo_exemplar, id_editora, autor_livro, id_estante, edicao_livro, ano_exemplar, n_total_exemplares, n_dispo_exemplares, loc_estante, n_da_estante_exemplar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sqlInsert)) {

            stm.setString(1, titulo);
            stm.setInt(2, id_editora);
            stm.setInt(3, id_autor);
            stm.setInt(4, idEstante);  
            stm.setInt(5, edicao_livro);
            stm.setInt(6, ano_exemplar);
            stm.setInt(7, n_total_exemplares);
            stm.setInt(8, n_dispo_exemplares);
            stm.setInt(9, loc_estante);
            stm.setInt(10, n_da_estante_exemplar);

            int linhasInseridas = stm.executeUpdate();
            if (linhasInseridas > 0) {
                System.out.println("Livro cadastrado com sucesso.");
            } else {
                System.out.println("Erro ao cadastrar o livro.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados do livro: " + e.getMessage());
        }

    }

    public void imprimirLivros() {
        Estante estante = new Estante(null);
        String sql = """
            SELECT tb_livros.id_exemplar, tb_livros.titulo_exemplar, tb_autores.nome AS autor, tb_editoras.nome AS editora,
                   tb_estantes_biblioteca.codigo AS estante
            FROM tb_livros
            JOIN tb_autores ON tb_livros.autor_livro = tb_autores.id
            JOIN tb_editoras ON tb_livros.id_editora = tb_editoras.id
            JOIN tb_estantes_biblioteca ON tb_livros.id_estante = tb_estantes_biblioteca.id_estante;
            """;
        
    
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
    
            while (rs.next()) {
            int id = rs.getInt("id_exemplar");
                String titulo = rs.getString("titulo_exemplar");
                String autor = rs.getString("autor");
                String editora = rs.getString("editora");
                int estanteid = rs.getInt("estante");
                String resEstante = estante.retornaCodigo(estanteid);
                System.out.println("ID: " + id);
                System.out.println("Título: " + titulo);
                System.out.println("Autor: " + autor);
                System.out.println("Editora: " + editora);
                System.out.println("Estante: " + resEstante);

                System.out.println("=====================================");
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os livros: " + e.getMessage());
        }
    }
    
}


