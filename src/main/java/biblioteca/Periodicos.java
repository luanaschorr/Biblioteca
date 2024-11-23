package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Periodicos {
        public void cadastraPeriodicos(){
        
        Estante estante = new Estante(null);
        Autores autores = new Autores();
        Scanner ler = new Scanner(System.in);

        System.out.println("Informe os dados do Periodico.");
        System.out.print("Titulo: ");
        String titulo = ler.nextLine();

        System.out.println("ID do autor:");
        int id_autor = ler.nextInt();
        Long autor = autores.verificarAutorExistente(id_autor);
        if (autor == null) {
            System.out.println("Autor não encontrado. Abortando operação.");
            return;
        }
        
        String codigoEstante = autores.retornaAutorId(id_autor);
        System.out.println(codigoEstante);
        int idEstante  = estante.verificaEstante(codigoEstante);

        System.out.print("ISSN: ");
        int issn = ler.nextInt();

        System.out.print("Ano de Publicação: ");
        int ano_exemplar = ler.nextInt();
        
        System.out.print("Volume: ");
        int per_volume = ler.nextInt();

        System.out.print("Numero total de exemplares: ");
        int n_total_exemplares = ler.nextInt(); 
        
        System.out.print("Numero de exemplares disponíveis: ");
        int n_dispo_exemplares = ler.nextInt();
    
        int n_da_estante_exemplar = 0; 

        String sql = "INSERT INTO tb_periodicos (titulo_exemplar, issn, ano_exemplar, n_total_exemplares, n_dispo_exemplares, per_volume, id_autor) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql)) {

        stm.setString(1, titulo);
        stm.setInt(2, issn);
        stm.setInt(3, ano_exemplar);
        stm.setInt(4, n_total_exemplares);
        stm.setInt(5, n_dispo_exemplares);
        stm.setLong(6, per_volume);
        stm.setLong(7, autor);
    

        int linhasInseridas = stm.executeUpdate();
            if(linhasInseridas > 0){
                 System.out.println("Inserção bem-sucedida!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }

public void imprimirPeriodico() {
        Estante estante = new Estante(null);
        String sql = """
            SELECT tb_periodicos.id_exemplar, 
                tb_periodicos.titulo_exemplar, 
                tb_autores.nome AS autor 
            FROM tb_periodicos
            JOIN tb_autores ON tb_periodicos.id_autor = tb_autores.id
            """;

            
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
                
            while (rs.next()) {
                int id = rs.getInt("id_exemplar");
                String titulo = rs.getString("titulo_exemplar");
                String autor = rs.getString("autor");
                //int estanteid = rs.getInt("estante");
                //String resEstante = estante.retornaCodigo(estanteid);
                System.out.println("ID: " + id);
                System.out.println("Título: " + titulo);
                System.out.println("Autor: " + autor);;
                //System.out.println("Estante: " + resEstante);

                System.out.println("=====================================");
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os preidocos: " + e.getMessage());
        }
    }
}
