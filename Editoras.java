package trabalhoBiblioteca;

import java.util.Scanner;

/*
id
nome
nacional
*/

/**
 *
 * @author luana
 */
public class Editoras {
    public void adicionaEditoras(){
        Scanner ler = new Scanner(System.in);
        
        System.out.println("Informe os dados do autor.");
        System.out.print("Nome: ");
        String nome = ler.nextLine();        
        
        System.out.print("Essa editora é nacional? (S/N): ");
        String option = ler.nextLine();
        
        if(null == option){
            System.out.println("Opção inválida.");
        } else switch (option) {
            case "S" -> {
                System.out.println("Perfeito! Essa editora será cadastrada no banco!");
                boolean nacional = true;
            }
            case "N" -> {
                System.out.println("Perfeito! Essa editora será cadastrada no banco!");
                boolean internacional = true;
            }
            default -> System.out.println("Opção inválida.");
        }
    }
}
