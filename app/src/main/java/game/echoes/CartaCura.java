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
        int curaAplicada = cura;
        String bono = "";
        if (isMelhorada()) {
            curaAplicada = cura + (cura / 2);
            bono = " [+50% aprimorada!]";
        }
        jogador.curar(curaAplicada); 
        System.out.println("💚 " + jogador.getNome() + " usou " + getNome() + " e recuperou " + curaAplicada + " de vida!" + bono);
        System.out.println("Vida atual: " + jogador.getVida());
    }
}

