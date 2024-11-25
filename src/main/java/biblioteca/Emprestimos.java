package biblioteca;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Emprestimos {
    private String emp_nome_aluno;
    private String emp_telefone_aluno;
    private String emp_cpf_aluno;
    private String emp_titulo_exemplar;
    private String cod_da_estante;
    private String data_emprestimo;

    public void realizarEmprestimo() {
        Scanner ler = new Scanner(System.in);
    
        System.out.println("Informe o CPF do aluno:");
        this.emp_cpf_aluno = ler.nextLine();
    
        System.out.println("Informe o título do exemplar:");
        this.emp_titulo_exemplar = ler.nextLine();
    
        String alunoSql = "SELECT nome, telefone FROM tb_alunos WHERE cpf = ?";

        try (Connection conn = ConexaoBanco.getConnection()) {
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

        String livroSql = "SELECT codigo_estante FROM tb_exemplares WHERE titulo_exemplar = ?";
    
            try (PreparedStatement livroStmt = conn.prepareStatement(livroSql)) {
                livroStmt.setString(1, emp_titulo_exemplar);
                try (ResultSet livroRs = livroStmt.executeQuery()) {
                    if (livroRs.next()) {
                        this.cod_da_estante = livroRs.getString("codigo_estante");
                    } else {
                        System.out.println("Livro não encontrado.");
                        return;
                    }
                }
            }
    
            String sql = "INSERT INTO tb_emprestimo (emp_nome_aluno, emp_telefone_aluno, emp_cpf_aluno, emp_titulo_exemplar, codigo_estante) " +
                         "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setString(1, emp_nome_aluno);
                stm.setString(2, emp_telefone_aluno);
                stm.setString(3, emp_cpf_aluno);
                stm.setString(4, emp_titulo_exemplar);
                stm.setString(5, cod_da_estante);
    
                int linhasInseridas = stm.executeUpdate();
                if (linhasInseridas > 0) {
                    System.out.println("Inserção bem-sucedida!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }    

    public void listarEmprestimo() {
        String sqlSelect = "SELECT * FROM tb_emprestimo";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try (Connection conn = ConexaoBanco.getConnection()) {
            try (PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
                try (ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("Nome: " + rs.getString("emp_nome_aluno"));
                        System.out.println("Telefone: " + rs.getString("emp_telefone_aluno"));
                        System.out.println("CPF: " + rs.getString("emp_cpf_aluno"));
                        System.out.println("Título: " + rs.getString("emp_titulo_exemplar"));
                        
                        Date dataEmprestimo = rs.getTimestamp("data_emprestimo");
                        if (dataEmprestimo != null) {
                            System.out.println("Data do Emprestimo: " + sdf.format(dataEmprestimo));
                        } else {
                            System.out.println("Data do Emprestimo: Não disponível");
                        }
                        
                        System.out.println("Codigo da Estante: " + rs.getString("codigo_estante"));
                        System.out.println("=======================================");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar dados: " + e.getMessage());
        }
    }
}