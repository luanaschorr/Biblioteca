package biblioteca;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoBanco{
  public static void main(String[] args){
      Scanner ler = new Scanner(System.in);
      try{
          Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Biblioteca",
            "postgres", "postgreegay");
            if(conexao != null){
                System.out.println("Banco conectado com sucesso!");
                Statement stm = conexao.createStatement();
                insereDados(stm);
                consultaDados(stm);
            } else{
                System.out.println("Erro ao conectar com o banco de dados!");
            }
      } catch(SQLException e ){
      }    
  }
  
  static void consultaDados(Statement stm){
      String sql = "select id, nome, nacional from tb_editoras";
      try{
          ResultSet result = stm.executeQuery(sql);
          while(result.next()){
              System.out.println("id: " + result.getInt("id") + ", nome " + result.getString("nome") + ", nacional: "+result.getBoolean("nacional"));
          }
      } catch(SQLException e){
      }
  }
  static void insereDados(Statement stm){
      try {
          String sql = "insert into tb_editoras (nome, nacional) values ('Cretas', false)";
          stm.executeUpdate(sql);
      } catch (SQLException ex) {
          Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
}