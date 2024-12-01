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


    public String geraCodigo(String sobrenome){
         if (sobrenome.length() >= 3) {
            return sobrenome.substring(0, 3).toUpperCase(); 
        } else {
            return sobrenome.toUpperCase();
        }
    }

    public String fazEstante(String sobrenome) {
        String codigoEstante = geraCodigo(sobrenome);
    
        String sqlCheck = "SELECT id_estante FROM tb_estantes_biblioteca WHERE codigo = ?";
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sqlCheck)) {
    
            stm.setString(1, codigoEstante);
    
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
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

    public boolean verificaEstante(String codigo) {
        String sqlCheck = "SELECT codigo FROM tb_estantes_biblioteca WHERE codigo = ?";
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sqlCheck)) {

            stm.setString(1, codigo);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Estante encontrada: Código " + codigo);
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar a estante no banco de dados: " + e.getMessage());
        }
        return false;
    }

    public int retornaIdEstante(String codigo) {
        int idEstante = -1;
        String sqlCheck = "SELECT id_estante FROM tb_estantes_biblioteca WHERE codigo = ?";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sqlCheck)) {

            stm.setString(1, codigo);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    idEstante = rs.getInt("id_estante");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }

        return idEstante;
    }
}