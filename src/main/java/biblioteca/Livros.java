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
        ler.nextLine();  
        long editoraV = editoras.verificarEditoraExistente(id_editora);
        if (editoraV == -1) {
            System.out.println("Editora não encontrada. Abortando operação.");
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

        String codigoEstante = autores.retornaSobrenomeAutorId(id_autor);
        int id_estante = estante.verificaEstante(codigoEstante);
        
        System.out.print("Edição: ");
        int edicao_livro = ler.nextInt();
        ler.nextLine();  
    
        System.out.print("Ano de Publicação: ");
        int ano_exemplar = ler.nextInt();
        ler.nextLine();
    
        System.out.print("Número total de exemplares: ");
        int n_total_exemplares = ler.nextInt();
        ler.nextLine();
    
        System.out.print("Número de exemplares disponíveis: ");
        int n_dispo_exemplares = ler.nextInt();
        ler.nextLine(); 

        String sqlInsert = """
            INSERT INTO tb_livros (titulo_exemplar, id_editora, autor_livro, id_estante, 
                edicao_livro, ano_exemplar, n_total_exemplares, n_dispo_exemplares)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;
    
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sqlInsert)) {
    
            stm.setString(1, titulo);
            stm.setInt(2, id_editora);
            stm.setInt(3, id_autor);
            stm.setInt(4, id_estante);  
            stm.setInt(5, edicao_livro);
            stm.setInt(6, ano_exemplar);
            stm.setInt(7, n_total_exemplares);
            stm.setInt(8, n_dispo_exemplares);
    
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
