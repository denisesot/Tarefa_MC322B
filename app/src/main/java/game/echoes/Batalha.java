package game.echoes;
import java.util.*;

public class Batalha extends Evento {

    private Inimigo inimigo;

    
    public Batalha(Inimigo inimigo) { //batalha guarda o inimigo
        this.inimigo = inimigo;
    }

    @Override
    public boolean iniciar(Heroi heroi, Scanner scanner) {

        System.out.println("\n⚔️ Um " + inimigo.getNome() + " bloqueia seu caminho!");
        
        GerenciadorDeCartas gerenciador = new GerenciadorDeCartas(heroi.getBaralhoPrincipal());

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
            
            // SISTEMA DE MOEDAS: Ganha ouro ao vencer
            Random rand = new Random();
            int ouroGanho = rand.nextInt(21) + 15; // Entre 15 e 35 moedas
            heroi.ganharOuro(ouroGanho);
            System.out.println("💰 Você saqueou " + ouroGanho + " de ouro! (Total: " + heroi.getOuro() + ")");
            
            // Sistema de recompensa de cartas
            int sorteio = rand.nextInt(9); 
            Carta recompensa = null;
            
            switch (sorteio) {
                case 0: recompensa = new CartaDano("Golpe Brutal", "Causa 8 de dano.", 1, 8); break;
                case 1: recompensa = new CartaEscudo("Barreira Mística", "Ganha 6 de escudo.", 1, 6); break;
                case 2: recompensa = new CartaVeneno("Toque Tóxico", "Aplica 3 de veneno por 2 turnos.", 1, 3, 2); break;
                case 3: recompensa = new CartaChama("Fogo Corrupto", "Causa 4 de dano e aplica queimadura.", 1, 4); break;
                case 4: recompensa = new CartaCura("Gota de Sanidade", "Cura 8 de vida.", 1, 8); break;
                case 5: recompensa = new CartaEnergetica("Adrenalina", "Recupera 2 de mana.", 0, 2); break;
                case 6: recompensa = new CartaMagica("Anomalia", "Aplica um efeito aleatório no alvo.", 2); break;
                case 7: recompensa = new CartaSacrificio("Preço de Sangue", "Perde 5 de vida, causa 15 de dano.", 1, 15, 5); break;
                case 8: recompensa = new CartaAtordoar("Sussurro do Abismo", "Impede a ação do inimigo.", 2, 1); break;
            }
            heroi.adicionarCarta(recompensa);
            System.out.println("🎁 RECOMPENSA: Você adicionou a carta [" + recompensa.getNome() + "] ao seu baralho!");
        } else {
            System.out.println("\nSua mente sucumbiu à loucura... Game Over.");
        }
        return heroi.estaVivo();
    }
}