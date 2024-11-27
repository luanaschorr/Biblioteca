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
    
        int id_editora = 0;

        while (id_editora == 0) {
            System.out.println("""
                Escolha uma opção para inserir a Editora:
                1- Listar Editoras.
                2- Buscar Editora.
                3- Digitar por ID.
                4- Voltar para o menu
                """);
        
            System.out.print("Escolha uma opção: ");
            int opcao = ler.nextInt();
            ler.nextLine();
        
            switch (opcao) {
                case 1:
                    System.out.println("Listar Editoras:");
                    editoras.listarEditoras();
        
                    System.out.print("Digite o ID da editora: ");
                    id_editora = ler.nextInt();
                    ler.nextLine();
        
                    Long editoraExistente = editoras.verificarEditoraExistente(id_editora);
                    if (editoraExistente != null) {
                        id_editora = editoraExistente.intValue();
                    } else {
                        System.out.println("Editora não encontrada. Tente novamente.");
                        id_editora = 0; 
                    }
                    break;
        
                case 2:
                    System.out.println("Buscar Editora:");
                    System.out.print("Digite o nome da Editora para buscar: ");
                    String nomeEditora = ler.nextLine();
        
                    Long idBusca = editoras.buscarEditoraPorNome(nomeEditora);
                    if (idBusca != null) {
                        id_editora = idBusca.intValue();
                        System.out.println("Editora encontrada: " + nomeEditora);
                    } else {
                        System.out.println("Editora não encontrada. Tente novamente.");
                        id_editora = 0; 
                    }
                    break;
        
                case 3:
                    System.out.print("Digite o ID da editora: ");
                    id_editora = ler.nextInt();
                    ler.nextLine();
        
                    Long editoraId = editoras.verificarEditoraExistente(id_editora);
                    if (editoraId != null) {
                        id_editora = editoraId.intValue();
                    } else {
                        System.out.println("Editora não encontrada. Tente novamente.");
                        id_editora = 0;
                    }
                    break;
        
                case 4:
                    System.out.println("Voltando ao menu principal.");
                    return; 
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
                    id_editora = 0; 
            }
        }
        
        int id_autor = -1;
while (id_autor == -1) {
    System.out.println("""
    
    Escolha uma opção para inserir o autor:
    1- Listar Autores.
    2- Buscar Autor.
    3- Digitar por ID.
    4- Voltar para o menu
    """);

    System.out.print("Escolha uma opção: ");
    int opcao = ler.nextInt();
    ler.nextLine(); // Consome o restante da linha.

    switch (opcao) {
        case 1:
            System.out.println("Listar Autores:");
            autores.listarAutores();

            System.out.print("Digite o ID do autor: ");
            id_autor = ler.nextInt();
            ler.nextLine(); // Consome o restante da linha.

            if (autores.verificarAutorExistente(id_autor) == -1) {
                System.out.println("Autor não encontrado. Tente novamente.");
                id_autor = -1;
            }
            break;

        case 2:
            System.out.print("Digite o nome do autor para buscar: ");
            String nomeAutor = ler.nextLine();

            Long idAutorBuscado = autores.buscarAutorPorNome(nomeAutor);
            if (idAutorBuscado != null && idAutorBuscado != -1) {
                id_autor = idAutorBuscado.intValue();
                System.out.println("Autor encontrado: " + nomeAutor);
            } else {
                System.out.println("Autor não encontrado. Tente novamente.");
                id_autor = -1;
            }
            break;

        case 3:
            System.out.print("Digite o ID do autor: ");
            id_autor = ler.nextInt();
            ler.nextLine(); // Consome o restante da linha.

            if (autores.verificarAutorExistente(id_autor) == -1) {
                System.out.println("Autor não encontrado. Tente novamente.");
                id_autor = -1;
            }
            break;

        case 4:
            System.out.println("Voltando ao menu principal.");
            return;

        default:
            System.out.println("Opção inválida. Escolha uma das opções acima.");
            id_autor = -1;
    }



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
            stm.setInt(2, id_editora);
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
            ;
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
