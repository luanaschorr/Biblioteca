package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * emp_nome_aluno
 * emp_telefone_aluno
 * emp_cpf_aluno
 * emp_titulo_exemplar
 * emp_n_da_estante
 * data_emprestimo
 */

public class Emprestimos {
    private String emp_nome_aluno;
    private String emp_telefone_aluno;
    private String emp_cpf_aluno;
    private String emp_titulo_exemplar;
    private int n_da_estante;
    private String data_emprestimo;
    public void realizarEmprestimo() {
        Scanner ler = new Scanner(System.in);
    
        System.out.println("Informe o CPF do aluno:");
        this.emp_cpf_aluno = ler.nextLine();
    
        System.out.println("Informe o título do exemplar:");
        this.emp_titulo_exemplar = ler.nextLine();
    
        // Consulta para obter o telefone e CPF do aluno
        String alunoSql = "SELECT nome, telefone FROM tb_alunos WHERE cpf = ?";
        String livroSql = "SELECT id_estante FROM tb_exemplares WHERE titulo_exemplar = ?";
    
        try (Connection conn = ConexaoBanco.getConnection()) {
            // Obter os dados do aluno
            try (PreparedStatement alunoStmt = conn.prepareStatement(alunoSql)) {
                alunoStmt.setString(1, emp_cpf_aluno);
                try (ResultSet alunoRs = alunoStmt.executeQuery()) {
                    if (alunoRs.next()) {
                        this.emp_telefone_aluno = alunoRs.getString("telefone");
                        this.emp_nome_aluno = alunoRs.getString("nome");
                    } else {
                        System.out.println("Aluno não encontrado.");
                        return;
                    }
                }
            }
    
            try (PreparedStatement livroStmt = conn.prepareStatement(livroSql)) {
                livroStmt.setString(1, emp_titulo_exemplar);
                try (ResultSet livroRs = livroStmt.executeQuery()) {
                    if (livroRs.next()) {
                        this.n_da_estante = livroRs.getInt("id_estante");
                    } else {
                        System.out.println("Livro não encontrado.");
                        return;
                    }
                }
            }
    
            // Inserir dados no empréstimo
            String sql = "INSERT INTO tb_emprestimo (emp_nome_aluno, emp_telefone_aluno, emp_cpf_aluno, emp_titulo_exemplar, id_estante) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setString(1, emp_nome_aluno);
                stm.setString(2, emp_telefone_aluno);
                stm.setString(3, emp_cpf_aluno);
                stm.setString(4, emp_titulo_exemplar);
                stm.setInt(5, n_da_estante);
    
                int linhasInseridas = stm.executeUpdate();
                if (linhasInseridas > 0) {
                    System.out.println("Inserção bem-sucedida!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }
    public void mostrarEmprestimos(){
        String sqlSelect = "SELECT * FROM tb_emprestimo";
        try(Connection conn = ConexaoBanco.getConnection()){
            try(PreparedStatement stm = conn.prepareStatement(sqlSelect)){
                try(ResultSet rs = stm.executeQuery()){
                    while(rs.next()){
                        System.out.println("Nome: " + rs.getString("emp_nome_aluno"));
                        System.out.println("Telefone: " + rs.getString("emp_telefone_aluno"));
                        System.out.println("CPF: " + rs.getString("emp_cpf_aluno"));
                        System.out.println("Título: " + rs.getString("emp_titulo_exemplar"));
                        System.out.println("Nº da Estante: " + rs.getInt("emp_n_da_estante"));
                        System.out.println("Data do Emprestimo: " + rs.getString("data_emprestimo"));                        
                    }
                }                
            }
        }
        catch (SQLException e){
            System.out.println("Erro ao selecionar dados: " + e.getMessage());
        }
    }
}