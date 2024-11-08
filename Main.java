/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package biblioteca;

/**
 *
 * @author luana
 */
public class Main {

    public static void main(String[] args) {
        
        
        Autores autor = new Autores();
        autor.cadastrarAutores();
        
        Editoras editora = new Editoras();
        editora.adicionaEditoras();
    }
}
