package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Periodicos {

    public void cadastraPeriodicos() {
        Estante estante = new Estante(null);
        Autores autores = new Autores();
        Scanner ler = new Scanner(System.in);

        System.out.println("Informe os dados do Periódico.");
        System.out.print("Título: ");
        String titulo = ler.nextLine();

        if (titulo.trim().isEmpty()) {
            System.out.println("É necessário informar um título válido! Tente novamente.");
            return;
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
            ler.nextLine(); 
        
            switch (opcao) {
                case 1:
                    System.out.println("Listar Autores:");
                    autores.listarAutores();
        
                    System.out.print("Digite o ID do autor: ");
                    id_autor = ler.nextInt();
                    ler.nextLine(); 
        
                    if (autores.verificarAutorExistente(id_autor) == -1) {
                        System.out.println("Autor não encontrado. Tente novamente.");
                        id_autor = -1;
                    }
                    break;
        
                case 2:
                    System.out.print("Digite o nome do autor para buscar: ");
                    String nomeAutor = ler.nextLine().toUpperCase();
        
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
                    ler.nextLine(); 
        
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

        System.out.print("ISSN: ");
        int issn = ler.nextInt();

        System.out.print("Ano de Publicação: ");
        int ano_exemplar = ler.nextInt();

        System.out.print("Volume: ");
        int per_volume = ler.nextInt();

        String sql = "INSERT INTO tb_periodicos (titulo_exemplar, issn, ano_exemplar, per_volume, codigo_estante, id_estante, id_autor) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, titulo.toUpperCase());
            stm.setInt(2, issn);
            stm.setInt(3, ano_exemplar);
            stm.setInt(4, per_volume);
            stm.setString(5, codigoEstante);
            stm.setInt(6, idEstante);
            stm.setLong(7, id_autor);

            int linhasInseridas = stm.executeUpdate();
            if (linhasInseridas > 0) {
                System.out.println("Periódico cadastrado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar o periódico.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }

    public void listarPeriodicos() {
        String sql = """
            SELECT tb_periodicos.id_exemplar, 
                   tb_periodicos.titulo_exemplar, 
                   tb_autores.nome AS autor,
                   tb_periodicos.codigo_estante AS estante,
                   tb_periodicos.disponivel
            FROM tb_periodicos
            JOIN tb_autores ON tb_periodicos.id_autor = tb_autores.id;
            """;

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_exemplar");
                String titulo = rs.getString("titulo_exemplar");
                String autor = rs.getString("autor");
                String codigoEstante = rs.getString("estante");
                boolean disponivel = rs.getBoolean("disponivel");

                System.out.println("ID: " + id);
                System.out.println("Título: " + titulo);
                System.out.println("Autor: " + autor);
                System.out.println("Estante: " + codigoEstante);
                System.out.println("Disponivel " + (disponivel ? "Sim" : "Nao"));
                System.out.println("=====================================");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os periódicos: " + e.getMessage());
        }
    }
}
