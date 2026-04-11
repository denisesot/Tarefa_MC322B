package game.echoes;
/**
 * Classe que representa a carta de cura no jogo.
 * 
 * Ao ser utilizada, esta carta recupera pontos de vida do herói que a usa.
 */
public class CartaCura extends Carta {
    private int cura;

    public CartaCura(String nome, String descricao, int custo, int cura) {
        super(nome, descricao, custo);
        this.cura = cura;
    }

    /**
     * Aplica cura ao herói.
     * 
     * @param jogador herói que usa a carta
     * @param alvo inimigo 
     */
    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        jogador.curar(cura); 
        System.out.println("💚 " + jogador.getNome() + " usou " + getNome() + " e recuperou " + cura + " de vida!");
        System.out.println("Vida atual: " + jogador.getVida());
    }
}