package game.echoes;
import java.util.Scanner;

/**
 * Evento que apresenta uma escolha de risco ao herói.
 * 
 * Neste evento, o jogador decide entre aceitar uma consequência negativa em
 * troca de uma recompensa ou ignorar a tentação e seguir pelo mapa.
 */
public class Escolha extends Evento {

    /**
     * Retorna o nome exibido para este evento no mapa.
     * 
     * @return O nome do evento narrativo
     */
    @Override
    public String getNomeEvento() {
        return "Altar Profano";
    }
    
    /**
     * Inicia o evento de escolha.
     * 
     * @param heroi O herói que enfrenta a escolha
     * @param scanner O scanner para ler a escolha do jogador
     * @return true se o herói sobreviveu, false se morreu
     */
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
