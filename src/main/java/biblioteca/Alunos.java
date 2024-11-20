package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        String inputDate = ler.nextLine();

        // Converte a entrada do usuário para LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = null;
        try {
            dataNascimento = LocalDate.parse(inputDate, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
            return; // Encerra o programa em caso de erro
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

            stm.setString(1, nome);
            stm.setString(2, sobrenome);

            // Converte LocalDate para java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(dataNascimento);
            // Configura o parâmetro para a consulta
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
}
