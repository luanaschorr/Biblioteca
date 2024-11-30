package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Alunos {
    public void cadastrarAlunos() {
        Scanner ler = new Scanner(System.in);

        System.out.println("Informe os dados do aluno.");
        System.out.print("Nome: ");
        String nome = ler.nextLine();

        System.out.print("Sobrenome: ");
        String sobrenome = ler.nextLine();

        if (nome.trim().isEmpty() || sobrenome.trim().isEmpty()) {
            System.out.println("Informação inválida! É necessário preencher todos os campos!");
            return;
        }  

        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        String inputDate = ler.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = null;
        try {
            dataNascimento = LocalDate.parse(inputDate, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
            return;
        }

        System.out.print("Número do RG: ");
        String numeroRg = ler.nextLine();

        System.out.print("Número da matrícula: ");
        String numeroMatricula = ler.nextLine();

        System.out.print("CPF: ");
        String cpf = ler.nextLine();

        System.out.print("Telefone: ");
        String telefone = ler.nextLine();

        String sql = "INSERT INTO tb_alunos (nome, sobrenome, data_nascimento, numero_rg, numero_matricula, cpf, telefone) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, nome.toUpperCase());
            stm.setString(2, sobrenome.toUpperCase());

            java.sql.Date sqlDate = java.sql.Date.valueOf(dataNascimento);
            stm.setDate(3, sqlDate);

            stm.setString(4, numeroRg);
            stm.setString(5, numeroMatricula);
            stm.setString(6, cpf);
            stm.setString(7, telefone);

            int linhasInseridas = stm.executeUpdate();
            if (linhasInseridas > 0) {
                System.out.println("Aluno cadastrado com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }
    public void listarAlunos() {
        String sql = "SELECT * FROM tb_alunos";

        try (Connection conn = ConexaoBanco.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_aluno");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                String data_nascimento = rs.getString("data_nascimento");
                String numero_rg = rs.getString("numero_rg");
                String numero_matricula = rs.getString("numero_matricula");
                String cpf = rs.getString("cpf");
                String telefone = rs.getString("telefone");
                
                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome + " " + sobrenome);
                System.out.println("Data nascimento: " + data_nascimento);
                System.out.println("RG: " + numero_rg);
                System.out.println("N° Matricula: " + numero_matricula);
                System.out.println("CPF: " + cpf);
                System.out.println("Telefone: " + telefone);
                System.out.println("==========================");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar autores: " + e.getMessage());
        }
    }
}
