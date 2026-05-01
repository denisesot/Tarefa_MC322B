package game.echoes;
/**
 * Classe que representa a carta "Energetica" no jogo.
 * 
 * Esta carta concede energia extra ao herói que a usa.
 */

public class CartaEnergetica extends Carta {
    private int energiaExtra;

    public CartaEnergetica(String nome, String descricao, int custo, int energiaExtra) {
        super(nome, descricao, custo);
        this.energiaExtra = energiaExtra;
    }

    /**
    * Aplica o efeito de energia extra ao herói.
    * 
    * @param jogador herói que usa a carta
    * @param alvo inimigo 
    */
    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        int manaRecuperada = energiaExtra;
        String bono = "";
        if (isMelhorada()) {
            manaRecuperada = energiaExtra + (energiaExtra / 2);
            bono = " [+50% aprimorada!]";
        }
        jogador.ganharMana(manaRecuperada);
        System.out.println("⚡ " + jogador.getNome() + " sentiu um surto de adrenalina!" + bono);
        System.out.println("Recuperou +" + manaRecuperada + " de Mana! (Mana atual: " + jogador.getMana() + ")");
    }
}


