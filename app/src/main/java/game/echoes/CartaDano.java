package game.echoes;
/**
 * Classe que representa uma carta de dano no jogo.
 * 
 * Esta carta, ao ser utilizada, causa uma quantidade específica de dano ao inimigo alvo.
 */
public class CartaDano extends Carta {
    private int dano;

    public CartaDano(String nome, String descricao,int custo, int dano) {
        super(nome, descricao, custo);
        this.dano = dano;
    }

    /**
     * Aplica dano ao alvo.
     * 
     * @param jogador herói que usa a carta
     * @param alvo inimigo que receberá o dano
     */
    @Override
    public void usar(Heroi jogador, Inimigo alvo) {
        alvo.receberDano(dano);
        System.out.println(jogador.getNome() + " usou " + getNome() + " e causou " + dano + " de dano em " + alvo.getNome());
    }
}



