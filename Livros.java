package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Livros {
            /*
    titulo_exemplar text NOT NULL,
    ano_exemplar integer NOT NULL,
    n_da_estante_exemplar integer NOT NULL,
    n_total_exemplares integer NOT NULL,
    n_dispo_exemplares integer NOT NULL,
    loc_estante bigint

    isbn bigint NOT NULL,
    id_editora bigint NOT NULL,
    autor_livro text NOT NULL,
    edicao_livro integer NOT NULL
    */
 
    
    public void adicionarLivro(){

        Editoras editoras = new Editoras();
        Autores autores = new Autores();
        Scanner ler = new Scanner(System.in);

        System.out.println("Informe os dados do livro.");
        System.out.print("Titulo: ");
        String titulo = ler.nextLine();

        System.out.print("ID editora: ");
        int id_editora = ler.nextInt();
        long editoraV = editoras.verificarEditoraExistente(id_editora);
        if (editoraV == -1) {
            System.out.println("Editora não encontrada. Abortando operação.");
            return; 
        }

        System.out.println("ID do autor:");
        int id_autor = ler.nextInt();
        Long autor = autores.verificarAutorExistente(id_autor);

        /* 
        System.out.print("ISBN: ");
        int isbn = ler.nextInt();
        */

        System.out.print("Edição: ");
        int edicao_livro = ler.nextInt();

        System.out.print("Ano de Publicação: ");
        int ano_exemplar = ler.nextInt();

        System.out.print("Numero total de exemplares: ");
        int n_total_exemplares = ler.nextInt(); 
        
        System.out.print("Numero de exemplares disponíveis: ");
        int n_dispo_exemplares = ler.nextInt(); 

        System.out.print("Local na estante: ");
        int loc_estante = ler.nextInt(); 

        System.out.print("Número da estante do exemplar: ");
        int n_da_estante_exemplar = ler.nextInt(); 


        String sql = "INSERT INTO tb_livros (titulo_exemplar, ano_exemplar, n_da_estante_exemplar, n_total_exemplares, n_dispo_exemplares, loc_estante, id_editora, autor_livro, edicao_livro) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql)) {

        stm.setString(1, titulo);  
        stm.setInt(2, ano_exemplar);  
        stm.setInt(3, n_da_estante_exemplar);  
        stm.setInt(4, n_total_exemplares);  
        stm.setInt(5, n_dispo_exemplares); 
        stm.setInt(6, loc_estante);  
        stm.setLong(7, editoraV);  
        stm.setLong(8, autor);  
        stm.setInt(9, edicao_livro);  


        int linhasInseridas = stm.executeUpdate();
            if(linhasInseridas > 0){
                 System.out.println("Inserção bem-sucedida!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }
}

