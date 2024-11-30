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
    private boolean disponivel;

    public void realizarEmprestimo() {
        Scanner ler = new Scanner(System.in);

        System.out.println("Informe o CPF do aluno:");
        this.emp_cpf_aluno = ler.nextLine();

        System.out.println("Informe o título do exemplar:");
        this.emp_titulo_exemplar = ler.nextLine().toUpperCase();

        String alunoSql = "SELECT nome, telefone FROM tb_alunos WHERE cpf = ?";

        try (Connection conn = ConexaoBanco.getConnection()) {
            try (PreparedStatement alunoStmt = conn.prepareStatement(alunoSql)) {
                alunoStmt.setString(1, emp_cpf_aluno);
                try (ResultSet alunoRs = alunoStmt.executeQuery()) {
                    if (alunoRs.next()) {
                        this.emp_telefone_aluno = alunoRs.getString("telefone");
                        this.emp_nome_aluno = alunoRs.getString("nome");
                    } else {
                        System.out.println("Informações não encontradas.");
                        return;
                    }
                }
            }

            String livroSql = "SELECT disponivel, codigo_estante FROM tb_exemplares WHERE titulo_exemplar = ?";

            try (PreparedStatement livroStmt = conn.prepareStatement(livroSql)) {
                livroStmt.setString(1, emp_titulo_exemplar);
                try (ResultSet livroRs = livroStmt.executeQuery()) {
                    if (livroRs.next()) {
                        this.disponivel = livroRs.getBoolean("disponivel");
                        if (!disponivel) {
                            System.out.println("Esse título já está emprestado!");
                            return;
                        } else {
                            this.cod_da_estante = livroRs.getString("codigo_estante");
                        }
                    } else {
                        System.out.println("Informações do livro nao encontradas.");
                        return;
                    }
                }
            }

            String sqlInsert = "INSERT INTO tb_emprestimo (emp_nome_aluno, emp_telefone_aluno, emp_cpf_aluno, emp_titulo_exemplar, codigo_estante) " +
                               "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stm = conn.prepareStatement(sqlInsert)) {
                stm.setString(1, emp_nome_aluno.toUpperCase());
                stm.setString(2, emp_telefone_aluno);
                stm.setString(3, emp_cpf_aluno);
                stm.setString(4, emp_titulo_exemplar.toUpperCase());
                stm.setString(5, cod_da_estante);

                int linhasInseridas = stm.executeUpdate();
                if (linhasInseridas > 0) {
                    System.out.println("Inserção bem-sucedida!");
                    atualizaDisponibilidadeEmprestimo();
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }

    public void atualizaDisponibilidadeEmprestimo(){
        String sqlUpdate = "UPDATE tb_exemplares SET disponivel = FALSE WHERE titulo_exemplar = ?";

        try (Connection conn = ConexaoBanco.getConnection()) {
                try (PreparedStatement dispoStm = conn.prepareStatement(sqlUpdate)) {
                    dispoStm.setString(1, emp_titulo_exemplar);
                    int linhasAtualizadas = dispoStm.executeUpdate();
                    if (linhasAtualizadas > 0) {
                        System.out.println("Atualização de disponibilidade bem-sucedida!");
                    }
                }
        } catch(SQLException e){
            System.out.println("Erro ao atualizar disponibilidade.");
        }
    }
    public void atualizaDisponibilidadeDevolucao(){
        String sqlUpdate = "UPDATE tb_exemplares SET disponivel = TRUE WHERE titulo_exemplar = ?";

        try (Connection conn = ConexaoBanco.getConnection()) {
                try (PreparedStatement dispoStm = conn.prepareStatement(sqlUpdate)) {
                    dispoStm.setString(1, emp_titulo_exemplar);
                    int linhasAtualizadas = dispoStm.executeUpdate();
                    if (linhasAtualizadas > 0) {
                        System.out.println("Atualização de disponibilidade bem-sucedida!");
                    }
                }
        } catch(SQLException e){
            System.out.println("Erro ao atualizar disponibilidade.");
        }
    }

    public void listarEmprestimo() {
        String sqlSelect = "SELECT * FROM tb_emprestimo";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try (Connection conn = ConexaoBanco.getConnection()) {
            try (PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
                try (ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("  ---- Dados do emprestimo ----");
                        System.out.println("=================================");
                        System.out.println("Nome: " + rs.getString("emp_nome_aluno"));
                        System.out.println("Telefone: " + rs.getString("emp_telefone_aluno"));
                        System.out.println("CPF: " + rs.getString("emp_cpf_aluno"));
                        System.out.println("Título: " + rs.getString("emp_titulo_exemplar"));
                        System.out.println("Código da Estante: " + rs.getString("codigo_estante"));
                        Date dataEmprestimo = rs.getTimestamp("data_emprestimo");
                        if (dataEmprestimo != null) {
                            System.out.println("Data do Empréstimo: " + sdf.format(dataEmprestimo));
                        } else {
                            System.out.println("Data do Empréstimo: Não disponível");
                        }
                        System.out.println("=================================");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar dados: " + e.getMessage());
        }
    }
    public void devolucaoExemplar() {
        Scanner ler = new Scanner(System.in);
    
        System.out.println("Informe o CPF do aluno:");
        this.emp_cpf_aluno = ler.nextLine();
    
        System.out.println("Informe o título do exemplar:");
        this.emp_titulo_exemplar = ler.nextLine();
    
        String sqlDevolucao = "DELETE FROM tb_emprestimo WHERE emp_cpf_aluno = ? AND emp_titulo_exemplar = ?";
    
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sqlDevolucao)) {
    
            stm.setString(1, emp_cpf_aluno);
            stm.setString(2, emp_titulo_exemplar);
    
            int linhasAfetadas = stm.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Devolução executada com sucesso!");
                atualizaDisponibilidadeDevolucao();
            } else {
                System.out.println("Nenhum registro encontrado para exclusão.");
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao executar a devolução: " + e.getMessage());
        }
    }   

}