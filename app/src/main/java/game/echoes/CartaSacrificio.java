package game.echoes;
/**
 * Classe que representa a carta "Sacrificio" no jogo.
 * 
 * Esta carta, ao ser utilizada, causa dano ao inimigo alvo, mas também faz com que o herói perca vida.
 */
public class CartaSacrificio extends Carta {
    private int dano;
    private int perdeVida;

    public CartaSacrificio(String nome, String descricao, int custo, int dano, int perdeVida) {
        super(nome, descricao, custo);
        this.dano = dano;
        this.perdeVida = perdeVida;
    }
    
    /**
     * Aplica o efeito de sacrifício, causando dano ao inimigo e fazendo o herói perder vida.
     * 
     * @param jogador herói que usa a carta
     * @param alvo inimigo que receberá o dano
     */
    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        jogador.receberDano(perdeVida);
        alvo.receberDano(dano);
        
        System.out.println("🩸 " + jogador.getNome() + " pagou com o próprio sangue (" + perdeVida + " de vida)!");
        System.out.println("O ataque brutal causou " + dano + " de dano em " + alvo.getNome() + "!");
    }
}