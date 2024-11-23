package biblioteca;

/*
    id bigint NOT NULL,
    nome text NOT NULL,
    sobrenome text NOT NULL
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Autores {
    Estante estante = new Estante(null);
    public void cadastrarAutores(){
        Scanner ler = new Scanner(System.in);
        
        System.out.println("Informe os dados do autor.");
        System.out.print("Nome: ");
        String nome = ler.nextLine();
        
        System.out.print("Sobrenome: ");
        String sobrenome = ler.nextLine();
        estante.fazEstante(sobrenome);
        System.out.println("Autor cadastrado com sucesso.");        
        
        String sql = "INSERT INTO tb_autores (nome, sobrenome) VALUES (?, ?)";
        
        try(Connection conn = ConexaoBanco.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql)){
            
            stm.setString(1, nome);
            stm.setString(2, sobrenome);

            int linhasInseridas = stm.executeUpdate();
            if(linhasInseridas > 0){
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
        ler.close();
    }

    /*
     *  faz busca no autor, pode alterar se quiser;
    */
    public long verificarAutorExistente(int autor) {
    String sql = "SELECT id FROM tb_autores WHERE id = ?";
    try (Connection conn = ConexaoBanco.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, autor);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("id"); 
        } else {
            return -1;  
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return -1;
    }
} 

public String retornaAutorId(int autor) {
    String sql = "SELECT sobrenome FROM tb_autores WHERE id = ?";
    try (Connection conn = ConexaoBanco.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, autor); 
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return rs.getString("sobrenome");  
                } else {
            return "Autor não encontrado"; 
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return "Erro ao consultar autor"; 
    }
}

public void listarAutores() {
    String sql = "SELECT id, nome, sobrenome FROM tb_autores";

    try (Connection conn = ConexaoBanco.getConnection();
         PreparedStatement stm = conn.prepareStatement(sql);
         ResultSet rs = stm.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String sobrenome = rs.getString("sobrenome");
            
            System.out.println("ID: " + id);
            System.out.println("Nome: " + nome + " " + sobrenome);
            System.out.println("==========================");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar autores: " + e.getMessage());
    }
}
}
