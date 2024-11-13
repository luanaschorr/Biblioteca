package biblioteca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Exemplares {
        private int id;
        private String titulo;
        private int anoPublicacao;
        private String autor;
        private int n_estante; 
        private int estante;
        private int n_dispo_exemplare;
        private int n_total_exemplare;
        private int loc_estante;
    
    
        public Exemplares(int id, String titulo, int anoPublicacao, String autor, int n_estante, 
                     int estante, int n_dispo_exemplare, int n_total_exemplare, int loc_estante) {
            this.id = id;
            this.titulo = titulo;
            this.anoPublicacao = anoPublicacao;
            this.autor = autor;
            this.n_estante = n_estante;  
            this.estante = estante;
            this.n_dispo_exemplare = n_dispo_exemplare;
            this.n_total_exemplare = n_total_exemplare;
            this.loc_estante = loc_estante;
        }
    
        public int getId() {
            return id;
        }
    
        public void setId(int id) {
            this.id = id;
        }
    
        public String getTitulo() {
            return titulo;
        }
    
        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }
    
        public int getAnoPublicacao() {
            return anoPublicacao;
        }
    
        public void setAnoPublicacao(int anoPublicacao) {
            this.anoPublicacao = anoPublicacao;
        }
    
        public String getAutor() {
            return autor;
        }
    
        public void setAutor(String autor) {
            this.autor = autor;
        }
    
        public int getNEstante() {
            return n_estante;  // Corrigido para o nome correto
        }
    
        public void setNEstante(int n_estante) {
            this.n_estante = n_estante;  // Corrigido para o nome correto
        }
    
        public int getEstante() {
            return estante;
        }
    
        public void setEstante(int estante) {
            this.estante = estante;
        }
    
        public int getNDispoExemplare() {
            return n_dispo_exemplare;
        }
    
        public void setNDispoExemplare(int n_dispo_exemplare) {
            this.n_dispo_exemplare = n_dispo_exemplare;
        }
    
        public int getNTotalExemplare() {
            return n_total_exemplare;
        }
    
        public void setNTotalExemplare(int n_total_exemplare) {
            this.n_total_exemplare = n_total_exemplare;
        }
    
        public int getLocEstante() {
            return loc_estante;
        }
    
        public void setLocEstante(int loc_estante) {
            this.loc_estante = loc_estante;
        }

    }

    