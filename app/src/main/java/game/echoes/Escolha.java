package game.echoes;
import java.util.Scanner;

public class Escolha extends Evento {
    @Override
    public boolean iniciar(Heroi heroi, Scanner scanner) {
        System.out.println("\n=================================");
        System.out.println("          ALTAR PROFANO          ");
        System.out.println("=================================");
        System.out.println("Você encontra um altar gotejando sangue escuro.");
        System.out.println("O que você faz?");
        System.out.println("1 - Beber o sangue (Perde 5 PV, mas ganha 30 de Ouro).");
        System.out.println("2 - Ignorar e seguir em frente.");
        System.out.print("Sua escolha: ");

        int opcao = scanner.nextInt();

        if (opcao == 1) {
            heroi.receberDano(5);
            if (heroi.estaVivo()) {
                heroi.ganharOuro(30);
                System.out.println("\nSua garganta queima, mas você encontra moedas no fundo do cálice!");
                System.out.println("💰 Ouro atual: " + heroi.getOuro());
            }
        } else {
            System.out.println("\nVocê ignora a tentação.");
        }
        return heroi.estaVivo(); 
    }
}