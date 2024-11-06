package trabalhoBiblioteca;

/*
    id bigint NOT NULL,
    nome text NOT NULL,
    sobrenome text NOT NULL
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Autores {
    public void cadastrarAutores(){
        Scanner ler = new Scanner(System.in);
        
        System.out.println("Informe os dados do autor.");
        System.out.print("Nome: ");
        String nome = ler.nextLine();
        
        System.out.print("Sobrenome: ");
        String sobrenome = ler.nextLine();        
        
        String sql = "INSERT INTO tb_autores (nome, sobrenome) VALUES (?, ?)";
        
        try(Connection conn = ConexaoBanco.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql)){
            
            stm.setString(1, nome);
            stm.setString(2, sobrenome);

            int linhasInseridas = stm.executeUpdate();
            if(linhasInseridas > 0){
                 System.out.println("Inserção bem-sucedida!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }
}