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
        
        if (nome.trim().isEmpty()) {
            System.out.println("É necessário informar um nome válido! Tente novamente.");
            return;
        }

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

            stm.setString(1, this.nome.toUpperCase());
            stm.setBoolean(2, this.nacional);

            int linhasInseridas = stm.executeUpdate();
            if (linhasInseridas > 0) {
                System.out.println("Editora cadastrada com sucesso!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir editora: " + e.getMessage());
        }
    }
public long verificarEditoraExistente(int id_editora) {
     String sql = "SELECT id FROM tb_editoras WHERE id = ?";
        
    try (Connection conn = ConexaoBanco.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id_editora);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getLong("id");  
            } else {
                System.out.println("Editora nao encontrada.");
                System.out.println("Abortando operacao!");
                return -1; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String retornaEditora(int id_editora) {
        String sql = "SELECT nome FROM tb_editoras WHERE id = ?";
        
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setInt(1, id_editora);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                return rs.getString("nome");  
            } else {
                return "não existe"; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "erro na consulta";
        }
    }

    public void listarEditoras() {
        String sql = "SELECT id, nome, nacional FROM tb_editoras";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            boolean encontrou = false;
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                boolean nacional = rs.getBoolean("nacional");

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                if(nacional){
                    System.out.println("Nacional");
                } else{
                    System.out.println("Internacional");
                }
                
                System.out.println("===============================");
                encontrou = true;
            }

            if (!encontrou) {
                System.out.println("Nenhuma editora cadastrada.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar as editoras: " + e.getMessage());
        }
    }
    public Long buscarEditoraPorNome(String nomeEditora) {
        String sql = "SELECT id FROM tb_editoras WHERE nome = ?"; 
        try (Connection conn = ConexaoBanco.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nomeEditora);  

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getLong("id"); 
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

