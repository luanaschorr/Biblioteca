package biblioteca;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Editoras {
    private String nome;
    private boolean nacional;

    public void adicionaEditoras(){
        Scanner ler = new Scanner(System.in);
        
        System.out.println("Informe os dados da editora.");
        System.out.print("Nome: ");
        this.nome = ler.nextLine();        
        
        System.out.print("Essa editora é nacional? (S/N): ");
        String option = ler.nextLine();
        
        if (option.equalsIgnoreCase("S")) {
            this.nacional = true;
        } else if (option.equalsIgnoreCase("N")) {
            this.nacional = false;
        } else {
            System.out.println("Opção inválida.");
            return;
        }
        
        String sql = "INSERT INTO tb_editoras (nome, nacional) VALUES (?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, this.nome);
            stm.setBoolean(2, this.nacional);

            int linhasInseridas = stm.executeUpdate();
            if (linhasInseridas > 0) {
                System.out.println("Editora cadastrada com sucesso!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir editora: " + e.getMessage());
        } finally {
            ler.close();
        }
    }
public long verificarEditoraExistente(int id_editora) {
     String sql = "SELECT id FROM tb_editoras WHERE id = ?";
        
    try (Connection conn = ConexaoBanco.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id_editora);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Retorna o ID da editora se ela existir
                return rs.getLong("id");  
            } else {
                // Retorna -1 caso a editora não seja encontrada
                return -1; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
