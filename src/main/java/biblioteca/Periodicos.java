package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Periodicos {
        public void cadastraPeriodicos(){

        Editoras editoras = new Editoras();
        Autores autores = new Autores();
        Scanner ler = new Scanner(System.in);

        System.out.println("Informe os dados do Periodico.");
        System.out.print("Titulo: ");
        String titulo = ler.nextLine();

        System.out.println("ID do autor:");
        int id_autor = ler.nextInt();
        Long autor = autores.verificarAutorExistente(id_autor);

        System.out.print("Ano de Publicação: ");
        int ano_exemplar = ler.nextInt();
        
        System.out.print("Volume: ");
        int per_volume = ler.nextInt();

        System.out.print("Numero total de exemplares: ");
        int n_total_exemplares = ler.nextInt(); 
        
        System.out.print("Numero de exemplares disponíveis: ");
        int n_dispo_exemplares = ler.nextInt(); 

        System.out.print("Local na estante: ");
        int loc_estante = ler.nextInt(); 

        System.out.print("Número da estante do exemplar: ");
        int n_da_estante_exemplar = ler.nextInt(); 


        String sql = "INSERT INTO tb_periodicos (titulo_exemplar, ano_exemplar, n_da_estante_exemplar, n_total_exemplares, n_dispo_exemplares, loc_estante, per_volume, id_autor) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql)) {

        stm.setString(1, titulo);  
        stm.setInt(2, ano_exemplar);  
        stm.setInt(3, n_da_estante_exemplar);  
        stm.setInt(4, n_total_exemplares);  
        stm.setInt(5, n_dispo_exemplares); 
        stm.setInt(6, loc_estante);  
        stm.setLong(7, per_volume);  
        stm.setLong(8, autor);  
      


        int linhasInseridas = stm.executeUpdate();
            if(linhasInseridas > 0){
                 System.out.println("Inserção bem-sucedida!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }
}
