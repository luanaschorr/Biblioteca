package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Alunos {
    public void cadastrarAlunos() {
        Scanner ler = new Scanner(System.in);

        System.out.println("Informe os dados do aluno.");
        System.out.print("Nome: ");
        String nome = ler.nextLine();

        System.out.print("Sobrenome: ");
        String sobrenome = ler.nextLine();

        System.out.print("Data de nascimento (yyyy-mm-dd): ");
        String dataNascimento = ler.nextLine();

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
            stm.setString(3, dataNascimento);
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


// // CREATE TABLE public.tb_alunos (
//     id_aluno bigint NOT NULL,
//     nome text NOT NULL,
//     sobrenome text NOT NULL,
//     data_nascimento date NOT NULL,
//     numero_rg text NOT NULL,
//     numero_matricula text NOT NULL,
//     cpf text NOT NULL,
//     telefone text NOT NULL
// );