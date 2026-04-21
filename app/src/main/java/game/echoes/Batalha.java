package game.echoes;
import java.util.*;
import game.echoes.Heroi;
import game.echoes.Inimigo;

public class Batalha {

    public static boolean iniciar(Heroi heroi, Inimigo inimigo, Scanner scanner) {

        GerenciadorDeCartas gerenciador = new GerenciadorDeCartas();

        while (heroi.estaVivo() && inimigo.estaVivo()) {

            heroi.resetarMana();
            heroi.resetaEscudo();

            //Aplicar efeitos

            heroi.aplicarEfeitos();
            inimigo.aplicarEfeitos();

            gerenciador.prepararNovoTurno();

            boolean turnoAtivo = true;

            while (turnoAtivo && heroi.estaVivo() && inimigo.estaVivo()) {
                System.out.println("\n-----------------------------------------------------------------------");
                System.out.println(heroi.getNome() + " - Vida: " + heroi.getVida() + ", Escudo: " + heroi.getEscudo());
                System.out.println("Mana: " + heroi.getMana());
                System.out.println("VS " + inimigo.getNome() + " | Vida: " + inimigo.getVida());

                inimigo.anunciarIntencao();
                gerenciador.exibirMao();

                System.out.println("\nEscolha uma ação:");
                System.out.println("0 - Sair do jogo");
                System.out.println("99 - Encerrar turno");
                System.out.println("\nDigite o número da carta: ");

                int escolha = scanner.nextInt();

                if (escolha == 0) {
                    System.out.println("Você abandonou a batalha...");
                    System.exit(0);
                }

                if (escolha == 99) {
                    turnoAtivo = false;
                } else {
                    gerenciador.jogarCarta(escolha - 1, heroi, inimigo);
                }

                if (heroi.getMana() <= 0) {
                    System.out.println("\nSua mana acabou! Encerrando turno...");
                    turnoAtivo = false;
                }
            }

            // TURNO INIMIGO

            if (inimigo.estaVivo() && heroi.estaVivo()) {
                System.out.println("\n---Turno de " + inimigo.getNome() + "---");

                if (!inimigo.estaAtordoado()) {
                    inimigo.atacar(heroi);
                } else {
                    System.out.println("💫 " + inimigo.getNome() + " está atordoado e perdeu o turno!");
                    inimigo.setAtordoado(false);
                }
            }
        }

        if (heroi.estaVivo()) {
            System.out.println("\nVocê venceu a batalha!");
        } else {
            System.out.println("\nSua mente sucumbiu à loucura... Game Over.");
        }

        return heroi.estaVivo();
    }
}


