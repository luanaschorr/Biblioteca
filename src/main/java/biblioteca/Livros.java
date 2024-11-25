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
        int idEditora = ler.nextInt();
        long editoraV = editoras.verificarEditoraExistente(idEditora);
        if (editoraV == -1) {
            return;
        }
    
        System.out.println("""

        Escolha uma opição para inserir o autor:
        1- Listar Autores.
        2- Buscar Autor.
        3- Digitar por ID.
        4- Voltar para o o menu
        """);

        System.out.print("Escolha uma opção: ");
        int opcao = ler.nextInt();  
        ler.nextLine(); 

        int id_autor = 0; 

        switch (opcao) {
        case 1:
            System.out.println("Você escolheu 'Listar Autores'.");
            autores.listarAutores();

            System.out.println("Digitar por ID:");
            id_autor = ler.nextInt();
            ler.nextLine();
            Long autor = autores.verificarAutorExistente(id_autor);
            if (autor == null || autor == -1) {
                System.out.println("Autor não encontrado.");
            }
            break; 

        case 2:
            System.out.println("Você escolheu 'Buscar Autor'.");
            System.out.print("Digite o nome do autor para buscar: ");
            String nomeAutor = ler.nextLine();
            Long autorId = autores.buscarAutorPorNome(nomeAutor);
            if (autorId != null) {
                System.out.println("Autor encontrado: " + nomeAutor);
                id_autor = autorId.intValue();
            } else {
                System.out.println("Autor não encontrado.");
                break;
            }
            break;

        case 3:
            System.out.println("Digitar por ID:");
            id_autor = ler.nextInt();
            ler.nextLine();
            
            autor = autores.verificarAutorExistente(id_autor);
            if (autor == null || autor == -1) {
                System.out.println("Autor não encontrado.");
            }
            break;

        default:
            System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            return;
        }

        if (id_autor == -1) {
        System.out.println("Operação cancelada. Autor inválido.");
        return;
        }
    
        String sobrenomeAutor = autores.retornaSobrenomeAutorId(id_autor);
        String codigoEstante = estante.geraCodigo(sobrenomeAutor);
    
        if (!estante.verificaEstante(codigoEstante)) {
            estante.fazEstante(sobrenomeAutor);
        }
    
        int idEstante = estante.retornaIdEstante(codigoEstante);
    
        System.out.print("Edição: ");
        int edicaoLivro = ler.nextInt();
    
        System.out.print("Ano de Publicação: ");
        int anoExemplar = ler.nextInt();
    
        String sqlInsert = "INSERT INTO tb_livros (titulo_exemplar, id_editora, autor_livro, id_estante, codigo_estante, edicao_livro, ano_exemplar) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sqlInsert)) {
    
            stm.setString(1, titulo);
            stm.setInt(2, idEditora);
            stm.setInt(3, id_autor);
            stm.setInt(4, idEstante);
            stm.setString(5, codigoEstante);
            stm.setInt(6, edicaoLivro);
            stm.setInt(7, anoExemplar);
    
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

    public void listarLivros() {
        String sql = """
            SELECT tb_livros.id_exemplar, tb_livros.titulo_exemplar, tb_autores.nome AS autor, tb_editoras.nome AS editora,
                   tb_estantes_biblioteca.codigo AS estante
            FROM tb_livros
            JOIN tb_autores ON tb_livros.autor_livro = tb_autores.id
            JOIN tb_editoras ON tb_livros.id_editora = tb_editoras.id
            JOIN tb_estantes_biblioteca ON tb_livros.codigo_estante = tb_estantes_biblioteca.codigo;
            """;

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_exemplar");
                String titulo = rs.getString("titulo_exemplar");
                String autor = rs.getString("autor");
                String editora = rs.getString("editora");
                String estante = rs.getString("estante");

                System.out.println("ID: " + id);
                System.out.println("Título: " + titulo);
                System.out.println("Autor: " + autor);
                System.out.println("Editora: " + editora);
                System.out.println("Estante: " + estante);
                System.out.println("=====================================");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar os livros: " + e.getMessage());
        }
    }
}
