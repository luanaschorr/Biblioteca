package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Estante {
    private String estante;

    public Estante(String estante) {
        this.estante = estante;
    }

    public String getEstante() {
        return estante;
    }
    public String geraCodigo(String sobrenome){
        if (sobrenome.length() >= 3) {
            return sobrenome.substring(0, 3).toUpperCase(); 
        } else {
            return sobrenome.toUpperCase();
        }
    }
 
    public String fazEstante(String sobrenome) {
        String codigoEstante = geraCodigo(sobrenome);

        String sqlCheck = "SELECT id_estante FROM tb_estantes_biblioteca WHERE id_estate = ?";
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sqlCheck)) {

            stm.setString(1, codigoEstante);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Estante já existe: " + codigoEstante);
                    return codigoEstante; 
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }

        String sqlInsert = "INSERT INTO tb_estantes_biblioteca (codigo) VALUES (?)";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)) {

            stmtInsert.setString(1, codigoEstante);
            stmtInsert.executeUpdate();
            estante = codigoEstante;
            System.out.println("Nova estante criada: " + estante);
        } catch (SQLException e) {
            System.err.println("Erro ao criar a estante: " + e.getMessage());
        }

        return codigoEstante;  
        
    }

    public int verificaEstante(String codigo) {
        String subCodigo = geraCodigo(codigo);
        System.out.println(subCodigo);
        int id = 0;
        String sqlCheck = "SELECT id_estante FROM tb_estantes_biblioteca WHERE codigo = ?";
    
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sqlCheck)) {
    
            stm.setString(1, subCodigo); 
    
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                     id = rs.getInt("id_estante");  
                    System.out.println("Estante já existe: " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro------ ao acessar o banco de dados: " + e.getMessage());
        }
    
        return id;  
    }
    public String retornaCodigo(int id){
        String codigo = null;
        String sqlCheck = "SELECT codigo FROM tb_estantes_biblioteca WHERE id_estante = ?";
    
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sqlCheck)) {
    
            stm.setInt(1, id); 
    
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    codigo = rs.getString("id_estante");
                      
                }
                
            }
        } catch (SQLException e) {
            System.out.println("Erro------ ao acessar o banco de dados: " + e.getMessage());
        }
        return codigo;
    }
}

    
