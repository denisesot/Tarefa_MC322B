package game.echoes;
import java.util.Random;

/**
 * Classe que representa a carta "Caos" no jogo.
 * 
 * Esta carta, ao ser utilizada, aplica um efeito aleatório no inimigo alvo,
 * podendo ser veneno, atordoamento ou queimadura.
 */
public class CartaMagica extends Carta {

    public CartaMagica(String nome, String descricao, int custo) {
        super(nome, descricao, custo);
    }

    /**
     * Aplica um efeito aleatório ao alvo.
     * 
     * @param jogador herói que usa a carta
     * @param alvo inimigo que receberá o efeito
     */
    @Override
    public void usar(Heroi jogador, Inimigo alvo) {

        System.out.println("✨ " + jogador.getNome() + " invocou forças caóticas do abismo...");

        Random rand = new Random();
        int sorteio = rand.nextInt(3);

        if (sorteio == 0) {
            alvo.adicionarEfeito(new EfeitoVeneno(alvo, 3, 2));
            System.out.println("🧪 O caos escolheu VENENO!");
            System.out.println(alvo.getNome() + " sofrerá dano por 2 turnos!");

        } else if (sorteio == 1) {
            alvo.adicionarEfeito(new EfeitoAtordoar(alvo, 1));
            System.out.println("💫 O caos escolheu ATORDOAMENTO!");
            System.out.println(alvo.getNome() + " perderá o próximo turno!");

        } else {
            alvo.adicionarEfeito(new EfeitoQueimadura(alvo));
            System.out.println("🔥 O caos escolheu QUEIMADURA!");
            System.out.println(alvo.getNome() + " está em chamas!");
        }
    }
}


