package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Estante {
    private String estante;

    public Estante(String estante) {
        this.estante = estante;
    }

    public String getEstante() {
        return estante;
    }

    public void fazEstante(String sobrenome) {
        // Gera o código da estante
        String codigoEstante;
        if (sobrenome.length() >= 3) {
            codigoEstante = sobrenome.substring(0, 3).toUpperCase();
        } else {
            codigoEstante = sobrenome.toUpperCase();
        }
           String sqlCheck = "SELECT codigo FROM tb_estantes_biblioteca WHERE codigo = ?";


        try (Connection conn = ConexaoBanco.getConnection();
            PreparedStatement stm = conn.prepareStatement(sqlCheck)) {
             stm.setString(1, codigoEstante);

        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                // Estante já existe, retorna o código existente
                estante = rs.getString("codigo");
                System.out.println("Estante já existe: " + estante);
                return;
            }
        }

        // Se não existir, insere uma nova estante no banco
        String sqlInsert = "INSERT INTO tb_estantes_biblioteca (codigo) VALUES (?)";

        try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)) {
            stmtInsert.setString(1, codigoEstante);
            stmtInsert.executeUpdate();
            estante = codigoEstante;
            System.out.println("Nova estante criada: " + estante);
        }

    } catch (SQLException e) {
        System.err.println("Erro ao verificar ou criar a estante: " + e.getMessage());
    }

    }
    

    @Override
    public String toString() {
        return estante;
    }

}

