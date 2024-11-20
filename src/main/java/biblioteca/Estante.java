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

    // Método para verificar ou criar uma estante com base no sobrenome
    public String fazEstante(String sobrenome) {
        String codigoEstante;
        if (sobrenome.length() >= 3) {
            codigoEstante = sobrenome.substring(0, 3).toUpperCase();  // Gera o código da estante
        } else {
            codigoEstante = sobrenome.toUpperCase();
        }

        String sqlCheck = "SELECT codigo FROM tb_estantes_biblioteca WHERE codigo = ?";

        // Verifica se a estante já existe no banco de dados
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sqlCheck)) {

            stm.setString(1, codigoEstante);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Estante já existe: " + codigoEstante);
                    return codigoEstante;  // Retorna o código da estante se já existe
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }

        // Se a estante não existir, cria uma nova
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

        return codigoEstante;  // Retorna o código da nova estante criada
    }
}

